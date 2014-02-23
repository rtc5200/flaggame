package jp._RS_.FlagGame.Events;

import jp._RS_.FlagGame.GameStatus;
import jp._RS_.FlagGame.Main;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FlagEvents implements Listener{
	private Main main;
	public FlagEvents(Main main) {
		this.main = main;
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
				if(main.getSbManager().isRedTeam(p))
				{
					if(b.getData() == 5 || b.getData() == 11)
					{
						main.getServer().broadcastMessage(p.getName() + "さんがフラッグを占領しました。");
						b.setData((byte) 14);
					}	
				}else if(main.getSbManager().isBlueTeam(p))
				{
					if(b.getData() == 5 || b.getData() == 14)
					{
						main.getServer().broadcastMessage(p.getName() + "さんがフラッグを占領しました。");
						b.setData((byte) 11);
					}
						
				}
			}
			
		}
	}

}
