package work_plan_builder.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import work_plan_builder.Config;
import work_plan_builder.Core;
import work_plan_builder.file_tools.Rm_dir;
import work_plan_builder.file_tools.Zip;

public class GUI extends JFrame{
	GUI this_class;
	public GUI() {
		super("Авторассчет таблиц");
		this_class = this;
		setLayout(new BorderLayout());
		
		JPanel panel_north = new JPanel(); 
		panel_north.setLayout(new BoxLayout(panel_north, BoxLayout.Y_AXIS));
		panel_north.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel label_top = new JLabel("<html><div style='font-size:12px; text-align: center;'>"+
				"Укажите вводные данные в файле<rb>" +
				" 'Задание.ods' и сохраните его, <rb>"+
				"затем нажмите Рассчитать."+
				"</div></html>"); 
		panel_north.add(label_top);
		label_top.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel panel_center = new JPanel(); 
		panel_center.setLayout(new BoxLayout(panel_center, BoxLayout.Y_AXIS));
		
		JButton b = new JButton("Рассчитать");
		panel_center.add(b);
		b.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		JPanel panel_south = new JPanel(); 
		panel_south.setLayout(new BoxLayout(panel_south, BoxLayout.Y_AXIS));
		
		JLabel label_bottom = new JLabel("<html><div style='font-size:12px; text-align: center;'>"+" "+"</div></html>", SwingConstants.CENTER); 
		panel_south.add(label_bottom);
		panel_south.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		add(panel_north, BorderLayout.NORTH);
		add(panel_south, BorderLayout.SOUTH);
		add(panel_center, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,200);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		setVisible(true);
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){  
				new Thread() {
			        public void run() {
			        	try {
				        	label_bottom.setText("<html><div style='font-size:14px; text-align: center;'>"+
				        			"Рассчитываем...                                 " +
				        			"</div></html>");
							label_bottom.validate();
							label_bottom.repaint();
							validate();
							repaint();
							Core start_calc = new Core();
							
							label_bottom.setText("<html><div style='font-size:14px; text-align: center;'>"+
									"Готово! Можно еще раз.                             "+
									"</div></html>");
							//throw new java.lang.Exception("this is not quite as bad");
			        	}catch(Exception e) {
			        		String sStackTrace = get_error_string(e);
			        		String error_folder = create_log_folder();
			        		copy_config(error_folder);
			        		error_trace_to_log(error_folder, sStackTrace);
			        		try {
								Zip.pack(error_folder, error_folder+".zip");
								Rm_dir.deleteFileOrFolder(Paths.get(error_folder));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			        		
			        		JOptionPane.showMessageDialog(this_class,
			        			    "Произошла ошибка. Программа будет закрыта. \n"+
			        				".zip отчет об ошибке сформирован в папке errors, \n"+
			        			    "отправте его разработчику: simonovsen@yandex.ru",
			        			    "Error",
			        			    JOptionPane.ERROR_MESSAGE);
			        		
			        		System.exit(0);
			        	}					
					}

					
			    }.start();
				
				
		  };
		});
	}
	/**
	 * Get stack trace as a string
	 * @param e
	 * @return
	 */
	private String get_error_string(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String sStackTrace = sw.toString(); 
		return sStackTrace;
	}
	
	private String create_log_folder() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		String current_moment =LocalDateTime.now().format(formatter);
		current_moment = current_moment.replaceAll(":", "-");
		String error_folder = "./errors/"+current_moment;
		
		new File(error_folder).mkdirs();
		return error_folder;
	}
	private void copy_config(String error_folder) {
		File src = new File(Config.conf_name);
		File dist = new File(error_folder + "/" + src.getName());
		try {
			Files.copy(src.toPath(), dist.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) {
			System.err.println("some err in copy_config");
		}
		
	}
	private void error_trace_to_log(String error_folder, String sStackTrace) {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(error_folder+"/err_log.txt"), "utf-8"))) {
			try {
				writer.write(sStackTrace);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	

}
