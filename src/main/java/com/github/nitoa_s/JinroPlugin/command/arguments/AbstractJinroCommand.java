package com.github.nitoa_s.JinroPlugin.command.arguments;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.nitoa_s.JinroPlugin.JinroGame;


public abstract class AbstractJinroCommand {
	protected final JinroGame game;
	protected final String commandName;

	public AbstractJinroCommand(JinroGame game, String commandName) {
		this.game = game;
		this.commandName = commandName;
	}


	protected boolean isPlayer(CommandSender sender) {
		return sender instanceof Player ? true : false;
	}

	protected boolean isJinroPlayer(Player player) {
		return game.existPlayer(player) ? false : true;
	}

	public final String getCommandName() {
		return commandName;
	}

	public abstract boolean condition(CommandSender sender, String[] args);
	public abstract void run(CommandSender sender, String[] args);
}
