package jp._RS_.FlagGame.Commands;

import jp._RS_.FlagGame.Variables.MessageVariables;

import org.bukkit.command.CommandSender;

public abstract class CommandBase implements CommandBase_Methods{
	@Override
	public void PerformFromConsole(CommandSender sender,String[] args)
	{
		rejectExecute(sender,RejectReason.NotSupported);
	}
	@Override
	public void PerformFromCommandBlock(CommandSender sender,String[] args)
	{
		rejectExecute(sender,RejectReason.NotSupported);
	}
	public void rejectExecute(CommandSender sender,RejectReason reason)
	{
		if(reason.equals(RejectReason.NotSupported))sender.sendMessage(MessageVariables.NotSupported);
		if(reason.equals(RejectReason.NotHavePermission))sender.sendMessage(MessageVariables.NotHavePermission);
		if(reason.equals(RejectReason.Unknown))sender.sendMessage(MessageVariables.UnknownError);
		if(reason.equals(RejectReason.NotEnoughArgs))sender.sendMessage(MessageVariables.NotEnoughArgs);
		if(reason.equals(RejectReason.TooManyArgs))sender.sendMessage(MessageVariables.TooManyArgs);
		if(reason.equals(RejectReason.InvalidTeamName))sender.sendMessage(MessageVariables.InvalidTeamName);
		if(reason.equals(RejectReason.InvalidPlayerName))sender.sendMessage(MessageVariables.InvalidPlayerName);
		if(reason.equals(RejectReason.AlreadyStarted))sender.sendMessage(MessageVariables.AlreadyStarted);
		if(reason.equals(RejectReason.InvalidLocation))sender.sendMessage(MessageVariables.InvalidLocation);
	}
}
