package work_plan_builder.plan_parts;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import work_plan_builder.abstract_parts.Work_process;

public class Turn extends Work_task{
	private List<Work_process> list_of_processes;
	private LocalDate start_date;
	private LocalDate end_date;
	private String name;
	
	public Turn(String name, String production_type, double prodyctivity, double required_production, 
			List<DayOfWeek> delivery_days, List<Work_process> list_of_processes, 
			LocalDate start_date, LocalDate end_date, int duration){
	super(production_type, prodyctivity, required_production, delivery_days, list_of_processes);
	this.name = name;
	this.start_date = start_date;
	this.end_date = end_date;
	}
	
	public void print_values() {
		System.out.println("turn: " + name);
		System.out.println("start of work: " + start_date);
		System.out.println("end of work:   " + end_date);
	}
	
	
	
}
