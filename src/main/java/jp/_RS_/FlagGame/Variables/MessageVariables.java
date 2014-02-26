package jp._RS_.FlagGame.Variables;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class MessageVariables {
	//-------
	public static String NotHavePermission = ChatColor.RED + "権限設定を確認してください。" + ChatColor.RESET;
	public static String NotEnoughArgs = ChatColor.RED + "引数が足りません。" + ChatColor.RESET;
	public static String InvalidPlayerName = ChatColor.RED + "プレイヤー名が不正です。" + ChatColor.RESET;
	public static String InvalidTeamName = ChatColor.RED + "チーム名が不正です。" + ChatColor.RESET;
	public static String TooManyArgs = ChatColor.RED + "引数が多すぎます。" + ChatColor.RESET;
	public static String InvalidLocation = ChatColor.RED + "座標が不正です。" + ChatColor.RESET;
	public static String NotSupported = ChatColor.RED + "対応していません。" + ChatColor.RESET;
	public static String UnknownError = ChatColor.RED + "不明なエラーが発生しました。" + ChatColor.RESET;
	public static String AlreadyStarted = ChatColor.RED + "すでにゲームが開始されています。" + ChatColor.RESET;
	public static List<String> getHelpMessageList()
	{
		ArrayList<String> result = new ArrayList<String>();
		result.add("/fg join [red/blue]                   -        赤/青チームに参加します。");
		result.add("/fg quit                                     -    チームから離脱します。");
		result.add("/fg start                                    -    ゲームを開始します。");
		result.add("/fg tele [red/blue]                   -    赤/青チームメンバーを自分のいる場所へテレポートします。");
		result.add("/fg tele <x,y,z> [red/blue]     -    赤/青チームメンバーを指定座標へテレポートします。");
		result.add("/fg setrp [red/blue]                 -    自分のいる場所を赤/青チームのリスポーンポイントに設定します。");
		result.add("/fg af                                         -    自分のいる場所のブロックをフラッグとして追加します。");
		
		return result;
	}
	//-------
	public static String Red = ChatColor.RED + "赤" + ChatColor.RESET;
	public static String Blue = ChatColor.BLUE + "青" + ChatColor.RESET;
}
