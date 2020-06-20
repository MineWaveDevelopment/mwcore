package de.minewave.mwcore.region;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import de.minewave.mwcore.user.User;

/**
 * Software by FLXnet More info at FLXnet.de Copyright (c) 2015-2020 by FLXnet
 * 
 * @author Felix
 */
public class RegionVisual {

	public static void showEffects(User user, Chunk chunk, Particle particle) {
		Player player = user.getPlayer();
		int startY = player.getLocation().getBlockY() - 10;
		
		for (int x = chunk.getX() * 16; x < chunk.getX() * 16 + 16; x++) {
			int z = chunk.getZ() * 16;
			for(int y = startY; y < startY + 20; y++) {
				player.spawnParticle(particle, new Location(chunk.getWorld(), x, y, z), 1);
			}
		}
		
		for (int x = chunk.getX() * 16; x < chunk.getX() * 16 + 16; x++) {
			int z = chunk.getZ() * 16 + 15;
			for(int y = startY; y < startY + 20; y++) {
				player.spawnParticle(particle, new Location(chunk.getWorld(), x, y, z), 1);
			}
		}

		for (int z = chunk.getZ() * 16 + 1; z < chunk.getZ() * 16 + 15; z++) {
			int x = chunk.getX() * 16;
			for(int y = startY; y < startY + 20; y++) {
				player.spawnParticle(particle, new Location(chunk.getWorld(), x, y, z), 1);
			}
		}

		for (int z = chunk.getZ() * 16 + 1; z < chunk.getZ() * 16 + 15; z++) {
			int x = chunk.getX() * 16 + 15;
			for(int y = startY; y < startY + 20; y++) {
				player.spawnParticle(particle, new Location(chunk.getWorld(), x, y, z), 1);
			}
		}
		
	}
	
	public static void markChunk(Chunk chunk, Material markItem) {
		for (int x = chunk.getX() * 16; x < chunk.getX() * 16 + 16; x++) {
			int z = chunk.getZ() * 16;
			chunk.getWorld().getBlockAt(x, searchY(chunk.getWorld(), x, z), z).setType(markItem);
		}

		for (int x = chunk.getX() * 16; x < chunk.getX() * 16 + 16; x++) {
			int z = chunk.getZ() * 16 + 15;
			chunk.getWorld().getBlockAt(x, searchY(chunk.getWorld(), x, z), z).setType(markItem);
		}

		for (int z = chunk.getZ() * 16 + 1; z < chunk.getZ() * 16 + 15; z++) {
			int x = chunk.getX() * 16;
			chunk.getWorld().getBlockAt(x, searchY(chunk.getWorld(), x, z), z).setType(markItem);
		}

		for (int z = chunk.getZ() * 16 + 1; z < chunk.getZ() * 16 + 15; z++) {
			int x = chunk.getX() * 16 + 15;
			chunk.getWorld().getBlockAt(x, searchY(chunk.getWorld(), x, z), z).setType(markItem);
		}
	}
	
	public static int searchY(World world, int x, int z) {
		int y;

		for (y = 255; y > 0; y--) {
			if (world.getBlockAt(x, y, z).getType() != Material.AIR) {
				if (world.getBlockAt(x, y, z).getType() == Material.TORCH
						|| world.getBlockAt(x, y, z).getType() == Material.REDSTONE_TORCH
						|| world.getBlockAt(x, y, z).getType() == Material.DEAD_BUSH
						|| world.getBlockAt(x, y, z).getType() == Material.SNOW) {
					y -= 1;
					break;
				}
				break;
			}
		}

		return y + 1;
	}

}
