package work_plan_builder.builders;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import com.github.miachm.sods.Color;
import com.github.miachm.sods.Range;

import work_plan_builder.abstract_parts.Work_process;
import work_plan_builder.plan_parts.Turn;
import work_plan_builder.calc.Column_name;
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
		sheet.getRange(0, 0).setValue("Техзадание");
		sheet.getRange(0, 1).setValue("Величина поставки");
		sheet.getRange(0, 2).setValue("Лотков");
		sheet.getRange(0, 3).setValue("Очередь");
		sheet.getRange(0, 4).setValue("Стадия");
	}

	private void set_cells(int start_column, int end_column, int current_row, Color c, String text) {
		for(int i = start_column; i <= end_column; i++) {
			sheet.getRange(current_row, i).setBackgroundColor(c);
			sheet.getRange(current_row, i).setValue(text);
		}
		
	}
	
	private void create_stats() {
		LocalDateTime day = stat.get_start_day();
		//System.out.println(day);
		for(int col = init_table_column; col<columns; col++) {
			int chamber_units = stat.get_chamber_units(day);
			int stillage_units = stat.get_stillage_units(day);
			sheet.getRange(current_row, col).setValue(chamber_units);
			sheet.getRange(current_row+1, col).setValue(stillage_units);
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
	    	
			//create_turn_info(t);
	    	sheet.getRange(current_row, 0).setValue(t.get_production_type());
	    	sheet.getRange(current_row, 1).setValue(t.get_required_production());
	    	sheet.getRange(current_row, 2).setValue(t.get_units_quantity());
	    	sheet.getRange(current_row, 3).setValue(t.get_name());
	    	
	    	
	    	for(Work_process proc: t.get_list_of_operation()) {
	    		
	    		//System.out.println(t.get_production_type());
	    		//System.out.println(proc.get_start_date() + " " +  proc.get_name());
	    		int start_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_start_date())+init_table_column;
	    		int end_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_end_date())+init_table_column;
	    		//System.out.println(start_column);
	    		//System.out.println(end_column);
	    		//System.out.println(current_row);
	    		sheet.getRange(current_row, 4).setValue(proc.get_name());
	    		set_cells(start_column, end_column, current_row, c, t.get_name());
	    		current_row++;
	    		c = c2;
	    	}
	    	
	    }
		current_row++;
	}

}
