package work_plan_builder.builders;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.awt.Color;
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
		//sheet.getRange(0, 0).setValue("Техзадание");
		//sheet.getRange(0, 1).setValue("Величина поставки");
		//sheet.getRange(0, 2).setValue("Лотков");
		//sheet.getRange(0, 3).setValue("Очередь");
		//sheet.getRange(0, 4).setValue("Стадия");
		
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
		//System.out.println(day);
		//sheet.getRange(current_row, init_table_column-1).setValue("Камера проращивания");
		//sheet.getRange(current_row+1, init_table_column-1).setValue("Аэропоника");
		sheet.setValueAt("Камера проращивания", init_table_column-1, current_row);
		sheet.setValueAt("Аэропоника",  init_table_column-1, current_row+1);
		for(int col = init_table_column; col<columns; col++) {
			int chamber_units = stat.get_chamber_units(day);
			int stillage_units = stat.get_stillage_units(day);
			//sheet.getRange(current_row, col).setValue(chamber_units);
			//sheet.getRange(current_row+1, col).setValue(stillage_units);
			sheet.setValueAt(chamber_units, col, current_row);
			sheet.setValueAt(stillage_units, col, current_row+1);
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
	    	//sheet.getRange(current_row, 0).setValue(t.get_production_type());
	    	//sheet.getRange(current_row, 1).setValue(t.get_required_production());
	    	//sheet.getRange(current_row, 2).setValue(t.get_units_quantity());
	    	//sheet.getRange(current_row, 3).setValue(t.get_name());
	    	
	    	sheet.setValueAt(t.get_production_type(), 0, current_row);
	    	sheet.setValueAt(t.get_required_production(), 1, current_row);
	    	sheet.setValueAt(t.get_units_quantity(), 2, current_row);
	    	sheet.setValueAt(t.get_name(), 3, current_row);
	    	
	    	for(Work_process proc: t.get_list_of_operation()) {
	    		
	    		//System.out.println(t.get_production_type());
	    		//System.out.println(proc.get_start_date() + " " +  proc.get_name());
	    		int start_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_start_date())+init_table_column;
	    		int end_column = (int)ChronoUnit.DAYS.between(turns.get_start_work_date(), proc.get_end_date())+init_table_column;
	    		//System.out.println(start_column);
	    		//System.out.println(end_column);
	    		//System.out.println(current_row);
	    		//sheet.getRange(current_row, 4).setValue(proc.get_name());
	    		sheet.setValueAt(proc.get_name(), 4, current_row);
	    		set_cells(start_column, end_column, current_row, c, t.get_name());
	    		current_row++;
	    		c = c2;
	    	}
	    	
	    }
		current_row++;
	}

}
