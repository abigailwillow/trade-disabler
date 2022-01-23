package com.abbydiode.tradedisabler.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.abbydiode.tradedisabler.App;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import javax.xml.stream.events.Namespace;
import java.util.List;

public class PlayerInteractEntityListener implements Listener {
	private App plugin;
	private List<String> disabledMaterials;
	private List<String> disabledEnchantments;
	
	public PlayerInteractEntityListener(App plugin) {
		this.plugin = plugin;
		this.disabledMaterials = plugin.getConfig().getStringList("disabledMaterials");
		this.disabledEnchantments = plugin.getConfig().getStringList("disabledEnchantments");
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		Entity entity = e.getRightClicked();
		if (entity.getType() == EntityType.VILLAGER) {
			Villager villager = (Villager) entity;
			villager.getRecipes().forEach(recipe -> {
				for (String material : disabledMaterials) {
					if (recipe.getResult().getType() == Material.matchMaterial(material)) {
						recipe.setUses(recipe.getMaxUses());
					} else if (recipe.getResult().getType() == Material.ENCHANTED_BOOK) {
						for (String enchantment : disabledEnchantments) {
							if (((EnchantmentStorageMeta) recipe.getResult().getItemMeta()).getStoredEnchantLevel(Enchantment.getByKey(NamespacedKey.fromString(enchantment))) > 0) {
								recipe.setUses(recipe.getMaxUses());
							}
						}
					}
				}
			});
		}
	}
}
