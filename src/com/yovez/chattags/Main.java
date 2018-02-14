package com.yovez.chattags;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		setupChat();
		setupEconomy();
		setupPermissions();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		if (e.getPlayer().hasPermission("chattags.color")) {
			Bukkit.getServer().broadcastMessage(getFullDisplayName(e.getPlayer()) + " §r» "
					+ ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		} else {
			Bukkit.getServer().broadcastMessage(getFullDisplayName(e.getPlayer()) + " §r» " + e.getMessage());
		}
	}

	public static Permission permission = null;
	public static Economy economy = null;
	public static Chat chat = null;

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null) {
			chat = chatProvider.getProvider();
		}

		return (chat != null);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	public String getFullDisplayName(Player p) {
		return ChatColor.translateAlternateColorCodes('&',
				chat.getPlayerPrefix(p) + p.getDisplayName() + chat.getPlayerSuffix(p));
	}

}
