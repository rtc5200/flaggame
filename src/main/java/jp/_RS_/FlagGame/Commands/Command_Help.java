package jp._RS_.FlagGame.Commands;

import jp._RS_.FlagGame.Variables.CommandVariables;
import jp._RS_.FlagGame.Variables.MessageVariables;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Command_Help extends CommandBase{
	public Command_Help(CommandSender sender,String[] args)
	{
		if(sender instanceof Player)PerformFromPlayer(sender,args);
		if(sender instanceof BlockCommandSender)PerformFromCommandBlock(sender,args);
		if(sender instanceof ConsoleCommandSender)PerformFromConsole(sender,args);
	}
	@Override
	public void PerformFromPlayer(CommandSender sender, String[] args) {
		if(!sender.isOp() && !sender.hasPermission(CommandVariables.Permission_Help))
		{
			rejectExecute(sender,RejectedReason.NotHavePermission);
			return;
		}
		for(String s : MessageVariables.getHelpMessageList())
		{
			sender.sendMessage(s);
		}
	}
	@Override
	public void PerformFromConsole(CommandSender sender,String[] args)
	{
		for(String s : MessageVariables.getHelpMessageList())
		{
			sender.sendMessage(s);
		}
	}

}
