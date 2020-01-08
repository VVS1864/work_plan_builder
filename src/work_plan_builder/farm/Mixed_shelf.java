package work_plan_builder.farm;

public class Mixed_shelf extends Shelf{
	
	
	
	public int get_empty_boxes() {
		int empty_boxes = 0;
		for(Box box:boxes) {
			if(box.is_empty()) empty_boxes += 1;
			}
		
		return empty_boxes;
	}
	
	public void put_box(Box new_box) {
		for(int i = 0; i<boxes.size(); i++) {
			if(boxes.get(i).is_empty()) boxes.set(i, new_box);
		}
		
	}
}
