package jp._RS_.FlagGame.Commands;

import jp._RS_.FlagGame.Variables.MessageVariables;

import org.bukkit.command.CommandSender;

public abstract class CommandBase implements CommandBase_Methods{
	@Override
	public void PerformFromConsole(CommandSender sender,String[] args)
	{
		rejectExecute(sender,RejectedReason.NotSupported);
	}
	@Override
	public void PerformFromCommandBlock(CommandSender sender,String[] args)
	{
		rejectExecute(sender,RejectedReason.NotSupported);
	}
	public void rejectExecute(CommandSender sender,RejectedReason reason)
	{
		if(reason.equals(RejectedReason.NotSupported))sender.sendMessage(MessageVariables.NotSupported);
		if(reason.equals(RejectedReason.NotHavePermission))sender.sendMessage(MessageVariables.NotHavePermission);
		if(reason.equals(RejectedReason.Unknown))sender.sendMessage(MessageVariables.UnknownError);
		if(reason.equals(RejectedReason.NotEnoughArgs))sender.sendMessage(MessageVariables.NotEnoughArgs);
		if(reason.equals(RejectedReason.TooManyArgs))sender.sendMessage(MessageVariables.TooManyArgs);
		if(reason.equals(RejectedReason.InvalidTeamName))sender.sendMessage(MessageVariables.InvalidTeamName);
		if(reason.equals(RejectedReason.InvalidPlayerName))sender.sendMessage(MessageVariables.InvalidPlayerName);
		if(reason.equals(RejectedReason.AlreadyStarted))sender.sendMessage(MessageVariables.AlreadyStarted);
		if(reason.equals(RejectedReason.InvalidLocation))sender.sendMessage(MessageVariables.InvalidLocation);
	}
}
