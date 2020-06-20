package de.minewave.mwcore.region.actions;

import java.util.List;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.actions.internal.ActionResult;
import de.minewave.mwcore.actions.internal.IAction;
import de.minewave.mwcore.region.Region;
import de.minewave.mwcore.user.User;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class RegionListAction implements IAction {

	@Getter
	private User user;
	
	public RegionListAction(User user) {
		this.user = user;
	}
	
	@Override
	public ActionResult run() {
		List<Region> regions = MWAccess.getRegionManager().getRegions(user);
		
		if(regions.size() == 0) {
			user.message("§6You currently do not own any regions");
			return null;
		}
		
		user.message("§6Here a list of your regions §7(" + regions.size() + ")");
		regions.forEach(region -> {
			user.lightMessage("§7region§b" + region.getChunkPoint());
		});
		return null;
	}

}
