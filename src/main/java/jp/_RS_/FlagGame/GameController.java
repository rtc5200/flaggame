package jp._RS_.FlagGame;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import jp._RS_.FlagGame.Config.ConfigHandler;
import jp._RS_.FlagGame.Scoreboard.SbManager;
import jp._RS_.FlagGame.Scoreboard.TeamSeparator;
import jp._RS_.FlagGame.Scoreboard.TeamTeleporter;
import jp._RS_.FlagGame.Timer.CountDown;

public class GameController {
	private Main main;
	private SbManager manager;
	private ConfigHandler config;
	private GameStatus status = GameStatus.READY;
	private CountDown count;
	public GameController(Main main) {
		this.main = main;
		manager = main.getSbManager();
		config = main.getConfigHandler();
	}
	public void start()
	{
		status = GameStatus.STARTED;
		HashMap<Player,Team> temp = new TeamSeparator(manager.getRed(),manager.getBlue()).separate(Bukkit.getOnlinePlayers());
		for(Player p : temp.keySet())
		{
			if(temp.get(p).equals(manager.getRed()))
			{
				manager.JoinRedTeam(p);
			}else{
				manager.JoinBlueTeam(p);
			}
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 100);
		}
		Location rrespawn = config.getRedTeamConfig().getRespawnPoint();
		Location brespawn = config.getBlueTeamConfig().getRespawnPoint();
		new TeamTeleporter(manager.getRed()).Teleport(rrespawn);
		new TeamTeleporter(manager.getBlue()).Teleport(brespawn);
		count = new CountDown(config.getGameTime(),main);
		count.start();
		main.getServer().broadcastMessage(ChatColor.GREEN + "ゲーム開始です!");
	}
	public void exit()
	{
		
	}
	public GameStatus getStatus()
	{
		return status;
	}

}
