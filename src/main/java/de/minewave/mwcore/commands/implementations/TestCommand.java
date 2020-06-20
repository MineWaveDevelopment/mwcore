package de.minewave.mwcore.commands.implementations;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.commands.ITestCommand;
import de.minewave.mwcore.commands.internal.KeyWords;
import de.minewave.mwcore.commands.internal.SubCommand;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.util.ConsoleHelper;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class TestCommand implements ITestCommand {

	@SubCommand(subCommandName = "test", subCommandSyntax = {"material", "sound", "text"})
	@Override
	public void test(CommandSender sender, List<String> arguments) {
		ConsoleHelper.message(sender, "§2Wir testen was §b@" + sender.getName() + "§2!");
	}
	
	@SubCommand(subCommandName = "testSound", subCommandSyntax = {"sound"})
	@Override
	public void testSound(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		Sound sound = (Sound) KeyWords.getValue("sound", arguments.get(0));
		player.playSound(player.getLocation(), sound, 1, 1);
		user.message("§2Playing sound §b" + sound);
	}
	
	@SubCommand(subCommandName = "permCheck", subCommandSyntax = {"word"})
	@Override
	public void permCheck(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		String text = (String) KeyWords.getValue("text", arguments.get(0));
		boolean hasPermission = user.hasPermission(text);
		user.message("§6Permission state for §f" + text + " §6= " + (hasPermission ? "§atrue" : "§cfalse"));
	}
	
	@SubCommand(subCommandName = "text", subCommandSyntax = {"text"})
	@Override
	public void text(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		StringBuilder builder = new StringBuilder();
		arguments.forEach(argument -> {
			builder.append(argument + " ");
		});
		
		user.message("§3Test: §7" + builder.toString().trim());
	}

	@SubCommand(subCommandName = "testParticle", subCommandSyntax = {"particle", "number"})
	@Override
	public void testParticle(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		
		Particle particle = (Particle) KeyWords.getValue("particle", arguments.get(0));
		int count = (int) KeyWords.getValue("number", arguments.get(1));
		
		player.spawnParticle(particle, player.getLocation(), count);
	}
	
}
