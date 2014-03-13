package jp._RS_.FlagGame.Utils;

import java.util.ArrayList;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Scoreboard.SbManager;

import org.bukkit.entity.Player;

public class PlayerSort {
	
	public static ArrayList<Player> getNotJoinedPlayers(SbManager m,Player[] players)
	{
		ArrayList<Player> result = new ArrayList<Player>();
		for(Player p : players)
		{
			if(!m.isPlaying(p))
			{
				result.add(p);
			}
		}
		return result;
	}
}
