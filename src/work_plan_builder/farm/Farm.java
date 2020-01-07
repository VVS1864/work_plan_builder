package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work_plan_builder.Production_type;
import work_plan_builder.plan_parts.Turn;

public class Farm {
	
	List<Stillage> stillages;
	HashMap<Production_type, List<Level>> levels = new HashMap<>();
	//Temporal hardcode
	private int stillages_num = 4;
	private int middle_num_per_stillage = 2;
	private int low_num_per_stillage = 1;
	private int high_num_per_stillage = 1;
	//Temporal hardcode
	
	public Farm() {
		stillages = new ArrayList<Stillage>();
		levels.put(Production_type.middle, new ArrayList<Level>());
		levels.put(Production_type.heavy, new ArrayList<Level>());
		levels.put(Production_type.light, new ArrayList<Level>());
		
		make_stillages();
		make_levels();
	}
	
	public int get_empty_boxes(Production_type prod) {
		int empty_boxes = 0;
		for (Stillage st: stillages) {
			empty_boxes+=st.get_empty_boxes(prod);
		}
		return empty_boxes;
	}
	
	public boolean put_boxes(int quantity, Production_type prod, Turn turn) {
		boolean successful = false;
		//try to put vertical (as columns)
		for (Stillage st: stillages) {
			if(quantity <= st.get_empty_boxes(prod)) {
				List<Box> new_boxes = make_boxes(quantity, turn);
				st.put_boxes(new_boxes, prod);
				successful = true;
				break;
			}	
			
		}
		
		//try to put horizontal (as rows)
		for (Level lv: levels.get(prod)) {
			if(quantity <= lv.get_empty_boxes()) {
				List<Box> new_boxes = make_boxes(quantity, turn);
				lv.put_boxes(new_boxes);
				successful = true;
				break;
			}
		}
		return successful;
	}
	
	private List<Box> make_boxes(int quantity, Turn turn){
		List<Box> new_boxes = new ArrayList<>();
		for(int i = 0; i < quantity; i++) {
			new_boxes.add(new Box(turn));
		}
		return new_boxes;
	}
	
	private void make_stillages() {
		for(int i = 0; i<stillages_num ; i++) {
			stillages.add(new Stillage());
		}
	}
	
	private void make_levels() {
		for(Production_type prod: levels.keySet()) {
			for( Stillage st: stillages) {
				List<Shelf> linked_shelvs = st.get_shelves(prod);
				levels.get(prod).add(new Level(prod, linked_shelvs));
			}
		}
	}
}
