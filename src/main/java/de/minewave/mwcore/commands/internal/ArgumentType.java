package de.minewave.mwcore.commands.internal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.Sound;

import com.google.common.collect.Lists;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.group.Group;
import de.minewave.mwcore.user.User;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class ArgumentType {
	
	public static List<String> getValues(String syntaxIdentifier) {
		List<String> values = Lists.newArrayList();
		switch (syntaxIdentifier) {
			case "sound":
				values.addAll(Arrays.stream(Sound.values()).map(Sound::name).collect(Collectors.toList()));
				values.add("§2[group] §7name of a Minecraft sound, Examples [BLOCK_NOTE_BLOCK_PLING]");
				break;
			case "material":
				values.addAll(Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()));
				values.add("§2[group] §7name of a Minecraft material, Examples [DIRT, COBBLESTONE]");
				break;
			case "user":
				values.addAll(MwCorePlugin.getInstance().getUserManager().getUsers().stream().map(User::getName).collect(Collectors.toList()));
				values.add("§2[user] §7name of a user, Examples [FelixBau]");
				break;
			case "group":
				values.addAll(MwCorePlugin.getInstance().getGroupManager().getGroups().stream().map(Group::getName).collect(Collectors.toList()));
				values.add("§2[group] §7name of a group, Examples [default, users]");
				break;
			case "name":
				values.add("§2[name]");
				break;
			case "decimal":
				values.add("§2[decimal] §7decimal number, Examples [1.0|9.3|100.53]");
				break;
			case "number":
				values.add("§2[number] §7integral number, Examples [10|999|3]");
				break;
			case "bool":
				values.add("true");
				values.add("false");
				values.add("§2[bool] §7boolean value, §atrue §7or §cfalse");
				break;
			case "coloredText":
				values.addAll(MwCorePlugin.getInstance().getGroupManager().getGroups().stream().map(Group::getGroupPrefix).collect(Collectors.toList()));
				values.add("§2[coloredText] §7color-codes with character &");
				break;
			default:
				break;
			}
		return values;
	}
	
	public static Object getValue(String syntaxIdentifier, String argument) {
		Object value = null;
		try {
			switch (syntaxIdentifier) {
				case "sound":
					value = Sound.valueOf(argument);
					break;
				case "material":
					value = Material.valueOf(argument);
					break;
				case "user":
					value = MwCorePlugin.getInstance().getUserManager().getUser(argument);
					break;
				case "group":
					value = MwCorePlugin.getInstance().getGroupManager().getGroup(argument);
					break;
				case "name":
					value = argument;
					break;
				case "decimal":
					value = Double.valueOf(argument);
					break;
				case "number":
					value = Integer.valueOf(argument);
					break;
				case "bool":
					value = Boolean.valueOf(argument);
					break;
				case "coloredText":
					value = argument;
					break;
				default:
					break;
			}
		} catch(Exception ex) {
			return null;
		}
		return value;
	}
	
}
