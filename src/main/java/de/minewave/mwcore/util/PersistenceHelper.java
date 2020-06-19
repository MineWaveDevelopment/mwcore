package de.minewave.mwcore.util;

import java.io.File;
import java.io.IOException;

import de.minewave.mwcore.MwCorePlugin;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class PersistenceHelper {

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static File getAndCreateDataFile(String fileName) {
		File pluginDataFolder = MwCorePlugin.getInstance().getDataFolder();
		if(!pluginDataFolder.exists()) pluginDataFolder.mkdirs();
		
		File dataFile = new File(pluginDataFolder, fileName);
		try {
			if(!dataFile.exists()) dataFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataFile;
	}
	
}
