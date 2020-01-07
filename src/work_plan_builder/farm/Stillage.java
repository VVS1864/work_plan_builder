package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work_plan_builder.Production_type;

public class Stillage extends Box_storage{
	HashMap<Production_type, List<Shelf>> stillage_shelvs = new HashMap<Production_type, List<Shelf>>();
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

		make_shelvs();
	}
	
	private void make_shelvs() {
		for(Production_type prod: stillage_shelvs.keySet()) {
			List<Shelf> shs = stillage_shelvs.get(prod);			
			shs.add(new Shelf());
				//List<Shelf> linked_shelvs = st.get_shelves(prod);
				//levels.get(prod).add(new Level(prod, linked_shelvs));
			
		}		
				
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
		
		/*
		int empty_boxes = 0;
		List<Shelf> list_shelvs = stillage_shelvs.get(prod);
		for (Shelf s: list_shelvs) {
			empty_boxes+=s.get_empty_boxes();
		}
		*/
		return super.get_empty_boxes(stillage_shelvs.get(prod));
	}

	public void put_boxes(List<Box> new_boxes, Production_type prod) {
		super.put_boxes(new_boxes, stillage_shelvs.get(prod));
		/*
		int fixed_boxes = 0;
		for(Shelf sh: stillage_shelvs.get(prod)) {
			int empty = sh.get_empty_boxes();
			int unfixed_boxes = new_boxes.size() - fixed_boxes;
			while(unfixed_boxes<=empty) {
				Box box = new_boxes.get(fixed_boxes);
				sh.put_box(box);
				fixed_boxes++;
				unfixed_boxes--;
			}
			
		}
		*/
	}
	
	public List<Shelf> get_shelves(Production_type prod){
		List<Shelf> ret_shelvs = new ArrayList<>();
		for(Shelf sh: stillage_shelvs.get(prod)) {
			ret_shelvs.add(sh);
		}
		return ret_shelvs;
	}
	
	

}
