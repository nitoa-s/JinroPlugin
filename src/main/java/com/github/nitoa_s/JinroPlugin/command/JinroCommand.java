package com.github.nitoa_s.JinroPlugin.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.command.arguments.commonCommand.JoinCommand;

public class JinroCommand implements CommandExecutor, TabCompleter {

	private JoinCommand join;

	public JinroCommand(JinroGame game) {
		join = new JoinCommand(game);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if( args.length == 0 ) return false;
		String subCommand = args[0].toLowerCase();
		switch( subCommand ) {
		case "join": if( join.condition(sender, args) ) {
					 	join.run(sender, args);
					 	return true;
					 }
					 break;
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
