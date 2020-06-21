package de.minewave.mwcore.actions.internal;

import org.bukkit.Chunk;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.group.Group;
import de.minewave.mwcore.group.actions.GroupCreateAction;
import de.minewave.mwcore.group.actions.GroupDeleteAction;
import de.minewave.mwcore.group.actions.GroupListAction;
import de.minewave.mwcore.region.ChunkPoint;
import de.minewave.mwcore.region.actions.RegionClaimAction;
import de.minewave.mwcore.region.actions.RegionInfoAction;
import de.minewave.mwcore.region.actions.RegionListAction;
import de.minewave.mwcore.region.actions.RegionMarkAction;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.user.actions.UserInfoAction;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class AsyncActions {

	public static void userInfoAction(Player player) {
		MWAccess.getAsyncActionQueue().process(new UserInfoAction(player));
	}
	
	public static void listGroupsAction(User user) {
		MWAccess.getAsyncActionQueue().process(new GroupListAction(user));
	}
	
	public static void createGroupAction(User user, Group group) {
		MWAccess.getAsyncActionQueue().process(new GroupCreateAction(user, group));
	}
	
	public static void removeGroupActon(User user, String name) {
		MWAccess.getAsyncActionQueue().process(new GroupDeleteAction(user, name));
	}
	
	public static void regionInfoAction(User user, ChunkPoint chunkPoint) {
		MWAccess.getAsyncActionQueue().process(new RegionInfoAction(user, chunkPoint));
	}
	
	public static void regionBuyAction(User user, ChunkPoint chunkPoint) {
		MWAccess.getAsyncActionQueue().process(new RegionClaimAction(user, chunkPoint));
	}
	
	public static void regionListAction(User user) {
		MWAccess.getAsyncActionQueue().process(new RegionListAction(user));
	}
	
	public static void regionMarkAction(User user, Chunk chunk, Particle particle) {
		MWAccess.getAsyncActionQueue().process(new RegionMarkAction(user, chunk, particle));
	}
	
}
