package de.minewave.mwcore.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.listeners.PlayerAsyncChatListener;
import de.minewave.mwcore.listeners.PlayerJoinListener;
import de.minewave.mwcore.listeners.PlayerLeaveListener;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class ListenerHelper {

	public static void setupEventListenerHandling() {
		MwCorePlugin plugin = MwCorePlugin.getInstance();
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		pluginManager.registerEvents(new PlayerJoinListener(), plugin);
		pluginManager.registerEvents(new PlayerLeaveListener(), plugin);
		pluginManager.registerEvents(new PlayerAsyncChatListener(), plugin);
	}
	
}
