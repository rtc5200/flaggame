package jp._RS_.FlagGame.Scoreboard;

import java.text.NumberFormat;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import jp._RS_.FlagGame.Main;

public class BarManager {
	private Main main;
	private FlagManager fmanager;
	private SbManager sm;
	public BarManager(Main main) {
		this.main = main;
		this.fmanager = main.getController().getFlagManager();
		sm = main.getSbManager();
	}
	public void updateBar()
	{
		String rmessage = ChatColor.RED + "赤" + ChatColor.RESET + "チームの占領率 ";
		String bmessage = ChatColor.BLUE + "青" + ChatColor.RESET + "チームの占領率";
		NumberFormat nPer = NumberFormat.getPercentInstance();
		String rpertemp;
		String bpertemp;
		if(fmanager.getRedFlags() <= 0)
		{
			rpertemp = "0";
		}else{
			rpertemp = nPer.format( fmanager.getRedFlags() / fmanager.getAllFlags()).replace("%", "");
		}
		if(fmanager.getBlueFlags() <= 0)
		{
			bpertemp = "0";
		}else{
			bpertemp = nPer.format( fmanager.getBlueFlags() / fmanager.getAllFlags()).replace("%", "");
		}
		float rper = Float.parseFloat(rpertemp);
		float bper =Float.parseFloat(bpertemp);
		for(OfflinePlayer p : sm.getRed().getPlayers())
		{
			BarAPI.setMessage(p.getPlayer(),rmessage,rper);
		}
		for(OfflinePlayer p : sm.getBlue().getPlayers())
		{
			BarAPI.setMessage(p.getPlayer(),bmessage,bper);
		}
	}
	public void removeAll()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			BarAPI.removeBar(p);
		}
	}

}
