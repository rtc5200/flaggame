package jp._RS_.FlagGame.Commands;

import org.bukkit.command.CommandSender;

public interface CommandBase_Methods {
	public void PerformFromPlayer(CommandSender sender,String[] args);
	public void PerformFromCommandBlock(CommandSender sender,String[] args);
	public void PerformFromConsole(CommandSender sender,String[] args);
}
