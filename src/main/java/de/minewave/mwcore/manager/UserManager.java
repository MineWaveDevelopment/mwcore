package de.minewave.mwcore.manager;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.manager.internal.IPersistableManager;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.util.ConsoleHelper;
import de.minewave.mwcore.util.JsonHelper;
import de.minewave.mwcore.util.PersistenceHelper;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class UserManager implements IPersistableManager {

	@Getter
	private List<User> users;
	
	public UserManager() {
		users = Lists.newArrayList();
		if(!load()) ConsoleHelper.consoleError("Could not load users from file!");
		
		Bukkit.getScheduler().runTaskTimer(MwCorePlugin.getInstance(), task -> {
			boolean saved = save();
			if(!saved) ConsoleHelper.console("§cUsers could not be saved in scheduled task!");
			ConsoleHelper.console("§aUsers have been saved.");
		}, 20 * 1, 20 * 30);
	}
	
	public User getUser(UUID uuid) {
		return users.stream().filter(d -> d.getUuid().equals(uuid)).findAny().get();
	}
	
	public User getUser(Player player) {
		return users.stream().filter(d -> d.getUuid().equals(player.getUniqueId())).findAny().get();
	}
	
	public User getUser(String name) {
		return users.stream().filter(d -> d.getName().equals(name)).findAny().get();
	}
	
	public boolean hasUser(UUID uuid) {
		return users.stream().filter(d -> d.getUuid().equals(uuid)).findAny().isPresent();
	}
	
	public boolean addUser(User user) {
		return users.add(user) && save();
	}
	
	@Override
	public boolean save() { // Create action?
		String json = JsonHelper.getGson().toJson(users);
		try {
			Files.write(json, PersistenceHelper.getAndCreateDataFile("users.json"), Charsets.UTF_8);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean load() { // Create action?
		try {
			User[] savedUsers = JsonHelper.getGson().fromJson(new FileReader(PersistenceHelper.getAndCreateDataFile("users.json")), User[].class);
			if(savedUsers != null) users = Lists.newArrayList(savedUsers); return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
