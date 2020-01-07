package work_plan_builder.farm;

import java.util.ArrayList;
import java.util.List;

import work_plan_builder.Production_type;

public class Farm {
	
	List<Stillage> stillages;
	public Farm() {
		stillages = new ArrayList<Stillage>();
	}
	
	public int get_empty_boxes(Production_type prod) {
		int empty_boxes = 0;
		for (Stillage st: stillages) {
			empty_boxes+=st.get_empty_boxes(prod);
		}
		return empty_boxes;
	}
}
