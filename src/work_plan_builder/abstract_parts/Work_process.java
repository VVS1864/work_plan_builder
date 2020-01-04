package work_plan_builder.abstract_parts;

import java.time.LocalDate;

public class Work_process{
	protected String name;
	protected int duration;
	protected int units_quantity;	
	protected LocalDate start_date;
	protected LocalDate end_date;
	
	public Work_process(String name, int duration, LocalDate start_date, LocalDate end_date, int units_quantity) {
		
		this.name = name;
		this.duration = duration;
		this.units_quantity = units_quantity;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public Work_process(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public int get_unit_quantity(){
		return units_quantity;
	}
	public LocalDate get_start_date() {
		return start_date;
	}
	public LocalDate get_end_date() {
		return end_date;
	}
	public String get_name() {
		return name;
	}
	public int get_duration() {
		return duration;
	}
	
}
