package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
	private List<Box> boxes;
	private boolean b;
	
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
	
	public void put_box(Box new_box) {
		for(int i = 0; i<boxes.size(); i++) {
			if(boxes.get(i).is_empty) boxes.set(i, new_box);
		}
		
	}
}
