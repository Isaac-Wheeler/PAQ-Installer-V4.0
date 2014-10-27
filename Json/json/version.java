/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
*/
package json;

/**
 * the version object for a version number
 * @author Isaac
 *
 */
public class version {

	//the major number
	private int major;
	//the minor number
	private int minor;
	//the build number
	private int build;

	/**
	 * overrides the toString method to provide better printing of the object for testing
	 * @return the string of the object;
	 */
	@Override
	public String toString() {
		return major + "." + minor + "."
				+ build;
	}

	/**
	 * the getter method for major
	 * @return major
	 */
	public int major() {
		return major;
	}
	
	/**
	 * the getter method for minor
	 * @return minor
	 */
	public int minor() {
		return minor;
	}
	
	/**
	 * the getter method for build
	 * @return build
	 */
	public int build() {
		return build;
	}
}
