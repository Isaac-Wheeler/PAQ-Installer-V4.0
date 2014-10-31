/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */

package client;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import common.main;

/**
 * Class File for the installation of forge
 * 
 * @author IsaacWheeler
 * 
 */
public class Forgeinstall {
	/**
	 * code for installing client side forge
	 * 
	 * @param forgeUrl
	 *            url of forge for download
	 * @throws Exception
	 */
	public static void forge(String forgeUrl) throws Exception {
		main.print("Downloading Forge");

		File tmp = File.createTempFile("forge", ".jar");
		tmp.deleteOnExit();

		
		//TODO:Have File Actually Download
		//downloaderBackup download = new downloaderBackup(forgeUrl, tmp);
		//download.downloader();

		main.print("Starting Forge installer");
		UglyLaunchTempPatch.jar(tmp);
	}
}
