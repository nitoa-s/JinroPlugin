package com.github.nitoa_s.JinroPlugin.scheduler;

import org.bukkit.scheduler.BukkitRunnable;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroPlugin;
import com.github.nitoa_s.JinroPlugin.JinroScoreBoard;

abstract class AbstractTimeTask extends BukkitRunnable {
	protected JinroPlugin plugin;
	protected JinroGame game;
	protected JinroScoreBoard board;
	protected int timer;

	AbstractTimeTask(JinroPlugin plugin, JinroGame game, JinroScoreBoard board, int timer) {
		this.plugin = plugin;
		this.game = game;
		this.board = board;
		this.timer = timer;
	}


	protected boolean isContinue() {
		return game.getDebug() || game.victoryRoleCamp() != null;
	}

	public abstract void ready();
}
