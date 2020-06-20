package de.minewave.mwcore.commands.internal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.Particle;
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
public class KeyWords {
	
	public static List<String> getValues(String syntaxIdentifier, String input) {
		List<String> values = Lists.newArrayList();
		switch (syntaxIdentifier) {
			case "sound":
				values.addAll(Arrays.stream(Sound.values())
						.filter(sound -> sound.name().startsWith(input.toUpperCase()) || sound.name().contains(input.toUpperCase()))
						.map(Sound::name)
						.limit(8)
						.collect(Collectors.toList()));
				values.add("§2[sound] §7name of a Minecraft sound, Examples [BLOCK_NOTE_BLOCK_PLING]");
				break;
			case "material":
				values.addAll(Arrays.stream(Material.values())
						.filter(material -> material.name().startsWith(input.toUpperCase()) || material.name().contains(input.toUpperCase()))
						.map(Material::name)
						.limit(8)
						.collect(Collectors.toList()));
				values.add("§2[material] §7name of a Minecraft material, Examples [DIRT, COBBLESTONE]");
				break;
			case "particle":
				values.addAll(Arrays.stream(Particle.values())
						.filter(particle -> particle.name().startsWith(input.toUpperCase()) || particle.name().contains(input.toUpperCase()))
						.map(Particle::name)
						.limit(8)
						.collect(Collectors.toList()));
				values.add("§2[particle] §7name of a Minecraft particle, Examples [WATER_SPLASH]");
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
				values.add("§2[name] §7name something like you want");
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
			case "word":
				values.add("§2[word] §7any kind of text, one word");
				break;
			case "text":
				values.add("§2[text...] §7any kind of text, multiple words possible");
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
				case "particle":
					value = Particle.valueOf(argument);
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
				case "word":
					value = argument;
				case "text":
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
	
	public static String getStringFromTextKeyWord(List<String> arguments, int skip) {
		StringBuilder builder = new StringBuilder();
		arguments.stream().skip(skip).forEach(argument -> {
			builder.append(argument + " ");
		});
		return builder.toString().trim();
	}
	
}
