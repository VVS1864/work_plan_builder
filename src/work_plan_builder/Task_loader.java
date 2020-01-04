package work_plan_builder;

import java.time.LocalDate;

import work_plan_builder.abstract_parts.Work_phase;
import work_plan_builder.plan_parts.Work_task;

public class Task_loader {
	Work_task[] tasks = new Work_task[1];
	Task_loader(){
		//Temporal hardcode
		String[] delivery_days = {"MONDAY", "THURSDAY"};
		
		Work_phase[] list_of_processes_1 = new Work_phase[2];
		list_of_processes_1[0] = new Work_phase("bas_st1", 5);
		list_of_processes_1[1] = new Work_phase("bas_st2", 15);
		
		tasks[0] = new Work_task("basil", 0.4, 4, delivery_days, list_of_processes_1);
		tasks[0].print_values();
		
		//Temporalhardcode
	}
	
	LocalDate get_start_work_plan_date() {
		return LocalDate.of(1993, 6, 9);
	}

}
