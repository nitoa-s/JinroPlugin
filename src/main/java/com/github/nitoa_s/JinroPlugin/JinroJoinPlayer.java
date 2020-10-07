package com.github.nitoa_s.JinroPlugin;

import org.bukkit.entity.Player;

import com.github.nitoa_s.JinroPlugin.role.JinroRole;

public class JinroJoinPlayer {

	private Player player;
	private JinroRole role;

	public JinroJoinPlayer(Player player) {
		this.player = player;
	}

	public void setRole(JinroRole role) {
		this.role = role;
	}

	public Player getPlayer() {
		return player;
	}

	public JinroRole getRole() {
		return role;
	}
}
