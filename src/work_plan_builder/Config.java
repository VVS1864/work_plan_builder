package work_plan_builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class Config {
	String config_path;
	JSONObject config;
	
	
	//private Properties config = new Properties();

	public Config() {
		load_config("config.txt");
		
	}
	/*
	public String get_conf_value(int task_ind, String value_name) {
		JSONArray tasks = config.getJSONArray("Tasks");
		JSONObject task = tasks.getJSONObject(task_ind);
		if (task.has(value_name)) {
			// System.out.println(value_name + " " + config.getProperty(value_name));
			Object jsonObj = task.get(value_name);
			if(jsonObj instanceof JSONObject || jsonObj instanceof JSONArray) {
				System.err.println("Get value error: is composite value '" + value_name + "'");
				return null;
			}
			
			return task.getString(value_name);
		} else {
			System.err.println("Get value error: config has not setting '" + value_name + "'");
			return null;
		}
}
	
*/

	private void load_config(String config_name) {
		// First try load from config in jar directory
		File jarDir = new File(Paths.get("").toAbsolutePath().normalize().toString());
		// File jarDir = new
		// File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		config_path = jarDir.getAbsolutePath();

		File f = new File(jarDir, config_name);
		//InputStream stream = new FileInputStream(conf);
		String file_string = read_file(f.getAbsolutePath());
		config = new JSONObject(file_string);
		
	}

	private String read_file(String filePath) {
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			System.err.println("Error load config! Config file " + filePath + " not found.");
			
		}
		return content;

	}
	/*
	public String get_string() {
		List<String> list_s = new ArrayList<>();
		int task_ind = 0;
		for (Object task : config.getJSONArray("Tasks")) {
			JSONObject jtask = (JSONObject) task;
			for(String ss: jtask.keySet()) {
				//System.out.println(ss);
				String value = get_conf_value(task_ind, ss);
				list_s.add(String.join(" ", ss, value));
			}		
			task_ind++;
		}
		return String.join("\n", list_s);
	}
	 */
}
