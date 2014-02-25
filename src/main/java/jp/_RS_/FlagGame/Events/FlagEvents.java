package jp._RS_.FlagGame.Events;

import jp._RS_.FlagGame.GameStatus;
import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Scoreboard.SbManager;

import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FlagEvents implements Listener{
	private Main main;
	private SbManager manager;
	public FlagEvents(Main main) {
		this.main = main;
		this.manager = main.getSbManager();
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			return;
		}
		Block b = e.getClickedBlock();
		Player p = e.getPlayer();
		if(main.getConfigHandler().getFlagConfig().isFlag(b))
		{
			if(main.getController().getStatus().equals(GameStatus.INGAME))
			{
				main.getBarManager().updateBar();
				if(main.getSbManager().isRedTeam(p))
				{
					if(b.getData() == 5 || b.getData() == 11)
					{
						main.getServer().broadcastMessage(p.getDisplayName() + "さんがフラッグを占領しました。");
						b.setData((byte) 14);
						for(OfflinePlayer ofp : manager.getRed().getPlayers())
						{
							Player sp = ofp.getPlayer();
							sp.playSound(sp.getLocation(), Sound.LEVEL_UP, 100, 1);
						}
						for(OfflinePlayer ofp : manager.getBlue().getPlayers())
						{
							Player sp = ofp.getPlayer();
							sp.playSound(sp.getLocation(), Sound.SPLASH, 100, 1);
						}
						
					}	
				}else if(main.getSbManager().isBlueTeam(p))
				{
					if(b.getData() == 5 || b.getData() == 14)
					{
						main.getServer().broadcastMessage(p.getDisplayName() + "さんがフラッグを占領しました。");
						b.setData((byte) 11);
						for(OfflinePlayer ofp : manager.getRed().getPlayers())
						{
							Player sp = ofp.getPlayer();
							sp.playSound(sp.getLocation(), Sound.SPLASH, 100, 1);
						}
						for(OfflinePlayer ofp : manager.getBlue().getPlayers())
						{
							Player sp = ofp.getPlayer();
							sp.playSound(sp.getLocation(), Sound.LEVEL_UP, 100, 1);
						}
					}		
				}
			}
			
		}
	}

}
