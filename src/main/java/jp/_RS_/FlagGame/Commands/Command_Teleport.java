package jp._RS_.FlagGame.Commands;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import jp._RS_.FlagGame.Main;
import jp._RS_.FlagGame.Scoreboard.SbManager;
import jp._RS_.FlagGame.Scoreboard.TeamTeleporter;
import jp._RS_.FlagGame.Variables.CommandVariables;
import jp._RS_.FlagGame.Variables.MessageVariables;

public class Command_Teleport extends CommandBase{
	private Main main;
	private SbManager manager;
	public Command_Teleport(Main main,CommandSender sender,String[] args) {
		this.main = main;
		manager = main.getSbManager();
		if(sender instanceof Player)PerformFromPlayer(sender,args);
		if(sender instanceof BlockCommandSender)PerformFromCommandBlock(sender,args);
		if(sender instanceof ConsoleCommandSender)PerformFromConsole(sender,args);
	}
	/*
	 * /fg teleport [red/blue]
	 * /fg teleport 0,0,0 [red/blue]
	 */
	@Override
	public void PerformFromPlayer(CommandSender sender,String[] args) {
		Player p = (Player)sender;
		if(!sender.isOp() && !sender.hasPermission(CommandVariables.Permission_Teleport))
		{
			sender.sendMessage(MessageVariables.NotHavePermission);
			return;
		}
		if(args.length < 2)
		{
			rejectExecute(sender,RejectReason.NotEnoughArgs);
		}
		if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase("red"))
			{
				Location loc = p.getLocation();
				new TeamTeleporter(manager.getRed()).Teleport(loc);
				sender.sendMessage(MessageVariables.Red + "チーム(" + manager.getRed().getPlayers().size() + "人)をテレポートしました。");
				return;
			}else if (args[1].equalsIgnoreCase("blue"))
			{
				Location loc = p.getLocation();
				new TeamTeleporter(manager.getBlue()).Teleport(loc);
				sender.sendMessage(MessageVariables.Blue + "チーム(" + manager.getBlue().getPlayers().size() + "人)をテレポートしました。");
				return;
			}else{
				rejectExecute(sender,RejectReason.InvalidTeamName);
				return;
			}
		}
		if(args.length == 3)
		{
			String[] lt = args[1].split(",");
			if(lt.length != 3)
			{
				rejectExecute(sender,RejectReason.InvalidLocation);
				return;
			}
			if(args[2].equalsIgnoreCase("red"))
			{
				new TeamTeleporter(manager.getRed()).Teleport(p.getWorld(),Integer.parseInt(lt[0]),Integer.parseInt(lt[1]),Integer.parseInt(lt[2]));
				sender.sendMessage(MessageVariables.Red + "チーム(" + manager.getRed().getPlayers().size() + "人)をテレポートしました。");
				return;
			}else if(args[2].equalsIgnoreCase("blue"))
			{
				new TeamTeleporter(manager.getBlue()).Teleport(p.getWorld(),Integer.parseInt(lt[0]),Integer.parseInt(lt[1]),Integer.parseInt(lt[2]));
				sender.sendMessage(MessageVariables.Blue + "チーム(" + manager.getBlue().getPlayers().size() + "人)をテレポートしました。");
				return;
			}else{
				rejectExecute(sender,RejectReason.InvalidTeamName);
				return;
			}
		}
		if(args.length > 3)
		{
			rejectExecute(sender,RejectReason.TooManyArgs);
			return;
		}
	}

	@Override
	public void PerformFromCommandBlock(CommandSender sender,String[] args) {
		Block b = ((BlockCommandSender)sender).getBlock();
		if(args.length == 2)
		{
			if(args[1].equalsIgnoreCase("red"))
			{
				Location loc = b.getLocation();
				new TeamTeleporter(manager.getRed()).Teleport(loc);
				return;
			}else if (args[1].equalsIgnoreCase("blue"))
			{
				Location loc = b.getLocation();
				new TeamTeleporter(manager.getBlue()).Teleport(loc);
				return;
			}else{
				return;
			}
		}
		if(args.length == 3)
		{
			String[] lt = args[1].split(",");
			if(lt.length != 3)
			{
				return;
			}
			if(args[2].equalsIgnoreCase("red"))
			{
				new TeamTeleporter(manager.getRed()).Teleport(b.getWorld(),Integer.parseInt(lt[0]),Integer.parseInt(lt[1]),Integer.parseInt(lt[2]));
				return;
			}else if(args[2].equalsIgnoreCase("blue"))
			{
				new TeamTeleporter(manager.getBlue()).Teleport(b.getWorld(),Integer.parseInt(lt[0]),Integer.parseInt(lt[1]),Integer.parseInt(lt[2]));
				return;
			}else{
				return;
			}
		}
		if(args.length > 3)
		{
			return;
		}	
	}
}
