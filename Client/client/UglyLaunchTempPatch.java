/*
This work is licensed under the Creative Commons
Attribution-NonCommercial 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/3.0/.
 */

package client;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.*;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;

import common.main;

/**
 * code for lunching external jar file
 * 
 * @author IsaacWheeler
 * 
 */
public class UglyLaunchTempPatch {

	/**
	 * code for lunching external jar file
	 * 
	 * @param jarFile
	 *            the jar file that is being lunched
	 * @param Server
	 *            is this a server lunch or not
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InterruptedException
	 */
	public static void jar(File jarFile) throws IOException,
			ClassNotFoundException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException,
			InterruptedException {

		main.print("\"" + jarFile.getAbsolutePath() + "\"");
		// sets jar to deleate on exit of program
		jarFile.deleteOnExit();

		
		
		 //runs the client version of the forge installer.

		
		Map map = new HashMap();
		map.put("file", jarFile);
		CommandLine cmdLine = new CommandLine("java");
		cmdLine.addArgument("-jar");
		cmdLine.addArgument("${file}");
		cmdLine.setSubstitutionMap(map);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(0);
		ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
		executor.setWatchdog(watchdog);
		int exitValue = executor.execute(cmdLine);
	}
}
