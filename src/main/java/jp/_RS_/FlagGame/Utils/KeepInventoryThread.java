package jp._RS_.FlagGame.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KeepInventoryThread extends Thread{
	private Player p;
	private ItemStack[] armor;
	private ItemStack[] items;
	public KeepInventoryThread(Player p,ItemStack[] armor,ItemStack[] items) {
		this.p = p;
		this.armor = armor;
		this.items = items;
	}
	@Override
	public void run()
	{
		p.getInventory().setArmorContents(armor);
		p.getInventory().setContents(items);
	}

}
