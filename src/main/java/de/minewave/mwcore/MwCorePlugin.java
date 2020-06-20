package de.minewave.mwcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.minewave.mwcore.actions.internal.AsyncActionQueue;
import de.minewave.mwcore.actions.internal.SyncActionQueue;
import de.minewave.mwcore.manager.GroupManager;
import de.minewave.mwcore.manager.RegionManager;
import de.minewave.mwcore.manager.UserManager;
import de.minewave.mwcore.util.CommandHelper;
import de.minewave.mwcore.util.ConsoleHelper;
import de.minewave.mwcore.util.ListenerHelper;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class MwCorePlugin extends JavaPlugin {

	@Getter
	private static MwCorePlugin instance;
	
	@Getter
	private AsyncActionQueue asyncActionQueue;
	
	@Getter
	private SyncActionQueue syncActionQueue;
	
	@Getter
	private UserManager userManager;
	
	@Getter
	private GroupManager groupManager;
	
	@Getter
	private RegionManager regionManager;
	
	@Override
	public void onEnable() {
		instance = this;
		
		asyncActionQueue = new AsyncActionQueue();
		Bukkit.getScheduler().runTaskTimerAsynchronously(instance, asyncActionQueue, 1, 1);
		
		syncActionQueue = new SyncActionQueue();
		Bukkit.getScheduler().runTaskTimer(instance, syncActionQueue, 1, 1);
		
		userManager = new UserManager();
		groupManager = new GroupManager();
		regionManager = new RegionManager();
		
		CommandHelper.setupCommandHandling();
		ListenerHelper.setupEventListenerHandling();

		ConsoleHelper.console("§3Plugin enabled!");
	}
	
	@Override
	public void onDisable() {
		ConsoleHelper.console("§3Plugin disabled!");
	}
	
}
