package work_plan_builder.abstract_parts;

import java.time.LocalDate;

public class Hard_work {
	String name;
	LocalDate date;
	int quantity;
	double laboriouness;
	
	public Hard_work(String name, LocalDate date, int quantity, double laboriouness) {
		this.date = date;
		this.name = name;
		this.quantity = quantity;
		this.laboriouness = laboriouness;
	}
	
	public String get_name() {
		return name;
	}
	public LocalDate get_date() {
		return date;
	}
	public int get_quantity() {
		return quantity;
	}
	
	public double get_laboriouness() {
		return laboriouness;
	}
	
	public void print_values() {
		System.out.println("=======");
		System.out.println("	name:   " + name);
		System.out.println("	date:   " + date);
		System.out.println("	quantity:   " + quantity);
		System.out.println("	laboriouness:   " + laboriouness);
	}
}
