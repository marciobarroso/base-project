package com.icodeuplay.base.view.swing.containers;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.icodeuplay.base.common.utils.ImageUtils;
import com.icodeuplay.base.common.utils.MessageUtils;
import com.icodeuplay.base.common.utils.ScreenUtils;

public class ApplicationContainer extends JFrame {

	private static final long serialVersionUID = 1L;

	private static ApplicationContainer INSTANCE;

	private JMenuBar menu;

	private static final int MENU_POSITION_X = 0;
	private static final int MENU_POSITION_Y = 0;
	private static final int MENU_WIDTH = 784;
	private static final int MENU_HEIGHT = 21;

	private ApplicationContainer() {
		super(MessageUtils.getString("app.title"));

		int width = Integer.parseInt(MessageUtils.getString("app.screen.width"));
		int height = Integer.parseInt(MessageUtils.getString("app.screen.height"));

		ImageUtils.setAppIcons(this);
		this.setBounds(ScreenUtils.getBounds(width, height, true));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.getContentPane().setLayout(new BorderLayout());

		this.menu = new JMenuBar();
		this.menu.setBounds(MENU_POSITION_X, MENU_POSITION_Y, MENU_WIDTH, MENU_HEIGHT);
		this.setJMenuBar(this.menu);

		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				if (MessageUtils.showConfirmDialog(null, MessageUtils.getString("app.messages.exit.confirm"),
						MessageUtils.getString("app.messages.exit.confirm.title"),
						MessageUtils.INFORMATION_MESSAGE_TYPE)) {
					System.exit(1);
				}
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}
		});

		this.setResizable(false);
		this.setVisible(true);
	}

	public static ApplicationContainer getInstance() {
		if (INSTANCE == null)
			INSTANCE = new ApplicationContainer();
		return INSTANCE;
	}

	public void addPanel(final JPanel panel) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				getContentPane().removeAll();
				getContentPane().add(panel, BorderLayout.CENTER);
				validate();
				repaint();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}

	public void reset() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				getContentPane().removeAll();
				validate();
				repaint();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}

	public void setMaximized(boolean value) {
		if (value) {
			setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		}
	}

	public void addMenu(JMenu menu) {
		/*
		 * menu.setFont(new Font("Verdana", Font.PLAIN, 11));
		 * 
		 * JMenuItem item = null; for (int i = 0; i < menu.getItemCount(); i++)
		 * { item = menu.getItem(i); item.setFont(new Font("Verdana",
		 * Font.PLAIN, 11)); }
		 */
		this.menu.add(menu);
	}

}
