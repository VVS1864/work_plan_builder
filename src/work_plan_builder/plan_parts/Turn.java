package work_plan_builder.plan_parts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import work_plan_builder.abstract_parts.Composite_work;
import work_plan_builder.abstract_parts.Hard_work;
import work_plan_builder.abstract_parts.Work_process;

public class Turn extends Composite_work{
	private LocalDate start_date;
	private LocalDate end_date;
	private String name;
	private int units_quantity;
	private List<Hard_work> hard_works = new ArrayList<>();
	
	public Turn(String name, String production_type, double prodyctivity, double required_production, 
			List<Work_process> list_of_processes, LocalDate start_date,  int units_quantity){
	super(production_type, prodyctivity, required_production, list_of_processes);
	this.name = name;
	this.start_date = start_date;	
	this.end_date = calc_end_date();
	this.units_quantity = units_quantity;
	make_processes(list_of_processes);
	make_hard_works();
	}
	
	private void make_hard_works() {
		hard_works.add(new Hard_work("Посадка", start_date, units_quantity, 0));
		
		//Temporary hardcode
		hard_works.add(new Hard_work("Перенос в аэропонику", list_of_operation.get(0).get_end_date(), units_quantity, 0));
		//Temporary hardcode
		
		hard_works.add(new Hard_work("Срезка", end_date, units_quantity, 0));
		hard_works.add(new Hard_work("Мытье лотков", end_date, units_quantity, 0));
		
		
	}

	private void make_processes(List<Work_process> list_of_processes) {
		list_of_operation = new ArrayList<Work_process>();
		LocalDate start_proc_date = start_date;
		for(Work_process phase: list_of_processes) {
			LocalDate end_proc_date = start_proc_date.plusDays(phase.get_duration());
			list_of_operation.add(new Work_process(phase.get_name(), phase.get_duration(), start_proc_date, end_proc_date, units_quantity));
			start_proc_date = end_proc_date;
			//list_of_operation.get(list_of_operation.size()-1).print_values();
		}
		
	}
	
	
	private LocalDate calc_end_date() {
		int duration = get_work_period();
		
		return start_date.plusDays(duration);
	}

	public void print_values() {
		System.out.println("turn: " + name);
		System.out.println("start of work: " + start_date);
		System.out.println("end of work:   " + end_date);
		System.out.println("====== HARDWORKS ======");
		for(Hard_work h: hard_works) {
			h.print_values();
		}
		
	}

	public String get_name() {
		return name;
	}
	public int get_units_quantity() {
		return units_quantity;
	}
	
	
}
