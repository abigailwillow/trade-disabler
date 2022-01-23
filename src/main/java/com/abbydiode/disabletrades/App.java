package com.abbydiode.disabletrades;

import org.bukkit.plugin.java.JavaPlugin;

import com.abbydiode.disabletrades.listeners.PlayerInteractEntityListener;

public class App extends JavaPlugin {
	@Override
	public void onEnable() {
		new PlayerInteractEntityListener(this);
	}
}
