package com.abbydiode.nomending.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import com.abbydiode.nomending.App;

public class PlayerInteractEntityListener implements Listener {
	private App plugin;
	
	public PlayerInteractEntityListener(App plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	// Use the PlayerInteractEntityListener event to set max uses to 0 upon right clicking villager with Mending trade
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		Entity entity = e.getRightClicked();
		// First check if entity is a villager
		if (entity.getType() == EntityType.VILLAGER) {
			Villager villager = (Villager) entity;
			// Loop through all the trades of the villager
			villager.getRecipes().forEach(recipe -> {
				// If the villager has an enchanted book for trade and it stores a MENDING enchantment -> set max uses to 0
				if (recipe.getResult().getType() == Material.ENCHANTED_BOOK && 
						((EnchantmentStorageMeta) recipe.getResult().getItemMeta()).getStoredEnchantLevel(Enchantment.MENDING) > 0) {
					recipe.setMaxUses(0);
				}
			});
		}
	}
}
