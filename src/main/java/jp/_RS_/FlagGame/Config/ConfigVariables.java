package jp._RS_.FlagGame.Config;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class ConfigVariables {
	public static File MainConfigFile(File DataFolder)
	{
		return new File(DataFolder,"config.yml");
	}
	public static File RedTeamConfigFile(File DataFolder)
	{
		return new File(DataFolder,"RedTeam.yml");
	}
	public static File BlueTeamConfigFile(File DataFolder)
	{
		return new File(DataFolder,"BlueTeam.yml");
	}
	public static File FlagConfigFile(File DataFolder)
	{
		return new File(DataFolder,"Flag.yml");
	}
	public static World world = Bukkit.getWorld("world");

}
