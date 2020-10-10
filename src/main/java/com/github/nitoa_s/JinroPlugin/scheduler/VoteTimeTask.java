package com.github.nitoa_s.JinroPlugin.scheduler;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroPlugin;
import com.github.nitoa_s.JinroPlugin.JinroScoreBoard;
import com.github.nitoa_s.JinroPlugin.role.RoleCamp;

public class VoteTimeTask extends AbstractTimeTask {

	VoteTimeTask(JinroPlugin plugin, JinroGame game, JinroScoreBoard board, int timer) {
		super(plugin, game, board, timer);
	}

	@Override
	public void run() {
		timer--;
		if( timer >= 0 ) {
			board.setTimeScore(timer);
			new VoteTimeTask(plugin, game, board, timer).runTaskLater(plugin, 20);
		} else {
			new HangTimeTask(plugin, game, board, 0).ready();
			new HangTimeTask(plugin, game, board, 0).runTaskLater(plugin, 50);
		}
	}

	@Override
	public void ready() {
		game.setState(JinroGame.VOTE_STATE);
		String[] messages = {
				"--------投票タイム--------",
				"投票の時間になりました。誰か一人を投票してください（自分に投票も可）。",
				"投票方法は「/jinro vote [対象プレイヤー]」です。"
		};
		game.sendJoinAllPlayer(messages);
	}

}
