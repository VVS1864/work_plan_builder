package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
	List<Box> boxes;
	
	public Shelf() {
		boxes = new ArrayList<>();
	}
	
	public int get_size() {
		return boxes.size();
	}

	public int get_empty_boxes() {
		int empty_boxes = 0;
		for(Box box:boxes) {
			if(box.is_empty) empty_boxes += 1;
			}
		
		return empty_boxes;
	}
}
