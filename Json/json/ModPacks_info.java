/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
*/
package json;


/**
 * Object for modpack info
 * @author Isaac Wheeler
 *
 */
public class ModPacks_info {

	//the name of the mod pack
	private String name;
	//the location of its install info
	private String Install_Info;
	//the location of its logo
	private String Logo_Location;
	
	/**
	 * getter method for getting the name
	 * @return the name 
	 */
	public String name(){
		return name;	
	}
	
	/**
	 * getter method for getting the install info
	 * @return the install info 
	 */
	public String Install_Info(){
		return Install_Info;
	}
	
	/**
	 * Getter method for getting the logo location
	 * @return the logo location
	 */
	public String Logo_location(){
		return Logo_Location;
	}
	
}
