package com.github.nitoa_s.JinroPlugin.scheduler;


import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroPlugin;

public class NightTimeTask  extends AbstractTimeTask {

	public NightTimeTask(JinroPlugin plugin, JinroGame game, int timer) {
		super(plugin, game, timer);
	}

	@Override
	public void run() {
		timer--;
		if( timer >= 0 ) {
			new NightTimeTask(plugin, game, timer).runTaskLater(plugin, 20);
		} else {
			game.setDay(game.getDay() + 1);
			new DayTimeTask(plugin, game, game.getDayTime()).ready();
			new DayTimeTask(plugin, game, game.getDayTime()).runTaskLater(plugin, 0);

		}

	}

	@Override
	public void ready() {
		String message = "--------" + game.getDay() + "日目の夜になりました-------";
		game.sendJoinAllPlayer(message);
	}

}
