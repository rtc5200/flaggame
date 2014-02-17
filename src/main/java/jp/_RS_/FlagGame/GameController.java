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
import jp._RS_.FlagGame.Scoreboard.ScoreCheckTask;
import jp._RS_.FlagGame.Scoreboard.TeamSeparator;
import jp._RS_.FlagGame.Scoreboard.TeamTeleporter;
import jp._RS_.FlagGame.Timer.CountDown;
import jp._RS_.FlagGame.Variables.MessageVariables;

public class GameController {
	private Main main;
	private SbManager manager;
	private ConfigHandler config;
	private GameStatus status = GameStatus.READY;
	private CountDown count;
	private ScoreCheckTask task;
	public GameController(Main main) {
		this.main = main;
		manager = main.getSbManager();
		config = main.getConfigHandler();
	}
	public void start()
	{
		status = GameStatus.STARTED;
		new TeamSeparator(manager).separate(Bukkit.getOnlinePlayers());
		Location rrespawn = config.getRedTeamConfig().getRespawnPoint();
		Location brespawn = config.getBlueTeamConfig().getRespawnPoint();
		new TeamTeleporter(manager.getRed()).Teleport(rrespawn);
		new TeamTeleporter(manager.getBlue()).Teleport(brespawn);
		count = new CountDown(config.getGameTime(),main);
		count.start();
		main.getServer().broadcastMessage(ChatColor.GREEN + "ゲーム開始です!");
		task = new ScoreCheckTask(main);
		Bukkit.getScheduler().runTaskTimer(main,task, 20, 20);
		task.setCancelled(false);
	}
	public void exit()
	{
		count.setCancelled(true);
		task.setCancelled(true);
		int reds = manager.getScoreManager().RedTeam_getScore();
		int blues = manager.getScoreManager().BlueTeam_getScore();
		if(reds > blues)
		{
			main.getServer().broadcastMessage(MessageVariables.Red + "チームの勝利!");
			//赤勝ち
		}else if (reds < blues)
		{
			main.getServer().broadcastMessage(MessageVariables.Blue + "チームの勝利!");
			//青勝ち
		}else{
			main.getServer().broadcastMessage("引き分け!");
			//引き分け
		}
		manager.ClearMembers();
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.teleport(p.getWorld().getSpawnLocation());
		}
		manager.getScoreManager().resetScores();
		status = GameStatus.READY;
	}
	public GameStatus getStatus()
	{
		return status;
	}

}
