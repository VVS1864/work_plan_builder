package work_plan_builder.abstract_parts;

import java.util.ArrayList;
import java.util.List;

import work_plan_builder.Production_type;

public class Composite_work {
	
	protected String production_name;
	protected double productivity_per_unit; 
	protected double required_production;
	protected List<Work_process> list_of_operation;
	protected Production_type prod;
	
	public Composite_work(String production_name, Production_type prod, double prodyctivity, double required_production, 
			List<Work_process> list_of_operation) {
		this.production_name = production_name;
		this.productivity_per_unit = prodyctivity;
		this.required_production = required_production;
		this.list_of_operation = new ArrayList<>(list_of_operation);
		this.prod = prod;
	}
	
	public String get_production_type() {
		return new String(production_name);
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
		
		return work_period;
	}
	public Production_type get_prod() {
		return prod;
	}

}
