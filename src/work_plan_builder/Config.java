package work_plan_builder;

import java.io.File;
import java.io.IOException;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class Config {
	public static final String conf_name = "./Задание.ods";
	public Sheet sheet;
	
	public Config() {
		load_config(conf_name);
		
	}

	private void load_config(String config_name) {
		File f = new File(config_name);
		sheet = read_file(f);
		if (sheet == null) return;
		
	}

	private Sheet read_file(File f) {
		Sheet content = null;
		try {
			content = SpreadSheet.createFromFile(f).getSheet(0);
			System.out.println("Config loaded successfuly");
		} catch (IOException e) {
			System.err.println("Error load config! Config file " + f.getAbsolutePath() + " not found.");
			
		}
		return content;

	}
	
}
