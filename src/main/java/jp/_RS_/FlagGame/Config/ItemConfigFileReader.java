package jp._RS_.FlagGame.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Bukkit;

public class ItemConfigFileReader {

	public static String read(File file) {
		String result = "";
		try {
			if(!file.exists())
			{
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				fw.write("<268,19,2>");
				fw.close();
				Bukkit.getLogger().info("[FlagGame] [" + file.getName() + "]生成しました。");
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str;
			while((str = br.readLine()) != null)
			{
				result += str;
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
