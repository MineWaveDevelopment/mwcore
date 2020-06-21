package de.minewave.mwcore.region.actions;

import org.bukkit.Chunk;
import org.bukkit.Particle;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.actions.internal.ActionResult;
import de.minewave.mwcore.actions.internal.IAction;
import de.minewave.mwcore.actions.internal.SyncActions;
import de.minewave.mwcore.manager.RegionManager;
import de.minewave.mwcore.region.ChunkPoint;
import de.minewave.mwcore.region.Region;
import de.minewave.mwcore.user.User;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class RegionClaimAction implements IAction {

	@Getter
	private User user;
	
	@Getter
	private ChunkPoint chunkPoint;
	
	@Getter
	private Chunk chunk;
	
	public RegionClaimAction(User user, ChunkPoint chunkPoint) {
		this.user = user;
		this.chunkPoint = chunkPoint;
		this.chunk = user.getPlayer().getLocation().getChunk();
	}
	
	@Override
	public ActionResult run() {
		
		RegionManager regionManager = MWAccess.getRegionManager();
		
		boolean exists = regionManager.isRegion(chunkPoint);
		if(exists) {
			user.message("§6This region is already occupied §b" + chunkPoint.toString());
			return null;
		}
		
		Region region = new Region(chunkPoint, user.getUuid());
		boolean added = regionManager.addRegion(region);
		
		if(!added) {
			user.message("§6Could not buy this region, pleas try again §b" + chunkPoint.toString());
			return null;
		}
		
		user.message("§aYou have successfully bought this region §b" + chunkPoint);
		SyncActions.regionMarkAction(user, chunk, Particle.WATER_SPLASH);
		
		return null;
	}

}
