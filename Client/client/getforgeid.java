/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */
package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import common.main;
/**
 * Class File for getting forge id from the minecraft launcher profiles 
 * @author IsaacWheeler
 *
 */
public class getforgeid {

	// name of minecraft profile
	public static final String MINECRAFT_LAUNCHER_PROFILES = "launcher_profiles.json";


	/**
	 * reads json data to get forge profile and forge id
	 * @return the last used version id of forge
	 * @throws IOException
	 */
	public static String findForgeProfile() throws IOException {
		File configFile = getConfigFile();

		Gson gson = GsonUtil.make();
		JsonObject jobj;
		try (InputStream is = new FileInputStream(configFile);
				InputStreamReader isr = new InputStreamReader(is)) {
			jobj = gson.fromJson(isr, JsonElement.class).getAsJsonObject();
		}

		JsonObject profiles = jobj.getAsJsonObject("profiles");
		JsonObject forge = profiles.getAsJsonObject("Forge");
		if (forge == null)
			return null;

		return forge.getAsJsonPrimitive("lastVersionId").getAsString();
	}

	/**
	 * gets profile.json file from .minecraft directory
	 * @return the config file for the minecraft launcher 
	 * @throws IOException
	 */
	private static File getConfigFile() throws IOException {
		String minecraftInstall = GetApplicationPath.minecraftpath();
		if (!new File(minecraftInstall).exists()) {
			main.infoBox(
					"Minecraft not found. please have run minecraft at least once",
					"PAQ Installer Error");
			main.exit(1);
		}

		File configFile = new File(minecraftInstall
				+ MINECRAFT_LAUNCHER_PROFILES);
		if (!configFile.exists()) {
			main.infoBox("Minecraft " + MINECRAFT_LAUNCHER_PROFILES
					+ " not found", "PAQ Installer Error");
			main.exit(1);
		}

		return configFile;
	}

	/**
	 * gets last run minecraft version
	 * @return returns the last run version id 
	 * @throws IOException
	 */
	public static String findLastUsedMcVersion() throws IOException {
		File configFile = getConfigFile();

		Gson gson = GsonUtil.make();
		JsonObject jobj;
		try (InputStream is = new FileInputStream(configFile);
				InputStreamReader isr = new InputStreamReader(is)) {
			jobj = gson.fromJson(isr, JsonElement.class).getAsJsonObject();
		}

		String selectedprofile = jobj.getAsJsonPrimitive("selectedProfile")
				.getAsString();

		JsonObject profiles = jobj.getAsJsonObject("profiles");
		JsonObject mc = profiles.getAsJsonObject(selectedprofile);
		if (mc == null)
			return null;

		String lastVersionID = mc.getAsJsonPrimitive("lastVersionId")
				.getAsString();
		if (lastVersionID.length() > 6) {
			lastVersionID = lastVersionID.substring(0, 5); // TODO: work out
															// better way to get
															// mc version.
		}
		return lastVersionID;
	}

}
