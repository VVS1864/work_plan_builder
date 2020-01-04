package work_plan_builder.abstract_parts;

public class Work_phase {
		protected String name;
		protected int duration;
		
		public Work_phase(String name, int duration) {
			this.name = name;
			this.duration = duration;
		}
				
		public String get_name() {
			return name;
		}
		public int get_duration() {
			return duration;
		}
}
		
	


