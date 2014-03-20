package jp._RS_.FlagGame.Scoreboard;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Config.ConfigHandler;
import jp._RS_.FlagGame.Config.FlagConfigHandler;

public class ScoreCheckTask extends BukkitRunnable{
	private Main main;
	private FlagConfigHandler fconfig;
	private ConfigHandler config;
	private ScoreManager manager;
	private boolean canceled = true;
	public ScoreCheckTask(Main main) {
		this.main = main;
		this.fconfig = main.getConfigHandler().getFlagConfig();
		this.manager = main.getSbManager().getScoreManager();
		this.config = main.getConfigHandler();
	}
	public void setCancelled(boolean b)
	{
		canceled = b;
	}
	@Override
	public void run() {
		if(!canceled)
		{
			if(manager.RedTeam_getScore() > config.getObjectivePoint() || manager.BlueTeam_getScore() > config.getObjectivePoint())
			{
				this.setCancelled(true);
				main.getController().exit();
				return;
			}
			for(Location l : fconfig.getFlagLocation())
			{
				if(l.getBlock().getType().equals(Material.WOOL) && l.getBlock().getData() == 14)
				{
					manager.RedTeam_AddScore(config.getRate());
					main.getBarManager().updateBar();
				}else if(l.getBlock().getType().equals(Material.WOOL) && l.getBlock().getData() == 11)
				{
					manager.BlueTeam_AddScore(config.getRate());
					main.getBarManager().updateBar();
				}
				main.getBarManager().updateBar();
			}
		}
	}

}
