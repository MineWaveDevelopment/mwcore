package de.minewave.mwcore.commands.implementations;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.actions.internal.AsyncActions;
import de.minewave.mwcore.commands.IRegionCommand;
import de.minewave.mwcore.commands.internal.SubCommand;
import de.minewave.mwcore.region.ChunkPoint;
import de.minewave.mwcore.user.User;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class RegionCommand implements IRegionCommand {

	@SubCommand(subCommandName = "info", subCommandSyntax = {})
	@Override
	public void info(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		ChunkPoint chunkPoint = ChunkPoint.getChunkPoint(player);
		AsyncActions.regionInfoAction(user, chunkPoint);	
	}

	@SubCommand(subCommandName = "buy", subCommandSyntax = {})
	@Override
	public void buy(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		ChunkPoint chunkPoint = ChunkPoint.getChunkPoint(player);
		AsyncActions.regionBuyAction(user, chunkPoint);
	}

	@SubCommand(subCommandName = "list", subCommandSyntax = {})
	@Override
	public void list(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		AsyncActions.regionListAction(user);
	}

}
