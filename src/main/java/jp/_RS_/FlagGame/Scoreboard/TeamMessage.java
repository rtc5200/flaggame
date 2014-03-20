package jp._RS_.FlagGame.Scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class TeamMessage {
	private Team team;
	public TeamMessage(Team team) {
		this.team = team;
	}
	public void sendMessage(String message)
	{
		for(OfflinePlayer of : team.getPlayers())
		{
			of.getPlayer().sendMessage(message);
		}
	}
	public void sendMessage(Player p,String message)
	{
		String s = ChatColor.AQUA + "[チーム]"  + ChatColor.RESET + "<" + p.getDisplayName() + "> " +ChatColor.AQUA + message + ChatColor.RESET;
		sendMessage(s);
	}

}
