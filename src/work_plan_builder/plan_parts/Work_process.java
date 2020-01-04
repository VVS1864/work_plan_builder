package work_plan_builder.plan_parts;

import java.time.LocalDate;

import work_plan_builder.abstract_parts.Work_entity;

public class Work_process extends Work_entity{
	protected int units_quantity;	
	
	public Work_process(String name, int duration, LocalDate start_date, LocalDate end_date, int units_quantity) {
		super(name, duration, start_date, end_date);
		this.units_quantity = units_quantity;
	}
	
	public int get_unit_quantity(){
		return units_quantity;
	}
	
	
}
