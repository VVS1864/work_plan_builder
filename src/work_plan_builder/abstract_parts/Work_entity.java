package work_plan_builder.abstract_parts;

import java.time.LocalDate;

public abstract class Work_entity extends Work_phase{
	protected LocalDate start_date;
	protected LocalDate end_date;

	protected Work_entity(String name, int duration, LocalDate start_date, LocalDate end_date) {
		super(name, duration);
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public LocalDate get_start_date() {
		return start_date;
	}
	public LocalDate get_end_date() {
		return end_date;
	}
}
