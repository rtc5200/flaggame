package jp._RS_.FlagGame.Commands;

import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Config.TeamConfigHandler;
import jp._RS_.FlagGame.Variables.CommandVariables;
import jp._RS_.FlagGame.Variables.MessageVariables;

public class Command_SetRespawnPoint implements CommandBase {
	private CommandSender sender;
	private String[] args;
	private Main main;
	private TeamConfigHandler rtconfig;
	private TeamConfigHandler btconfig;
	public Command_SetRespawnPoint(Main main,CommandSender sender,String[] args) {
		this.main = main;
		this.sender =sender;
		this.args = args;
		rtconfig = main.getConfigHandler().getRedTeamConfig();
		btconfig = main.getConfigHandler().getBlueTeamConfig();
		if(sender instanceof Player)PerformFromPlayer(sender,args);
		if(sender instanceof BlockCommandSender)PerformFromCommandBlock(sender,args);
		if(sender instanceof ConsoleCommandSender)PerformFromConsole(sender,args);
	}
	/*
	 *  /fg setrp [red/blue]
	 */
	@Override
	public void PerformFromPlayer(CommandSender sender,String[] args) {
		Player p = (Player)sender;
		if(!sender.isOp() && !sender.hasPermission(CommandVariables.Permission_SetRespawnPoint))
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
			if(args[1].equalsIgnoreCase("red"))
			{
				Location loc = p.getLocation();
				rtconfig.setRespawnLocation(loc);
				sender.sendMessage(MessageVariables.Red + "チームのリスポーン地点を設定しました。");
				return;
			}else if(args[1].equalsIgnoreCase("blue"))
			{
				Location loc = p.getLocation();
				btconfig.setRespawnLocation(loc);
				sender.sendMessage(MessageVariables.Blue + "チームのリスポーン地点を設定しました。"); 
				return;
			}else{
				sender.sendMessage(MessageVariables.InvalidTeamName);
				return;
			}
		}
		sender.sendMessage(MessageVariables.TooManyArgs);
		return;
	}

	@Override
	public void PerformFromCommandBlock(CommandSender sender,String[] args) {
		
		
	}

	@Override
	public void PerformFromConsole(CommandSender sender,String[] args) {
		
	}

}
