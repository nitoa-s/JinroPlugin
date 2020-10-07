package com.github.nitoa_s.JinroPlugin;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;

import com.github.nitoa_s.JinroPlugin.role.JinroRole;
import com.github.nitoa_s.JinroPlugin.role.RoleCamp;

public class JinroGame {
	private JinroConfig config;
	private ArrayList<JinroJoinPlayer> joinPlayers = new ArrayList<JinroJoinPlayer>(1);
	private int nightTime;
	private int dayTime;
	private int voteTime;
	private boolean debug;
	public JinroGame(JinroConfig config) {
		this.config = config;
	}

	public void addPlayer(Player player) {
		if( existPlayer(player) ) {
			player.sendMessage("あなたは既に参加表明しています");
		} else {
			joinPlayers.add(new JinroJoinPlayer(player));
			sendJoinAllPlayer(player.getDisplayName() + "さんが人狼ゲームに参加表明しました");
		}
	}

	public void start() {
		ArrayList<JinroRole> roles = getUseRoles();
		if( !debug && roles.size() != joinPlayers.size() ) {
			sendJoinAllPlayer( "参加人数と使用役職数が合っていません(参加人数：" + joinPlayers.size() + ", 役職数：" + roles.size());
			return;
		}
		nightTime = config.getIntValue("time.nightTime");
		dayTime = config.getIntValue("time.dayTime");
		voteTime = config.getIntValue("time.voteTime");
		for( JinroJoinPlayer joinPlayer: joinPlayers ) {
			int randomIndex = new Random().nextInt(roles.size());
			joinPlayer.setRole(roles.get(randomIndex));
			roles.remove(randomIndex);
		}
	}

	private ArrayList<JinroRole> getUseRoles() {
		ArrayList<JinroRole> roles = new ArrayList<JinroRole>(1);
		for( JinroRole role: JinroRole.values() )
			for( int i = 0; i < config.getIntValue("roles." + role.getRoleKey()); i++)
				roles.add(role);
		return roles;

	}
	public void sendJoinAllPlayer(String... messages) {
		for( JinroJoinPlayer joinPlayer: joinPlayers )
			joinPlayer.getPlayer().sendMessage(messages);
	}

	public boolean existPlayer(Player target) {
		for( JinroJoinPlayer joinPlayer: joinPlayers )
			if( joinPlayer.getPlayer() == target ) return true;
		return false;
	}

	public RoleCamp victoryRoleCamp() {
		int survivalNum = 0;
		int werewolfCampNum = 0;
		for( JinroJoinPlayer joinPlayer: joinPlayers )
			if( !joinPlayer.getIsDeath() && joinPlayer.getRole().getRoleCamp() != RoleCamp.FOX_CAMP ) {
				survivalNum++;
				if( joinPlayer.getRole().getRoleCamp() == RoleCamp.WEREWOLF_CAMP ) werewolfCampNum++;
			}
		if( werewolfCampNum == 0 ) return RoleCamp.VILLAGE_CAMP;
		if( survivalNum <= werewolfCampNum ) return RoleCamp.WEREWOLF_CAMP;
		return null;
	}

	public void setDebug() {
		debug = true;
	}
}
