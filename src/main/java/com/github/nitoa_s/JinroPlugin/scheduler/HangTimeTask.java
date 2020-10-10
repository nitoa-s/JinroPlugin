package com.github.nitoa_s.JinroPlugin.scheduler;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroJoinPlayer;
import com.github.nitoa_s.JinroPlugin.JinroPlugin;
import com.github.nitoa_s.JinroPlugin.JinroScoreBoard;

public class HangTimeTask extends AbstractTimeTask {

	HangTimeTask(JinroPlugin plugin, JinroGame game, JinroScoreBoard board, int timer) {
		super(plugin, game, board, timer);
	}

	@Override
	public void run() {
		JinroJoinPlayer[] mostVotedPlayers = game.getVoteManager().getMostPlayer();
		if( mostVotedPlayers.length == 1) {
			game.sendJoinAllPlayer(mostVotedPlayers[0].getPlayer().getDisplayName() + "さんです。");
			mostVotedPlayers[0].isDied();
			game.sendJoinAllPlayer("--------(投票内訳)--------");
			game.sendJoinAllPlayer(game.getVoteManager().voteText());
			if( isContinue() ) {
				game.sendJoinAllPlayer("ゲーム終了です");
				game.gameEnd();
			} else {
				game.getVoteManager().reset();
				new NightTimeTask(plugin, game, board, game.getNightTime()).ready();
				new NightTimeTask(plugin, game, board, game.getNightTime()).runTaskLaterAsynchronously(plugin, 0);
			}
		} else {
			if( mostVotedPlayers.length == 0) {
				game.sendJoinAllPlayer("誰も投票していないため、再投票です");
			} else {
				game.sendJoinAllPlayer("最多得票が複数名いるため、再投票になります。次の最多得票人物の中から選んでください。");
				for(JinroJoinPlayer player: mostVotedPlayers)
					game.sendJoinAllPlayer(player.getPlayer().getDisplayName());
			}
			game.setState(JinroGame.VOTE_STATE);
			new VoteTimeTask(plugin, game, board, game.getVoteTime()).runTaskLaterAsynchronously(plugin, 0);
			game.getVoteManager().reset();
		}

	}

	@Override
	public void ready() {
		game.setState(JinroGame.HANG_STATE);
		game.sendJoinAllPlayer("投票タイム終了です。最多得票数を得たプレイヤーは・・・");
	}

}
