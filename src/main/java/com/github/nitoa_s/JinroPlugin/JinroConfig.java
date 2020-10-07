package com.github.nitoa_s.JinroPlugin;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.nitoa_s.JinroPlugin.role.JinroRole;

public class JinroConfig {
	private JinroPlugin plugin;
	private FileConfiguration config;

	public JinroConfig(JinroPlugin plugin) {
		this.plugin = plugin;
		plugin.saveDefaultConfig();
		config = plugin.getConfig();
		initialize();
	}

	// 新役職が追加された際に自動的にconfigに追加するようにした（役職を削除した際は手動で削除が必要）
	private void initialize() {
		for( JinroRole role: JinroRole.values() ) {
			String configKey = "roles." + role.getRoleKey();
			if( !config.contains(configKey) ) setValue(configKey, 0);
		}
	}

	public void setValue(String key, String value) {
		config.set(key, value);
	}

	public void setValue(String key, int value) {
		config.set(key, value);
	}

	public int getIntValue(String key) {
		return config.getInt(key);
	}

	public void setValue(String key, boolean value) {
		config.set(key, value);
	}

	public void save() {
		plugin.saveConfig();
	}
}
