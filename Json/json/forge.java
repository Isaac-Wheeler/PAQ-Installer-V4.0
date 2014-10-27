/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
*/
package json;

/**
 * Forge info object
 * @author IsaacWheeler
 *
 */
public class forge {
	//installer web location
	private String installer;
	//forge id info
	private String id;
	//Server Zip web location
	private String ServerZip;

	/**
	 * installer info getter
	 * @return installer string
	 */
	public String installer() {
		return installer;
	}
	
	/**
	 * id info getter
	 * @return id info string
	 */
	public String id() {
		return id;
	}
	
	/**
	 * server zip info getter
	 * @return ServerZip info string
	 */
	public String ServerZip(){
		return ServerZip;
	}
}
