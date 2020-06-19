package de.minewave.mwcore.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.commands.GroupCommand;
import de.minewave.mwcore.commands.TestCommand;
import de.minewave.mwcore.commands.UserCommand;
import de.minewave.mwcore.commands.internal.ICommand;
import de.minewave.mwcore.commands.internal.MineWaveCommandExecuter;
import de.minewave.mwcore.commands.internal.SubCommand;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class CommandHelper {

	private static final MineWaveCommandExecuter commandExecuter = new MineWaveCommandExecuter();
	
	@Getter
	private static Map<String, ICommand> commands = Maps.newHashMap();
	
	public static void setupCommandHandling() {
		setupCommandExecutor();
		commands.put("test", new TestCommand());
		commands.put("user", new UserCommand());
		commands.put("group", new GroupCommand());
	}
	
	private static void setupCommandExecutor() {
		Map<String, Map<String, Object>> commands = MwCorePlugin.getInstance().getDescription().getCommands();
		commands.keySet().forEach(command -> {
			MwCorePlugin.getInstance().getCommand(command).setExecutor(commandExecuter);
		});
	}
	
	public static Method getSubCommandMethod(ICommand command, String subCommandName) {
		return Arrays.stream(command.getClass().getMethods()).filter(method -> method.getName().equalsIgnoreCase(subCommandName)).findAny().get();
	}
	
	public static SubCommand getSubCommand(ICommand command, String subCommandName) {
		List<SubCommand> subCommands = getSubCommands(command);
		if(subCommands.size() == 0) return null;
		return subCommands.stream().filter(subCommand -> subCommand.subCommandName().equalsIgnoreCase(subCommandName)).findAny().get();
	}
	
	public static boolean hasSubCommand(ICommand command, String subCommandName) {
		return getSubCommands(command).stream().filter(subCommand -> subCommand.subCommandName().equalsIgnoreCase(subCommandName)).findAny().isPresent();
	}
	
	public static List<SubCommand> getSubCommands(ICommand command) {
		List<SubCommand> subCommands = Lists.newArrayList();
		Arrays.stream(command.getClass().getMethods()).forEach(method -> {
			if(method.isAnnotationPresent(SubCommand.class)) {
				subCommands.add(method.getAnnotation(SubCommand.class));
			}
		});
		return subCommands;
	}
	
	public static ICommand get(String name) {
		return commands.get(name);
	}
	
	public static boolean exists(String commandName) {
		return commands.containsKey(commandName);
	}
	
}
