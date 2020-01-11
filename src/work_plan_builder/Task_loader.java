package work_plan_builder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import work_plan_builder.abstract_parts.Work_process;
import work_plan_builder.plan_parts.Work_task;

public class Task_loader {
	private List<Work_task> tasks = new ArrayList<Work_task>();
	
	public Task_loader(){
		//Temporal hardcode
		List<DayOfWeek> delivery_days = new ArrayList<DayOfWeek>();
		delivery_days.add(DayOfWeek.THURSDAY);
		delivery_days.add(DayOfWeek.MONDAY);
		
		List<Work_process> list_of_processes_1 = new ArrayList<>();
		list_of_processes_1.add(new Work_process("Проращивание", 4));
		list_of_processes_1.add(new Work_process("Аэропоника", 15));
		tasks.add(new Work_task("Базилик", 0.4, 11, delivery_days, list_of_processes_1));
		
		list_of_processes_1 = new ArrayList<>();
		list_of_processes_1.add(new Work_process("Проращивание", 2));
		list_of_processes_1.add(new Work_process("Аэропоника", 6));
		tasks.add(new Work_task("Подсолнечник", 0.4, 4, delivery_days, list_of_processes_1, true, 9));
		
		list_of_processes_1 = new ArrayList<>();
		list_of_processes_1.add(new Work_process("Проращивание", 3));
		list_of_processes_1.add(new Work_process("Аэропоника", 10));
		tasks.add(new Work_task("Руккола", 0.4, 4, delivery_days, list_of_processes_1));
		
		list_of_processes_1 = new ArrayList<>();
		list_of_processes_1.add(new Work_process("Проращивание", 10));
		list_of_processes_1.add(new Work_process("Аэропоника", 5));
		tasks.add(new Work_task("Мизуна", 0.4, 4, delivery_days, list_of_processes_1));
		/*
		list_of_processes_1 = new ArrayList<>();
		list_of_processes_1.add(new Work_process("Проращивание", 4));
		list_of_processes_1.add(new Work_process("Аэропоника", 15));
		tasks[2] = new Work_task("Редис", 0.4, 2, delivery_days, list_of_processes_1);
		//tasks[0].print_values();
		*/
		//Temporal hardcode
	}
	
	LocalDate get_start_work_plan_date() {
		return LocalDate.of(2019, 11, 19);
	}
	
	public List<Work_task> get_task_list() {
		return new ArrayList<Work_task>(tasks);
	}
	

}
