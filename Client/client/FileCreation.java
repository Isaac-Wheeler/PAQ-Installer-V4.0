/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
*/
package client;

import java.io.File;
import java.io.IOException;

import argo.saj.InvalidSyntaxException;
import common.FileUtils;
/**
 * code for creating required files
 * @author IsaacWheeler
 *
 */
public class FileCreation {
	
	
	/**
	 * creates directors that are need for fully working minecraft install 
	 * @param PAQ file for the PAQ directory
	 * @param mods file for the mods directory inside PAQ directory 
	 * @param config file for the config directory inside the PAQ directory
	 * @throws IOException
	 * @throws InvalidSyntaxException
	 */
	
	public static void FileMake(File paq, File mods, File config) throws IOException, InvalidSyntaxException {
		PAQ(paq);
		Mods(mods);
		Config(config);
	}
	
	/**
	 * checks if PAQ directory exists and creates it if not
	 * @param PAQ the file for the PAQ Directory
	 */
	public static void PAQ(File paq){
		if (!paq.exists()) {
			paq.mkdir();
		}
	}
	
	/**
	 * //checks if mods directory exists and either creates it if it does not or deletes and recreates it if not
	 * @param mods the file for the mods directory 
	 */
	public static void Mods(File mods){
		if (!mods.exists()) {
			mods.mkdir();
		} else {
			FileUtils.DelateDirectory(mods);
			mods.mkdir();
		}
	}
	
	/**
	 * checks if config directory exists and either creates it if it does not or deletes and recreates it if not
	 * @param config the file for the config directory
	 */
	public static void Config(File config){
		if (!config.exists()) {
			config.mkdir();
		} else {
			FileUtils.DelateDirectory(config);
			config.mkdir();
		}
	}
	
}
