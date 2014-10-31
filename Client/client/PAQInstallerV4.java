package client;

/*
 This work is licensed under the Creative Commons
 Attribution-NonCommercial 3.0 Unported License.
 To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */

/***
 Created By Isaac Wheeler
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JToggleButton;

import java.awt.Window.Type;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import javax.swing.JButton;

import common.ArgsData;
import common.main;

public class PAQInstallerV4 {

	public JFrame frame;
	public static PAQInstallerV4 window;
	private JToggleButton tglbtnInstall;
	public static String[] args;
	public JLabel lblNewLabel;

	/**
	 * Launch the Window.
	 */
	public static void display() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new PAQInstallerV4();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PAQInstallerV4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setResizable(false);
		frame.setBounds(100, 100, 803, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblNewLabel = new JLabel("Error Image File Missing or Broken");
		lblNewLabel.setIcon(new ImageIcon(PAQInstallerV4.class
				.getResource("imgs/PAQLogo.png")));
		lblNewLabel.setBounds(182, 74, 468, 177);
		frame.getContentPane().add(lblNewLabel);

		tglbtnInstall = new JToggleButton("");
		tglbtnInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();

				ArgsData argsData = main.getArgsData();

				if (Settings.modpack != null) {
					argsData.setInstallLocation(Settings.modpack
							.ModPacks_info().get(Settings.ModPackId)
							.Install_Info());
				}

				if (Settings.ModPackVersion != null) {
					argsData.setVersion(Settings.ModPackVersion);
				}

				if (Settings.frmSettings != null) {
					Settings.frmSettings.dispose();
				}
				install install;
				try {
					install = new install();
					install.configInstall();
				} catch (Exception e) {
					main.print(e.toString());
					main.exit(1);
				}

				
			}
		});
		tglbtnInstall.setSelectedIcon(new ImageIcon(PAQInstallerV4.class
				.getResource("imgs/paq_buttons_Installing.png")));
		tglbtnInstall.setIcon(new ImageIcon(PAQInstallerV4.class
				.getResource("imgs/Install1.png")));
		tglbtnInstall.setRolloverIcon(new ImageIcon(PAQInstallerV4.class
				.getResource("imgs/paq_buttons_Install_over.png")));
		tglbtnInstall.setBounds(159, 307, 504, 125);
		frame.getContentPane().add(tglbtnInstall);

		JButton btnSettings = new JButton();
		btnSettings.setIcon(new ImageIcon(PAQInstallerV4.class
				.getResource("imgs/gear.png")));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Settings window = new Settings();
							window.frmSettings.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnSettings.setBounds(698, 463, 89, 73);
		frame.getContentPane().add(btnSettings);
	}
}
