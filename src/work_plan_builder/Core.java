package work_plan_builder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import work_plan_builder.builders.Plan_table_builder;

public class Core {
	private Task_loader task_from_file;
	private Plan_table_builder table;
	private Work_plan_builder work_plan;
	
	public Core(){
		
		load_task();
		build_plan_table();
		build_work_plan();
		write_plans_to_ods();
		/*
		String[] delivery_days = new String[] {"MONDAY"};
		Work_task t = new Work_task("ssh", 0.4, 5, delivery_days);
		t.print_values();
		*/
	}

	private void write_plans_to_ods() {
		
		
	}

	private void build_work_plan() {
		LocalDate start_work_plan_date = task_from_file.get_start_work_plan_date();
		//SimpleDateFormat start_work_plan_date = task_from_file.get_start_work_plan_date();
		System.out.println(start_work_plan_date);
		
		work_plan = new Work_plan_builder();
		
	}

	private void build_plan_table() {
		table = new Plan_table_builder(task_from_file);
		
	}

	private void load_task() {
		task_from_file = new Task_loader();
		
	}
}
