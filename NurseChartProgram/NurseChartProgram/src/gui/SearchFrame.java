package gui;

import javax.swing.JFrame;

public class SearchFrame extends JFrame {

	NurseGUI ng;

	public SearchGUI sg = new SearchGUI(this);

	public SearchFrame(NurseGUI n) {
		ng = n;

		this.setBounds(46, 46, 1104, 800);
		this.setTitle("ȯ�� ���� ��ȸ");

	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}

	public void menu() {
		showBox();
	}

}
