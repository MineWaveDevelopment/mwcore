package de.minewave.mwcore.region.actions;

import org.bukkit.Chunk;
import org.bukkit.Particle;

import de.minewave.mwcore.actions.internal.ActionResult;
import de.minewave.mwcore.actions.internal.IAction;
import de.minewave.mwcore.region.RegionVisual;
import de.minewave.mwcore.user.User;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class RegionMarkAction implements IAction {

	@Getter
	private User user;
	
	@Getter
	private Chunk chunk;
	
	@Getter
	private Particle particle;
	
	public RegionMarkAction(User user, Chunk chunk, Particle particle) {
		this.user = user;
		this.chunk = chunk;
		this.particle = particle;
	}
	
	@Override
	public ActionResult run() {
		RegionVisual.showEffects(user, chunk, particle);
		return null;
	}

}
