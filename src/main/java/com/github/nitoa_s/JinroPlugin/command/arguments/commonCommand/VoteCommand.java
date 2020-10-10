package com.github.nitoa_s.JinroPlugin.command.arguments.commonCommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroJoinPlayer;
import com.github.nitoa_s.JinroPlugin.JinroVoteManager;
import com.github.nitoa_s.JinroPlugin.command.arguments.AbstractJinroCommand;

public class VoteCommand extends AbstractJinroCommand {
	private final static String COMMAND_NAME = "vote";
	private JinroVoteManager voteManager;

	public VoteCommand(JinroGame game) {
		super(game, COMMAND_NAME);
		this.voteManager = game.getVoteManager();
	}

	@Override
	public boolean condition(CommandSender sender, String[] args) {
		if( !isPlayer(sender) ) {
			sender.sendMessage("このコマンドはプレイヤー専用コマンドです");
			return false;
		}

		Player player = (Player) sender;
		if( !isJinroPlayer(player) ) {
			sender.sendMessage("このコマンドは人狼ゲームに参加している人限定のコマンドです");
			return false;
		}

		if( !isState(JinroGame.VOTE_STATE) ) {
			player.sendMessage("このコマンドは投票時間で実行できるコマンドです");
			return false;
		}

		if( args.length != 2) {
			player.sendMessage("コマンドが正しくありません。「/jinro vote [対象プレイヤー]」です。");
			return false;
		}

		if( voteManager.isVotedFlag(game.getPlayer((Player) sender)) ) {
			player.sendMessage("あなたは既に投票済みです");
			return false;
		}

		if( !game.existPlayer(args[1]) ) {
			player.sendMessage("対象プレイヤー" + args[1] + "はゲームに参加していません");
			return false;
		}

		if( !voteManager.hasLockedPlayer(game.getPlayer(args[1])) ) {
			player.sendMessage("先ほどの投票で最多得票数のプレイヤーが投票対象です。");
			return false;
		}

		return true;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		JinroJoinPlayer isVotedPlayer = game.getPlayer(player);
		JinroJoinPlayer targetPlayer = game.getPlayer(args[1]);
		voteManager.addVote(isVotedPlayer, targetPlayer);
		player.sendMessage(args[1] + "に投票しました");
	}

}
