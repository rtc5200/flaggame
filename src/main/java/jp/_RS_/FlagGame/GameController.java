package jp._RS_.FlagGame;

import jp._RS_.FlagGame.Config.ConfigHandler;
import jp._RS_.FlagGame.Config.ConfigVariables;
import jp._RS_.FlagGame.Scoreboard.BarManager;
import jp._RS_.FlagGame.Scoreboard.FlagManager;
import jp._RS_.FlagGame.Scoreboard.SbManager;
import jp._RS_.FlagGame.Scoreboard.ScoreCheckTask;
import jp._RS_.FlagGame.Scoreboard.TeamSeparator;
import jp._RS_.FlagGame.Scoreboard.TeamTeleporter;
import jp._RS_.FlagGame.Timer.CountDown;
import jp._RS_.FlagGame.Utils.PlayerSort;
import jp._RS_.FlagGame.Variables.MessageVariables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class GameController {
	private Main main;
	private SbManager manager;
	private ConfigHandler config;
	private GameStatus status = GameStatus.READY;
	private CountDown count;
	private ScoreCheckTask task;
	private FlagManager fmanager;
	public GameController(Main main) {
		this.main = main;
		manager = main.getSbManager();
		config = main.getConfigHandler();
		fmanager = new FlagManager(main);
	}
	public void start()
	{
		manager.getScoreManager().resetScores();
		fmanager.resetFlags();
		status = GameStatus.WAIT;
		BukkitScheduler s = main.getServer().getScheduler();
		main.getServer().broadcastMessage(ChatColor.GREEN + "チーム分け準備中です.....");
		s.runTaskLater(main,new Runnable(){
			@Override
			public void run() {
				new TeamSeparator(manager).separate(PlayerSort.getNotJoinedPlayers(manager, Bukkit.getOnlinePlayers()));
				Location rrespawn = config.getRedTeamConfig().getRespawnPoint();
				Location brespawn = config.getBlueTeamConfig().getRespawnPoint();
				new TeamTeleporter(manager.getRed()).Teleport(rrespawn);
				new TeamTeleporter(manager.getBlue()).Teleport(brespawn);
				main.getServer().broadcastMessage(ChatColor.GREEN + "チーム分け完了しました!");
				count = new CountDown(config.getGameTime(),main);
				for(String s: MessageVariables.getGameStartMessageList(config))
				{
					main.getServer().broadcastMessage(s);
				}
				count.start();
				task = new ScoreCheckTask(main);
				task.setCancelled(false);
				main.getServer().getScheduler().runTaskTimer(main,task, 20, 20);
				status = GameStatus.INGAME;
				main.getBarManager().updateBar();
			}
		},20);
	}
	public void exit()
	{
		task.setCancelled(true);
		count.setCancelled(true);
		int reds = manager.getScoreManager().RedTeam_getScore();
		int blues = manager.getScoreManager().BlueTeam_getScore();
		if(reds > blues)
		{
			main.getServer().broadcastMessage(MessageVariables.Red + "チームの勝利です!");
			//赤勝ち
		}else if (reds < blues)
		{
			main.getServer().broadcastMessage(MessageVariables.Blue + "チームの勝利です!");
			//青勝ち
		}else{
			main.getServer().broadcastMessage("引き分けです!");
			//引き分け
		}
		main.getServer().broadcastMessage(ChatColor.GREEN + "お疲れ様でした。");
		new TeamTeleporter(manager.getRed()).Teleport(ConfigVariables.world.getSpawnLocation());
		new TeamTeleporter(manager.getBlue()).Teleport(ConfigVariables.world.getSpawnLocation());
		manager.ClearMembers();
		main.getBarManager().removeAll();
		status = GameStatus.READY;
	}
	public GameStatus getStatus()
	{
		return status;
	}
	public FlagManager getFlagManager()
	{
		return fmanager;
	}
}
