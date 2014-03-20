package jp._RS_.FlagGame.Scoreboard;

import java.text.NumberFormat;
import java.util.ArrayList;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import jp._RS_.FlagGame.Main;

public class BarManager {
	private Main main;
	private FlagManager fmanager;
	private SbManager sm;
	private ArrayList<BukkitTask> tasks = new ArrayList<BukkitTask>();
	public BarManager(Main main) {
		this.main = main;
		this.fmanager = main.getController().getFlagManager();
		sm = main.getSbManager();
	}
	public void updateBar()
	{
		 String rmessage = ChatColor.RED + "赤" + ChatColor.RESET + "チームの占領率 ";
		 String bmessage = ChatColor.BLUE + "青" + ChatColor.RESET + "チームの占領率";
		for(OfflinePlayer p : sm.getRed().getPlayers())
		{
			Player op = p.getPlayer();
			if(p.isOnline())
			{
				if(BarAPI.hasBar(op))
				{
					BarAPI.setHealth(op, getRedFlagPercent());
				}else{
					BarAPI.setMessage(op,rmessage,getRedFlagPercent());
				}
			}	
		}
		for(OfflinePlayer p : sm.getBlue().getPlayers())
		{
			Player op = p.getPlayer();
			if(p.isOnline())
			{
				if(BarAPI.hasBar(op))
				{
					BarAPI.setHealth(op, getBlueFlagPercent());
				}else{
					BarAPI.setMessage(op,bmessage,getBlueFlagPercent());
				}
			}
		}
	}
	public void setRedBar(Player p)
	{
		String rmessage = ChatColor.RED + "赤" + ChatColor.RESET + "チームの占領率 ";
		BarAPI.setMessage(p,rmessage,getRedFlagPercent());
	}
	public void setBlueBar(Player p)
	{
		 String bmessage = ChatColor.BLUE + "青" + ChatColor.RESET + "チームの占領率";
		BarAPI.setMessage(p,bmessage,getBlueFlagPercent());
	}
	public void message(String s)
	{
		if(tasks.size() > 0)
		{
			for(BukkitTask task : tasks)
			{
				task.cancel();
			}
			tasks = new ArrayList<BukkitTask>();
		}
		final String original = s;
		final char[] cs = original.toCharArray();
		BukkitScheduler sc = Bukkit.getScheduler();
		int i = 0;
		for(i = 0; original.length() >= i;i++)
		{
			final int ci = i;
			tasks.add(sc.runTaskLater(main, new Runnable(){
				@Override
				public void run() {
					for(OfflinePlayer op : main.getSbManager().getRed().getPlayers())
					{
						if(op.getPlayer() != null)
						{
							BarAPI.setMessage(op.getPlayer(),original.substring(0,ci),getRedFlagPercent());
						}
					}
					for(OfflinePlayer op : main.getSbManager().getBlue().getPlayers())
					{
						if(op.getPlayer() != null)
						{
							BarAPI.setMessage(op.getPlayer(),original.substring(0,ci),getBlueFlagPercent());
						}
					}
				}	
			}, i*2));
		}
		tasks.add(sc.runTaskLater(main, new Runnable(){
			@Override
			public void run() {
				removeAll();
				taskFinish();
			}
		}, i*2 + 20));
	}
	private void taskFinish()
	{
		tasks = new ArrayList<BukkitTask>();
	}
	public void removeAll()
	{
		for(OfflinePlayer ofp : Bukkit.getOfflinePlayers())
		{
			if(ofp.isOnline() && ofp.getPlayer() != null)
			{
				BarAPI.removeBar(ofp.getPlayer());
			}
			
		}
	}
	private float getRedFlagPercent()
	{
		return (float)fmanager.getRedFlags() / fmanager.getAllFlags()*100;
	}
	private float getBlueFlagPercent()
	{
		return (float)fmanager.getBlueFlags() / fmanager.getAllFlags()*100;
	}

}
