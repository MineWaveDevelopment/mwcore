package de.minewave.mwcore.util;

import org.bukkit.Material;
import org.bukkit.block.Block;

import com.google.common.collect.Lists;

import de.minewave.mwcore.region.Region;
import de.minewave.mwcore.user.User;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class RegionHelper {

	public static boolean canBuild(User user, Region region) {
		boolean isOwner = region.getOwner().equals(user.getUuid());
		boolean isMember = region.getMembers().contains(user.getUuid());
		return isOwner || isMember;
	}
	
	public static boolean isExcluded(Block block) {
		return Lists.newArrayList(Material.CHEST).contains(block.getType());
	}
	
}
