package de.minewave.mwcore;

import de.minewave.mwcore.actions.internal.AsyncActionQueue;
import de.minewave.mwcore.actions.internal.SyncActionQueue;
import de.minewave.mwcore.manager.GroupManager;
import de.minewave.mwcore.manager.RegionManager;
import de.minewave.mwcore.manager.UserManager;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class MWAccess {

	@Getter
	private static MwCorePlugin instance = MwCorePlugin.getInstance();
	
	@Getter
	private static AsyncActionQueue asyncActionQueue = instance.getAsyncActionQueue();
	
	@Getter
	private static SyncActionQueue syncActionQueue = instance.getSyncActionQueue();
	
	@Getter
	private static UserManager userManager = instance.getUserManager();
	
	@Getter
	private static GroupManager groupManager = instance.getGroupManager();
	
	@Getter
	private static RegionManager regionManager = instance.getRegionManager();
	
}
