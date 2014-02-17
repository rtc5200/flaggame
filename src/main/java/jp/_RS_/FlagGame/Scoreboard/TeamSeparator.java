package jp._RS_.FlagGame.Scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class TeamSeparator {
	private Team red;
	private Team blue;
	public TeamSeparator(Team red,Team blue) {
		this.red = red;
		this.blue = blue;
	}
	public HashMap<Player, Team> separate(Player[] players)
	{
		HashMap<Player,Team> result = new HashMap<Player,Team>();
		ArrayList<Player> pls = new ArrayList<Player>();
		for(Player p : players)
		{
			pls.add(p);
		}
		Collections.shuffle(pls);
		for(int i = 0;i < pls.size();i++)
		{
			if(i <= pls.size()/2)result.put(pls.get(i), red);
			else result.put(pls.get(i), blue);
		}
		return result;
	}

}
