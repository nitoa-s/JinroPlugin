package com.github.nitoa_s.JinroPlugin;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class JinroPlugin extends JavaPlugin implements Listener{

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this,this);
		getLogger().info("人狼プラグインが読み込まれました");
	}

	public void onDisable() {
		getLogger().info("人狼プラグインを停止しました");
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		getLogger().info("誰かが参加しました");
		Player p = e.getPlayer();
		String message = "人狼ゲームサーバーへようこそ";
		p.sendMessage(message);
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		ArrayList<String> pages = new ArrayList<String>(1);
		BookMeta bookMeta = (BookMeta) book.getItemMeta();
		bookMeta.setAuthor("運営");
		bookMeta.setTitle("ルールブック");
		pages.add("人狼ゲームルールブック");
		pages.add("人狼ゲームルールブック2");
		bookMeta.setPages(pages);
		book.setItemMeta(bookMeta);
		if( !p.getInventory().contains(book) ) p.getInventory().addItem(book);
	}
}
