package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

import common.main;
/**
 * class to get install info from the json file on web
 * @author IsaacWheeler
 *
 */
public class GetInstallInfo {
	/**
	 * gets install info from web 
	 * @return the installInfo object
	 * @throws Exception
	 */
	public static InstallInfo JsonInfo(String version, String installLocation) throws Exception {
		Gson gson = new Gson();
		BufferedReader br;

		main.print(installLocation);
		
		br = read(installLocation);

		Versioninfo Versioninfo = gson.fromJson(br, Versioninfo.class);

		if (version != null) {

		} else {
			version = Versioninfo.versions()
					.get(Versioninfo.versions().size() - 1).toString();
		}
		BufferedReader br2 = read(Versioninfo.InstallInfoDirectory() + version
				+ ".json");

		InstallInfo obj = gson.fromJson(br2, InstallInfo.class);

		return obj;

	}

	/**
	 * reads web site data to remove the requirement to download file
	 * @param url the web site being read
	 * @return returns the bufferedReader for the web site data
	 * @throws Exception
	 */
	private static BufferedReader read(String url) throws Exception {

		return new BufferedReader(

		new InputStreamReader(

		new URL(url).openStream()));
	}
	
	/**
	 * gets the java object for mod packs
	 * @return the mod pack object
	 * @throws Exception
	 */
	public static ModPacks modpacks() throws Exception {
		Gson gson = new Gson();
		BufferedReader br;
			
		br = read("http://mage-tech.org/PAQ/ModPacks.json");
	
		ModPacks modpacks = gson.fromJson(br, ModPacks.class);

		return modpacks;

	}
	
	/**
	 * gets just the version info from a mod pack
	 * @param mod the location of the file being read
	 * @return the version info
	 * @throws Exception
	 */
	public static Versioninfo Versioninfo(String mod) throws Exception {
		Gson gson = new Gson();
		BufferedReader br;
		if (mod != null) {

			br = read(mod);
		} else {
			br = read("http://mage-tech.org/PAQ/versioninfo.json");
		}

		Versioninfo Versioninfo = gson.fromJson(br, Versioninfo.class);

		return Versioninfo;

	}
}
