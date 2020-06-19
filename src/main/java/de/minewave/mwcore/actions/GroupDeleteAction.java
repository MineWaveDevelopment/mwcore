package de.minewave.mwcore.actions;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.actions.internal.ActionResult;
import de.minewave.mwcore.actions.internal.IAction;
import de.minewave.mwcore.manager.GroupManager;
import de.minewave.mwcore.user.User;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class GroupDeleteAction implements IAction {

	@Getter
	private User user;
	
	@Getter
	private String name;
	
	public GroupDeleteAction(User user, String name) {
		this.user = user;
		this.name = name;
	}

	@Override
	public ActionResult run() {
		GroupManager groupManager = MwCorePlugin.getInstance().getGroupManager();
		
		boolean exists = groupManager.hasGroup(name);
		if(!exists) {
			user.message("§cA group named §7" + name + " §cdoes not exist");
			return null;
		}
		
		boolean removed = groupManager.removeGroup(name);
		if(!removed) {
			user.message("§cCould not remove group §7" + name);
			return null;
		}
		
		user.message("§aGroup §7" + name + " §ahas been removed");
		
		return null;
	}
	
}
