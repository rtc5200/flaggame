package jp._RS_.FlagGame.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Scoreboard.SbManager;
import jp._RS_.FlagGame.Variables.CommandVariables;
import jp._RS_.FlagGame.Variables.MessageVariables;

public class Command_Quit extends CommandBase{
	private Main main;
	private SbManager manager;
	
	public Command_Quit(Main main,CommandSender sender,String[] args) {
		this.main = main;
		manager = main.getSbManager();
		if(sender instanceof Player)PerformFromPlayer(sender,args);
		if(sender instanceof BlockCommandSender)PerformFromCommandBlock(sender,args);
		if(sender instanceof ConsoleCommandSender)PerformFromConsole(sender,args);
	}

	@Override
	public void PerformFromPlayer(CommandSender sender,String[] args) {
		Player p = (Player)sender;
		if(!p.isOp() && !p.hasPermission(CommandVariables.Permission_Quit))
		{
			rejectExecute(sender,RejectReason.NotHavePermission);
			return;
		}
		if(args.length < 1)
		{
			rejectExecute(sender,RejectReason.NotEnoughArgs);
			return;
		}
		if(args.length == 1)
		{
			manager.Quit(p);
			sender.sendMessage(p.getName() + "がチームから脱退しました。"); 
			return;
		}
		if(args.length == 2)
		{
			Player p1 = Bukkit.getPlayerExact(args[1]);
			if(p1 == null || !p1.isOnline())
			{
				rejectExecute(sender,RejectReason.InvalidPlayerName);
				return;
			}
			manager.Quit(p1);
			sender.sendMessage(p1.getName() + "がチームから脱退しました。");
			return;
		}
		if(args.length > 2)
		{
			rejectExecute(sender,RejectReason.TooManyArgs);
			return;
		}
	}

	@Override
	public void PerformFromCommandBlock(CommandSender sender,String[] args) {
		if(args.length == 2)
		{
			Player p1 = Bukkit.getPlayerExact(args[1]);
			if(p1 == null || !p1.isOnline())
			{
				return;
			}
			manager.Quit(p1);
			return;
		}
	}

	@Override
	public void PerformFromConsole(CommandSender sender,String[] args) {
		if(args.length == 2)
		{
			Player p1 = Bukkit.getPlayerExact(args[1]);
			if(p1 == null || !p1.isOnline())
			{
				return;
			}
			manager.Quit(p1);
			return;
		}
		
	}

}
