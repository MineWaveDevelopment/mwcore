package de.minewave.mwcore.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.region.ChunkPoint;
import de.minewave.mwcore.region.Region;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.util.RegionHelper;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class PlayerInteractEntityListener implements Listener {

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractAtEntityEvent e) {
		Player player = e.getPlayer();
		Entity interacted = e.getRightClicked();
		
		User user = MWAccess.getUserManager().getUser(player);
		
		boolean isRegion = MWAccess.getRegionManager().isRegion(ChunkPoint.getChunkPoint(interacted.getLocation()));
		if(!isRegion) return;
		
		Region region = MWAccess.getRegionManager().getRegion(ChunkPoint.getChunkPoint(interacted.getLocation()));
		if(region == null) return;
		
		boolean canBuild = RegionHelper.canBuild(user, region);
		if(canBuild) return;
		
		e.setCancelled(true);
		user.message("§cSorry but you cannot interact here.");
		
	}
	
}
