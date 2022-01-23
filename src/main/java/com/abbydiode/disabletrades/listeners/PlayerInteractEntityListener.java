package com.abbydiode.disabletrades.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.abbydiode.disabletrades.App;

import java.util.List;

public class PlayerInteractEntityListener implements Listener {
	private App plugin;
	private List<String> disabledTrades = plugin.getConfig().getStringList("disabledTrades");
	
	public PlayerInteractEntityListener(App plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		Entity entity = e.getRightClicked();
		if (entity.getType() == EntityType.VILLAGER) {
			Villager villager = (Villager) entity;
			villager.getRecipes().forEach(recipe -> {
				for (String item : disabledTrades) {
					plugin.getLogger().info(item);
					if (recipe.getResult().getType() == Material.getMaterial(item)) {
						plugin.getLogger().info("disabling " + item);
						recipe.setUses(recipe.getMaxUses());
					}
				}
				/* if (recipe.getResult().getType() == Material.ENCHANTED_BOOK &&
						((EnchantmentStorageMeta) recipe.getResult().getItemMeta()).getStoredEnchantLevel(Enchantment.MENDING) > 0) {
					recipe.setMaxUses(0);
				} */
			});
		}
	}
}
