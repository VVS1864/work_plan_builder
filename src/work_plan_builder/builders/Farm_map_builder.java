package work_plan_builder.builders;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.miachm.sods.Color;
import com.github.miachm.sods.Range;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import work_plan_builder.Production_type;
import work_plan_builder.farm.Box;
import work_plan_builder.farm.Farm;
import work_plan_builder.farm.Production_map;
import work_plan_builder.farm.Shelf;
import work_plan_builder.farm.Stillage;

public class Farm_map_builder {
	private Sheet sheet;

	public Farm_map_builder(Farm farm) {
		try {
			int rows = 4 + 50;
			int columns = Shelf.shelf_size + 50;
			sheet = new Sheet("A", rows, columns);

			Production_map pm = new Production_map();
			int current_row = 0;
			System.out.println("ST     " + farm.get_stillages().size());
			for (Stillage st : farm.get_stillages()) {
				System.out.println("prod     ");
				for (Production_type prod : pm.pm.keySet()) {
					System.out.println("shelves     " + st.get_shelves(prod).size());
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
}
