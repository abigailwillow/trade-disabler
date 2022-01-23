package com.abbydiode.tradedisabler;

import org.bukkit.plugin.java.JavaPlugin;

import com.abbydiode.tradedisabler.listeners.PlayerInteractEntityListener;

public class App extends JavaPlugin {
	@Override
	public void onEnable() {
		saveDefaultConfig();

		new PlayerInteractEntityListener(this);
	}
}
