package work_plan_builder;

import java.time.LocalDate;

import work_plan_builder.builders.Plan_table_builder;
import work_plan_builder.builders.Turns;
import work_plan_builder.builders.Work_plan_builder;

public class Core {
	private Task_loader task_from_file;
	private Turns calculated_turns;
	private Plan_table_builder table;
	private Work_plan_builder work_plan;
	
	public Core(){
		
		load_task();
		calc_turns();
		build_plan_table();
		build_work_plan();
		write_plans_to_ods();
		
	}

	private void calc_turns() {
		calculated_turns = new Turns(task_from_file.get_task_list(), task_from_file.get_start_work_plan_date());
		
	}

	private void write_plans_to_ods() {
		
		
	}

	private void build_work_plan() {
		LocalDate start_work_plan_date = task_from_file.get_start_work_plan_date();
		//System.out.println(start_work_plan_date);
		
		work_plan = new Work_plan_builder(calculated_turns);
		
	}

	private void build_plan_table() {
		table = new Plan_table_builder(calculated_turns);
		
	}

	private void load_task() {
		task_from_file = new Task_loader();
		
	}
}
