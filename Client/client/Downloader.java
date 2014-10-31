package client;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JProgressBar;

import common.main;

/**
 * @author Isaac Wheeler
 *
 */
public class Downloader implements Runnable {

	public static String site;
	public static File file;
	public static boolean status;
	private static JFrame frm;
	private static JProgressBar current;
	private static Download download;
	//set up for downloader
	public static void main(String _site, File _file)
			throws InterruptedException, MalformedURLException {
		site = _site;
		file = _file;
		download = new Download(new URL(site), file);
		guiBuilder();
		Thread t = new Thread(new Downloader());
		t.start();
		t.join();
		
		
		frm.dispose();
		
	}
	
	//Creates GUI for downloading and actviates background worker to download file
	@Override
	public void run() {
		
		Observer o;
		download.addObserver(o);
		o
	}
	
	public static void guiBuilder(){
		frm = new JFrame();
		JLabel textLabel;
		current = new JProgressBar(0,download.getSize());
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