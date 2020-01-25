package work_plan_builder.builders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

import work_plan_builder.Production_type;
import work_plan_builder.abstract_parts.Hard_work;
import work_plan_builder.farm.Box;
import work_plan_builder.farm.Farm;
import work_plan_builder.farm.Shelf;
import work_plan_builder.plan_parts.Turn;
import work_plan_builder.plan_parts.Work_task;

public class Turns {
	/**
	 * coefficient of supply
	 */
	private double K = 1.15;
	private int name_count = 0;
	private List<Work_task> tasks;
	private List<Turn> turns = new ArrayList<>();
	private LocalDate start_work_plan_date;
	private LocalDate end_last_work_date;
	private int work_period = 0;
	private Farm farm;
	
	public Turns(List<Work_task> tasks, LocalDate start_work_plan_date){
		
		this.tasks = tasks;
		this.start_work_plan_date = start_work_plan_date;
		for(Work_task task: tasks) {
			List<Turn> new_turns = make_turns(task);
			turns.addAll(new_turns);
		}
		calc_end_date_and_work_period();
		//end_last_work_date = start_work_plan_date.plusDays(work_period);
	}
	
	private void calc_end_date_and_work_period() {
		end_last_work_date = start_work_plan_date;
		for(Turn t: turns) {
			LocalDate turn_end = t.get_end_date();
			if(end_last_work_date.isBefore(turn_end)){
				end_last_work_date = turn_end;
			}
		}
		work_period = (int)ChronoUnit.DAYS.between(start_work_plan_date, end_last_work_date);
	}

	private List<Turn> make_turns(Work_task task) {
		Production_type prod = task.get_prod();
		int work_period = task.get_work_period();
		int turns_quantity = task.get_delivery_days().size()*4;
		int units_quantity = 0;
		if(task.manual_size()) {
			units_quantity = task.get_units_quantity();
		}
		else {
			//caclulate quantity and complete it to multiple shelf size
			units_quantity = (int)Math.ceil(task.get_required_production()*K/task.get_productivity_per_unit());
			int shelves_quantity = (int)Math.ceil(units_quantity*1.0/Shelf.shelf_size);
			units_quantity = Shelf.shelf_size * shelves_quantity;
		}
		double productivity = task.get_productivity_per_unit();
		double production = productivity*units_quantity;
		List<Turn> turns = new ArrayList<Turn>();
		List<DayOfWeek> delivery_days = task.get_delivery_days();
		
		LocalDate start_day = task.get_start_date();
		String turn_type = task.get_production_type();
		
		
		
		for(int i = 0; i<turns_quantity; i++) {
			name_count++;
			String name = String.format("%03d", name_count);
			
			
			LocalDate end_work_date = calc_end_work_date(delivery_days, work_period, start_day);
			LocalDate start_work_date = end_work_date.minusDays(work_period);
			
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
			Turn new_turn = new Turn(name, turn_type, prod, productivity, production, task.get_list_of_operation(), start_work_date, units_quantity, color_set[color_ind]);
			
			
			//boolean success_box_input = farm.put_boxes(units_quantity, prod, new_turn);
			//if(success_box_input) {
				turns.add(new_turn);
				start_day = start_work_date.plusDays(1);
			//}
			//else {
			//	System.err.println("There are not empty boxes for put new turn!");
			//}
		}
		/*
		for(Turn t: turns) {
			t.print_values();
		}
		*/
		//System.out.println(turns.size());
		//System.out.println("turns quantity: " + turns_quantity);
		//System.out.println("units quantity: " + units_quantity);
		return turns;
	}
	
	private LocalDate calc_end_work_date(List<DayOfWeek> delivery_days, int work_period, LocalDate start_day) {
		LocalDate end_work_date = start_day.plusDays(work_period);
		DayOfWeek d1 = end_work_date.getDayOfWeek();
		
		while(!delivery_days.contains(d1)) {
			end_work_date = end_work_date.plusDays(1);
			d1 = end_work_date.getDayOfWeek();
		}
		return end_work_date;
		
	}
	
	public int get_turns_quantity() {
		return turns.size();
	}
	
	public int get_prosesses_quantity() {
		int proc_num = 0;
		for(Turn t: turns) {
			proc_num += t.get_list_of_operation().size();
		}
		
		return proc_num;
	}
	
	public LocalDate get_end_work_date() {
		return end_last_work_date;
	}
	
	public LocalDate get_start_work_date() {
		return start_work_plan_date;
	}
	
	public int get_work_period() {
		return work_period;
	}
	
	public List<Turn> get_turns() {
		return turns;
	}
	
}
