package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
	
	public static final int shelf_size = 18;
	protected List<Box> boxes;
	private boolean is_empty = true;
		
	public Shelf() {
		boxes = new ArrayList<>();
		make_boxes();
	}
	
	private void make_boxes() {
		for(int i = 0; i<shelf_size; i++) {
			boxes.add(new Box());
		}
		
	}	
	
	public boolean is_empty() {
		return is_empty;
	}
	
	public void put_boxes(List<Box> new_boxes) {
		if(boxes.size() == new_boxes.size()) {
			boxes = new_boxes;
			is_empty = false;
		}
		else {
			System.err.println("Put boxes error. List size of new boxes is not equal to shelf size");
		}
		
	}
}
