package work_plan_builder.abstract_parts;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Composite_work {
	
	protected String production_type;
	protected double productivity_per_unit; 
	protected double required_production;
	protected List<Work_process> list_of_operation;
	
	public Composite_work(String production_type, double prodyctivity, double required_production, 
			List<Work_process> list_of_operation) {
		this.production_type = production_type;
		this.productivity_per_unit = prodyctivity;
		this.required_production = required_production;
		this.list_of_operation = new ArrayList<>(list_of_operation);
	}
	
	public String get_production_type() {
		return new String(production_type);
	}
	
	public double get_required_production() {
		return required_production;
	}
	
	public double get_productivity_per_unit() {
		return productivity_per_unit;
	}
	
	public List<Work_process> get_list_of_operation() {
		return new ArrayList<Work_process>(list_of_operation);
	}
	
	public int get_work_period() {
		int work_period = 0;
		for(Work_process f: list_of_operation) {
			work_period+=f.get_duration();
		}
		//work_period-=1; //because first day is considerable!!!!!!!
		return work_period;
	}

}
