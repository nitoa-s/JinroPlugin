package com.github.nitoa_s.JinroPlugin.scheduler;

import org.bukkit.scheduler.BukkitRunnable;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroPlugin;

abstract class AbstractTimeTask extends BukkitRunnable {
	protected JinroPlugin plugin;
	protected JinroGame game;
	protected int timer;

	AbstractTimeTask(JinroPlugin plugin, JinroGame game, int timer) {
		this.plugin = plugin;
		this.game = game;
		this.timer = timer;
	}


	public abstract void ready();
}
