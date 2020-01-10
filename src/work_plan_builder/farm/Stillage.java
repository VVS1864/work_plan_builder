package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work_plan_builder.Production_type;

public class Stillage extends Box_storage{
	
	HashMap<Production_type, List<Shelf>> stillage_shelves = new HashMap<Production_type, List<Shelf>>();
	//Temporal hardcode
	private int middle_num_per_stillage = 2;
	private int low_num_per_stillage = 1;
	private int high_num_per_stillage = 1;
	//Temporal hardcode
			/*
			new ArrayList<>{
		new ArrayList<>shelves_middle,
		new ArrayList<>shelves_low,
		new ArrayList<>shelves_high
	}*/
			
	public Stillage() {
		stillage_shelves.put(Production_type.middle, new ArrayList<Shelf>());
		stillage_shelves.put(Production_type.heavy, new ArrayList<Shelf>());
		stillage_shelves.put(Production_type.light, new ArrayList<Shelf>());

		make_shelves();
	}
	
	private void make_shelves() {
		for(Production_type prod: stillage_shelves.keySet()) {
			List<Shelf> shs = stillage_shelves.get(prod);	
			int num = 0;
			switch(prod) {
				case light: num = 1; break;
				case heavy: num = 1; break;
				case middle: num = 2; break;
			}
			for(int i=0; i<num; i++) {
				shs.add(new Shelf());
			}
			
			
				//List<Shelf> linked_shelves = st.get_shelves(prod);
				//levels.get(prod).add(new Level(prod, linked_shelves));
			
		}		
				
	}

	public int get_size() {
		int stillage_size = 0;
		for(List<Shelf> list: stillage_shelves.values()) {
			stillage_size += get_shelves_size(list);
		}
		
		return stillage_size;
		
	}
	
	private int get_shelves_size(List<Shelf> shelves) {
		int shelf_size = 0;
		for (Shelf s: shelves) {
			shelf_size+=Shelf.shelf_size;
		}
		return shelf_size;
	}
	
	public int get_empty_boxes(Production_type prod) {
		
		/*
		int empty_boxes = 0;
		List<Shelf> list_shelves = stillage_shelves.get(prod);
		for (Shelf s: list_shelves) {
			empty_boxes+=s.get_empty_boxes();
		}
		*/
		return super.get_empty_boxes(stillage_shelves.get(prod));
	}

	public void put_boxes(List<Box> new_boxes, Production_type prod) {
		super.put_boxes(new_boxes, stillage_shelves.get(prod));
		/*
		int fixed_boxes = 0;
		for(Shelf sh: stillage_shelves.get(prod)) {
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
		List<Shelf> ret_shelves = new ArrayList<>();
		for(Shelf sh: stillage_shelves.get(prod)) {
			ret_shelves.add(sh);
		}
		return ret_shelves;
	}
	
}
