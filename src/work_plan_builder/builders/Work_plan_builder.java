package work_plan_builder.builders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import work_plan_builder.abstract_parts.Hard_work;
import work_plan_builder.plan_parts.Turn;

public class Work_plan_builder extends Ods_builder{
	
	public Work_plan_builder(Turns turns){
		super(turns, "Work Plan");
		create_sheet();
	}

	@Override
	protected void filling_alg() {
		int current_column = init_table_column;
		LinkedHashMap<LocalDate, List<Hard_work>> work_calendar = get_work_calendar();
		for(LocalDate day: work_calendar.keySet()) {
			int current_row = 2;
			//String day_programm = new String();
			for(Hard_work h: work_calendar.get(day)) {
				String turn_name = h.get_turn().get_name();
				String h_name = h.get_name();
				String quantity = Integer.toString(h.get_quantity());
				String laboriouness = Double.toString(h.get_laboriouness()) + " человеко*часов";
				String prod_name = h.get_turn().get_production_type();
				
				String work = String.join("", h_name, " ", prod_name, " ", quantity, " ящиков", " (", turn_name, ")");
				//sheet.getRange(current_row, current_column).setValue(work);
				sheet.setValueAt(work,  current_column, current_row);
				sheet.getCellAt(current_column, current_row).setStyleName("wr");
				current_row++;
			}
			sheet.getColumn(current_column-1).setWidth(30);//setColumnWidth(current_column-1, 30.0);
			current_column++;
		}
		
		
		
	}


	private LinkedHashMap<LocalDate, List<Hard_work>> get_work_calendar() {
		LinkedHashMap<LocalDate, List<Hard_work>> work_calendar = new LinkedHashMap<>();
		for(LocalDate day: get_date_list(turns.get_start_work_date(), columns)) {
			work_calendar.put(day, new ArrayList<Hard_work>());
		}
			
		for(Turn t: turns.get_turns()) {
			List<Hard_work> turn_hardworks = t.get_hardwork_list();
			for(Hard_work h: turn_hardworks) {
				
				work_calendar.get(h.get_date()).add(h);
			}
		}	
		
		return work_calendar;
	}

	@Override
	protected void create_top() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void set_initial_cell() {
		init_table_column = 1;
		init_table_row = 2;
		
	}
	    
	
	    
	    
	    
	
	
	

}
