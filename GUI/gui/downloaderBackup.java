/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */
package gui;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.FlowLayout;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Window.Type;

/**
 * used for dowloading client side mods and other files
 * 
 * @author IsaacWheeler
 * 
 */
public class downloaderBackup {
	// the gui frame
	private JFrame frm;
	// the file path for the file being dowloaded
	private File file;
	// status image aray
	private static ImageIcon[] img;
	// graffic for the user to watch
	private JLabel loadingImage;
	// the site the file is being downloaded from
	private String site;

	private JLabel textLabel;

	/**
	 * sets up and starts the file download
	 * 
	 * @param _site
	 *            the web location of the file being downloaded
	 * @param _file
	 *            the file path of the file being downloaded
	 * @throws IOException
	 */
	public downloaderBackup(String _site, File _file) throws IOException {
		// making it visable to the whole class
		file = _file;
		site = _site;
		// displaying the gui
		guiBuilder();
		//sets the file name
		setFileName();
	}

	/**
	 * sets up and displays the gui
	 */
	private void guiBuilder() {
		frm = new JFrame();
		frm.setType(Type.POPUP);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		textLabel = new JLabel("Downloading: error getting file name", SwingConstants.CENTER);
		frm.getContentPane().add(textLabel);
		loadingImage = new JLabel("Downloaded: 0%", SwingConstants.CENTER);
		frm.getContentPane().add(loadingImage);
		frm.setVisible(true);
		frm.getContentPane().setLayout(new FlowLayout());
		frm.setSize(300, 80);
	}
	
	/**
	 * sets the file name for the file label
	 */
	private void setFileName(){
		if (file.getName().length() > 20) {
			textLabel.setText("Downloading: "
					+ file.getName().subSequence(0, 20) + "...");
		} else {
			textLabel.setText("Downloading: " + file.getName());
		}
	}

	/**
	 * dowloads the file
	 * 
	 * @throws IOException
	 *             handled else where
	 */
	public void downloader() throws IOException {
		URL url = new URL(site);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		int filesize = connection.getContentLength();
		int totalDataRead = 0;
		try (java.io.BufferedInputStream in = new java.io.BufferedInputStream(
				connection.getInputStream())) {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(
					file.toString());
			try (java.io.BufferedOutputStream bout = new BufferedOutputStream(
					fos, 1024)) {
				byte[] data = new byte[1024];
				int i;
				int j = 1;
				while ((i = in.read(data, 0, 1024)) >= 0) {
					totalDataRead = totalDataRead + i;
					bout.write(data, 0, i);


					int percent = Math.abs((totalDataRead * 100) / filesize);
					loadingImage.setText("Downloaded: " + percent + "%");

				}
				frm.dispose();
			}
		}

	}

	/**
	 * leads the images for the loading gif to an aray
	 */
	private static void loadimages() {
		ImageIcon[] imgtmp = {
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step1.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step2.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step3.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step4.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step5.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step6.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step7.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step8.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step9.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step10.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step11.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step12.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step13.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step14.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step15.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step16.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step17.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step18.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step19.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step20.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step21.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step22.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step23.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step24.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step25.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step26.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step27.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step28.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step29.png")),
				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step30.png")),

				new ImageIcon(
						PAQInstallerV3.class
								.getResource("/gui/loadingImages/step31.png")) };
		img = imgtmp;

	}

}
