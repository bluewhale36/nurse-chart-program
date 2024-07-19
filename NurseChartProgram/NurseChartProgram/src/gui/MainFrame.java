package gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public LogInGUI lg = new LogInGUI(this);
	public NurseGUI ng = new NurseGUI(this);

	public MainFrame() {
		this.setVisible(true);
		this.setBounds(0, 0, 1200, 750);
		this.setResizable(false);
		this.setTitle("간호사 차트 관리 프로그램");

		lg.menu();

		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}
}
