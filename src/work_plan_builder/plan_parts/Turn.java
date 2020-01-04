package work_plan_builder.plan_parts;

import work_plan_builder.abstract_parts.Work_entity;

public class Turn extends Work_task{
	private Work_process[] list_of_processes;
	
	Turn(String name, String production_type, double prodyctivity, int required_production, String[] delivery_days, 
			Work_entity[] processes_list){
	super(production_type, prodyctivity, required_production, delivery_days, processes_list);
	}
	
	
	
}
