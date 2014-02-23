package jp._RS_.FlagGame.Commands;

import jp._RS_.FlagGame.GameController;
import jp._RS_.FlagGame.GameStatus;
import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Variables.CommandVariables;
import jp._RS_.FlagGame.Variables.MessageVariables;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Command_Start implements CommandBase{
	private Main main;
	private GameController controller;
	public Command_Start(Main main,CommandSender sender,String[] args) {
		this.main = main;
		this.controller = main.getController();
		if(sender instanceof Player)PerformFromPlayer(sender,args);
		if(sender instanceof BlockCommandSender)PerformFromCommandBlock(sender,args);
		if(sender instanceof ConsoleCommandSender)PerformFromConsole(sender,args);
	}

	@Override
	public void PerformFromPlayer(CommandSender sender, String[] args) 
	{
		if(!sender.isOp() && !sender.hasPermission(CommandVariables.Permission_Join))
		{
			sender.sendMessage(MessageVariables.NotHavePermission);
			return;
		}
		if(!controller.getStatus().equals(GameStatus.READY))
		{
			sender.sendMessage("ゲームはすでに開始されています。");
			return;
		}
		controller.start();
	}

	@Override
	public void PerformFromCommandBlock(CommandSender sender, String[] args) {
		if(!controller.getStatus().equals(GameStatus.READY))
		{
			return;
		}
		controller.start();
		
	}

	@Override
	public void PerformFromConsole(CommandSender sender, String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
