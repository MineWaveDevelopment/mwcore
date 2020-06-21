package de.minewave.mwcore.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.google.common.collect.Maps;

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
public class PlayerInteractListener implements Listener {

	private static HashMap<UUID, Long> messages = Maps.newHashMap();
	private static long spamProtectionInterval = 3 * 1000;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		Block interacted = e.getClickedBlock();
		
		if(action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR) return;
		
		User user = MWAccess.getUserManager().getUser(player);
		
		boolean isRegion = MWAccess.getRegionManager().isRegion(ChunkPoint.getChunkPoint(interacted.getLocation()));
		if(!isRegion) return;
		
		Region region = MWAccess.getRegionManager().getRegion(ChunkPoint.getChunkPoint(interacted.getLocation()));
		if(region == null) return;
		
		boolean canBuild = RegionHelper.canBuild(user, region);
		if(canBuild) return;
		
		e.setCancelled(true);
		
		if(shouldSendMessage(player.getUniqueId())) user.message("§cSorry but you cannot build here.");
	}
	
	public boolean shouldSendMessage(UUID uuid) {
		if(!messages.containsKey(uuid)) {
			messages.put(uuid, System.currentTimeMillis());
			return true;
		}
		
		if((System.currentTimeMillis() - messages.get(uuid)) >= spamProtectionInterval) {
			messages.replace(uuid, System.currentTimeMillis());
			return true;
		}
		
		return false;
	}
	
}
