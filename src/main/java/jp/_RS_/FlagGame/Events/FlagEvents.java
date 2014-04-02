package jp._RS_.FlagGame.Events;

import java.util.logging.Logger;

import jp._RS_.FlagGame.GameStatus;
import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Scoreboard.SbManager;
import jp._RS_.FlagGame.Utils.FireworkEffectPlayer;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Wool;

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
				DyeColor preco = getWoolColor(b.getState());
				if(main.getSbManager().isRedTeam(p))
				{
					if(preco != null && preco == DyeColor.LIME || preco == DyeColor.BLUE)//5,11
					{
						main.getServer().broadcastMessage(p.getDisplayName() + "さんがフラッグを占領しました。");
						setWoolColor(b.getState(),DyeColor.RED);//14
						FireworkEffectPlayer.playEffect(b.getLocation(), FireworkEffect.builder().with(Type.BURST).withColor(Color.RED).build());
						for(OfflinePlayer ofp : manager.getRed().getPlayers())
						{
							Player sp = ofp.getPlayer();
							if(sp != null)sp.playSound(sp.getLocation(), Sound.LEVEL_UP, 100, 1);
						}
						for(OfflinePlayer ofp : manager.getBlue().getPlayers())
						{
							Player sp = ofp.getPlayer();
							if(sp != null)sp.playSound(sp.getLocation(), Sound.SPLASH, 100, 1);
						}
						main.getBarManager().message(p.getDisplayName() + "さんがフラッグを占領しました");
					}
				}else if(main.getSbManager().isBlueTeam(p))
				{
					if(preco != null && preco  == DyeColor.LIME || preco == DyeColor.RED)//5,14
					{
						main.getServer().broadcastMessage(p.getDisplayName() + "さんがフラッグを占領しました。");
						setWoolColor(b.getState(),DyeColor.BLUE);
						FireworkEffectPlayer.playEffect(b.getLocation(), FireworkEffect.builder().with(Type.BURST).withColor(Color.BLUE).build());
						for(OfflinePlayer ofp : manager.getRed().getPlayers())
						{
							Player sp = ofp.getPlayer();
							if(sp != null)sp.playSound(sp.getLocation(), Sound.SPLASH, 100, 1);
						}
						for(OfflinePlayer ofp : manager.getBlue().getPlayers())
						{
							Player sp = ofp.getPlayer();
							if(sp != null)sp.playSound(sp.getLocation(), Sound.LEVEL_UP, 100, 1);
						}
						main.getBarManager().message(p.getDisplayName() + "さんがフラッグを占領しました");
					}
				}
			}
		}
	}
	private void setWoolColor(BlockState state,DyeColor color)
	{
		MaterialData data = state.getData();
		if(data instanceof Wool)
		{
			Wool wool = (Wool) data;
			wool.setColor(color);
			state.setData(wool);
			state.update(true);
		}
	}
	private DyeColor getWoolColor(BlockState state)
	{
		MaterialData data = state.getData();
		if(data instanceof Wool)
		{
			Wool wool = (Wool) data;
			return wool.getColor();
		}
		return null;
	}

}
