package com.yovez.chattags;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

public class ChatListener implements Listener {

	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		if (e.getPlayer().hasPermission("yovursechat.color")) {
			Bukkit.getServer().broadcastMessage(getFullDisplayName(e.getPlayer()) + " §r» "
					+ ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		} else {
			Bukkit.getServer().broadcastMessage(getFullDisplayName(e.getPlayer()) + " §r» " + e.getMessage());
		}
	}

	public String getFullDisplayName(Player p) {
		return ChatColor.translateAlternateColorCodes('&',
				Main.chat.getPlayerPrefix(p) + p.getDisplayName() + Main.chat.getPlayerSuffix(p));
	}

}
