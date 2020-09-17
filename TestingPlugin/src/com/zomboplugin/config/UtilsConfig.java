package com.zomboplugin.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilsConfig {

	protected static void writeFile(File file, String text) {
		try {
			if(file.createNewFile()) {
				FileWriter myWriter = new FileWriter(file);
				myWriter.write(text);
				myWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
