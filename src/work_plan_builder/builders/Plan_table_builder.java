package work_plan_builder.builders;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.github.miachm.sods.Color;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import work_plan_builder.abstract_parts.Work_process;
import work_plan_builder.plan_parts.Turn;

public class Plan_table_builder extends Ods_builder{
	
	public Plan_table_builder(Turns turns){
		super(turns);
	}
	
	@Override
	protected void create_top() {
		sheet.getRange(0, 0).setValue("Техзадание");
		sheet.getRange(0, 1).setValue("Величина поставки");
		sheet.getRange(0, 2).setValue("Лотков");
		sheet.getRange(0, 3).setValue("Очередь");
		sheet.getRange(0, 4).setValue("Стадия");
	}

	/*
	private void create_dates(LocalDate start_work_date, int columns) {
		for(int column = init_table_column; column<columns; column++) {
			
			sheet.getRange(1, column).setValue(start_work_date.format(DateTimeFormatter.ofPattern("EEEEE, dd MMM")));
			start_work_date = start_work_date.plusDays(1);
		}
		
	}
*/
	private void set_cells(int start_column, int end_column, int current_row, Color c) {
		for(int i = start_column; i <= end_column; i++) {
			sheet.getRange(current_row, i).setBackgroundColor(c);
		}
		
	}

	@Override
	protected void set_initial_cell() {
		init_table_column = 5;
		init_table_row = 2;
		
	}

	@Override
	protected void filling_alg() {
		int current_row = init_table_row;
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
	    		
	    		System.out.println(t.get_production_type());
	    		System.out.println(proc.get_start_date() + " " +  proc.get_name());
	    		int start_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_start_date())+init_table_column;
	    		int end_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_end_date())+init_table_column;
	    		System.out.println(start_column);
	    		System.out.println(end_column);
	    		System.out.println(current_row);
	    		sheet.getRange(current_row, 4).setValue(proc.get_name());
	    		set_cells(start_column, end_column, current_row, c);
	    		current_row++;
	    		c = c2;
	    	}
	    	
	    }
		
	}

}
