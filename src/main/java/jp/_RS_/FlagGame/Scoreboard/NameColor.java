package jp._RS_.FlagGame.Scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class NameColor {

	public static void Red(Player p)
	{
		p.setDisplayName(ChatColor.RED + p.getName() + ChatColor.RESET);
	}
	public static void Blue(Player p)
	{
		p.setDisplayName(ChatColor.BLUE + p.getName() + ChatColor.RESET);
	}
	public static void Reset(Player p)
	{
		p.setDisplayName(ChatColor.RESET + p.getName() + ChatColor.RESET);
	}

}
