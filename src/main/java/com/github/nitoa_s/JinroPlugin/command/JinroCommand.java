package com.github.nitoa_s.JinroPlugin.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.command.arguments.commonCommand.JoinCommand;
import com.github.nitoa_s.JinroPlugin.command.arguments.commonCommand.StartCommand;
import com.github.nitoa_s.JinroPlugin.command.arguments.commonCommand.VoteCommand;

public class JinroCommand implements CommandExecutor, TabCompleter {

	private JoinCommand join;
	private StartCommand start;
	private VoteCommand vote;

	public JinroCommand(JinroGame game) {
		join = new JoinCommand(game);
		start = new StartCommand(game);
		vote = new VoteCommand(game);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if( args.length == 0 ) return false;
		String subCommand = args[0].toLowerCase();
		switch( subCommand ) {
		case "join":if( join.condition(sender, args) ) join.run(sender, args);
					break;
		case "start":if( start.condition(sender, args) ) start.run(sender, args);
					break;
		case "vote":if( vote.condition(sender, args) ) vote.run(sender, args);
					break;
		default:return false;
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
