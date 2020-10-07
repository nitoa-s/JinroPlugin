package com.github.nitoa_s.JinroPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.nitoa_s.JinroPlugin.command.JinroCommand;
import com.github.nitoa_s.JinroPlugin.item.book.RoleBook;
import com.github.nitoa_s.JinroPlugin.item.book.RuleBook;

public class JinroPlugin extends JavaPlugin implements Listener{
	JinroConfig config;
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this,this);
		config = new JinroConfig(this);
		JinroGame game = new JinroGame(config);
		getCommand("jinro").setExecutor(new JinroCommand(game));
		getLogger().info("人狼プラグインが読み込まれました");
	}

	public void onDisable() {
		config.save();
		getLogger().info("人狼プラグインを停止しました");
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		getLogger().info("誰かが参加しました");
		Player p = e.getPlayer();
		String message = "人狼ゲームサーバーへようこそ";
		p.sendMessage(message);
		ItemStack ruleBook = RuleBook.giveRuleBook();
		ItemStack roleBook = RoleBook.giveRoleBook();
		if( !p.getInventory().contains(ruleBook) ) p.getInventory().addItem(ruleBook);
		if( !p.getInventory().contains(roleBook) ) p.getInventory().addItem(roleBook);
	}
}
