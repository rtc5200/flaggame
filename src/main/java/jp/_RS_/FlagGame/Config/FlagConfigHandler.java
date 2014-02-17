package jp._RS_.FlagGame.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

public class FlagConfigHandler {
	private ArrayList<Location> points  = new ArrayList<Location>();
	public FlagConfigHandler(YamlConfiguration config) {
		for(String s : config.getStringList("Locations"))
		{
			String[] ls= s.split(",");
			points.add(new Location(ConfigVariables.world,Integer.parseInt(ls[0]),Integer.parseInt(ls[1]),Integer.parseInt(ls[2])));
		}
	}
	public ArrayList<Location> getFlagLocation()
	{
		return points;
	}
	public boolean isFlag(Location loc)
	{
		if(points.contains(loc))return true;
		return false;
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
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
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
		return new FlagConfigHandler(config);
	}

}
