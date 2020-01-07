package work_plan_builder.farm;

import work_plan_builder.plan_parts.Turn;

public class Box {
	boolean is_empty = false;
	Turn turn;
	public Box(Turn turn) {
		this.turn = turn;
		turn.set_box(this);
		is_empty = false;
	}
	
	public Turn get_turn() {
		return turn;
	}
}
