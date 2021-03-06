package com.github.nitoa_s.JinroPlugin;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.nitoa_s.JinroPlugin.event.JinroChatEvent;
import com.github.nitoa_s.JinroPlugin.role.JinroRole;
import com.github.nitoa_s.JinroPlugin.role.RoleCamp;
import com.github.nitoa_s.JinroPlugin.scheduler.NightTimeTask;

public class JinroGame {
	public final static String NIGHT_STATE = "夜";
	public final static String DAY_STATE = "昼";
	public final static String VOTE_STATE = "投票";
	public final static String HANG_STATE = "処刑";
	public final static String ATTACK_STATE = "襲撃";
	private JinroPlugin plugin;
	private JinroConfig config;
	private JinroScoreBoard scoreBoard;
	private JinroVoteManager voteManager;
	private ArrayList<JinroJoinPlayer> joinPlayers = new ArrayList<JinroJoinPlayer>(1);
	private int nightTime;
	private int dayTime;
	private int voteTime;
	private int day = 1;
	private String state;
	private boolean debug;

	public JinroGame(JinroPlugin plugin, JinroConfig config) {
		this.plugin = plugin;
		this.config = config;
		scoreBoard = new JinroScoreBoard();
		voteManager = new JinroVoteManager();
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
			plugin.getLogger().info(String.valueOf(randomIndex));
			joinPlayer.setRole(roles.get(randomIndex));
			roles.remove(randomIndex);
		}
		for( JinroJoinPlayer joinPlayer: joinPlayers) scoreBoard.displayBoard(joinPlayer.getPlayer());
		plugin.getServer().getPluginManager().registerEvents(new JinroChatEvent(plugin, this), plugin);
		new NightTimeTask(plugin, this, scoreBoard, nightTime).ready();
		new NightTimeTask(plugin, this, scoreBoard, nightTime).runTaskLater(plugin, 0);
	}

	public void gameEnd() {
		if( !debug ) {
			RoleCamp victoryCamp = victoryRoleCamp();
			sendJoinAllPlayer(victoryCamp.getCampName() + "の勝利となりました。勝利プレイヤーは以下の方々です");
			String[] rolePlayerInfo = new String[joinPlayers.size()];
			for( int i = 0; i < joinPlayers.size(); i++ ) {
				JinroJoinPlayer joinPlayer = joinPlayers.get(i);
				if( joinPlayer.getRole().getRoleCamp() == victoryCamp ) sendJoinAllPlayer(joinPlayer.getPlayer().getDisplayName());
				rolePlayerInfo[i] = joinPlayer.getPlayer().getDisplayName() + " --> " + joinPlayer.getRole().getRoleName();
			}
			sendJoinAllPlayer("--------(役職内訳)--------");
			sendJoinAllPlayer(rolePlayerInfo);
		}
		plugin.getLogger().info("人狼ゲーム終了");
		for(JinroJoinPlayer joinPlayer: joinPlayers) scoreBoard.undisplayBoard(joinPlayer);
		voteManager.reset();
		joinPlayers.clear();
		day = 1;
		state = null;
		debug = false;
		plugin.getLogger().info("設定を初期化しました");
	}

	private ArrayList<JinroRole> getUseRoles() {
		ArrayList<JinroRole> roles = new ArrayList<JinroRole>(1);
		for( JinroRole role: JinroRole.values() ) {
			if( !config.containKey("roles." + role.getRoleKey()) ) continue;
			for( int i = 0; i < config.getIntValue("roles." + role.getRoleKey()); i++)
				roles.add(role);
		}
		return roles;
	}

	public void sendJoinAllPlayer(String... messages) {
		for( JinroJoinPlayer joinPlayer: joinPlayers )
			joinPlayer.getPlayer().sendMessage(messages);
	}

	public boolean existPlayer(Player target) {
		if( getPlayer(target) == null ) return false;
		return true;
	}

	public boolean existPlayer(String targetName) {
		if( getPlayer(targetName) == null ) return false;
		return true;
	}

	public JinroJoinPlayer getPlayer(Player target) {
		for( JinroJoinPlayer joinPlayer: joinPlayers )
			if( joinPlayer.getPlayer() == target ) return joinPlayer;
		return null;
	}

	public JinroJoinPlayer getPlayer(String targetName) {
		for( JinroJoinPlayer joinPlayer: joinPlayers )
			if( joinPlayer.getPlayer().getDisplayName().equals(targetName) ) return joinPlayer;
		return null;
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

	public void setDay(int day) {
		this.day = day;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setDebug() {
		debug = true;
	}


	public JinroVoteManager getVoteManager() {
		return voteManager;
	}

	public ArrayList<JinroJoinPlayer> getAllPlayers(){
		return joinPlayers;
	}

	public int getNightTime() {
		return nightTime;
	}

	public int getDayTime() {
		return dayTime;
	}

	public int getVoteTime() {
		return voteTime;
	}

	public int getDay() {
		return day;
	}

	public String getState() {
		return state;
	}

	public boolean getDebug() {
		return debug;
	}
}
