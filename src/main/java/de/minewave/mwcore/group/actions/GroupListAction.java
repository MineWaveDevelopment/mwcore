package de.minewave.mwcore.group.actions;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.actions.internal.ActionResult;
import de.minewave.mwcore.actions.internal.IAction;
import de.minewave.mwcore.manager.GroupManager;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.util.ConsoleHelper;
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
		GroupManager groupManager = MWAccess.getGroupManager();
		
		user.message("§6Available user-groups:");
		groupManager.getGroups().forEach(group -> {
			ConsoleHelper.groupInfo(user.getPlayer(), group);
		});
		
		return null;
	}

}
