/**
 * 
 */
package common;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import client.PAQInstallerV4;
import server.start;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * code that is used thru out the program
 * 
 * @author IsaacWheeler
 * 
 * @version 10.27.14
 */
public class main {

	private static PrintWriter out;
	private static ArgsData argsData;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logSetUp();
		optionRead(args);

		if (ArgsData.isServer() == true) {
			try {
				start.svstart();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				// Starting Client Side GUI
				PAQInstallerV4.display();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static void optionRead(String[] args) {

		boolean server;
		String version;
		String instanceName;
		String installLocation;

		OptionParser parser = new OptionParser("m::v::si::");
		OptionSet options = parser.parse(args);
		if (options.has("m")) {
			installLocation = (String) options.valueOf("m");
			print((String) options.valueOf("m"));
		} else {
			installLocation = "http://mage-tech.org/PAQ/versioninfo.json";
			print(installLocation);
		}
		if (options.has("v")) {
			version = (String) options.valueOf("v");
			print(version);
		} else {
			version = null;
		}
		if (options.has("i")) {
			instanceName = (String) options.valueOf("i");
			print(instanceName);
		} else {
			instanceName = ("PAQ");
		}
		server = (options.has("s"));

		argsData = new ArgsData(instanceName, installLocation, server, version);

	}

	private static void logSetUp() {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		try {
			out = new PrintWriter(new FileWriter("PAQlog "
					+ dateFormat.format(date) + ".txt"), true);
		} catch (IOException e1) {
			print(e1.getMessage(), true);
			System.exit(1);
		}

	}

	public static void print(String txtToPrint) {
		out.println(txtToPrint);
		out.flush();
	}

	public static void print(String txtToPrint, boolean showInTerminal) {
		System.out.println(txtToPrint);
		out.println(txtToPrint);
		out.flush();
	}

	/**
	 * main exit class closes log file before exiting
	 * 
	 * @param status
	 *            if program is exiting with error or not
	 */
	public static void exit(int status) {
		out.close();
		System.exit(status);
	}

	/**
	 * @return the argsData
	 */
	public static ArgsData getArgsData() {
		return argsData;
	}

	/**
	 * @param argsData
	 *            the argsData to set
	 */
	public static void setArgsData(ArgsData argsData) {
		main.argsData = argsData;
	}

	/**
	 * message box pop up
	 * 
	 * @param infoMessage
	 *            message that you want shown
	 * @param location
	 *            location that it is called from (not used often)
	 */
	public static void infoBox(String infoMessage, String location) {
		JOptionPane.showMessageDialog(null, infoMessage,
				"InfoBox: " + location, JOptionPane.INFORMATION_MESSAGE);
	}
}
