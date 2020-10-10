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
		player.sendMessage("あなたの役職は「" + role.getRoleName() + "」です");
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JinroJoinPlayer other = (JinroJoinPlayer) obj;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}
}
