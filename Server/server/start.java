/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */
package server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import json.GetInstallInfo;
import json.InstallInfo;
import common.ArgsData;
import common.FileUtils;
import common.main;
import common.StreamUtils;

/**
 * Class file for server side install code
 * 
 * @author IsaacWheeler
 * 
 */
public class start {
	//current running directory
	private static File dir;
	//the folder the mods are being installed into
	private static File mods;
	//the folder the configs are being installed into
	private static File config;
	// the install info object
	private static InstallInfo installInfo;

	/**
	 * main function for server side install
	 * 
	 * @throws Exception
	 */
	public static void svstart() throws Exception {
		main.print(" Installig Server", true);

		// getting current directory of where the file is being run

		dir = new File(FileUtils.getCleanPath());

		// printing directory for refrence

		main.print(dir.toString());

		// getting install config file

		ArgsData argsData = main.getArgsData();

		installInfo = GetInstallInfo.JsonInfo(argsData.getVersion(),
				argsData.getInstallLocation());

		createFiles();

		installConfigs();
		
		// downloading mods to mods folder

		ModsDownload.modsDownload(installInfo, mods, true);

		main.print("Installing Forge Stuff please Stand by", true);

		installForge();
		
		main.print("Server Install Done", true);
	}

	/**
	 * creating mods and config folders in current directory
	 */
	private static void createFiles() {
		mods = new File(dir.toString() + "/mods");
		config = new File(dir.toString() + "/config");

		if (mods.exists()) {
			FileUtils.DelateDirectory(mods);
		}
		mods.mkdir();

		if (config.exists()) {
			FileUtils.DelateDirectory(config);
		}
		config.mkdirs();

	}

	/**
	 * installing config files
	 */
	private static void installConfigs() throws IOException {
		// downloading tmp config zip

		File config = File.createTempFile("Config", ".zip");
		config.deleteOnExit();
		try (InputStream is = new URL(installInfo.Config()).openStream()) {
			StreamUtils.saveTo(is, config);
		}

		// unziping config file to config folder

		FileUtils.unzip(config.toString(), config.toString());
	}
	
	/**
	 * installing forge
	 * 
	 */
	private static void installForge() throws IOException{
		
		// checking for existing forge stuff and removing if it exists

				File libraies = new File(dir.toString() + "/libraries");

				if (libraies.exists()) {
					FileUtils.DelateDirectory(libraies);
				}

				File mcserver = new File(dir.toString() + "/minecraft_server.1.7.2.jar");

				if (mcserver.exists()) {
					mcserver.delete();
				}

				File paqServer = new File(dir.toString() + "/PAQ.jar");

				if (paqServer.exists()) {
					paqServer.delete();
				}

				// downloading forge server zip and extracting to current directory

				File ServerZip = File.createTempFile("ServerZip", ".zip");
				ServerZip.deleteOnExit();
				try (InputStream is = new URL(installInfo.forge().get(0).ServerZip())
						.openStream()) {
					StreamUtils.saveTo(is, ServerZip);
				}

				FileUtils.unzip(ServerZip.toString(), dir.toString());

	}

}
