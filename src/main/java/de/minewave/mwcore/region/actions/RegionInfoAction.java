package de.minewave.mwcore.region.actions;

import org.bukkit.Particle;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.actions.internal.ActionResult;
import de.minewave.mwcore.actions.internal.AsyncActions;
import de.minewave.mwcore.actions.internal.IAction;
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
public class RegionInfoAction implements IAction {

	@Getter
	private User user;
	
	@Getter
	private ChunkPoint chunkPoint;
	
	public RegionInfoAction(User user, ChunkPoint chunkPoint) {
		this.user = user;
		this.chunkPoint = chunkPoint;
	}
	
	@Override
	public ActionResult run() {
		
		RegionManager regionManager = MWAccess.getRegionManager();
		
		boolean exists = regionManager.isRegion(chunkPoint);
		if(exists) {
			Region region = regionManager.getRegion(chunkPoint);
			
			User owner = MWAccess.getUserManager().getUser(region.getOwner());
			if(user.equals(owner)) {
				user.message("§2This is your region, §bToDo: show region details");
				AsyncActions.regionMarkAction(user, user.getPlayer().getLocation().getChunk(), Particle.DRIP_WATER);
				return null;
			}
			
			user.message("§6This region is already occupied by §d" + owner.getName() + " §b" + chunkPoint.toString());
			AsyncActions.regionMarkAction(user, user.getPlayer().getLocation().getChunk(), Particle.DRIPPING_HONEY);
			
			return null;
		}
		
		user.message("§2This region is free §b" + chunkPoint.toString());
		user.message("Use §b/region buy §fto be the new owner of this region");
		AsyncActions.regionMarkAction(user, user.getPlayer().getLocation().getChunk(), Particle.COMPOSTER);
		
		return null;
	}

}
