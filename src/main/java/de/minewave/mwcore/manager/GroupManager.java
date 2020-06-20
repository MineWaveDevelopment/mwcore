package de.minewave.mwcore.manager;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.group.Group;
import de.minewave.mwcore.manager.internal.IPersistableManager;
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
public class GroupManager implements IPersistableManager {

	@Getter
	private List<Group> groups;
	
	public GroupManager() {
		groups = Lists.newArrayList();
		if(!load()) ConsoleHelper.consoleError("Could not load groups from file!");
		
		Bukkit.getScheduler().runTaskTimer(MwCorePlugin.getInstance(), task -> {
			boolean saved = save();
			if(!saved) ConsoleHelper.console("§cGroups could not be saved in scheduled task!");
			//ConsoleHelper.console("§aGroups have been saved.");
		}, 20 * 1, 20 * 30);
	}
	
	public Group getGroup(String name) {
		return groups.stream().filter(group -> group.getName().equalsIgnoreCase(name)).findAny().get();
	}
	
	public boolean hasGroup(String name) {
		return groups.stream().filter(group -> group.getName().equalsIgnoreCase(name)).findAny().isPresent();
	}
	
	public boolean addGroup(Group group) {
		return groups.add(group) && save();
	}
	
	public boolean removeGroup(String name) {
		return groups.remove(getGroup(name));
	}
	
	@Override
	public boolean save() { // Create action?
		String json = JsonHelper.getGson().toJson(groups);
		try {
			Files.write(json, PersistenceHelper.getAndCreateDataFile("groups.json"), Charsets.UTF_8);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean load() { // Create action?
		try {
			Group[] savedGroups = JsonHelper.getGson().fromJson(new FileReader(PersistenceHelper.getAndCreateDataFile("groups.json")), Group[].class);
			if(savedGroups != null) groups = Lists.newArrayList(savedGroups); return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
