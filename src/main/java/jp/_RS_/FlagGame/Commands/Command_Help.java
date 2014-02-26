package jp._RS_.FlagGame.Commands;

import jp._RS_.FlagGame.Variables.CommandVariables;
import jp._RS_.FlagGame.Variables.MessageVariables;

import org.bukkit.command.CommandSender;

public class Command_Help extends CommandBase{
	@Override
	public void PerformFromPlayer(CommandSender sender, String[] args) {
		if(!sender.isOp() && !sender.hasPermission(CommandVariables.Permission_Help))
		{
			rejectExecute(sender,RejectReason.NotHavePermission);
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
