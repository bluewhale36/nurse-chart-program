package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogInGUI extends JPanel implements ActionListener {

	MainFrame mf = null;

	JOptionPane logIn, errorM, success;

	JPanel cPanel = new JPanel();
	JPanel sPanel = new JPanel();
	JPanel[] cPanelg = new JPanel[7];

	JLabel error = new JLabel("������ ID �Ǵ� PW�� ���� �ʽ��ϴ�.");

	JTextField idtf = new JTextField(20);
	JTextField pwtf = new JTextField(20);

	JButton nurse = new JButton("��ȣ��");
	JButton exit = new JButton("����");

	public LogInGUI(MainFrame f) {
		mf = f;

		logIn = new JOptionPane();

		this.setLayout(new BorderLayout());

		this.add(cPanel, "Center");
		this.add(sPanel, "South");

		cPanel.setLayout(new GridLayout(7, 1));
		for (int i = 0; i < cPanelg.length; i++) {
			cPanelg[i] = new JPanel();
			cPanel.add(cPanelg[i]);
		}

		cPanelg[2].add(new JLabel("������ �α����� �����մϴ�."));
		cPanelg[3].add(new JLabel("ID"));
		cPanelg[3].add(idtf);
		cPanelg[4].add(new JLabel("PW"));
		cPanelg[4].add(pwtf);

		sPanel.add(nurse);
		sPanel.add(exit);

		nurse.addActionListener(this);
		exit.addActionListener(this);

	}

	public void menu() {
		mf.add(this);
		erase();
		this.setVisible(true);
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}

	public void erase() {
		idtf.setText("");
		pwtf.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(nurse)) {
			if (this.idtf.getText().equals("dntmdgns") && this.pwtf.getText().equals("wkdcodbs")) {
				mf.remove(this);
				cPanelg[5].remove(error); // ���� �޼��� �����
				idtf.setText(""); // �ԷµǾ��ִ� ���̵� ��������
				pwtf.setText(""); // �ԷµǾ��ִ� ��й�ȣ ��������
				mf.ng.menu();
				mf.ng.showBox();
			} else {
				cPanelg[5].add(error); // ���� �޼��� ����
				idtf.setText("");
				pwtf.setText("");
				this.showBox();
			}
		}
		if (e.getSource().equals(exit)) {
			System.exit(0); // �ý��� ����
		}

	}

}
