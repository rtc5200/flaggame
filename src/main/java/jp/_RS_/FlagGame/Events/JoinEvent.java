package jp._RS_.FlagGame.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import jp._RS_.FlagGame.Main;

public class JoinEvent implements Listener{
	private Main main;
	public JoinEvent(Main main) {
		this.main = main;
	}
	@EventHandler
	public void setScoreboardWhenJoin(PlayerJoinEvent e)
	{
		e.getPlayer().setScoreboard(main.getSbManager().getScoreboard());
	}}
