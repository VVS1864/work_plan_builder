package work_plan_builder.plan_parts;

import work_plan_builder.abstract_parts.Composite_process;
import work_plan_builder.abstract_parts.Work_phase;

public class Work_task extends Composite_process{
	protected Work_phase[] list_of_phases;
	
	public Work_task(String production_type, double prodyctivity, int required_production, String[] delivery_days, 
			Work_phase[] list_of_phases){
		super(production_type, prodyctivity, required_production, delivery_days, list_of_phases);
	}
}
