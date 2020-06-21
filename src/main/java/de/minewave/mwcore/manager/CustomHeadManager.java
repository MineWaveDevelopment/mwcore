package de.minewave.mwcore.manager;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.head.CustomHead;
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
public class CustomHeadManager implements IPersistableManager {

	@Getter
	private List<CustomHead> heads;
	
	public CustomHeadManager() {
		heads = Lists.newArrayList();
		if(!load()) ConsoleHelper.consoleError("Could not load heads from file!");
		
		Bukkit.getScheduler().runTaskTimer(MwCorePlugin.getInstance(), task -> {
			boolean saved = save();
			if(!saved) ConsoleHelper.console("§cHeads could not be saved in scheduled task!");
			//ConsoleHelper.console("§aHeads have been saved.");
		}, 20 * 1, 20 * 30);
	}
	
	public CustomHead getHead(String name) {
		return heads.stream().filter(head -> head.getName().equals(name)).findAny().get();
	}
	
	public boolean hasHead(String name) {
		return heads.stream().filter(head -> head.getName().equals(name)).findAny().isPresent();
	}
	
	public boolean addHead(CustomHead head) {
		return heads.add(head) && save();
	}
	
	@Override
	public boolean save() { // Create action?
		String json = JsonHelper.getGson().toJson(heads);
		try {
			Files.write(json, PersistenceHelper.getAndCreateDataFile("heads.json"), Charsets.UTF_8);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean load() { // Create action?
		try {
			CustomHead[] savedHeads = JsonHelper.getGson().fromJson(new FileReader(PersistenceHelper.getAndCreateDataFile("heads.json")), CustomHead[].class);
			if(savedHeads != null) heads = Lists.newArrayList(savedHeads); return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
