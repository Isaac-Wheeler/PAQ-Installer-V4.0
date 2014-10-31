/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */
package server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import common.StreamUtils;
import common.main;
import json.InstallInfo;

/**
 * Server side mods download
 * 
 * @author IsaacWheeler
 * 
 */
public class ModsDownload {

	/**
	 * main download class for downloading mods
	 * 
	 * @param obj
	 *            install info obj
	 * @param Mods17X
	 *            mods directory
	 * @param ClientOnly
	 *            is this a client only mod
	 */
	public static void modsDownload(InstallInfo obj, File Mods17X,
			Boolean ClientOnly) {

		for (int i = 0; i < (obj.mods().size()); i++) {

			if ((ClientOnly != obj.mods().get(i).ClientOnly())) {

				main.print("Downloading: " + obj.mods().get(i).name(), true);

				URL modUrl = null;
				try {
					modUrl = new URL(obj.mods().get(i).link());
				} catch (MalformedURLException e) {
					main.print(e.getMessage());
					main.exit(1);
				}

				File modfile = new File(Mods17X.toString() + "/"
						+ obj.mods().get(i).name());

				main.print(modfile.toString());

				int cont = 0;

				do {

					cont += 1;

					try {
						modfile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try (InputStream is = modUrl.openStream()) {

						StreamUtils.saveTo(is, modfile);

					} catch (IOException e) {
						main.print(e.getMessage());
						main.exit(1);
					}

					if (modfile.length() != obj.mods().get(i).FileSize()) {

						main.print("Error File Size does not match for "
								+ obj.mods().get(i).name(), true);

						modfile.delete();

					} else {

						main.print("File Size Good for "
								+ obj.mods().get(i).name() + " Moveing on", true);

					}

					if (cont == 5) {

						main.print("Max retry reached Exiting Install Please Try agian later", true);

						main.exit(1);
						

					}

				} while ((modfile.length() != obj.mods().get(i).FileSize())
						&& (cont != 5));
			}

		}
	}

}
