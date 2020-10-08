package com.github.nitoa_s.JinroPlugin.scheduler;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroPlugin;

public class DayTimeTask extends AbstractTimeTask {

	DayTimeTask(JinroPlugin plugin, JinroGame game, int timer) {
		super(plugin, game, timer);
	}

	@Override
	public void run() {
		timer--;
		if( timer >= 0 ) {
			new DayTimeTask(plugin, game, timer).runTaskLater(plugin, 20);
		} else {
			new VoteTimeTask(plugin, game, game.getVoteTime()).ready();
			new VoteTimeTask(plugin, game, game.getVoteTime()).runTaskLater(plugin, 0);
		}
	}

	@Override
	public void ready() {
		String message = "--------" + game.getDay() + "日目の朝になりました--------";
		game.sendJoinAllPlayer(message);
	}

}
