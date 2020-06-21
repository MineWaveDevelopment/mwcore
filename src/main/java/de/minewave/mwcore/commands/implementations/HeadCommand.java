package de.minewave.mwcore.commands.implementations;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.commands.IHeadCommand;
import de.minewave.mwcore.commands.internal.KeyWords;
import de.minewave.mwcore.commands.internal.SubCommand;
import de.minewave.mwcore.head.CustomHead;
import de.minewave.mwcore.user.User;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class HeadCommand implements IHeadCommand {

	@SubCommand(subCommandName = "get", subCommandSyntax = {"word"})
	@Override
	public void get(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = User.getPlayer(sender);
		User user = MWAccess.getUserManager().getUser(player);
		
		String headResource = (String) KeyWords.getValue("word", arguments.get(0));
		ItemStack head = CustomHead.getCustomHead(headResource, "§ecustom_head");
		
		player.getInventory().addItem(head);
		user.message("§aAdded custom head to your inventory");
	}

	@SubCommand(subCommandName = "create", subCommandSyntax = {"word", "word"})
	@Override
	public void create(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = User.getPlayer(sender);
		User user = MWAccess.getUserManager().getUser(player);
		
		String name = (String) KeyWords.getValue("word", arguments.get(0));
		String headResource = (String) KeyWords.getValue("word", arguments.get(1));
		
		CustomHead head = new CustomHead(name, headResource);
		boolean added = MWAccess.getCustomHeadManager().addHead(head);
		if(!added) {
			user.message("§cCustom head §7" + name + " §ccould not be created");
			return;
		}
		
		user.message("§aCustom head §7" + name + " §ahas been created");
	}

	@SubCommand(subCommandName = "list", subCommandSyntax = {""})
	@Override
	public void list(CommandSender sender, List<String> arguments) {
		
	}

	@SubCommand(subCommandName = "give", subCommandSyntax = {""})
	@Override
	public void give(CommandSender sender, List<String> arguments) {

	}

	@SubCommand(subCommandName = "setDisplayname", subCommandSyntax = {""})
	@Override
	public void setDisplayname(CommandSender sender, List<String> arguments) {

	}

}
