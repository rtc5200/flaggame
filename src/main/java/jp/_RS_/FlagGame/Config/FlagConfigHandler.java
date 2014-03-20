package jp._RS_.FlagGame.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

public class FlagConfigHandler {
	private ArrayList<Location> points  = new ArrayList<Location>();
	private YamlConfiguration config;
	private File file;
	public FlagConfigHandler(File file) {
		this.file = file;
		this.config = YamlConfiguration.loadConfiguration(file);
		config.options().copyDefaults(true);
		ArrayList<String> ls = new ArrayList<String>();
		ls.add("0,0,0");
		config.addDefault("Locations",ls);
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		for(String s : config.getStringList("Locations"))
		{
			String[] ls2= s.split(",");
			points.add(new Location(ConfigVariables.world,Integer.parseInt(ls2[0]),Integer.parseInt(ls2[1]),Integer.parseInt(ls2[2])));
		}
	}
	public ArrayList<Location> getFlagLocation()
	{
		return points;
	}
	public void AddFlagLocation(Location loc)
	{
		int x = loc.getBlockX();int y = loc.getBlockY();int z = loc.getBlockZ();
		String s = x + "," +  y + "," + z;
		List<String> list = config.getStringList("Locations");
		list.add(s);
		config.set("Locations",list );
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public boolean isFlag(Location loc)
	{
		if(points.contains(loc))return true;
		return false;
	}
	public int getAmount()
	{
		return points.size();
	}
	public boolean isFlag(Block b)
	{
		if(points.contains(b.getLocation())&&b.getType().equals(Material.WOOL))
		{
			return true;
		}
		return false;
	}
	public static FlagConfigHandler load(File file)
	{
		return new FlagConfigHandler(file);
	}

}
