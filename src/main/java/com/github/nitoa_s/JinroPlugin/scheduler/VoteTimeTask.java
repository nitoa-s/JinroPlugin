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
			// TODO: 同数再投票
			RoleCamp victoryCamp = game.victoryRoleCamp();
			if( game.getDebug() || victoryCamp != null ) {
				game.sendJoinAllPlayer("ゲーム終了");
			} else {
				new NightTimeTask(plugin, game, board, game.getNightTime()).ready();
				new NightTimeTask(plugin, game, board, game.getNightTime()).runTaskLater(plugin, 0);
			}
		}
	}

	@Override
	public void ready() {
		String[] messages = {
				"--------投票タイム--------",
				"投票の時間になりました。誰か一人を投票してください（自分に投票も可）。",
				"投票方法は「/jinro vote [対象プレイヤー]」です。"
		};
		game.sendJoinAllPlayer(messages);
	}

}
