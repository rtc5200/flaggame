package jp._RS_.FlagGame.Commands;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Config.FlagConfigHandler;
import jp._RS_.FlagGame.Scoreboard.SbManager;
import jp._RS_.FlagGame.Variables.CommandVariables;
import jp._RS_.FlagGame.Variables.MessageVariables;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Command_AddFlag extends CommandBase{
	private Main main;
	private SbManager manager;
	private FlagConfigHandler config;
	public Command_AddFlag(Main main,CommandSender sender,String[] args) {
		manager = main.getSbManager();
		config = main.getConfigHandler().getFlagConfig();
		if(sender instanceof Player)PerformFromPlayer(sender,args);
		if(sender instanceof BlockCommandSender)PerformFromCommandBlock(sender,args);
		if(sender instanceof ConsoleCommandSender)PerformFromConsole(sender,args);
	}

	@Override
	public void PerformFromPlayer(CommandSender sender, String[] args) {
		Player p = (Player)sender;
		if(!sender.isOp() && !sender.hasPermission(CommandVariables.Permission_AddFlag))
		{
			rejectExecute(sender,RejectedReason.NotHavePermission);
			return;
		}
		config.AddFlagLocation(p.getLocation());
		sender.sendMessage("保存しました。");
	}
}
