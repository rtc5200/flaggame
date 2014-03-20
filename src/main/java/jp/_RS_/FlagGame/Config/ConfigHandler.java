package jp._RS_.FlagGame.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Timer.CountDown;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class ConfigHandler {
	private YamlConfiguration mconfig;
	private TeamConfigHandler rtconfig;
	private TeamConfigHandler btconfig;
	private FlagConfigHandler fconfig;
	private Main main;
	private Long time;
	private Integer obgp;
	private Integer op;
	private ItemConfigHandler IChandler;
	public ConfigHandler(Main main) {
		this.main = main;
		new FileHelper(main.getLogger(),main.getDataFolder());
		mconfig = YamlConfiguration.loadConfiguration(ConfigVariables.MainConfigFile(main.getDataFolder()));
		rtconfig = TeamConfigHandler.load(ConfigVariables.RedTeamConfigFile(main.getDataFolder()));
		btconfig = TeamConfigHandler.load(ConfigVariables.BlueTeamConfigFile(main.getDataFolder()));
		fconfig = FlagConfigHandler.load(ConfigVariables.FlagConfigFile(main.getDataFolder()));
		mconfig.addDefault("GameTime", 90L);
		mconfig.addDefault("ObjectivePoint", 100);
		mconfig.addDefault("OccupyIncreasePointRate", 10);
		mconfig.options().copyDefaults(true);
		try {
			mconfig.save(new File(main.getDataFolder(),"config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		time = mconfig.getLong("GameTime");
		obgp = mconfig.getInt("ObjectivePoint");
		op = mconfig.getInt("OccupyIncreasePointRate");
		IChandler = new ItemConfigHandler(main);
	}
	public TeamConfigHandler getRedTeamConfig()
	{
		return rtconfig;
	}
	public TeamConfigHandler getBlueTeamConfig()
	{
		return btconfig;
	}
	public FlagConfigHandler getFlagConfig()
	{
		return fconfig;
	}
	public Long getGameTime()
	{
		return time;
	}
	public Integer getObjectivePoint()
	{
		return obgp;
	}
	public Integer getRate()
	{
		return op;
	}
	public ArrayList<ItemStack> getRedItems()
	{
		return IChandler.getRedItems();
	}
	public ArrayList<ItemStack> getBlueItems()
	{
		return IChandler.getBlueItems();
	}

}
