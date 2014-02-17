package jp._RS_.FlagGame.Scoreboard;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Config.FlagConfigHandler;

public class ScoreCheckTask extends BukkitRunnable{
	private Main main;
	private FlagConfigHandler config;
	private ScoreManager manager;
	private boolean canceled = true;
	public ScoreCheckTask(Main main) {
		this.main = main;
		this.config = main.getConfigHandler().getFlagConfig();
		this.manager = main.getSbManager().getScoreManager();
	}
	public void setCancelled(boolean b)
	{
		canceled = b;
	}
	@Override
	public void run() {
		if(!canceled)
		{
			for(Location l : config.getFlagLocation())
			{
				if(l.getBlock().getType().equals(Material.WOOL) && l.getBlock().getData() == 11)
				{
					manager.RedTeam_AddScore(10);
				}else if(l.getBlock().getType().equals(Material.WOOL) && l.getBlock().getData() == 14)
				{
					manager.BlueTeam_AddScore(10);
				}
			}
		}
	}

}
