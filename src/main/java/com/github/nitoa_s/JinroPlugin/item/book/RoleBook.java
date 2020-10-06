package com.github.nitoa_s.JinroPlugin.item.book;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class RoleBook {
	private static final String[] CONTENTS = {
			"役職情報"
	};

	public static ItemStack giveRoleBook() {
		ItemStack roleBook = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookMeta = (BookMeta) roleBook.getItemMeta();
		bookMeta.setAuthor("運営");
		bookMeta.setTitle("役職一覧");
		bookMeta.setPages(CONTENTS);
		roleBook.setItemMeta(bookMeta);
		return roleBook;
	}
}
