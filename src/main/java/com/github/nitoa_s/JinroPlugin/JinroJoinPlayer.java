package com.github.nitoa_s.JinroPlugin;

import org.bukkit.entity.Player;

import com.github.nitoa_s.JinroPlugin.role.JinroRole;

public class JinroJoinPlayer {

	private Player player;
	private JinroRole role;
	private boolean isDeath;

	public JinroJoinPlayer(Player player) {
		this.player = player;
		this.isDeath = false;
	}

	public void setRole(JinroRole role) {
		this.role = role;
	}

	public void isDied() {
		player.damage(1000);
		isDeath = true;
	}
	public Player getPlayer() {
		return player;
	}

	public JinroRole getRole() {
		return role;
	}

	public boolean getIsDeath() {
		return isDeath;
	}
}
