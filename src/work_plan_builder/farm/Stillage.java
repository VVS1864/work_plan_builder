package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work_plan_builder.Production_type;

public class Stillage {
	HashMap<Production_type, List<Shelf>> stillage_shelvs = new HashMap<>();
			/*
			new ArrayList<>{
		new ArrayList<>shelvs_middle,
		new ArrayList<>shelvs_low,
		new ArrayList<>shelvs_high
	}*/
			
	public Stillage() {
		stillage_shelvs.put(Production_type.middle, new ArrayList<Shelf>());
		stillage_shelvs.put(Production_type.heavy, new ArrayList<Shelf>());
		stillage_shelvs.put(Production_type.light, new ArrayList<Shelf>());

		
	}
	
	public int get_size() {
		int stillage_size = 0;
		for(List<Shelf> list: stillage_shelvs.values()) {
			stillage_size += get_shelvs_size(list);
		}
		
		return stillage_size;
		
	}
	
	private int get_shelvs_size(List<Shelf> shelvs) {
		int shelf_size = 0;
		for (Shelf s: shelvs) {
			shelf_size+=s.get_size();
		}
		return shelf_size;
	}
	
	public int get_empty_boxes(Production_type prod) {
		int empty_boxes = 0;
		List<Shelf> list_shelvs = stillage_shelvs.get(prod);
		for (Shelf s: list_shelvs) {
			empty_boxes+=s.get_empty_boxes();
		}
		return empty_boxes;
	}
}
