package work_plan_builder.abstract_parts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



public class Work_process{
	//Temporal hardcode
	public static final String first_proc_name = "Проращивание";
	public static final String second_proc_name = "Аэропоника";
	public static final LocalTime start_time = LocalTime.parse("12:30:00");
	public static final LocalTime end_time = LocalTime.parse("12:20:00");
	//Temporal hardcode
	protected String name;
	protected int duration;
	protected int units_quantity;	
	protected LocalDateTime start_date;
	protected LocalDateTime end_date;
	public boolean is_phase;
	
	public Work_process(String name, int duration, LocalDate start_date, LocalDate end_date, int units_quantity) {
		is_phase = false;
		this.name = name;
		this.duration = duration;
		this.units_quantity = units_quantity;
		this.start_date = start_date.atTime(start_time);
		this.end_date = end_date.atTime(end_time);
	}
	public Work_process(String name, int duration) {
		is_phase = true;
		this.name = name;
		this.duration = duration;
	}
	
	public int get_unit_quantity(){
		return units_quantity;
	}
	public LocalDate get_start_date() {
		return start_date.toLocalDate();
	}
	public LocalDate get_end_date() {
		return end_date.toLocalDate();
	}
	public LocalDateTime get_start_date_time() {
		return start_date;
	}
	public LocalDateTime get_end_date_time() {
		return end_date;
	}
	public String get_name() {
		return name;
	}
	public int get_duration() {
		return duration;
	}
	public void print_values() {
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println("name: " + name);
		System.out.println("is_phase: " + is_phase);
		System.out.println("duration: " + duration);
		System.out.println("units_quantity: " + units_quantity);
		System.out.println("start_date: " + start_date);
		System.out.println("end_date: " + end_date);
		System.out.println("------------------------------");
	}
	
}
