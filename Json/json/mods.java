/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
*/
package json;

/**
 * object repsenting a mod 
 * @author Isaac
 *
 */
public class mods {

	//the file name of the mod
	private String name;
	//the location of the mod
	private String link;
	//the id number of the mod (not used)
	private int id;
	//if its adfly or not (not used)
	private boolean adfly;
	//if the mod is a client side only mod
	private boolean ClientOnly;
	//the file size of the mod
	private long FileSize;

	/**
	 * overrides the toString method to provide better printing of the object for testing
	 * @return the string of the object;
	 */
	@Override
	public String toString() {
		return "name= " + name + " link= " + link + " id= " + id + " adfly= "
				+ adfly;
	}
	
	/**
	 * Getting method for name
	 * @return the name
	 */
	public String name(){
		return name;
	}
	/**
	 * getter method for link
	 * @return the link
	 */
	public String link() {
		return link;
	}
	
	/**
	 * getter method for id number
	 * @return the id number
	 */
	public int id() {
		return id;
	}
	
	/**
	 * getter method for adfly
	 * @return the adfly 
	 */
	public boolean adfly() {
		return adfly;
	}
	
	/**
	 * getter method for Client Only
	 * @return the ClientOnly 
	 */
	public boolean ClientOnly(){
		return ClientOnly;
	}
	
	/**
	 * getter method for the file size
	 * @return the file size
	 */
	public long FileSize() {
		return FileSize;
	}
}
