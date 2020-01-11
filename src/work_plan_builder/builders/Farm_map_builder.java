package work_plan_builder.builders;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.github.miachm.sods.Color;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import work_plan_builder.Production_type;
import work_plan_builder.calc.Statistics;
import work_plan_builder.farm.Box;
import work_plan_builder.farm.Farm;
import work_plan_builder.farm.Production_map;
import work_plan_builder.farm.Shelf;
import work_plan_builder.farm.Stillage;
import work_plan_builder.plan_parts.Turn;

public class Farm_map_builder{
	private Sheet sheet;

	public Farm_map_builder(Farm farm, Statistics stat, LocalDateTime day) {
		//Temporal hardcode
				
		int color_ind = 0;
		Color[] color_set = new Color[] {
				new Color(255, 255, 0),
				new Color(0, 255, 255),
				new Color(0, 255, 0),
				
				new Color(255, 0, 255),
				new Color(255, 0, 0),
				new Color(255, 100, 100),
				new Color(255, 255, 150),
						
						
		};
		//color_ind++;
		//if(color_ind>=color_set.length) color_ind =0;
		//Temporal hardcode
		
		
		
		put_boxes_to_farm(farm, stat, day);
		try {
			int rows = 4 + 50;
			int columns = Shelf.shelf_size + 50;
			sheet = new Sheet("A", rows, columns);

			Production_map pm = new Production_map();
			int current_row = 0;
			//System.out.println("ST     " + farm.get_stillages().size());
			for (Stillage st : farm.get_stillages()) {
				//System.out.println("prod     ");
				for (Production_type prod : pm.pm.keySet()) {
					//System.out.println("shelves     " + st.get_shelves(prod).size());
					for (Shelf sh : st.get_shelves(prod)) {
						int current_col = 0;
						for (Box b : sh.get_boxes()) {
							if(!b.is_empty()) {
								sheet.getRange(current_row, current_col).setBackgroundColor(b.c);
								sheet.getRange(current_row, current_col).setValue(b.get_turn().get_name());
							}
							sheet.getRange(current_row, current_col).setBackgroundColor(new Color(255, 255, 50));
							current_col++;
						}
						current_row++;
					}
					
				}
				current_row+=2;
			}

			SpreadSheet spread = new SpreadSheet();
			spread.appendSheet(sheet);
			spread.save(new File("Map.ods"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void put_boxes_to_farm(Farm farm, Statistics stat, LocalDateTime day) {
		for(Turn t: stat.stillage_calendar.get(day)) {
			Production_type prod = new Production_map().get_production_type(t.get_production_type());
			System.out.println(t.get_production_type() + " " + prod.toString());
			//farm.put_boxes(t.get_units_quantity(), t.get_production_type(), t);
		}
		
	}
}
