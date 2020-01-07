package work_plan_builder.farm;

import java.util.List;
/**
 * Vertical or horizontal place for store boxes
 * @author vlad
 *
 */
public class Box_storage {
	
	
	protected int get_empty_boxes(List<Shelf> shelvs) {
		int empty_boxes = 0;
		for (Shelf s: shelvs) {
			empty_boxes+=s.get_empty_boxes();
		}
		return empty_boxes;
	}
	
	protected void put_boxes(List<Box>new_boxes, List<Shelf> shelvs) {
		int fixed_boxes = 0;
		for(Shelf sh: shelvs) {
			int empty = sh.get_empty_boxes();
			int unfixed_boxes = new_boxes.size() - fixed_boxes;
			while(unfixed_boxes<=empty) {
				Box box = new_boxes.get(fixed_boxes);
				sh.put_box(box);
				fixed_boxes++;
				unfixed_boxes--;
			}
			
		}
	}
}
	