package work_plan_builder.builders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import work_plan_builder.abstract_parts.Work_process;
import work_plan_builder.plan_parts.Turn;
import work_plan_builder.plan_parts.Work_task;

public class Turns {
	private double K = 1.15;
	private int name_count = 0;
	private Work_task[] tasks;
	private List<Turn> turns = new ArrayList<>();
	private LocalDate start_work_plan_date;
	
	public Turns(Work_task[] tasks, LocalDate start_work_plan_date){
		this.tasks = tasks;
		this.start_work_plan_date = start_work_plan_date;
		for(Work_task task: tasks) {
			List<Turn> new_turns = make_turns(task);
			turns.addAll(new_turns);
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
		for(int i = 0; i<turns_quantity; i++) {
			String name = String.format("%03d", name_count++);
			String turn_type = task.get_production_type();
			LocalDate end_work_date = calc_end_work_date(delivery_days, work_period, start_day);
			LocalDate start_work_date = end_work_date.minusDays(work_period);
			turns.add(new Turn(name, turn_type, productivity, production, task.get_list_of_operation(), start_work_date, units_quantity));
			start_day = start_work_date.plusDays(1);
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
}
