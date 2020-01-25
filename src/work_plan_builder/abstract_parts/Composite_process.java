package work_plan_builder.abstract_parts;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import work_plan_builder.Production_type;

public class Composite_process extends Composite_work{
	private List<DayOfWeek> delivery_days;
	
	
	
	protected Composite_process(String production_name, Production_type prod, double prodyctivity, double required_production, List<DayOfWeek> delivery_days, 
			List<Work_process> list_of_operation){
		super(production_name, prod, prodyctivity, required_production, list_of_operation);
		this.delivery_days = new ArrayList<DayOfWeek>(delivery_days);
		
	}
		
	public List<DayOfWeek> get_delivery_days() {
		return new ArrayList<>(delivery_days);
	}
	
	public void print_values(){
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("production type: " + production_name);
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