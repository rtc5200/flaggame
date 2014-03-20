package jp._RS_.FlagGame.Scoreboard;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Config.ConfigHandler;
import jp._RS_.FlagGame.Config.TeamConfigHandler;
import jp._RS_.FlagGame.Events.PlayerTeamJoinEvent;
import jp._RS_.FlagGame.Events.PlayerTeamQuitEvent;
import jp._RS_.FlagGame.Variables.ScoreboardVariables;

public class SbManager {
	private Main main;
	private Scoreboard sb;
	private Team red;
	private Team blue;
	private Objective pstats;
	private Objective fstats;
	private ConfigHandler config;
	private TeamConfigHandler rtconfig;
	private TeamConfigHandler btconfig;
	private ScoreManager smanager;
	public SbManager(Main main) {
		this.main = main;
		config = main.getConfigHandler();
		rtconfig = main.getConfigHandler().getRedTeamConfig();
		btconfig = main.getConfigHandler().getBlueTeamConfig();
		load();
	}
	private void load()
	{
		sb = main.getServer().getScoreboardManager().getNewScoreboard();
		red = sb.registerNewTeam(ScoreboardVariables.Team_RED_Name);
		blue = sb.registerNewTeam(ScoreboardVariables.Team_BLUE_Name);
		pstats = sb.registerNewObjective(ScoreboardVariables.Objective_TeamStats_Name, "dummy");
		fstats = sb.registerNewObjective(ScoreboardVariables.Objective_FlagStats_Name, "dummy");
		pstats.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		fstats.setDisplaySlot(DisplaySlot.SIDEBAR);
		red.setAllowFriendlyFire(false);
		blue.setAllowFriendlyFire(false);
		red.setPrefix(ChatColor.RED.toString());
		blue.setPrefix(ChatColor.BLUE.toString());
		red.setSuffix(ChatColor.RESET.toString());
		blue.setSuffix(ChatColor.RESET.toString());
		smanager = new ScoreManager(main,this);
	}
	public ScoreManager getScoreManager()
	{
		return smanager;
	}
	public Scoreboard getScoreboard()
	{
		return sb;
	}
	public void JoinRedTeam(Player p)
	{
		red.addPlayer(p);
		PlayerInventory iv = p.getInventory();
		iv.clear();
		iv.setHelmet(rtconfig.getHelmet());
		iv.setChestplate(rtconfig.getChestplate());
		iv.setLeggings(rtconfig.getLeggings());
		iv.setBoots(rtconfig.getBoots());
		for(ItemStack i : config.getRedItems())
		{
			iv.addItem(i);
		}
		NameColor.Red(p);
		Bukkit.getServer().getPluginManager().callEvent(new PlayerTeamJoinEvent(main,p,red));
		main.getBarManager().setRedBar(p);
	}
	public void JoinBlueTeam(Player p)
	{
		blue.addPlayer(p);
		PlayerInventory iv = p.getInventory();
		iv.clear();
		iv.setHelmet(btconfig.getHelmet());
		iv.setChestplate(btconfig.getChestplate());
		iv.setLeggings(btconfig.getLeggings());
		iv.setBoots(btconfig.getBoots());
		for(ItemStack i : config.getBlueItems())
		{
			iv.addItem(i);
		}
		NameColor.Blue(p);
		Bukkit.getServer().getPluginManager().callEvent(new PlayerTeamJoinEvent(main,p,blue));
		main.getBarManager().setBlueBar(p);
	}
	public void JoinEqually(Player p)
	{
		if(red.getSize() > blue.getSize())
		{
			JoinBlueTeam(p);
		}else{
			JoinRedTeam(p);
		}
	}
	public void Quit(Player p)
	{
		if(red.hasPlayer(p))
		{
			red.removePlayer(p);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerTeamQuitEvent(main,p,red));
		}else if(blue.hasPlayer(p))
		{
			blue.removePlayer(p);
			Bukkit.getServer().getPluginManager().callEvent(new PlayerTeamQuitEvent(main,p,blue));
		}
		PlayerInventory iv = p.getInventory();
		iv.clear();
		iv.setHelmet(new ItemStack(Material.AIR));
		iv.setChestplate(new ItemStack(Material.AIR));
		iv.setLeggings(new ItemStack(Material.AIR));
		iv.setBoots(new ItemStack(Material.AIR));
		NameColor.Reset(p);
		main.getBarManager().updateBar();
		BarAPI.removeBar(p);
	}
	public void ClearMembers()
	{
		for(OfflinePlayer p : red.getPlayers())
		{
			red.removePlayer(p);
			NameColor.Reset(p.getPlayer());
			Player op = p.getPlayer();
			if(op != null && op.isOnline())
			{
				op.setHealth(20);
				op.setMaxHealth(20);
				op.setFoodLevel(20);
				op.getInventory().clear();
			}
			
		}
		for(OfflinePlayer p : blue.getPlayers())
		{
			blue.removePlayer(p);
			NameColor.Reset(p.getPlayer());
			Player op = p.getPlayer();
			if(op != null && op.isOnline())
			{
				op.setHealth(20);
				op.setMaxHealth(20);
				op.setFoodLevel(20);
				op.getInventory().clear();
			}
		}
	}
	public boolean isRedTeam(Player p)
	{
		if(red.hasPlayer(p))return true;
		return false;
	}
	public boolean isBlueTeam(Player p)
	{
		if(blue.hasPlayer(p))return true;
		return false;
	}
	public boolean isPlaying(Player p)
	{
		if(red.hasPlayer(p) || blue.hasPlayer(p))return true;
		return false;
	}
	public Team getRed()
	{
		return red;
	}
	public Team getBlue()
	{
		return blue;
	}

}
