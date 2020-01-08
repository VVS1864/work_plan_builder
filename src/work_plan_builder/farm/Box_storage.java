package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.List;
/**
 * Vertical or horizontal place for store boxes
 * @author vlad
 *
 */
public class Box_storage {
	
	
	protected int get_empty_boxes(List<Shelf> shelves) {
		int empty_boxes = 0;
		for (Shelf s: shelves) {
			if(s.is_empty()) {
				empty_boxes+=Shelf.shelf_size;
			}
		}
		return empty_boxes;
	}
	
	/**
	 * 
	 * @param new_boxes - boxes in quantity aliquot shelves(!!!)
	 * @param shelves
	 */
	protected void put_boxes(List<Box>new_boxes, List<Shelf> shelves) {
		int shelf_num = new_boxes.size()/Shelf.shelf_size;
		int size = Shelf.shelf_size;
		
		for(int i = 0; i<shelf_num-1; i++) {
			List<Box> boxes_part = new ArrayList<>();
			int start_list_index = i*size;
			int end_list_index = (i+1)*size;

			boxes_part = new_boxes.subList(start_list_index, end_list_index);
			for(Shelf sh: shelves) {
				if(sh.is_empty()) {
					sh.put_boxes(boxes_part);
					break;
				}
			}
			
			
		}
	}
}
	