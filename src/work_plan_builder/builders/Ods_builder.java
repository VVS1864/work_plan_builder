package work_plan_builder.builders;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jopendocument.dom.spreadsheet.CellStyle;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.dom.template.engine.DataFormatter;
import org.jopendocument.model.style.StyleTableCellProperties;


public abstract class Ods_builder {
	protected Sheet sheet;
	protected SpreadSheet spreadSheet;
	protected int init_table_column = 0;
	protected int init_table_row = 0;
	protected int columns = 1;
	protected int rows = 1;
	protected String table_name;
	protected Turns turns;

	public Ods_builder(Turns turns, String name) {
		this.turns = turns;
		this.table_name = name;

		set_initial_cell();
		rows = turns.get_prosesses_quantity() + 8;
		columns = turns.get_work_period() + 8;
		try {
			spreadSheet = SpreadSheet.createFromFile(new File("./default.ods"));
		}catch(IOException e) {
			System.err.println("Error, default file not found.");
		}
		sheet = spreadSheet.getSheet(0);
		sheet.setColumnCount(columns);
		sheet.setRowCount(rows);
		
		create_dates(get_date_list(turns.get_start_work_date(), columns));
		create_top();

		filling_alg();
	}

	protected void create_sheet() {
		try {
			spreadSheet.saveAs(new File(table_name + ".ods"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected List<LocalDate> get_date_list(LocalDate start_work_date, int days) {
		List<LocalDate> date_set = new ArrayList<>();
		for (int day = 0; day < days; day++) {
			date_set.add(start_work_date);
			start_work_date = start_work_date.plusDays(1);
		}
		return date_set;
	}

	protected void create_dates(List<LocalDate> date_set) {
		int day = 0;
		for (int column = init_table_column; column < date_set.size(); column++) {
			//sheet.getRange(1, column).setValue(date_set.get(day).format(DateTimeFormatter.ofPattern("EEEEE, dd MMM")));
			String fd = date_set.get(day).format(DateTimeFormatter.ofPattern("EE dd MMM"));
			sheet.setValueAt(fd, column, 1);
			day++;
			
			
		}

	}

	protected abstract void set_initial_cell();

	protected abstract void filling_alg();

	protected abstract void create_top();
}
