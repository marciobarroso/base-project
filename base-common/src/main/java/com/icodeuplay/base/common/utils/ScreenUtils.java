package com.icodeuplay.base.common.utils;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class ScreenUtils {

	public static File screenshot(String path, String filename, String extension) {
		if (path == null)
			path = System.getProperty("java.io.tmpdir");
		if (filename == null)
			filename = new SimpleDateFormat("yyyyMMddHmmS").format(new Date());
		if (extension == null)
			extension = "jpg";

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dim = toolkit.getScreenSize();
		Rectangle rectangle = new Rectangle(dim);

		Robot robot = null;
		BufferedImage capturedImage = null;

		try {
			robot = new Robot();
			capturedImage = robot.createScreenCapture(rectangle);
			ImageIO.write(capturedImage, extension, new File(new StringBuilder(path).append(File.separator)
					.append(filename).append(".").append(extension).toString()));
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new File(path + File.separator + filename);
	}

	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public static Rectangle getBounds(int width, int height, boolean center, Component parent) {
		if (width == 0 && height == 0) {
			return new Rectangle(getScreenSize());
		} else if (center) {
			if (parent != null) {
				Dimension screen = parent.getSize();
				return new Rectangle(screen.width / 2 - width / 2, screen.height / 2 - height / 2, width, height);
			} else {
				Dimension screen = ScreenUtils.getScreenSize();
				return new Rectangle(screen.width / 2 - width / 2, screen.height / 2 - height / 2, width, height);
			}
		} else {
			return new Rectangle(0, 0, width, height);
		}
	}

	public static Rectangle getBounds(int width, int height, boolean center) {
		return getBounds(width, height, center, null);
	}
}
