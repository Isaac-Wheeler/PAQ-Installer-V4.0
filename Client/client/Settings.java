package client;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Window.Type;

import javax.swing.JLabel;

import java.util.List;

import javax.swing.JComboBox;


import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Settings {

	public static JFrame frmSettings;

	public static json.ModPacks modpack;
	private JComboBox cbModPackVersion;
	private JComboBox cbModPacks;

	public static int ModPackId;
	public static String ModPackVersion;

	final File[] Images;

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public Settings() throws Exception {
		modpack = json.GetInstallInfo.modpacks();
		Images = getimages();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frmSettings = new JFrame();
		frmSettings.setTitle("Settings");
		frmSettings.setType(Type.UTILITY);
		frmSettings.setBounds(100, 100, 250, 100);
		frmSettings.getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] { ColumnSpec.decode("113px"),
						FormFactory.GROWING_BUTTON_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("28px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.MIN_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("28px"), }, new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("20px"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblModPack = new JLabel("Mod Pack:");
		frmSettings.getContentPane().add(lblModPack, "1, 2, left, center");

		cbModPacks = new JComboBox();
		cbModPacks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModPackId = cbModPacks.getSelectedIndex();

				json.Versioninfo versioninfo = null;
				try {
					versioninfo = json.GetInstallInfo.Versioninfo(modpack
							.ModPacks_info().get(ModPackId).Install_Info());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<json.version> versions = versioninfo.versions();
				cbModPackVersion.setModel(new DefaultComboBoxModel(versions
						.toArray()));
				cbModPackVersion
						.setSelectedIndex(versioninfo.versions().size() - 1);
				cbModPackVersion.repaint();

				// image Downloader

				ImageIcon imgThisImg = new ImageIcon(Images[ModPackId]
						.getAbsolutePath());

				System.out.println(imgThisImg);

				PAQInstallerV4.window.lblNewLabel.setIcon(imgThisImg);
				PAQInstallerV4.window.lblNewLabel.repaint();

				ModPackVersion = cbModPackVersion.getSelectedItem().toString();

			}
		});
		cbModPacks.setModel(new DefaultComboBoxModel(ModPack()));
		frmSettings.getContentPane().add(cbModPacks, "2, 2, left, top");

		JLabel lblModPackVersion = new JLabel("Mod Pack Version:");
		frmSettings.getContentPane().add(lblModPackVersion,
				"1, 4, left, center");

		cbModPackVersion = new JComboBox();
		cbModPackVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModPackVersion = cbModPackVersion.getSelectedItem().toString();

				// TODO:note location
			}
		});
		json.Versioninfo versioninfo = json.GetInstallInfo
				.Versioninfo("http://mage-tech.org/PAQ/versioninfo.json");
		List<json.version> versions = versioninfo.versions();
		cbModPackVersion.setModel(new DefaultComboBoxModel(versions.toArray()));
		frmSettings.getContentPane().add(cbModPackVersion, "2, 4, left, top");
	}

	private String[] ModPack() {
		String[] ModPack = new String[modpack.ModPacks_info().size()];

		for (int i = 0; i <= modpack.ModPacks_info().size() - 1; i++) {
			ModPack[i] = modpack.ModPacks_info().get(i).name();
		}

		return ModPack;
	}

	private File[] getimages() {
		File[] Images = new File[modpack.ModPacks_info().size()];

		for (int i = 0; i <= modpack.ModPacks_info().size() - 1; i++) {

			OutputStream out = null;
			URLConnection conn = null;
			InputStream in = null;
			File Tempimage = new File("tempimage" + i + ".jpg");

			Tempimage.deleteOnExit();

			try {
				URL url = new URL(modpack.ModPacks_info().get(i)
						.Logo_location());
				out = new BufferedOutputStream(new FileOutputStream(Tempimage));
				conn = url.openConnection();
				in = conn.getInputStream();
				byte[] buffer = new byte[1024];

				int numRead;
				long numWritten = 0;

				while ((numRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, numRead);
					numWritten += numRead;
				}

				System.out.println("\t" + numWritten);
			} catch (Exception exception) {
				exception.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (IOException ioe) {
				}
			}

			Images[i] = Tempimage;

		}

		return Images;

	}

	public JComboBox getCbModPackVersion() {
		return cbModPackVersion;
	}

	public JComboBox getCbModPacks() {
		return cbModPacks;
	}

	public JFrame getFrmSettings() {
		return frmSettings;
	}
}
