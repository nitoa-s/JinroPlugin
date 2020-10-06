package com.github.nitoa_s.JinroPlugin.item.book;


import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class RuleBook implements Listener {
	private static String[] contents = {
		"人狼ゲームルールブック1",
		"人狼ゲームルールブック2"
	};
	public static ItemStack giveRuleBook() {
		ItemStack ruleBook = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookMeta = (BookMeta) ruleBook.getItemMeta();
		bookMeta.setAuthor("運営");
		bookMeta.setTitle("ルールブック");
		bookMeta.setPages(contents);
		ruleBook.setItemMeta(bookMeta);
		return ruleBook;
	}
}
