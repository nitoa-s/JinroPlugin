package com.github.nitoa_s.JinroPlugin.scheduler;


import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroPlugin;
import com.github.nitoa_s.JinroPlugin.JinroScoreBoard;

public class NightTimeTask  extends AbstractTimeTask {

	public NightTimeTask(JinroPlugin plugin, JinroGame game, JinroScoreBoard board, int timer) {
		super(plugin, game, board, timer);
	}

	@Override
	public void run() {
		timer--;
		if( timer >= 0 ) {
			board.setTimeScore(timer);
			new NightTimeTask(plugin, game, board, timer).runTaskLater(plugin, 20);
		} else {
			game.setDay(game.getDay() + 1);
			new DayTimeTask(plugin, game, board, game.getDayTime()).ready();
			new DayTimeTask(plugin, game, board, game.getDayTime()).runTaskLater(plugin, 0);

		}

	}

	@Override
	public void ready() {
		game.setState(JinroGame.NIGHT_STATE);
		String message = "--------" + game.getDay() + "日目の夜になりました-------";
		game.sendJoinAllPlayer(message);
	}

}
