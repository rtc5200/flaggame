package jp._RS_.FlagGame.Events;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Scoreboard.SbManager;
import jp._RS_.FlagGame.Scoreboard.TeamMessage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatEvent implements Listener { 
	private Main main;
	private SbManager manager;
	public ChatEvent(Main main) {
		this.main = main;
		this.manager = main.getSbManager();
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(PlayerChatEvent e)
	{
		Player p = e.getPlayer();
		if(manager.isRedTeam(p))
		{
			new TeamMessage(manager.getRed()).sendMessage(p,e.getMessage());
			e.setCancelled(true);
		}else if(manager.isBlueTeam(p))
		{
			new TeamMessage(manager.getBlue()).sendMessage(p,e.getMessage());
			e.setCancelled(true);
		}
	}

}
