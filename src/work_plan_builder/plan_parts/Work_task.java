package work_plan_builder.plan_parts;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import work_plan_builder.Production_type;
import work_plan_builder.abstract_parts.Composite_process;
import work_plan_builder.abstract_parts.Work_process;

public class Work_task extends Composite_process{
	protected List<Work_process> list_of_phases;
	private boolean manual_size = false;
	private int units_quantity = 0;
	LocalDate start_date;
	Production_type prod;
	
	/*
	public Work_task(String production_type, Production_type prod, double prodyctivity, double required_production, 
			List<DayOfWeek> delivery_days, List<Work_process> list_of_phases, LocalDate start_date){
		
		super(production_type, prodyctivity, required_production, delivery_days, list_of_phases);
		this.start_date = start_date;
	}
	*/
	public Work_task(String production_type, Production_type prod, double prodyctivity, double required_production, 
			List<DayOfWeek> delivery_days, List<Work_process> list_of_phases, boolean manual_size, int units_quantity, LocalDate start_date){
		//this(production_type, prodyctivity, required_production, delivery_days, list_of_phases, start_date);
		super(production_type, prodyctivity, required_production, delivery_days, list_of_phases);
		this.start_date = start_date;
		this.manual_size = manual_size;
		this.units_quantity = units_quantity;
		this.prod = prod;
	}
	

	public int get_units_quantity() {
		return units_quantity;
	}
	public boolean manual_size() {
		return manual_size;
	}
	public LocalDate get_start_date() {
		return start_date;
	}
	public Production_type get_prod() {
		return prod;
	}
}
