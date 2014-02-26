package jp._RS_.FlagGame.Commands;

import jp._RS_.FlagGame.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FGCommandExecutor implements CommandExecutor {
	private Main main;
	public FGCommandExecutor(Main main) {
		this.main = main;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length == 0)
		{
			return false;
		}
		if(args[0].equalsIgnoreCase("join"))
		{
			new Command_Join(main,sender,args);
			return true;
		}else if(args[0].equalsIgnoreCase("quit"))
		{
			new Command_Quit(main,sender,args);
			return true;
		}else if(args[0].equalsIgnoreCase("tele"))
		{
			new Command_Teleport(main,sender,args);
			return true;
		}else if(args[0].equalsIgnoreCase("setrp"))
		{
			new Command_SetRespawnPoint(main,sender,args);
			return true;
		}else if(args[0].equalsIgnoreCase("start"))
		{
			new Command_Start(main,sender,args);
			return true;
		}else if(args[0].equalsIgnoreCase("af"))
		{
			new Command_AddFlag(main,sender,args);
			return true;
		}else if(args[0].equalsIgnoreCase("help"))
		{
			new Command_Help(sender,args);
			return true;
		}
		return false;
	}
	/*private boolean canExecute(CommandSender sender,String permission)
	{
		if(sender instanceof BlockCommandSender)return true;
		if(sender instanceof ConsoleCommandSender)return false;
		if(sender instanceof Player && sender.hasPermission(permission) || sender.isOp())return true;
		return false;	
	}*/

}
