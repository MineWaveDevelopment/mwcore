package de.minewave.mwcore.items;

import java.util.List;

import org.bukkit.Material;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class CustomItem {

	@Expose
	@Getter
	private String name;
	
	@Expose
	@Getter @Setter
	private Material material;
	
	@Expose
	@Getter @Setter
	private String displayname;
	
	@Expose
	@Getter @Setter
	private List<String> lore;
	
	public CustomItem(String name, Material material) {
		this.name = name;
		this.material = material;
		this.displayname = name;
		this.lore = Lists.newArrayList();
	}
	
}
