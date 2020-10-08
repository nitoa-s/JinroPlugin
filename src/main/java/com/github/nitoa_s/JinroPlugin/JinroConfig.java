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
	}

	public void setValue(String key, String value) {
		config.set(key, value);
	}

	public void setValue(String key, int value) {
		config.set(key, value);
	}

	public void setValue(String key, boolean value) {
		config.set(key, value);
	}

	public int getIntValue(String key) {
		return config.getInt(key);
	}

	public boolean containKey(String key) {
		return config.contains(key);
	}


	public void save() {
		plugin.saveConfig();
	}
}
