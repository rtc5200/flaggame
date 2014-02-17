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
public class Command_Join implements CommandBase {
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
			sender.sendMessage(MessageVariables.NotHavePermission);
			return;
		}
		if(args.length < 2)
		{
			sender.sendMessage(MessageVariables.NotEnoughArgs);
			return;
		}
		if(args.length == 2)
		{
			Player p = (Player)sender;
			if(args[1].equalsIgnoreCase("red"))
			{
				manager.JoinRedTeam(p);
				sender.sendMessage(p.getName() + "が" + MessageVariables.Red + "チームに参加しました。");
				return;
			}else if(args[1].equalsIgnoreCase("blue"))
			{
				manager.JoinBlueTeam(p);
				sender.sendMessage(p.getName() + "が" +  MessageVariables.Blue + "チームに参加しました。");
				return;
			}
			sender.sendMessage(MessageVariables.InvalidTeamName);
			return;
		}else if(args.length  == 3)
		{
			Player p = Bukkit.getPlayerExact(args[1]);
			if(p == null || !p.isOnline())
			{
				sender.sendMessage(MessageVariables.InvalidPlayerName);
				return;
			}
			if(args[2].equalsIgnoreCase("red"))
			{
				manager.JoinRedTeam(p);
				sender.sendMessage(p.getName() + "が" + MessageVariables.Red + "チームに参加しました。");
				return;
			}else if(args[2].equalsIgnoreCase("blue"))
			{
				manager.JoinBlueTeam(p);
				sender.sendMessage(p.getName() + "が" +  MessageVariables.Blue + "チームに参加しました。");
				return;
			}
			sender.sendMessage(MessageVariables.InvalidTeamName);
			return;
		}else if(args.length > 3)
		{
			sender.sendMessage(MessageVariables.TooManyArgs);
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
					return;
				}else if(args[2].equalsIgnoreCase("blue"))
				{
					manager.JoinBlueTeam(p);
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
					return;
				}else if(args[2].equalsIgnoreCase("blue"))
				{
					manager.JoinBlueTeam(p);
					return;
				}
				return;
			}
	}
}