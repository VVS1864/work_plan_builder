package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.List;

import work_plan_builder.Production_type;

public class Level extends Box_storage{
	Production_type prod;
	List<Shelf> shelvs;
	
	public Level(Production_type prod, List<Shelf> shelvs) {
		this.prod = prod;
		this.shelvs = shelvs;
	}
	
	public int get_size() {
		int level_size = 0;
		for(Shelf s: shelvs) {
			level_size += s.get_size();
		}
		return level_size;
	}
	
	public int get_empty_boxes() {
		/*
		int empty_boxes = 0;
		for (Shelf s: shelvs) {
			empty_boxes+=s.get_empty_boxes();
		}
		*/
		return super.get_empty_boxes(shelvs);
		
	}

	public void put_boxes(List<Box>new_boxes) {
		super.put_boxes(new_boxes, shelvs);
		/*
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
		*/
		
	}
}
