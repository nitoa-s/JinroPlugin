package com.github.nitoa_s.JinroPlugin;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.github.nitoa_s.JinroPlugin.role.RoleCamp;

public class JinroGame {
	private ArrayList<JinroJoinPlayer> joinPlayers = new ArrayList<JinroJoinPlayer>(1);

	public JinroGame() {

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
		// 人狼ゲーム開始処理
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
}
