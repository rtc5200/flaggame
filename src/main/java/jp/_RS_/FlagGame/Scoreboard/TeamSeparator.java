package jp._RS_.FlagGame.Scoreboard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class TeamSeparator {
	private Team red;
	private Team blue;
	private SbManager manager;
	public TeamSeparator(SbManager manager) {
		this.manager = manager;
		this.red = manager.getRed();
		this.blue = manager.getBlue();
	}
	public HashMap<Player, Team> separate(Player[] players)
	{
		HashMap<Player,Team> result = new HashMap<Player,Team>();
		ArrayList<Player> pls = new ArrayList<Player>();
		for(Player p : players)
		{
			pls.add(p);
		}
		Collections.shuffle(pls);
		for(int i = 0;i < pls.size();i++)
		{
			if(i%2 == 0)manager.JoinRedTeam(pls.get(i));
			else manager.JoinBlueTeam(pls.get(i));
			
			pls.get(i).playSound(pls.get(i).getLocation(), Sound.LEVEL_UP, 100, 1);
		}
		return result;
	}
	/**
     * 配列を指定サイズで分割します。分割された配列は List (ArrayList) に格納して戻します。
     * <p>
     * 例)
     * <pre>
     * String[] array = new Foo[] { "1", "2", "3", "4", "5", "6", "7" };
     * List&lt;String[]&gt; result = Util.splitArray(array, 3, String.class);
     *
     * ==&gt; [ {"1", "2", "3"}, {"4", "5", "6"}, ("7"} ]
     * </pre>
     *
     * @param <T> 分割元配列の型
     * @param array 分割元となる配列
     * @param size 分割サイズ
     * @param arrayClass 分割後配列の型
     * @return 分割された配列のリスト
     */
    public static <T> List<T[]> splitArray(T[] array, int size, Class<?> arrayClass) {
        List<T[]> list = new ArrayList<T[]>();
        for (int i = 0; i < array.length; i += size) {
            int subArrayLength = (array.length - i > size) ? size : (array.length - i);
            T[] subArray = newArrayInstance(arrayClass, subArrayLength);
            System.arraycopy(array, i, subArray, 0, subArrayLength);
            list.add(subArray);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] newArrayInstance(Class<?> arrayClass, int subArrayLength) {
        return (T[]) Array.newInstance(arrayClass, subArrayLength);
    }

}
