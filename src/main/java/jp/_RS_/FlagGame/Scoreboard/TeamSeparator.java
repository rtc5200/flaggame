package jp._RS_.FlagGame.Scoreboard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class TeamSeparator {
	private Team red;
	private Team blue;
	private SbManager manager;
	public TeamSeparator(SbManager manager) {
		this.manager = manager;
		this.red = manager.getRed();
		this.blue = manager.getBlue();
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
			if(i%2 == 0)manager.JoinRedTeam(pls.get(i));
			else manager.JoinBlueTeam(pls.get(i));
			
			pls.get(i).playSound(pls.get(i).getLocation(), Sound.LEVEL_UP, 100, 1);
		}
		return result;
	}
}
