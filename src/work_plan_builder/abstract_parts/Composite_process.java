package work_plan_builder.abstract_parts;

import java.time.DayOfWeek;

import work_plan_builder.abstract_parts.Work_entity;
import work_plan_builder.abstract_parts.Work_phase;

public class Composite_process{
	private String production_type;
	private double productivity_per_unit; 
	private int required_production;
	private DayOfWeek[] delivery_days;
	private Work_phase[] list_of_operation;
	
	protected Composite_process(String production_type, double prodyctivity, int required_production, String[] delivery_days, 
			Work_phase[] list_of_operation){
		
		this.production_type = production_type;
		this.productivity_per_unit = prodyctivity;
		this.required_production = required_production;
		set_delivery_days(delivery_days);
		this.list_of_operation = list_of_operation.clone();
	}
	
	private void set_delivery_days(String[] s_days){
		this.delivery_days = new DayOfWeek[s_days.length];
		for(int i = 0; i<delivery_days.length; i++) {
			String s = s_days[i];
			this.delivery_days[i] = DayOfWeek.valueOf(s);
		}
	}
	
	public void print_values(){
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("production type: " + production_type);
		System.out.println("required production:" + " " + required_production);
		System.out.println("number of delivery per week:" + " " + delivery_days.length);
		System.out.println("delivery days:");
		for(int i = 0; i<delivery_days.length; i++) {
			System.out.println(delivery_days[i].name());
		}
		System.out.println("productivity per unit:" + " " + productivity_per_unit);
		System.out.println("phases:");
		for(int i = 0; i<list_of_operation.length; i++) {
			
			System.out.println(i+1 + " " + list_of_operation[i].get_name() + " " + list_of_operation[i].get_duration() + " " + "days");
			
		}
		System.out.println("------------------------------");
	}
}