package de.minewave.mwcore.commands.implementations;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.commands.IItemCommand;
import de.minewave.mwcore.commands.internal.KeyWords;
import de.minewave.mwcore.commands.internal.SubCommand;
import de.minewave.mwcore.items.CustomItem;
import de.minewave.mwcore.user.User;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class ItemCommand implements IItemCommand {

	@SubCommand(subCommandName = "create", subCommandSyntax = {"name", "material"})
	@Override
	public void create(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		String name = (String) KeyWords.getValue("name", arguments.get(0));
		Material material = (Material) KeyWords.getValue("material", arguments.get(1));
		
		CustomItem item = new CustomItem(name, material);
		
		boolean added = MWAccess.getCustomItemManager().addItem(item);
		if(!added) {
			user.message("§cCould not add the new custom item §7" + name);
			return;
		}
		
		user.message("§aCreated custom item §7" + name + "§a, material §6" + material);
		
	}

	@SubCommand(subCommandName = "list", subCommandSyntax = {})
	@Override
	public void list(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		List<CustomItem> items = MWAccess.getCustomItemManager().getItems();
		user.message("§6There are §a" + items.size() + " §6custom items");
		items.forEach(item -> {
			user.lightMessage(" §7" + item.getName() + " §7| §d" + item.getMaterial() + " §7| §e'" + ChatColor.translateAlternateColorCodes('&', item.getDisplayname()) + "§e'");
		});
	}

	@SubCommand(subCommandName = "give", subCommandSyntax = {"customItem"})
	@Override
	public void give(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		CustomItem item = (CustomItem) KeyWords.getValue("customItem", arguments.get(0));
		
		ItemStack bukkitItem = new ItemStack(item.getMaterial());
		ItemMeta meta = bukkitItem.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', item.getDisplayname()));
		meta.setLore(item.getLore());
		bukkitItem.setItemMeta(meta);
		
		user.getPlayer().getInventory().addItem(bukkitItem);
		user.message("§aAdded custom item §7" + item.getName() + " §ato your inventory");
	}

	@SubCommand(subCommandName = "setDisplayname", subCommandSyntax = {"customItem", "text"})
	@Override
	public void setDisplayname(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		CustomItem item = (CustomItem) KeyWords.getValue("customItem", arguments.get(0));
		String displayname = (String) KeyWords.getStringFromTextKeyWord(arguments, 1);
		
		item.setDisplayname(displayname);
		
		user.message("§aChanged displayname of §7" + item.getName() + " §ato §e'" + displayname + "§e'");
	}

}
