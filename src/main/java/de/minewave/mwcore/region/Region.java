package de.minewave.mwcore.region;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;

import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class Region {

	@Expose
	@Getter
	private ChunkPoint chunkPoint;
	
	@Expose
	@Getter
	private UUID owner;
	
	@Expose
	@Getter
	private List<UUID> members;
	
	public Region(ChunkPoint chunkPoint, UUID owner) {
		this.chunkPoint = chunkPoint;
		this.owner = owner;
		this.members = Lists.newArrayList();
	}
	
	public boolean equals(Region other) {
		return chunkPoint.getX() == other.getChunkPoint().getX() && chunkPoint.getZ() == other.getChunkPoint().getZ();
	}
	
}
