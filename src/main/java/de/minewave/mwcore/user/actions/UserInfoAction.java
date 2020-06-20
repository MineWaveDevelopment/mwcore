package de.minewave.mwcore.user.actions;

import org.bukkit.entity.Player;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.actions.internal.ActionResult;
import de.minewave.mwcore.actions.internal.IAction;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.util.ConsoleHelper;
import de.minewave.mwcore.util.DateHelper;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class UserInfoAction implements IAction {

	@Getter
	private Player player;
	
	public UserInfoAction(Player player) {
		this.player = player;
	}
	
	@Override
	public ActionResult run() {
		User user = MWAccess.getUserManager().getUser(player);
		ConsoleHelper.player(player, "§bInformation about your stored user profile");
		player.sendMessage("§2UUID: §f" + user.getUuid());
		player.sendMessage("§2First joined: §e" + DateHelper.getFormattedLocalDateTime(user.getDateCreated()));
		player.sendMessage("§2Latest joined: §e" + DateHelper.getFormattedLocalDateTime(user.getLastJoined()));
		player.sendMessage("§2Group: §d" + user.getGroupName());
		player.sendMessage("§2Debug: " + (user.isDebug() ? "§atrue" : "§cfalse"));
		return null;
	}

}
