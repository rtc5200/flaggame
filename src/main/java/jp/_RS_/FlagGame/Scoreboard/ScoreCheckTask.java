package jp._RS_.FlagGame.Scoreboard;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Config.FlagConfigHandler;

public class ScoreCheckTask extends BukkitRunnable{
	private Main main;
	private FlagConfigHandler config;
	public ScoreCheckTask(Main main) {
		this.main = main;
		this.config = main.getConfigHandler().getFlagConfig();
	}
	@Override
	public void run() {
		for(Location l : config.getFlagLocation())
		{
			
		}
		
	}

}
