package de.minewave.mwcore.actions.internal;

import org.bukkit.Chunk;
import org.bukkit.Particle;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.region.actions.RegionMarkAction;
import de.minewave.mwcore.user.User;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class SyncActions {

	public static void regionMarkAction(User user, Chunk chunk, Particle particle) {
		MWAccess.getSyncActionQueue().process(new RegionMarkAction(user, chunk, particle));
	}
	
}
