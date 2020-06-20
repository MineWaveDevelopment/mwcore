package de.minewave.mwcore.group.actions;

import de.minewave.mwcore.MWAccess;
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
public class GroupCreateAction implements IAction {

	@Getter
	private User user;
	
	@Getter
	private Group group;
	
	public GroupCreateAction(User user, Group group) {
		this.user = user;
		this.group = group;
	}
	
	@Override
	public ActionResult run() {
		GroupManager groupManager = MWAccess.getGroupManager();
		
		boolean exists = groupManager.hasGroup(group.getName());
		if(exists) {
			user.message("§cA group named §7" + group.getName() + " §calready exists");
			return null;
		}
		
		boolean added = groupManager.addGroup(group);
		if(!added) {
			user.message("§cCould not create new group §7" + group.getName());
			return null;
		}
		
		user.message("§aGroup §7" + group.getName() + " §ahas been created");
		
		return null;
	}

}
