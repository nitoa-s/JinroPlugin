package com.github.nitoa_s.JinroPlugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.nitoa_s.JinroPlugin.JinroGame;
import com.github.nitoa_s.JinroPlugin.JinroJoinPlayer;
import com.github.nitoa_s.JinroPlugin.JinroPlugin;

public class JinroChatEvent implements Listener {
	private JinroPlugin plugin;
	private JinroGame game;

	public JinroChatEvent(JinroPlugin plugin, JinroGame game) {
		this.plugin = plugin;
		this.game = game;
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		switch( game.getState() ) {
		case JinroGame.NIGHT_STATE: nightChat(event);break;
		case JinroGame.VOTE_STATE:
		case JinroGame.HANG_STATE: voteAndHangChat(event);break;
		default: plugin.getLogger().info(event.getMessage());break;
		}
	}

	private void nightChat(AsyncPlayerChatEvent event) {
		JinroJoinPlayer joinPlayer = game.getPlayer(event.getPlayer());
		String message = event.getMessage();
		event.getRecipients().clear();
		if( joinPlayer.getRole().getJinroChat() ) {
			for(JinroJoinPlayer targetPlayer: game.getAllPlayers())
				if( targetPlayer.getRole().getJinroChat() ) event.getRecipients().add(targetPlayer.getPlayer());
		} else {
			joinPlayer.getPlayer().sendMessage(message);
		}
		event.getRecipients().add(event.getPlayer());
	}

	private void voteAndHangChat(AsyncPlayerChatEvent event) {
		event.getRecipients().clear();
		event.getRecipients().add(event.getPlayer());
	}
}
