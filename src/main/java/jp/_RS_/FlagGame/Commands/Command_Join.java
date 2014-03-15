package jp._RS_.FlagGame.Commands;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Scoreboard.SbManager;
import jp._RS_.FlagGame.Variables.CommandVariables;
import jp._RS_.FlagGame.Variables.MessageVariables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
/*
 * /fg join [red/blue]
 *  /fg join [Player's name] [red/blue]
 */
public class Command_Join extends CommandBase {
	private Main main;
	private SbManager manager;
	public Command_Join(Main main,CommandSender sender,String[] args) {
		this.main = main;
		manager = main.getSbManager();
		if(sender instanceof Player)PerformFromPlayer(sender,args);
		if(sender instanceof BlockCommandSender)PerformFromCommandBlock(sender,args);
		if(sender instanceof ConsoleCommandSender)PerformFromConsole(sender,args);
	}
	@Override
	public void PerformFromPlayer(CommandSender sender,String[] args) {
		if(!sender.isOp() && !sender.hasPermission(CommandVariables.Permission_Join))
		{
			rejectExecute(sender,RejectedReason.NotHavePermission);
			return;
		}
		if(args.length < 2)
		{
			rejectExecute(sender,RejectedReason.NotEnoughArgs);
			return;
		}
		if(args.length == 2)
		{
			Player p = (Player)sender;
			if(args[1].equalsIgnoreCase("red"))
			{
				manager.JoinRedTeam(p);
				p.teleport(main.getConfigHandler().getRedTeamConfig().getRespawnPoint());
				return;
			}else if(args[1].equalsIgnoreCase("blue"))
			{
				manager.JoinBlueTeam(p);
				p.teleport(main.getConfigHandler().getBlueTeamConfig().getRespawnPoint());
				return;
			}else if(args[1].equalsIgnoreCase("eq"))
			{
				manager.JoinEqually(p);
				if(manager.isRedTeam(p))p.teleport(main.getConfigHandler().getRedTeamConfig().getRespawnPoint());
				if(manager.isBlueTeam(p))p.teleport(main.getConfigHandler().getBlueTeamConfig().getRespawnPoint());
				return;
			}
			rejectExecute(sender,RejectedReason.InvalidTeamName);
			return;
		}else if(args.length  == 3)
		{
			Player p = Bukkit.getPlayerExact(args[1]);
			if(p == null || !p.isOnline())
			{
				rejectExecute(sender,RejectedReason.InvalidPlayerName);
				return;
			}
			if(args[2].equalsIgnoreCase("red"))
			{
				manager.JoinRedTeam(p);
				p.teleport(main.getConfigHandler().getRedTeamConfig().getRespawnPoint());
				return;
			}else if(args[2].equalsIgnoreCase("blue"))
			{
				manager.JoinBlueTeam(p);
				p.teleport(main.getConfigHandler().getBlueTeamConfig().getRespawnPoint());
				return;
			}else if(args[2].equalsIgnoreCase("eq"))
			{
				manager.JoinEqually(p);
				return;
			}
			rejectExecute(sender,RejectedReason.InvalidTeamName);
			return;
		}else if(args.length > 3)
		{
			rejectExecute(sender,RejectedReason.TooManyArgs);
			return;
		}
	}
	@Override
	public void PerformFromCommandBlock(CommandSender sender,String[] args) {
		 if(args.length  == 3)
			{
				Player p = Bukkit.getPlayerExact(args[1]);
				if(p == null || !p.isOnline())
				{
					return;
				}
				if(args[2].equalsIgnoreCase("red"))
				{
					manager.JoinRedTeam(p);
					p.teleport(main.getConfigHandler().getRedTeamConfig().getRespawnPoint());
					return;
				}else if(args[2].equalsIgnoreCase("blue"))
				{
					manager.JoinBlueTeam(p);
					p.teleport(main.getConfigHandler().getBlueTeamConfig().getRespawnPoint());
					return;
				}else if(args[2].equalsIgnoreCase("eq"))
				{
					manager.JoinEqually(p);
					return;
				}
				return;
			}
	}
	@Override
	public void PerformFromConsole(CommandSender sender,String[] args) {
		 if(args.length  == 3)
			{
				Player p = Bukkit.getPlayerExact(args[1]);
				if(p == null || !p.isOnline())
				{
					return;
				}
				if(args[2].equalsIgnoreCase("red"))
				{
					manager.JoinRedTeam(p);
					p.teleport(main.getConfigHandler().getRedTeamConfig().getRespawnPoint());
					return;
				}else if(args[2].equalsIgnoreCase("blue"))
				{
					manager.JoinBlueTeam(p);
					p.teleport(main.getConfigHandler().getBlueTeamConfig().getRespawnPoint());
					return;
				}
				return;
			}
	}
}
