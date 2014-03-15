package jp._RS_.FlagGame.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import jp._RS_.FlagGame.GameStatus;
import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Scoreboard.NameColor;

public class JoinEvent implements Listener{
	private Main main;
	public JoinEvent(Main main) {
		this.main = main;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		p.setScoreboard(main.getSbManager().getScoreboard());
		if(main.getController().getStatus().equals(GameStatus.READY))
		{
			NameColor.Reset(p);
			main.getSbManager().Quit(p);
			p.teleport(p.getWorld().getSpawnLocation());
		}
	}}
