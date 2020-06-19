package de.minewave.mwcore.user;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.group.Group;
import de.minewave.mwcore.util.ConsoleHelper;
import lombok.Getter;
import lombok.Setter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class User {

	@Expose
	@Getter
	private String name;
	
	@Expose
	@Getter
	private UUID uuid;
	
	@Expose
	@Getter
	private long dateCreated;
	
	@Expose
	@Getter @Setter
	private long lastJoined;
	
	@Expose
	@Getter @Setter
	private String groupName;
	
	@Expose
	@Getter @Setter
	private boolean debug;
	
	public User(Player player, String groupName) {
		this.name = player.getName();
		this.uuid = player.getUniqueId();
		this.dateCreated = System.currentTimeMillis();
		this.groupName = groupName;
		this.debug = false;
	}
	
	public void message(String message) {
		ConsoleHelper.message(getPlayer(), message);
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}
	
	public boolean isOnline() {
		return getPlayer() != null && getPlayer().isOnline();
	}
	
	public static User create(Player player, String groupName) {
		return new User(player, groupName);
	}
	
	public Group getGroup() {
		return MwCorePlugin.getInstance().getGroupManager().getGroup(groupName);
	}
	
}
