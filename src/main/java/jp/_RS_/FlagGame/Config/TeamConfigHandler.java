package jp._RS_.FlagGame.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Utils.StringItemStackConverter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class TeamConfigHandler {
	private ItemStack Helmet;
	private ItemStack Chestplate;
	private ItemStack Leggings;
	private ItemStack Boots;
	private Location respawn;
	//private ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	private YamlConfiguration config;
	private File file;
	public TeamConfigHandler(File file) {
		this.config =YamlConfiguration.loadConfiguration(file);
		this.file = file;
		config.addDefault("Helmet", Material.AIR.getId());
		config.addDefault("Chestplate",Material.AIR.getId());
		config.addDefault("Leggings", Material.AIR.getId());
		config.addDefault("Boots", Material.AIR.getId());
		config.addDefault("RespawnPoint", "0,0,0");
		config.options().copyDefaults(true);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Helmet = new ItemStack(Material.getMaterial(config.getInt("Helmet")));
		Chestplate = new ItemStack(Material.getMaterial(config.getInt("Chestplate")));
		Leggings = new ItemStack(Material.getMaterial(config.getInt("Leggings")));
		Boots = new ItemStack(Material.getMaterial(config.getInt("Boots")));
		String[] lt = config.getString("RespawnPoint").split(",");
		respawn = new Location(ConfigVariables.world,Integer.parseInt(lt[0]),Integer.parseInt(lt[1]) + 1,Integer.parseInt(lt[2]));
	}
	public ItemStack getHelmet()
	{
		return Helmet;
	}
	public ItemStack getChestplate()
	{
		return Chestplate;
	}
	public ItemStack getLeggings()
	{
		return Leggings;
	}
	public ItemStack getBoots()
	{
		return Boots;
	}
	public Location getRespawnPoint()
	{
		return respawn;
	}
	public void setRespawnLocation(Location loc)
	{
		int x = loc.getBlockX();int y = loc.getBlockY() + 1;int z = loc.getBlockZ();
		String s = x + "," +  y + "," + z;
		config.set("RespawnPoint", s);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*public ArrayList<ItemStack> getItems()
	{
		return items;
	}*/
	public static TeamConfigHandler load(File file)
	{
		return new TeamConfigHandler(file);
	}

}
