package work_plan_builder.calc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import work_plan_builder.Production_type;
import work_plan_builder.abstract_parts.Work_process;
import work_plan_builder.builders.Turns;
import work_plan_builder.plan_parts.Turn;

public class Statistics {
	private LocalDateTime start;
	private LocalDateTime end;
	public LinkedHashMap<LocalDateTime, List<Turn>> stillage_calendar_light = new LinkedHashMap<>();
	public LinkedHashMap<LocalDateTime, List<Turn>> stillage_calendar_middle = new LinkedHashMap<>();
	public LinkedHashMap<LocalDateTime, List<Turn>> stillage_calendar_heavy = new LinkedHashMap<>();
	public LinkedHashMap<LocalDateTime, List<Turn>> stillage_calendar_total = new LinkedHashMap<>();
	public LinkedHashMap<LocalDateTime, List<Turn>> chamber_calendar = new LinkedHashMap<>();

	public Statistics(Turns turns) {
		start = turns.get_start_work_date().atTime(Work_process.start_time.plusMinutes(5));
		end = turns.get_end_work_date().atTime(Work_process.end_time);
		for (LocalDateTime day = start; !(day.isAfter(end)); day = day.plusDays(1)) {
			//System.out.println(day);
			chamber_calendar.put(day, new ArrayList<Turn>());
			stillage_calendar_total.put(day, new ArrayList<Turn>());
			stillage_calendar_light.put(day, new ArrayList<Turn>());
			stillage_calendar_middle.put(day, new ArrayList<Turn>());
			stillage_calendar_heavy.put(day, new ArrayList<Turn>());
			
			for (Turn t : turns.get_turns()) {
				for (Work_process proc : t.get_list_of_operation()) {
					LocalDateTime proc_start = proc.get_start_date_time();
					LocalDateTime proc_end = proc.get_end_date_time();
					if ((day.isAfter(proc_start) || day.isEqual(proc_start))
							&& (day.isBefore(proc_end) || day.isEqual(proc_end))) {
						// Temporal hardcode
						if (proc.get_name().equals(Work_process.first_proc_name)) {
							chamber_calendar.get(day).add(t);
						} else if (proc.get_name().equals(Work_process.second_proc_name)) {
							stillage_calendar_total.get(day).add(t);
							if(t.get_prod() == Production_type.middle) {
								stillage_calendar_middle.get(day).add(t);
							}
							else if(t.get_prod() == Production_type.light) {
								stillage_calendar_light.get(day).add(t);
							}
							else if(t.get_prod() == Production_type.heavy) {
								stillage_calendar_heavy.get(day).add(t);
							}
						}
						// Temporal hardcode
					}
				}

			}
		}
	}

	public LocalDateTime get_start_day() {
		return start;
	}

	public LocalDateTime get_end_day() {
		return end;
	}

	public int get_chamber_units(LocalDateTime day) {
		return get_units(chamber_calendar, day);
	}

	public int get_stillage_total_units(LocalDateTime day) {
		return get_units(stillage_calendar_total, day);
	}
	
	public int get_stillage_light_units(LocalDateTime day) {
		return get_units(stillage_calendar_light, day);
	}
	
	public int get_stillage_middle_units(LocalDateTime day) {
		return get_units(stillage_calendar_middle, day);
	}
	
	public int get_stillage_heavy_units(LocalDateTime day) {
		return get_units(stillage_calendar_heavy, day);
	}

	private int get_units(LinkedHashMap<LocalDateTime, List<Turn>> map, LocalDateTime day) {
		int units = 0;
		if (map.containsKey(day)) {
			for (Turn t : map.get(day)) {
				if (t != null) {
					units += t.get_units_quantity();
				}
			}

		}
		else {
			System.err.println("There is not day in keyset: " + day.toString());
		}
		return units;
	}

	public String get_string() {
		// String ls_full = new String();
		List<String> ls_day_stillage = new ArrayList<>();
		List<String> ls_day_chamber = new ArrayList<>();
		for (LocalDateTime day : chamber_calendar.keySet()) {

			for (Turn t : chamber_calendar.get(day)) {
				ls_day_chamber.add(
						String.join(" ", Work_process.first_proc_name, day.toLocalDate().toString(), t.get_name()));
			}
			for (Turn t : stillage_calendar_total.get(day)) {
				ls_day_stillage.add(
						String.join(" ", Work_process.second_proc_name, day.toLocalDate().toString(), t.get_name()));
			}
		}
		String ls_full_stillage = String.join("\n", ls_day_stillage);
		String ls_full_chamber = String.join("\n", ls_day_chamber);
		return String.join("\n", ls_full_chamber, ls_full_stillage);
	}

}
