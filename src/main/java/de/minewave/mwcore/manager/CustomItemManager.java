package de.minewave.mwcore.manager;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.items.CustomItem;
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
public class CustomItemManager implements IPersistableManager {

	@Getter
	private List<CustomItem> items;
	
	public CustomItemManager() {
		items = Lists.newArrayList();
		if(!load()) ConsoleHelper.consoleError("Could not load items from file!");
		
		Bukkit.getScheduler().runTaskTimer(MwCorePlugin.getInstance(), task -> {
			boolean saved = save();
			if(!saved) ConsoleHelper.console("§cItems could not be saved in scheduled task!");
			//ConsoleHelper.console("§aItems have been saved.");
		}, 20 * 1, 20 * 30);
	}
	
	public CustomItem getItem(String name) {
		return items.stream().filter(item -> item.getName().equals(name)).findAny().get();
	}
	
	public boolean hasItem(String name) {
		return items.stream().filter(item -> item.getName().equals(name)).findAny().isPresent();
	}
	
	public boolean addItem(CustomItem item) {
		return items.add(item) && save();
	}
	
	@Override
	public boolean save() { // Create action?
		String json = JsonHelper.getGson().toJson(items);
		try {
			Files.write(json, PersistenceHelper.getAndCreateDataFile("items.json"), Charsets.UTF_8);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean load() { // Create action?
		try {
			CustomItem[] savedItems = JsonHelper.getGson().fromJson(new FileReader(PersistenceHelper.getAndCreateDataFile("items.json")), CustomItem[].class);
			if(savedItems != null) items = Lists.newArrayList(savedItems); return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
