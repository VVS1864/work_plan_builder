package work_plan_builder;


import work_plan_builder.builders.Plan_table_builder;
import work_plan_builder.builders.Turns;
import work_plan_builder.builders.Work_plan_builder;
import work_plan_builder.calc.Statistics;
import work_plan_builder.farm.Farm;

public class Core {
	private Task_loader task_from_file;
	private Turns calculated_turns;
	private Plan_table_builder table;
	private Work_plan_builder work_plan;
	private Statistics stat;
	public Core(){		
		load_task();
		Farm farm = new Farm();
		calc_turns(farm);
		

		stat = new Statistics(calculated_turns);

		build_plan_table(stat);

		build_work_plan();

		//build_farm_map(farm, stat, day);
		//write_plans_to_ods();
		
		
	}

	//private void build_farm_map(Farm farm, Statistics st, LocalDateTime day) {
	//	Farm_map_builder map = new Farm_map_builder(farm, st, day);	
	//}

	private void calc_turns(Farm farm) {
		calculated_turns = new Turns(task_from_file.get_task_list(), task_from_file.get_start_work_plan_date());
		
	}

	private void write_plans_to_ods() {
		
		
	}

	private void build_work_plan() {
		//LocalDate start_work_plan_date = task_from_file.get_start_work_plan_date();
		//System.out.println(start_work_plan_date);
		
		work_plan = new Work_plan_builder(calculated_turns);
		
	}

	private void build_plan_table(Statistics st) {
		table = new Plan_table_builder(calculated_turns, st);
		
	}

	private void load_task() {
		task_from_file = new Task_loader();
		
	}
}
