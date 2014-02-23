package jp._RS_.FlagGame.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import jp._RS_.FlagGame.GameController;
import jp._RS_.FlagGame.GameStatus;
import jp._RS_.FlagGame.Main;

public class Waiting implements Listener{
	private GameController ct;
	public Waiting(Main main) {
		ct = main.getController();
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void wait(PlayerMoveEvent e)
	{
		if(ct.getStatus().equals(GameStatus.WAIT))
		{
			Player p = e.getPlayer();
			Location from = e.getFrom();
			Location to = e.getFrom();
			if(from.getBlockX() == to.getBlockX() && from.getBlockZ() == to.getBlockZ())
			{
				e.setCancelled(false);
			}
		}
	}

}
