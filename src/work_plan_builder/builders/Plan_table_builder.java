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

public class Plan_table_builder {
	private Sheet sheet;
	
	public Plan_table_builder(Turns turns){
		try {
			int rows = turns.get_prosesses_quantity()+3;
		    int columns = turns.get_work_period()+3;
		    //System.out.println("rows: " +  rows);
		    //System.out.println("columns: " +  columns);
		    sheet = new Sheet("A", rows, columns);
		    List<Turn> turns_list = turns.get_turns();
		    int current_row = 1; 
		    Color c1 = new Color(255, 255, 0);
		    Color c2 = new Color(255, 0, 0);
		    create_dates(turns.get_start_work_date(), columns);
		    for (Turn t: turns_list) {
		    	Color c = c1;
		    	for(Work_process proc: t.get_list_of_operation()) {
		    		
		    		System.out.println(t.get_production_type());
		    		System.out.println(proc.get_start_date() + " " +  proc.get_name());
		    		int start_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_start_date());
		    		int end_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_end_date());
		    		System.out.println(start_column);
		    		System.out.println(end_column);
		    		System.out.println(current_row);
		    		
		    		set_cells(start_column, end_column, current_row, c);
		    		current_row++;
		    		c = c2;
		    	}
		    	
		    }
		    //sheet.getRange(2, 2).setBackgroundColor(new Color(255, 255, 0));
		    SpreadSheet spread = new SpreadSheet();
		    spread.appendSheet(sheet);
		    spread.save(new File("Out.ods"));
		}
		catch (IOException e){
            e.printStackTrace();
		}
	}

	private void create_dates(LocalDate start_work_date, int columns) {
		for(int column = 0; column<columns; column++) {
			
			sheet.getRange(0, column).setValue(start_work_date.format(DateTimeFormatter.ofPattern("EEEEE, dd MMMM")));
			start_work_date = start_work_date.plusDays(1);
		}
		
	}

	private void set_cells(int start_column, int end_column, int current_row, Color c) {
		for(int i = start_column; i <= end_column; i++) {
			sheet.getRange(current_row, i).setBackgroundColor(c);
		}
		
	}
}
