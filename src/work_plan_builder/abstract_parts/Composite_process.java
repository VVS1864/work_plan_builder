package work_plan_builder.abstract_parts;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import work_plan_builder.abstract_parts.Work_phase;

public class Composite_process{
	private String production_type;
	private double productivity_per_unit; 
	private double required_production;
	private List<DayOfWeek> delivery_days;
	private List<Work_process> list_of_operation;
	
	protected Composite_process(String production_type, double prodyctivity, double required_production, List<DayOfWeek> delivery_days, 
			List<Work_process> list_of_operation){
		this.production_type = production_type;
		this.productivity_per_unit = prodyctivity;
		this.required_production = required_production;
		this.delivery_days = new ArrayList<DayOfWeek>(delivery_days);
		//set_delivery_days(delivery_days);
		this.list_of_operation = list_of_operation;
		
	}
	/*
	private void set_delivery_days(String[] s_days){
		this.delivery_days = new ArrayList<DayOfWeek>();
		for(String s: s_days) {
			this.delivery_days.add(DayOfWeek.valueOf(s));
		}
	}
	*/
	public String get_production_type() {
		return new String(production_type);
	}
	
	public double get_required_production() {
		return required_production;
	}
	
	public double get_productivity_per_unit() {
		return productivity_per_unit;
	}
	
	public List<DayOfWeek> get_delivery_days() {
		return new ArrayList<>(delivery_days);
	}
	
	public List<Work_process> get_list_of_operation() {
		return new ArrayList<Work_process>(list_of_operation);
	}
	
	public int get_work_period() {
		int work_period = 0;
		for(Work_process f: list_of_operation) {
			work_period+=f.get_duration();
		}
		work_period-=1; //because first day is considerable!!!!!!!
		return work_period;
	}
	
	public void print_values(){
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("production type: " + production_type);
		System.out.println("required production:" + " " + required_production);
		System.out.println("number of delivery per week:" + " " + delivery_days.size());
		System.out.println("delivery days:");
		for(DayOfWeek d: delivery_days) {
			System.out.println(d);
		}
		System.out.println("productivity per unit:" + " " + productivity_per_unit);
		System.out.println("phases:");
		for(int i = 0; i<list_of_operation.size(); i++) {
			
			System.out.println(i+1 + " " + list_of_operation.get(i).get_name() + " " + list_of_operation.get(i).get_duration() + " " + "days");
			
		}
		System.out.println("------------------------------");
	}
}