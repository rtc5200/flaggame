package jp._RS_.FlagGame.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreManager {
	private SbManager manager;
	private Scoreboard sb;
	private Objective ob;
	private OfflinePlayer reds;
	private OfflinePlayer blues;
	public ScoreManager(SbManager manager) {
		this.manager = manager;
		sb = manager.getScoreboard();
	}
	public void register()
	{
		ob = sb.registerNewObjective("チームスコア", "dummy");
		ob.setDisplaySlot(DisplaySlot.SIDEBAR);
		ob.setDisplayName("チームスコア");
		reds = Bukkit.getOfflinePlayer(ChatColor.RED + "赤");
		blues = Bukkit.getOfflinePlayer(ChatColor.BLUE + "青");
		ob.getScore(reds).setScore(0);
		ob.getScore(blues).setScore(0);
	}
	public void RedTeam_AddScore(int s)
	{
		int i = ob.getScore(reds).getScore();
		ob.getScore(reds).setScore(i + s);
	}
	public void BlueTeam_AddScore(int s)
	{
		int i = ob.getScore(blues).getScore();
		ob.getScore(blues).setScore(i + s);
	}
	public void resetScores()
	{
		ob.getScore(reds).setScore(0);
		ob.getScore(blues).setScore(0);
	}
	public Integer RedTeam_getScore()
	{
		return ob.getScore(reds).getScore();
	}
	public Integer BlueTeam_getScore()
	{
		return ob.getScore(blues).getScore();
	}

}
