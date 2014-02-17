package jp._RS_.FlagGame.Scoreboard;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.scoreboard.Team;

public class TeamTeleporter {
	private Team team;
	public TeamTeleporter(Team team) {
		this.team = team;
	}
	public void Teleport(Location loc)
	{
		for(OfflinePlayer of : team.getPlayers())
		{
			if(of.isOnline())of.getPlayer().teleport(loc);
		}
	}
	public void Teleport(World world,int x,int y,int z)
	{
		Teleport(new Location(world,x,y,z));
	}

}
