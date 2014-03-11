package jp._RS_.FlagGame;

import java.util.logging.Logger;

import jp._RS_.FlagGame.Commands.FGCommandExecutor;
import jp._RS_.FlagGame.Config.ConfigHandler;
import jp._RS_.FlagGame.Events.ChatEvent;
import jp._RS_.FlagGame.Events.FlagEvents;
import jp._RS_.FlagGame.Events.JoinEvent;
import jp._RS_.FlagGame.Events.TeamEvent;
import jp._RS_.FlagGame.Scoreboard.BarManager;
import jp._RS_.FlagGame.Scoreboard.SbManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	private Logger log = this.getLogger();
	private SbManager sm;
	private ConfigHandler config;
	private GameController controller;
	private BarManager bar;
	@Override
	public void onEnable()
	{
		config = new ConfigHandler(this);
		sm = new SbManager(this);
		getCommand("fg").setExecutor(new FGCommandExecutor(this));
		registerEvents();
		controller = new GameController(this);
		bar = new BarManager(this);
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.setScoreboard(sm.getScoreboard());
		}
	}
	@Override
	public void onDisable()
	{
		
	}
	private void registerEvents()
	{
		getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
		getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
		getServer().getPluginManager().registerEvents(new FlagEvents(this), this);
		getServer().getPluginManager().registerEvents(new TeamEvent(this), this);
		//getServer().getPluginManager().registerEvents(new Waiting(this), this);
	}
	public SbManager getSbManager()
	{
		return sm;
	}
	public ConfigHandler getConfigHandler()
	{
		return config;
	}
	public GameController getController()
	{
		return controller;
	}
	public BarManager getBarManager()
	{
		return bar;
	}
	
}
