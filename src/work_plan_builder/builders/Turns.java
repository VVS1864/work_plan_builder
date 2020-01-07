package work_plan_builder.builders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import work_plan_builder.Production_type;
import work_plan_builder.abstract_parts.Hard_work;
import work_plan_builder.farm.Box;
import work_plan_builder.farm.Farm;
import work_plan_builder.plan_parts.Turn;
import work_plan_builder.plan_parts.Work_task;

public class Turns {
	private double K = 1.15;
	private int name_count = 0;
	private Work_task[] tasks;
	private List<Turn> turns = new ArrayList<>();
	private LocalDate start_work_plan_date;
	private LocalDate end_last_work_date;
	private int work_period = 0;
	private Farm farm;
	
	public Turns(Farm farm, Work_task[] tasks, LocalDate start_work_plan_date){
		this.farm = farm;
		this.tasks = tasks;
		this.start_work_plan_date = start_work_plan_date;
		for(Work_task task: tasks) {
			List<Turn> new_turns = make_turns(task);
			turns.addAll(new_turns);
		}
		calc_work_period();
	}
	
	private void calc_work_period() {
		for(Turn t: turns) {
			work_period += t.get_work_period();
		}
	}

	private List<Turn> make_turns(Work_task task) {
		int work_period = task.get_work_period();
		int turns_quantity = task.get_delivery_days().size()*4;
		int units_quantity = (int)Math.ceil(task.get_required_production()*K/task.get_productivity_per_unit());
		double productivity = task.get_productivity_per_unit();
		double production = productivity*units_quantity;
		List<Turn> turns = new ArrayList<Turn>();
		List<DayOfWeek> delivery_days = task.get_delivery_days();
		
		LocalDate start_day = start_work_plan_date;
		String turn_type = task.get_production_type();
		
		//Temporal hardcode
		Production_type prod = Production_type.middle;
		String[] low = {"Подсолнечник", "Горох"};
		String[] mid = {"Мизуна", "Горчица", "Редис"};
		String[] high = {"Базилик микро", "Подсолнечник", "Горох"};
		for (String s: low){
			if(turn_type.equals(s)) {
				prod = Production_type.heavy;
			}
		}
		for (String s: mid){
			if(turn_type.equals(s)) {
				prod = Production_type.middle;
			}
		}
		for (String s: high){
			if(turn_type.equals(s)) {
				prod = Production_type.light;
			}
		}
		//Temporal hardcode
		
		for(int i = 0; i<turns_quantity; i++) {
			name_count++;
			String name = String.format("%03d", name_count);
			
			
			LocalDate end_work_date = calc_end_work_date(delivery_days, work_period, start_day);
			LocalDate start_work_date = end_work_date.minusDays(work_period);
			
			Turn new_turn = new Turn(name, turn_type, productivity, production, task.get_list_of_operation(), start_work_date, units_quantity);
			
			boolean success_box_input = farm.put_boxes(units_quantity, prod, new_turn);
			
			if(true) {//success_box_input) {
				System.out.println(success_box_input);
				turns.add(new_turn);
				start_day = start_work_date.plusDays(1);
			}
		}
		for(Turn t: turns) {
			t.print_values();
		}
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
