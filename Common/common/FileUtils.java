package common;

import java.io.File;
import java.io.IOException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * File utility class for handling diffrent types of file tasks
 * @author IsaacWheeler
 * @version 10.27.14
 *
 */
public class FileUtils {
	
	/**
	 * used to create folders
	 * @param FolderLocation the location and name of the file you want in the format ".../name"
	 */
	public static void folders(String FolderLocation) {
		new File(FolderLocation).mkdir();
	}

	/**
	 * get the current directory location that the program is running in 
	 * @return String that tells the current location that the program is running in
	 */
	public static String getCleanPath() {
		String workingDir = System.getProperty("user.dir");

		return workingDir;
	}

	/**
	 * used to recursive delete a directory 
	 * @param directory the directory you want deleted
	 */
	public static void DelateDirectory(File directory) {

		// make sure directory exists
		if (!directory.exists()) {

			main.print("Directory does not exist.");
			System.exit(0);

		} else {

			try {

				delete(directory);

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

	}
	
	
	/**
	 * used to delete a file
	 * @param file file that you want deleted
	 * @throws IOException thrown if file is missing or does not exist
	 */
	public static void delete(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
				}
			}

		} else {
			// if file, then delete it
			file.delete();
		}
	}
	
	/**
	 * unzip code useing Zipfile resource 
	 * @param Zipfile the file that you want unziped
	 * @param Directry where you want it unizipped to
	 */
	public static void unzip(String Zipfile, String Directry) {
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(Zipfile);
			zipFile.extractAll(Directry);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

}
