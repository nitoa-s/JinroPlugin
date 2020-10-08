package com.github.nitoa_s.JinroPlugin.command.arguments.commonCommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.command.arguments.AbstractJinroCommand;

public class StartCommand extends AbstractJinroCommand {
	public final static String COMMAND_NAME = "start";
	private final static String OPTION_COMMAND = "debug";

	public StartCommand(JinroGame game) {
		super(game, COMMAND_NAME);
	}

	@Override
	public boolean condition(CommandSender sender, String[] args) {
		if( isPlayer(sender) ) {
			Player player = (Player) sender;
			if( !player.isOp() ) {
				player.sendMessage("このコマンドはOP権限しか扱えません");
				return false;
			}
		}
		return true;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		if( args.length == 2 && args[1].equals(OPTION_COMMAND) ) game.setDebug();
		game.start();
	}



}
