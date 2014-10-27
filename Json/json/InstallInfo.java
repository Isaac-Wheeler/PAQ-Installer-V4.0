/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
*/
package json;

import java.util.List;
/**
 * Install info object
 * @author IsaacWheeler
 *
 */
public class InstallInfo {
	//config file url
	private String Config;
	//forge object
	private List<forge> Forge;
	//version object
	private List<version> version;
	//mods object
	private List<mods> mods;

	/**
	 * override of the toString object for correct printing
	 */
	@Override
	public String toString() {
		return "DataObject  " + version() + mods + "";
	}
	
	/**
	 * getter for the Version object
	 * @return the version object
	 */
	public List<version> version() {
		return version;
	}
	
	/**
	 * getter for the mods object
	 * @return the mods object
	 */
	public List<mods> mods() {
		return mods;
	}
	
	/**
	 * getter for the Config string 
	 * @return the config string
	 */
	public String Config(){
		return Config;
	}

	/**
	 * getter for the forge object
	 * @return the forge object
	 */
	public List<forge> forge(){
		return Forge;
	}
}
