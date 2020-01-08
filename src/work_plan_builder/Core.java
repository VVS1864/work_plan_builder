package work_plan_builder;

import java.time.LocalDate;

import work_plan_builder.builders.Farm_map_builder;
import work_plan_builder.builders.Plan_table_builder;
import work_plan_builder.builders.Turns;
import work_plan_builder.builders.Work_plan_builder;
import work_plan_builder.farm.Farm;

public class Core {
	private Task_loader task_from_file;
	private Turns calculated_turns;
	private Plan_table_builder table;
	private Work_plan_builder work_plan;
	
	public Core(){
		
		load_task();
		Farm farm = new Farm();
		calc_turns(farm);
		build_plan_table();
		build_work_plan();
		build_farm_map(farm);
		//write_plans_to_ods();
		
		
	}

	private void build_farm_map(Farm farm) {
		Farm_map_builder map = new Farm_map_builder(farm);
		
	}

	private void calc_turns(Farm farm) {
		calculated_turns = new Turns(farm, task_from_file.get_task_list(), task_from_file.get_start_work_plan_date());
		
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
