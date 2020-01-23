package work_plan_builder.builders;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.awt.Color;
import work_plan_builder.abstract_parts.Work_process;
import work_plan_builder.plan_parts.Turn;
import work_plan_builder.calc.Statistics;

public class Plan_table_builder extends Ods_builder{
	
	Statistics stat;
	int current_row;
	public Plan_table_builder(Turns turns, Statistics stat){
		super(turns, "Plan Table");
		this.stat = stat;
		create_stats();
		create_sheet();
	}
	
	@Override
	protected void create_top() {		
		sheet.setValueAt("Техзадание", 0, 0);
		sheet.setValueAt("Величина поставки", 1, 0);
		sheet.setValueAt("Лотков", 2, 0);
		sheet.setValueAt("Очередь", 3, 0);
		sheet.setValueAt("Стадия", 4, 0);
	}

	private void set_cells(int start_column, int end_column, int current_row, Color c, String text) {
		for(int i = start_column; i <= end_column; i++) {
			sheet.getCellAt(i, current_row).setBackgroundColor(c);
			sheet.setValueAt(text, i, current_row);
			
		}
		
	}
	
	private void create_stats() {
		LocalDateTime day = stat.get_start_day();
		
		sheet.setValueAt("Камера проращивания", init_table_column-1, current_row);
		sheet.setValueAt("Аэропоника",  init_table_column-1, current_row+1);
		sheet.setValueAt("Всего",  init_table_column-1, current_row+2);
		for(int col = init_table_column; col<columns; col++) {
			int chamber_units = stat.get_chamber_units(day);
			int stillage_units = stat.get_stillage_units(day);
			int total_units = chamber_units+stillage_units;
			
			sheet.setValueAt(chamber_units, col, current_row);
			sheet.setValueAt(stillage_units, col, current_row+1);
			sheet.setValueAt(total_units, col, current_row+2);
			day = day.plusDays(1);
		}
	}

	@Override
	protected void set_initial_cell() {
		init_table_column = 5;
		init_table_row = 2;
		
	}

	@Override
	protected void filling_alg() {
		current_row = init_table_row;
		Color c1 = new Color(255, 255, 0);
		Color c2 = new Color(255, 0, 0);
		for (Turn t: turns.get_turns()) {
	    	Color c = c1;    	
	    	sheet.setValueAt(t.get_production_type(), 0, current_row);
	    	sheet.setValueAt(t.get_required_production(), 1, current_row);
	    	sheet.setValueAt(t.get_units_quantity(), 2, current_row);
	    	sheet.setValueAt(t.get_name(), 3, current_row);
	    	
	    	for(Work_process proc: t.get_list_of_operation()) {   		
	    		int start_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_start_date())+init_table_column;
	    		int end_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_end_date())+init_table_column;
	    		
	    		sheet.setValueAt(proc.get_name(), 4, current_row);
	    		set_cells(start_column, end_column, current_row, c, t.get_name());
	    		current_row++;
	    		c = c2;
	    	}
	    	
	    }
		current_row++;
	}

}
