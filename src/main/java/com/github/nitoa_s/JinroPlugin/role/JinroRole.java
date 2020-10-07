package com.github.nitoa_s.JinroPlugin.role;

import org.bukkit.entity.Player;

import com.github.nitoa_s.JinroPlugin.JinroJoinPlayer;

public enum JinroRole {
	VILLAGE("村人", RoleCamp.VILLAGE_CAMP),
	FORTUNE("占い師", RoleCamp.VILLAGE_CAMP),
	MEDIUM("霊媒師", RoleCamp.VILLAGE_CAMP),
	HUNTER("狩人", RoleCamp.VILLAGE_CAMP),
	WEREWOLF("人狼", RoleCamp.WEREWOLF_CAMP),
	MADMAN("狂人", RoleCamp.WEREWOLF_CAMP, RoleCamp.VILLAGE_CAMP),
	;
	private String roleName;
	private RoleCamp roleCamp;
	private RoleCamp fortuneCamp;

	private JinroRole(String roleName, RoleCamp roleCamp, RoleCamp fortuneCamp) {
		this.roleName = roleName;
		this.roleCamp = roleCamp;
		this.fortuneCamp = fortuneCamp;
	}

	private JinroRole(String roleName, RoleCamp roleCamp) {
		this(roleName, roleCamp, roleCamp);
	}

	public String getRoleName() {
		return roleName;
	}

	public RoleCamp getRoleCamp() {
		return roleCamp;
	}

	// 占いの結果
	public static String getFortuneResult(JinroJoinPlayer target) {
		String playerName = target.getPlayer().getDisplayName();
		if( target.getRole().fortuneCamp == RoleCamp.WEREWOLF_CAMP ) return playerName + "は人狼です";
		return playerName + "は人狼ではありません";
	}

	// 霊媒の結果
	public static String getMediumResult(JinroJoinPlayer target) {
		String playerName = target.getPlayer().getDisplayName();
		if( target.getRole().roleCamp == RoleCamp.WEREWOLF_CAMP ) return playerName + "は人狼です";
		return playerName + "は人狼ではありません";
	}
}
