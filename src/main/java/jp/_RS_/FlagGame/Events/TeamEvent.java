package jp._RS_.FlagGame.Events;

import net.minecraft.server.v1_6_R2.Packet205ClientCommand;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import jp._RS_.FlagGame.Main;



public class TeamEvent implements Listener{
	private Main main;
	public TeamEvent(Main main) {
		this.main = main;
	}
	@EventHandler
	public void Respawn(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		
		if(!main.getSbManager().isPlaying(p))
		{
			return;
		}
		Packet205ClientCommand packet = new Packet205ClientCommand();
		packet.a = 1;
		((CraftPlayer)p).getHandle().playerConnection.a(packet);
		Location loc = null;
		if(main.getSbManager().isRedTeam(p))
		{
			loc = main.getConfigHandler().getRedTeamConfig().getRespawnPoint();
		}else{
			loc = main.getConfigHandler().getBlueTeamConfig().getRespawnPoint();
		}
		p.teleport(loc);
	}

}
