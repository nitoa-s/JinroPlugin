package com.github.nitoa_s.JinroPlugin.scheduler;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroPlugin;
import com.github.nitoa_s.JinroPlugin.JinroScoreBoard;

public class DayTimeTask extends AbstractTimeTask {

	DayTimeTask(JinroPlugin plugin, JinroGame game, JinroScoreBoard board, int timer) {
		super(plugin, game, board, timer);
	}

	@Override
	public void run() {
		timer--;
		if( timer >= 0 ) {
			board.setTimeScore(timer);
			new DayTimeTask(plugin, game, board, timer).runTaskLater(plugin, 20);
		} else {
			new VoteTimeTask(plugin, game, board, game.getVoteTime()).ready();
			new VoteTimeTask(plugin, game, board, game.getVoteTime()).runTaskLater(plugin, 0);
		}
	}

	@Override
	public void ready() {
		game.setState(JinroGame.DAY_STATE);
		String message = "--------" + game.getDay() + "日目の朝になりました--------";
		game.sendJoinAllPlayer(message);
	}

}
