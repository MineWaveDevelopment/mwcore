package de.minewave.mwcore.actions.internal;

import org.bukkit.entity.Player;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.actions.GroupCreateAction;
import de.minewave.mwcore.actions.GroupDeleteAction;
import de.minewave.mwcore.actions.GroupListAction;
import de.minewave.mwcore.actions.UserInfoAction;
import de.minewave.mwcore.group.Group;
import de.minewave.mwcore.user.User;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class AsyncActions {

	public static void userInfoAction(Player player) {
		MwCorePlugin.getInstance().getAsyncActionQueue().process(new UserInfoAction(player));
	}
	
	public static void listGroupsAction(User user) {
		MwCorePlugin.getInstance().getAsyncActionQueue().process(new GroupListAction(user));
	}
	
	public static void createGroupAction(User user, Group group) {
		MwCorePlugin.getInstance().getAsyncActionQueue().process(new GroupCreateAction(user, group));
	}
	
	public static void removeGroupActon(User user, String name) {
		MwCorePlugin.getInstance().getAsyncActionQueue().process(new GroupDeleteAction(user, name));
	}
	
}
