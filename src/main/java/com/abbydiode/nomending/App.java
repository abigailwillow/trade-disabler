package com.abbydiode.nomending;

import org.bukkit.plugin.java.JavaPlugin;

import com.abbydiode.nomending.listeners.PlayerInteractEntityListener;

public class App extends JavaPlugin {
	@Override
	public void onEnable() {
		new PlayerInteractEntityListener(this);
	}
}
