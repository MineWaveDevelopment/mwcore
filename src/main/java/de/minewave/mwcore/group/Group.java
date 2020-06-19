package de.minewave.mwcore.group;

import java.util.List;

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
public class Group {

	@Expose
	@Getter
	private final String name;
	
	@Expose
	@Getter @Setter
	private String groupPrefix;
	
	@Expose
	@Getter
	private List<String> permissions;
	
	public Group(String name) {
		this.name = name;
		this.groupPrefix = "[" + name + "]";
		this.permissions = Lists.newArrayList();
	}
	
}
