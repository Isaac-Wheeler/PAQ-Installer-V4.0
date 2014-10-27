/**
 * 
 */
package common;

/**
 * @author IsaacWheeler
 * 
 */
public class ArgsData {

	// the name of the mod pack being installed
	private String instanceName;

	// the location of the install data
	private String installLocation;

	// is server install or not
	private boolean server;

	// version being installed
	private String version;

	/**
	 * @param instanceName
	 *            the name of the mod pack being installed
	 * @param installLocation
	 *            the location of the install data
	 * @param server
	 *            is server install or not
	 * @param version
	 *            version being installed
	 */
	public ArgsData(String instanceName, String installLocation,
			boolean server, String version) {
		this.instanceName = instanceName;
		this.installLocation = installLocation;
		this.server = server;
		this.version = version;
	}

	/**
	 * @return the instanceName
	 */
	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * @return the server
	 */
	public boolean isServer() {
		return server;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param instanceName
	 *            the instanceName to set
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * @param server
	 *            the server to set
	 */
	public void setServer(boolean server) {
		this.server = server;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the installLocation
	 */
	public String getInstallLocation() {
		return installLocation;
	}

	/**
	 * @param installLocation
	 *            the installLocation to set
	 */
	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}

}
