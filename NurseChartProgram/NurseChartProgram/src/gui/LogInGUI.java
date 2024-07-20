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

	JLabel error = new JLabel("관리자 ID 또는 PW가 맞지 않습니다.");

	JTextField idtf = new JTextField(20);
	JTextField pwtf = new JTextField(20);

	JButton nurse = new JButton("간호사");
	JButton exit = new JButton("종료");

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

		cPanelg[2].add(new JLabel("관리자 로그인을 진행합니다."));
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
				cPanelg[5].remove(error); // 에러 메세지 지우기
				idtf.setText(""); // 입력되어있던 아이디 공백으로
				pwtf.setText(""); // 입력되어있던 비밀번호 공백으로
				mf.ng.menu();
				mf.ng.showBox();
			} else {
				cPanelg[5].add(error); // 에러 메세지 띄우기
				idtf.setText("");
				pwtf.setText("");
				this.showBox();
			}
		}
		if (e.getSource().equals(exit)) {
			System.exit(0); // 시스템 종료
		}

	}

}
