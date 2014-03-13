package jp._RS_.FlagGame.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Team;

import jp._RS_.FlagGame.Main;

public class PlayerTeamJoinEvent extends Event{
	private Main main;
	private final HandlerList handles = new HandlerList();
	private Player p;
	private Team team;
	public PlayerTeamJoinEvent(Main main,Player player,Team team) {
		this.main = main;
		p = player;
		this.team = team;
	}
	@Override
	public HandlerList getHandlers() {
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
