package jp._RS_.FlagGame.Config;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Utils.StringItemStackConverter;

public class ItemConfigHandler {
	private Main main;
	private File RICFile;
	private File BICFile;
	private ArrayList<ItemStack> RedItems = new ArrayList<ItemStack>();
	private ArrayList<ItemStack> BlueItems = new ArrayList<ItemStack>();
	public ItemConfigHandler(Main main) {
		this.main = main;
		RICFile = ConfigVariables.RedTeamItemFile(main.getDataFolder());
		BICFile = ConfigVariables.BlueTeamItemFile(main.getDataFolder());
		load();
	}
	private void load()
	{
		String red_temp1 = ItemConfigFileReader.read(RICFile);
		String blu_temp1 = ItemConfigFileReader.read(BICFile);
		RedItems.addAll(StringItemStackConverter.convertToList(red_temp1));
		BlueItems.addAll(StringItemStackConverter.convertToList(blu_temp1));
	}
	public ArrayList<ItemStack> getRedItems()
	{
		return RedItems;
	}
	public ArrayList<ItemStack> getBlueItems()
	{
		return BlueItems;
	}

}
