package work_plan_builder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import work_plan_builder.abstract_parts.Work_process;
import work_plan_builder.plan_parts.Work_task;

public class Task_loader {
	private List<Work_task> tasks = new ArrayList<Work_task>();
	Config task_file;
	
	public Task_loader() {
		HashMap<String, DayOfWeek> days = new HashMap<>();
		days.put("Пн", DayOfWeek.MONDAY);
		days.put("Вт", DayOfWeek.TUESDAY);
		days.put("Ср", DayOfWeek.WEDNESDAY);
		days.put("Чт", DayOfWeek.THURSDAY);
		days.put("Пт", DayOfWeek.FRIDAY);
		days.put("Сб", DayOfWeek.SATURDAY);
		days.put("Вс", DayOfWeek.SUNDAY);
		
		HashMap<String, Production_type> prods = new HashMap<>();
		prods.put("Низ", Production_type.heavy);
		prods.put("Середина", Production_type.middle);
		prods.put("Верх", Production_type.light);
		
		task_file = new Config();
		int top_row = 1;
		int current_row = 2;
		int max_row = task_file.sheet.getRowCount();
		int max_col = task_file.sheet.getColumnCount();
		
		for(current_row = 2; current_row < max_row; current_row++) {			
			String task_name = "";
			Production_type prod = Production_type.middle;
			double productivity = 0;
			double required_production = 0;
			boolean manual_size = false;
			int units_quantity = 0;
			LocalDate start_date = null;
			List<DayOfWeek> delivery_days = new ArrayList<DayOfWeek>();
			List<Work_process> list_of_processes = new ArrayList<>();
			if (get_str_value(0, current_row).equals("")) continue;
			for(int col = 0; col < max_col; col++) {
				String column_top = get_str_value(col, top_row);
				String cell_text = get_str_value(col, current_row);
				if(days.containsKey(column_top)) {
					if(cell_text.equals("Да")) {
						delivery_days.add(days.get(column_top));
					}
				}
				else if (column_top.equals("Название продукции")) {
					task_name = cell_text;
				}
				else if (column_top.equals("Длительность аэропоники, суток")) {
					String work_process_name = "Аэропоника";
					int work_process_duration = parse_int(cell_text);
					list_of_processes.add(new Work_process(work_process_name, work_process_duration));
				}
				else if (column_top.equals("Длительность проращивания, суток")) {
					String work_process_name = "Проращивание";
					int work_process_duration = parse_int(cell_text);
					list_of_processes.add(new Work_process(work_process_name, work_process_duration));
				}
				else if (column_top.equals("Расположение на стеллаже")) {
					prod = prods.get(cell_text);
				}
				else if (column_top.equals("Продуктивность, кг/лоток")) {
					productivity = parse_double(cell_text);
				}
				else if (column_top.equals("Величина поставки, кг")) {
					required_production = parse_double(cell_text);
				}
				else if (column_top.equals("Дата начала посадок")) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
					start_date = LocalDate.parse(cell_text, formatter);
				}
				else if (column_top.equals("Фиксированное количество лотков")) {
					int units_q = parse_int(cell_text);
					if(units_q>0) {
						units_quantity = units_q;
						manual_size = true;
					}
				}
			}
			boolean stop_key = false;
			if(delivery_days.isEmpty()) {
				System.err.println("Warning! Delivery days are not pointed for " + task_name);
				stop_key = true;
			}
			if(start_date == null) {
				System.err.println("Warning! Start date is not pointed for " + task_name);
				stop_key = true;
			}
			
			if(stop_key == false) {
				tasks.add(new Work_task(task_name, prod, productivity, required_production, delivery_days, list_of_processes,
					manual_size, units_quantity, start_date));
			}
		}
		
		/*
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
*/
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
	String replace_all(String s) {
		s = s.replaceAll(",", ".");
		s = s.replaceAll(" ", "");
		return s;
	}
	
	int parse_int(String s) {
		String ss = replace_all(s);
		int value = 0;
		try{
			value = Integer.parseInt(ss);
		}catch(NumberFormatException e) {
			System.err.println("read config integer trouble!");
		}
		return value;
	}
	
	double parse_double(String s) {
		String ss = replace_all(s);
		double value = 0.0;
		try{
			value = Double.parseDouble(ss);
		}catch(NumberFormatException e) {
			System.err.println("read config double trouble!");
		}
		return value;
	}
	
	String get_str_value(int column, int row) {
		try {
			 task_file.sheet.getCellAt(column, row).getTextValue();
		}catch(Exception e) {
			System.out.println(column + " " + row);
		}
		return task_file.sheet.getCellAt(column, row).getTextValue();
	}

	LocalDate get_start_work_plan_date() {
		LocalDate first_task = tasks.get(0).get_start_date();
		for (Work_task t: tasks) {
			if (first_task.compareTo(t.get_start_date())>0) {
				first_task = t.get_start_date();
			}
		}
		return first_task;
	}

	public List<Work_task> get_task_list() {
		return new ArrayList<Work_task>(tasks);
	}

}
