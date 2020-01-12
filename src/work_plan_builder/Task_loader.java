package work_plan_builder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import work_plan_builder.abstract_parts.Work_process;
import work_plan_builder.plan_parts.Work_task;

public class Task_loader {
	private List<Work_task> tasks = new ArrayList<Work_task>();

	public Task_loader() {

		Config task_file = new Config();
		int task_ind = 0;
		for (Object task : task_file.config.getJSONArray("Tasks")) {
			JSONObject jtask = (JSONObject) task;

			String task_name = "";
			double productivity = 0;
			double required_production = 0;
			boolean manual_size = false;
			int units_quantity = 0;
			List<DayOfWeek> delivery_days = new ArrayList<DayOfWeek>();
			List<Work_process> list_of_processes = new ArrayList<>();

			for (String key : jtask.keySet()) {
				// Object jsonObj = jtask.get(key);
				if (key.equals("production_type")) {
					task_name = jtask.getString(key);
				} else if (key.equals("productivity")) {
					productivity = jtask.getDouble(key);
				} else if (key.equals("required_production")) {
					required_production = jtask.getDouble(key);
				} else if (key.equals("manual_size")) {
					manual_size = jtask.getBoolean(key);
				} else if (key.equals("units_quantity")) {
					units_quantity = jtask.getInt(key);
				} else if (key.equals("delivery_days")) {
					for (Object s_day : jtask.getJSONArray(key)) {
						if (s_day.equals("Пн")) {
							delivery_days.add(DayOfWeek.MONDAY);
						} else if (s_day.equals("Вт")) {
							delivery_days.add(DayOfWeek.TUESDAY);
						} else if (s_day.equals("Ср")) {
							delivery_days.add(DayOfWeek.WEDNESDAY);
						} else if (s_day.equals("Чт")) {
							delivery_days.add(DayOfWeek.THURSDAY);
						} else if (s_day.equals("Пт")) {
							delivery_days.add(DayOfWeek.FRIDAY);
						} else if (s_day.equals("Сб")) {
							delivery_days.add(DayOfWeek.SATURDAY);
						} else if (s_day.equals("Вс")) {
							delivery_days.add(DayOfWeek.SUNDAY);
						}
					}
				} else if (key.equals("list_of_processes")) {
					String work_process_name;
					int work_process_duration;
					JSONObject proc = jtask.getJSONObject(key);
					for (String process_name : proc.keySet()) {
						work_process_name = process_name;
						work_process_duration = proc.getInt(process_name);
						list_of_processes.add(new Work_process(work_process_name, work_process_duration));
					}
				}
			}

			tasks.add(new Work_task(task_name, productivity, required_production, delivery_days, list_of_processes,
					manual_size, units_quantity));
		}

		/*
		 * //Temporal hardcode List<DayOfWeek> delivery_days = new
		 * ArrayList<DayOfWeek>(); delivery_days.add(DayOfWeek.THURSDAY);
		 * delivery_days.add(DayOfWeek.MONDAY);
		 * 
		 * List<Work_process> list_of_processes_1 = new ArrayList<>();
		 * list_of_processes_1.add(new Work_process("Проращивание", 4));
		 * list_of_processes_1.add(new Work_process("Аэропоника", 15)); tasks.add(new
		 * Work_task("Базилик", 0.4, 11, delivery_days, list_of_processes_1));
		 * 
		 * list_of_processes_1 = new ArrayList<>(); list_of_processes_1.add(new
		 * Work_process("Проращивание", 2)); list_of_processes_1.add(new
		 * Work_process("Аэропоника", 6)); tasks.add(new Work_task("Подсолнечник", 0.4,
		 * 4, delivery_days, list_of_processes_1, true, 9));
		 * 
		 * list_of_processes_1 = new ArrayList<>(); list_of_processes_1.add(new
		 * Work_process("Проращивание", 3)); list_of_processes_1.add(new
		 * Work_process("Аэропоника", 10)); tasks.add(new Work_task("Руккола", 0.4, 4,
		 * delivery_days, list_of_processes_1));
		 * 
		 * list_of_processes_1 = new ArrayList<>(); list_of_processes_1.add(new
		 * Work_process("Проращивание", 10)); list_of_processes_1.add(new
		 * Work_process("Аэропоника", 5)); tasks.add(new Work_task("Мизуна", 0.4, 4,
		 * delivery_days, list_of_processes_1)); //tasks[0].print_values();
		 * 
		 * //Temporal hardcode
		 */
	}

	LocalDate get_start_work_plan_date() {
		return LocalDate.of(2019, 11, 19);
	}

	public List<Work_task> get_task_list() {
		return new ArrayList<Work_task>(tasks);
	}

}
