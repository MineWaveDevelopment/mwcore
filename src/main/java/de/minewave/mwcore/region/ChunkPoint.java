package de.minewave.mwcore.region;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class ChunkPoint {

	@Expose
	@Getter
	private int x;
	
	@Expose
	@Getter
	private int z;
	
	public ChunkPoint(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	public static ChunkPoint getChunkPoint(Player player) {
		Chunk chunk = player.getLocation().getChunk();
		return new ChunkPoint(chunk.getX(), chunk.getZ());
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + z + "]";
	}
	
	public boolean equals(ChunkPoint other) {
		return x == other.getX() && z == other.getZ();
	}
	
}
