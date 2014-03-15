package jp._RS_.FlagGame.Events;

import jp._RS_.FlagGame.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Team;

public class PlayerTeamQuitEvent extends Event {
	private Main main;
	private static final HandlerList handles = new HandlerList();
	private Player p;
	private Team team;
	public PlayerTeamQuitEvent(Main main,Player player,Team team) {
		this.main = main;
		p = player;
		this.team = team;
	}
	@Override
	public HandlerList getHandlers() {
		return handles;
	}
	public static HandlerList getHandlerList()
	{
		return handles;
	}
	public Player getPlayer()
	{
		return p;
	}
	public Team getTeam()
	{
		return team;
	}

}
