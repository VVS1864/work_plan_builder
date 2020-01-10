package work_plan_builder.builders;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;
import com.github.miachm.sods.Style;

import work_plan_builder.plan_parts.Turn;

public abstract class Ods_builder {
	protected Sheet sheet;
	protected int init_table_column = 0;
	protected int init_table_row = 0;
	protected int columns = 1;
	protected int rows = 1;
	protected String table_name;
	protected Turns turns;
	
	public Ods_builder(Turns turns, String name) {
		this.turns = turns;
		this.table_name = name;
		try {
			set_initial_cell();
			rows = turns.get_prosesses_quantity()+3;
		    columns = turns.get_work_period()+3;
		    sheet = new Sheet("A", rows, columns);
		   		    
		    create_dates(get_date_list(turns.get_start_work_date(), columns));
		    create_top();
		    
		    filling_alg();
		    
		    SpreadSheet spread = new SpreadSheet();
		    spread.appendSheet(sheet);
		    spread.save(new File(table_name+".ods"));
			}
			catch (IOException e){
	            e.printStackTrace();
			}
	}
	
	protected List<LocalDate> get_date_list(LocalDate start_work_date, int days){
		List<LocalDate> date_set = new ArrayList<>();
		for(int day = 0; day<days; day++) {
			date_set.add(start_work_date);
			//sheet.getRange(1, day).setValue(start_work_date.format(DateTimeFormatter.ofPattern("EEEEE, dd MMM")));
			start_work_date = start_work_date.plusDays(1);
		}
		return date_set;
	}

	protected void create_dates(List<LocalDate> date_set) {
		int day = 0;
		for(int column = init_table_column; column<date_set.size(); column++) {
			sheet.getRange(1, column).setValue(date_set.get(day).format(DateTimeFormatter.ofPattern("EEEEE, dd MMM")));
			day++;
		}
		
		
	}
	
	protected abstract void set_initial_cell();
	protected abstract void filling_alg();
	protected abstract void create_top();
}
