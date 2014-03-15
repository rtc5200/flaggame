package jp._RS_.FlagGame.Config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ItemConfigFileWriter {

	public void save(File file,String string)
	{
		try {
			FileWriter fw = new FileWriter(file,true);
			fw.write(string);
			fw.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
