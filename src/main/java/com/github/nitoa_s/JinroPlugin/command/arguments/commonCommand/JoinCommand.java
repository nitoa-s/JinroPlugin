package com.github.nitoa_s.JinroPlugin.command.arguments.commonCommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.command.arguments.AbstractJinroCommand;

public class JoinCommand extends AbstractJinroCommand {
	public final static String COMMAND_NAME = "join";

	public JoinCommand(JinroGame game) {
		super(game, COMMAND_NAME);
	}

	@Override
	public boolean condition(CommandSender sender, String[] args) {
		if( !isPlayer(sender) ) {
			sender.sendMessage("このコマンドはプレイヤーしか実行できません");
			return false;
		}
		return true;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		game.addPlayer((Player) sender);
	}
}
