/**
 * 
 */
package client;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import json.GetInstallInfo;
import json.InstallInfo;
import argo.saj.InvalidSyntaxException;
import common.FileUtils;
import common.main;

/**
 * @author IsaacWheeler
 * @version 10.27.14
 */
public class install {

	InstallInfo obj;
	private File paq;
	private File mods;
	private File config;

	public install() throws Exception {
		obj = GetInstallInfo.JsonInfo(main.getArgsData().getVersion(), main
				.getArgsData().getInstallLocation());

		forgeCheck();

		File AppPath = GetApplicationPath.AppPath();
		paq = new File(AppPath.toString() + "/PAQ/"
				+ main.getArgsData().getInstanceName());
		mods = new File(paq.toString() + "/mods");
		config = new File(paq.toString() + "/config");
		FileCreation.FileMake(paq, mods, config);

	}

	private void forgeCheck() {

		try {
			String McVersion = obj.forge().get(0).id();
			McVersion = McVersion.substring(0, 5); // TODO: find better method
			if (!getforgeid.findLastUsedMcVersion().contentEquals(McVersion)) {
				main.infoBox("Please Run: " + McVersion
						+ " At least once, You Currently Have selected: "
						+ getforgeid.findLastUsedMcVersion()
						+ " Program Exiting", "Warrning");
				main.print(McVersion + " does not match "
						+ getforgeid.findLastUsedMcVersion());
				main.exit(0);
			}
			if (getforgeid.findForgeProfile() != null) {
				if (getforgeid.findForgeProfile().contains(
						obj.forge().get(0).id())) { // Possible profile find
					// issue
					main.print("Forge is already installed moving on to next step",true);
				} else {
					Forgeinstall.forge(obj.forge().get(0).installer());
				}
			} else {
				Forgeinstall.forge(obj.forge().get(0).installer());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void configInstall() throws MalformedURLException, InterruptedException {
		File configZip = null;
		try {
			configZip = File.createTempFile("Config", ".zip");
		} catch (IOException e) {
			e.printStackTrace();
		}
		configZip.deleteOnExit();
		
		Downloader.main(obj.Config(), configZip);
		
		FileUtils.unzip(configZip.toString(), config.toString());

	}

}
