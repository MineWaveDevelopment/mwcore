package de.minewave.mwcore.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class VehicleCreateListener implements Listener {

	@EventHandler
	public void onVehicleCreate(VehicleCreateEvent e) {
		Vehicle vehicle = e.getVehicle();
		EntityType type = vehicle.getType();

		if(type == EntityType.MINECART_TNT) {
			e.setCancelled(true);
			return;
		}
		
	}
	
}
