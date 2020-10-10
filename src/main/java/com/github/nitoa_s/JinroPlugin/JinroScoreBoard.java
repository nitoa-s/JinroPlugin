package com.github.nitoa_s.JinroPlugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.md_5.bungee.api.ChatColor;

public class JinroScoreBoard {
	private ScoreboardManager manager;
	private Scoreboard scoreBoard;
	private Objective board;
	private Score time;


	public JinroScoreBoard() {
		manager = Bukkit.getScoreboardManager();
		scoreBoard = manager.getNewScoreboard();
		board = scoreBoard.registerNewObjective("jinroInfo", "dummy", ChatColor.RED + "人狼ゲーム");
		board.setDisplaySlot(DisplaySlot.SIDEBAR);
		time = board.getScore(ChatColor.AQUA + "制限時間： ");
	}

	public void setTimeScore(int seconds) {
		time.setScore(seconds);
	}

	public void displayBoard(Player player) {
		player.setScoreboard(scoreBoard);
	}

	public void unDisplayBoard(Player player) {
		 player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
	}
}
