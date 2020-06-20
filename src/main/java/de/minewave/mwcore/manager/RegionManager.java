package de.minewave.mwcore.manager;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.manager.internal.IPersistableManager;
import de.minewave.mwcore.region.ChunkPoint;
import de.minewave.mwcore.region.Region;
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
public class RegionManager implements IPersistableManager {

	@Getter
	private List<Region> regions;
	
	public RegionManager() {
		regions = Lists.newArrayList();
		if(!load()) ConsoleHelper.consoleError("Could not load regions from file!");
		
		Bukkit.getScheduler().runTaskTimer(MwCorePlugin.getInstance(), task -> {
			boolean saved = save();
			if(!saved) ConsoleHelper.console("§cRegions could not be saved in scheduled task!");
			//ConsoleHelper.console("§aRegions have been saved.");
		}, 20 * 1, 20 * 30);
	}
	
	public List<Region> getRegions(User user) {
		return regions.stream().filter(region -> region.getOwner().equals(user.getUuid())).collect(Collectors.toList());
	}
	
	public Region getRegion(ChunkPoint chunkPoint) {
		return regions.stream().filter(region -> region.getChunkPoint().equals(chunkPoint)).findAny().get();
	}
	
	public boolean isRegion(ChunkPoint chunkPoint) {
		return regions.stream().filter(region -> region.getChunkPoint().equals(chunkPoint)).findAny().isPresent();
	}
	
	public boolean addRegion(Region region) {
		return regions.add(region) && save();
	}
	
	@Override
	public boolean save() { // Create action?
		String json = JsonHelper.getGson().toJson(regions);
		try {
			Files.write(json, PersistenceHelper.getAndCreateDataFile("regions.json"), Charsets.UTF_8);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean load() { // Create action?
		try {
			Region[] savedRegions = JsonHelper.getGson().fromJson(new FileReader(PersistenceHelper.getAndCreateDataFile("regions.json")), Region[].class);
			if(savedRegions != null) regions = Lists.newArrayList(savedRegions); return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
