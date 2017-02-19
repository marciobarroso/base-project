package com.icodeuplay.base.view.swing.util;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.skin.AutumnSkin;
import org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin;
import org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin;
import org.pushingpixels.substance.api.skin.BusinessSkin;
import org.pushingpixels.substance.api.skin.ChallengerDeepSkin;
import org.pushingpixels.substance.api.skin.CremeCoffeeSkin;
import org.pushingpixels.substance.api.skin.DustCoffeeSkin;
import org.pushingpixels.substance.api.skin.EmeraldDuskSkin;
import org.pushingpixels.substance.api.skin.GeminiSkin;
import org.pushingpixels.substance.api.skin.GraphiteAquaSkin;
import org.pushingpixels.substance.api.skin.GraphiteGlassSkin;
import org.pushingpixels.substance.api.skin.GraphiteSkin;
import org.pushingpixels.substance.api.skin.MagellanSkin;
import org.pushingpixels.substance.api.skin.MistAquaSkin;
import org.pushingpixels.substance.api.skin.MistSilverSkin;
import org.pushingpixels.substance.api.skin.ModerateSkin;
import org.pushingpixels.substance.api.skin.NebulaBrickWallSkin;
import org.pushingpixels.substance.api.skin.NebulaSkin;
import org.pushingpixels.substance.api.skin.OfficeBlue2007Skin;
import org.pushingpixels.substance.api.skin.OfficeSilver2007Skin;
import org.pushingpixels.substance.api.skin.RavenSkin;
import org.pushingpixels.substance.api.skin.SaharaSkin;
import org.pushingpixels.substance.api.skin.TwilightSkin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.LoggerFactory;

import com.icodeuplay.base.common.utils.MessageUtils;
import com.icodeuplay.base.common.utils.Serializator;

public class LookAndFeelSelector implements Serializable {

	private static final long serialVersionUID = -4279063808452575992L;

	private static final String LOOK_AND_FEEL_SERIALIZATOR_KEY = "LOOK_AND_FEEL_SERIALIZATOR_KEY";

	private static Map<String, String> STYLES;

	public static String getAppKey() {
		return LOOK_AND_FEEL_SERIALIZATOR_KEY.concat(MessageUtils.getString("app.uid"));
	}

	private static LookAndFeelObject loadUserLookAndFeel() {
		LookAndFeelObject object = (LookAndFeelObject) Serializator.unserialize(getAppKey());

		if (object == null || object.getName() == null || "".equals(object.getName())) {
			object = new LookAndFeelObject();
			object.setName("Business");
			Serializator.serialize(object, getAppKey());
		}

		return object;
	}

	public static void run() {
		LookAndFeelObject object = loadUserLookAndFeel();

		setStyle(object.getName());
	}

	public static void selectStyle(Component frame) {
		LookAndFeelObject object = loadUserLookAndFeel();

		Object[] options = getStyles().toArray();
		Arrays.sort(options);

		String selected = (String) JOptionPane.showInputDialog(frame,
				MessageUtils.getString("app.messages.theme.selector"),
				MessageUtils.getString("app.messages.theme.selector.title"), JOptionPane.PLAIN_MESSAGE, null, options,
				object.getName());

		if (selected != null) {
			setStyle(selected);
		}
	}

	private static Map<String, String> getClassStyles() {
		if (STYLES == null) {
			STYLES = new HashMap<String, String>();
			List<Class<?>> styles = loadLookAndFeelImplementation();
			String name = null;
			for (Class<?> c : styles) {
				name = c.getSimpleName();
				name = name.replace("Skin", "");
				STYLES.put(name, c.getName());
			}
		}
		return STYLES;
	}

	public static List<String> getStyles() {
		List<String> list = new ArrayList<String>();

		for (String key : getClassStyles().keySet()) {
			list.add(key);
		}

		Collections.sort(list);

		return list;
	}

	public static void setStyle(String style) {
		try {
			SubstanceLookAndFeel.setSkin(getClassStyles().get(style));
			LookAndFeelObject object = new LookAndFeelObject();
			object.setName(style);
			Serializator.serialize(object, getAppKey());
		} catch (Exception e) {

			LoggerFactory.getLogger(LookAndFeelSelector.class).error("Erro trying to set LAF for style {} ", style, e);
		}
	}

	public static void setAutumm() {
		SubstanceLookAndFeel.setSkin(new AutumnSkin());
	}

	public static void setBusiness() {
		SubstanceLookAndFeel.setSkin(new BusinessSkin());
	}

	public static void setBusinessBlackSteel() {
		SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
	}

	public static void setBusinessBluesSteel() {
		SubstanceLookAndFeel.setSkin(new BusinessBlueSteelSkin());
	}

	public static void setChallengerDeep() {
		SubstanceLookAndFeel.setSkin(new ChallengerDeepSkin());
	}

	public static void setCremeCoffee() {
		SubstanceLookAndFeel.setSkin(new CremeCoffeeSkin());
	}

	public static void setDustCoffee() {
		SubstanceLookAndFeel.setSkin(new DustCoffeeSkin());
	}

	public static void setDustSkin() {
		SubstanceLookAndFeel.setSkin(new org.pushingpixels.substance.api.skin.DustSkin());
	}

	public static void setEmeraldDusk() {
		SubstanceLookAndFeel.setSkin(new EmeraldDuskSkin());
	}

	public static void setGenimi() {
		SubstanceLookAndFeel.setSkin(new GeminiSkin());
	}

	public static void setGraphiteAqua() {
		SubstanceLookAndFeel.setSkin(new GraphiteAquaSkin());
	}

	public static void setGraphiteGlass() {
		SubstanceLookAndFeel.setSkin(new GraphiteGlassSkin());
	}

	public static void setGraphite() {
		SubstanceLookAndFeel.setSkin(new GraphiteSkin());
	}

	public static void setMagellan() {
		SubstanceLookAndFeel.setSkin(new MagellanSkin());
	}

	public static void setMistAqua() {
		SubstanceLookAndFeel.setSkin(new MistAquaSkin());
	}

	public static void setMistSilver() {
		SubstanceLookAndFeel.setSkin(new MistSilverSkin());
	}

	public static void setModerate() {
		SubstanceLookAndFeel.setSkin(new ModerateSkin());
	}

	public static void setNebulaBrickWall() {
		SubstanceLookAndFeel.setSkin(new NebulaBrickWallSkin());
	}

	public static void setNebula() {
		SubstanceLookAndFeel.setSkin(new NebulaSkin());
	}

	public static void setOfficeBlue2007() {
		SubstanceLookAndFeel.setSkin(new OfficeBlue2007Skin());
	}

	public static void setOfficeSilver2007() {
		SubstanceLookAndFeel.setSkin(new OfficeSilver2007Skin());
	}

	public static void setRaven() {
		SubstanceLookAndFeel.setSkin(new RavenSkin());
	}

	public static void setSahara() {
		SubstanceLookAndFeel.setSkin(new SaharaSkin());
	}

	public static void setTwilight() {
		SubstanceLookAndFeel.setSkin(new TwilightSkin());
	}

	static class LookAndFeelObject implements Serializable {

		private static final long serialVersionUID = -7328487364880552330L;

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	private static List<Class<?>> loadLookAndFeelImplementation() {
		String package_prefix = "org.pushingpixels.substance.api.skin";
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.addScanners(new SubTypesScanner());
		config.addUrls(ClasspathHelper.forPackage(package_prefix));
		Reflections reflections = new Reflections(config);
		Set<Class<? extends SubstanceSkin>> classes = reflections.getSubTypesOf(SubstanceSkin.class);

		List<Class<?>> temp = new ArrayList<Class<?>>();

		for (Class<?> c : classes) {
			if (c.getName().startsWith(package_prefix)) {
				temp.add(c);
			}
		}

		return temp;
	}
}
