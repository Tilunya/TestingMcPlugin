package com.zomboplugin.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilsConfig {

	protected static void writeFile(File file, String text) {
		try {
			//I commented the condition so I can overwrite an existing file, idk yet of the impact on the other functionnalities
			//if(file.createNewFile()) {
				FileWriter myWriter = new FileWriter(file);
				myWriter.write(text);
				myWriter.close();
			//}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
