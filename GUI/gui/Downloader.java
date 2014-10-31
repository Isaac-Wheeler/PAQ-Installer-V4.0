package gui;
/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */

/***
 Created By Isaac Wheeler
 with refrence to http://stackoverflow.com/questions/21954581/using-swing-gui-to-make-a-progress-bar-show-up-while-downloading-a-file
 */

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JProgressBar;

import common.Main;

/**
 * @deprecated no longer used left for possible fixing later on
 * @author Isaac Wheeler
 *
 */
public class Downloader implements Runnable {

	public static String site;
	public static File file;
	public static boolean status;
	private static JFrame frm;
	private static JProgressBar current;
	//set up for downloader
	public static void main(String _site, File _file)
			throws InterruptedException {
		site = _site;
		file = _file;
		guiBuilder();
		Thread t = new Thread(new Downloader());
		t.start();
		t.join();
		
		
		/*while (status != true) {
			Thread.sleep(100);
		}*/
		
		Main.print("holding to not over load server");
		
		Thread.sleep(5000);
		
		frm.dispose();
		
	}
	
	//Creates GUI for downloading and actviates background worker to download file
	@Override
	public void run() {
		final Worker worker = new Worker(site, file);
		
		worker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent pcEvt) {
				if ("progress".equals(pcEvt.getPropertyName())) {
					current.setValue((Integer) pcEvt.getNewValue());
				} else if (pcEvt.getNewValue() == SwingWorker.StateValue.DONE) {
					try {
						worker.get();
					} catch (InterruptedException | ExecutionException e) {
						Main.print(e.getMessage());
						
						Main.infoBox("There Was a Error with the download please report this on the PAQ Fourms, with the log file", null);
						
						
					}
				}

			}
		});
		worker.execute();
		
	}

	public static void guiBuilder(){
		frm = new JFrame();
		JLabel textLabel;
		current = new JProgressBar(0, 100);
		current.setSize(300, 100);
		current.setValue(0);
		current.setStringPainted(true);
		if(file.getName().length() > 20){
			textLabel = new JLabel("Downloading: " + file.getName().subSequence(0, 20) + "..." ,SwingConstants.CENTER); 
		} else {
			textLabel = new JLabel("Downloading: " + file.getName() ,SwingConstants.CENTER);
		}
		frm.add(current);
		frm.add(textLabel);
		frm.setVisible(true);
		frm.setLayout(new FlowLayout());
		frm.setSize(300, 100);
		frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
//private class for background worker to download files
class Worker extends SwingWorker<Void, Void> {
	private String site;
	private File file;

	public Worker(String site, File file) {
		this.site = site;
		this.file = file;
	}

	@Override
	protected Void doInBackground() throws Exception {
		Downloader.status = false;
		URL url = new URL(site);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		int filesize = connection.getContentLength();
		int totalDataRead = 0;
		try (java.io.BufferedInputStream in = new java.io.BufferedInputStream(
				connection.getInputStream())) {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
			try (java.io.BufferedOutputStream bout = new BufferedOutputStream(
					fos, 1024)) {
				byte[] data = new byte[1024];
				int i;
				while ((i = in.read(data, 0, 1024)) >= 0) {
					totalDataRead = totalDataRead + i;
					bout.write(data, 0, i);
					int percent = Math.abs((totalDataRead * 100) / filesize);
					setProgress(percent);
				}
			}
		}
		return null;
	}

	@Override
	public void done() {
		Downloader.status = true;
		//this.notifyAll();
	}
}