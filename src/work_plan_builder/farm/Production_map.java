package work_plan_builder.farm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import work_plan_builder.Production_type;
public class Production_map {
	public LinkedHashMap<Production_type, List<String>> pm = new LinkedHashMap<>(); 
	
	public Production_map() {
		List<String> low = new ArrayList<>();
		low.add("Подсолнечник");
		low.add("Горох");
		List<String> mid = new ArrayList<>();
		mid.add("Мизуна");
		mid.add("Горчица");
		mid.add("Редис");
		List<String> high = new ArrayList<>();
		high.add("Базилик");
		high.add("Горох");
		
		pm.put(Production_type.heavy, low);
		pm.put(Production_type.middle, mid);
		pm.put(Production_type.light, high);
	}
	
	public Production_type get_production_type(String prod_name) {
		Production_type prod = Production_type.middle;
		for(Production_type p: pm.keySet()) {
			if(pm.get(p).contains(prod_name)) {
				prod = p;
				break;
			}
		}
		return prod;
	}
}
