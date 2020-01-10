package work_plan_builder.abstract_parts;

import java.time.LocalDate;

import work_plan_builder.plan_parts.Turn;

public class Hard_work {
	Turn turn;
	String name;
	LocalDate date;
	int quantity;
	double laboriouness;
	
	public Hard_work(String name, LocalDate date, int quantity, double laboriouness, Turn turn) {
		this.date = date;
		this.name = name;
		this.quantity = quantity;
		this.laboriouness = laboriouness;
		this.turn = turn;
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
	public Turn get_turn() {
		return turn;
	}
	
	
	public double get_laboriouness() {
		return laboriouness;
	}
	
	public void print_values() {
		System.out.println("=======");
		System.out.println("	name:   " + name);
		System.out.println("	turn:   " + turn.get_name());
		System.out.println("	production:   " + turn.get_production_type());
		System.out.println("	date:   " + date);
		System.out.println("	quantity:   " + quantity);
		System.out.println("	laboriouness:   " + laboriouness);
	}
}
