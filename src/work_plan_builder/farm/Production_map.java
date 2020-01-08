package work_plan_builder.farm;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import work_plan_builder.Production_type;
public class Production_map {
	public LinkedHashMap<Production_type, String[]> pm = new LinkedHashMap<>(); 
	
	public Production_map() {
		String[] low = new String[] {"Подсолнечник", "Горох"};
		String[] mid = {"Мизуна", "Горчица", "Редис"};
		String[] high = {"Базилик микро", "Подсолнечник", "Горох"};
		pm.put(Production_type.light, low);
		pm.put(Production_type.middle, mid);
		pm.put(Production_type.heavy, high);
	}
}
