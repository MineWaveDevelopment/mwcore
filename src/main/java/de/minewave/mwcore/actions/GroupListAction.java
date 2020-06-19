package de.minewave.mwcore.actions;

import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.actions.internal.ActionResult;
import de.minewave.mwcore.actions.internal.IAction;
import de.minewave.mwcore.group.Group;
import de.minewave.mwcore.manager.GroupManager;
import de.minewave.mwcore.user.User;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class GroupListAction implements IAction {

	@Getter
	private User user;
	
	public GroupListAction(User user) {
		this.user = user;
	}
	
	@Override
	public ActionResult run() {
		GroupManager groupManager = MwCorePlugin.getInstance().getGroupManager();
		user.message(Lists.newArrayList(groupManager.getGroups().stream().map(Group::getName).collect(Collectors.toList())).toString());
		return null;
	}

}
