package jp._RS_.FlagGame.Scoreboard;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Config.FlagConfigHandler;

public class FlagManager {
	private Main main;
	private FlagConfigHandler config;
	private ArrayList<Location> fs;
	public FlagManager(Main main) {
		this.main = main;
		config = main.getConfigHandler().getFlagConfig();
		fs = config.getFlagLocations();
	}
	public void resetFlags()
	{
		for(Location loc : fs)
		{
			Block b = loc.getBlock();
			if(!b.getType().equals(Material.WOOL))
			{
				b.setType(Material.WOOL);
			}
			b.setData((byte) 5);
		}
	}
	public int getRedFlags()
	{
		int result = 0;
		for(Location loc : fs)
		{
			Block b = loc.getBlock();
			if(b.getType().equals(Material.WOOL) && b.getData() == 14)
			{
				result += 1;
			}
		}
		return result;
	}
	public int getNeutralFlags()
	{
		int result = 0;
		for(Location loc : fs)
		{
			Block b = loc.getBlock();
			if(b.getType().equals(Material.WOOL) && b.getData() == 5)
			{
				result += 1;
			}
		}
		return result;
	}
	public int getAllFlags()
	{
		int result = 0;
		for(Location loc : fs)
		{
			Block b = loc.getBlock();
			if(b.getType().equals(Material.WOOL))
			{
				result += 1;
			}
		}
		return result;
	}
	public int getBlueFlags()
	{
		int result = 0;
		for(Location loc : fs)
		{
			Block b = loc.getBlock();
			if(b.getType().equals(Material.WOOL) && b.getData() == 11)
			{
				result += 1;
			}
		}
		return result;
	}
}
