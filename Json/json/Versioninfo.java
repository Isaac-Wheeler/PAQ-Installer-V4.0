/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
*/
package json;

import java.util.List;

/**
 * Version info object
 * @author Isaac
 *
 */
public class Versioninfo {

	//the install directory for mods info
	private String InstallInfoDirectory;
	//the different versions
	private List<version> versions;

	/**
	 * getter for Versions
	 * @return the versions
	 */
	public List<version> versions() {
		return versions;
	}

	/**
	 * getter for the install info directory
	 * @return the install info directory
	 */
	public String InstallInfoDirectory() {
		return InstallInfoDirectory;
	}
}
