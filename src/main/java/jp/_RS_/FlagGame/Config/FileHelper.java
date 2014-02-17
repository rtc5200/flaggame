package jp._RS_.FlagGame.Config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class FileHelper {
	private File DataFolder;
	private Logger log;
	public FileHelper(Logger log,File DataFolder) {
		this.DataFolder = DataFolder;
		this.log = log;
		createFiles(ConfigVariables.MainConfigFile(DataFolder));
		createFiles(ConfigVariables.RedTeamConfigFile(DataFolder));
		createFiles(ConfigVariables.BlueTeamConfigFile(DataFolder));
		createFiles(ConfigVariables.FlagConfigFile(DataFolder));
	}
	public void createFiles(File file)
	{
		if(!DataFolder.exists())DataFolder.mkdirs();
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.warning("コンフィグファイル生成に失敗しました。");
				e.printStackTrace();
			}
		}
	}

}
