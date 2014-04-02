package jp._RS_.FlagGame.Events;

import java.util.ArrayList;

import me.confuser.barapi.BarAPI;
import net.minecraft.server.v1_6_R2.Packet205ClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.Team;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Utils.KeepInventoryThread;
import jp._RS_.FlagGame.Variables.MessageVariables;



public class TeamEvent implements Listener{
	private Main main;
	public TeamEvent(Main main) {
		this.main = main;
	}
	@EventHandler(priority = EventPriority.LOW)
	public void DeathRespawn(PlayerDeathEvent e)
	{
		final Player p = (Player) e.getEntity();
		BarAPI.removeBar(p);
		ItemStack[] armor = p.getInventory().getArmorContents();
		ItemStack[] items = p.getInventory().getContents();
		e.setKeepLevel(true);
		e.setDroppedExp(0);
		e.getDrops().clear();
		Bukkit.getScheduler().scheduleSyncDelayedTask(main, new KeepInventoryThread(p, armor, items));
		 Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable()
		    {
		      public void run()
		      {
		        Packet205ClientCommand packet = new Packet205ClientCommand();
		        packet.a = 1;
		        ((CraftPlayer)p).getHandle().playerConnection.a(packet);
		        if(main.getSbManager().isRedTeam(p))
				{
					p.teleport(main.getConfigHandler().getRedTeamConfig().getRespawnPoint());
					main.getBarManager().setRedBar(p);
				}else if(main.getSbManager().isBlueTeam(p)){
					p.teleport(main.getConfigHandler().getBlueTeamConfig().getRespawnPoint());
					main.getBarManager().setBlueBar(p);
				}else{
					p.teleport(p.getWorld().getSpawnLocation());
				}
		      }
		    });
	}
	@EventHandler(priority = EventPriority.LOW)
	public void DamageRespawn(EntityDamageEvent e)
	{

	}
	@EventHandler(priority = EventPriority.LOW)
	public void onDrop(PlayerDropItemEvent e)
	{
		Player p = e.getPlayer();
		if(main.getSbManager().isPlaying(p))
		{
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onJoinTeam(PlayerTeamJoinEvent e)
	{
		Player p = e.getPlayer();
		Team t = e.getTeam();
		p.setMaxHealth(20);
		p.setHealth(20);
		p.setFoodLevel(20);
		if(main.getSbManager().getRed().equals(t))
		{
			Bukkit.broadcastMessage(p.getDisplayName() + "が" + MessageVariables.Red + "チームに参加しました。");
		}else{
			Bukkit.broadcastMessage(p.getDisplayName() + "が" + MessageVariables.Blue + "チームに参加しました。");
		}

	}
	@EventHandler
	public void onQuitTeam(PlayerTeamQuitEvent e)
	{
		Player p = e.getPlayer();
		Bukkit.broadcastMessage(p.getDisplayName() + "がチームから抜けました。");
		p.setMaxHealth(20);
		p.setHealth(20);
		p.setFoodLevel(20);
	}

}
