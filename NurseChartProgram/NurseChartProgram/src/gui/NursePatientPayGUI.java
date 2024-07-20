package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import dto.Doctor;
import dto.Patient;
import dto.ResRecord;

public class NursePatientPayGUI extends JPanel implements ActionListener, KeyListener {

	NurseGUI ng;

	JPanel gridC, gridS, gridCP[], gridCP1[], gridCP2[], gridCP1NI[], gridCP2DM[], gridCP2PP[], con[], name, idNum,
			disease, medicine, pay, payment;

	JTextField nametf, idNumtf6, idNumtf7, distf, medtf, paytf, pInfo[];

	JRadioButton payN, payY;

	String[] paymentC = { "(결제수단선택)", "카드", "현금(계좌이체)", "수납 중(카드)", "수납 중(현금)" };
	JComboBox paymentCom;

	JButton payB;

	private JOptionPane error = new JOptionPane();
	private JOptionPane success = new JOptionPane();

	private BevelBorder bbRai = new BevelBorder(BevelBorder.RAISED);
	private BevelBorder bbLow = new BevelBorder(BevelBorder.LOWERED);
	private LineBorder bLine = new LineBorder(Color.black, 1, true);

	public NursePatientPayGUI(NurseGUI n) {
		ng = n;

		payN = new JRadioButton("수납X");
		payY = new JRadioButton("수납O");

		gridC = new JPanel();
		gridS = new JPanel();

		gridCP = new JPanel[3];
		gridCP1 = new JPanel[4];
		gridCP2 = new JPanel[4];

		this.setLayout(new BorderLayout());
		this.add(gridC, "Center");
		this.add(gridS, "South");

		gridC.setBorder(bLine);
		gridS.setBorder(bLine);

		gridC.setLayout(new GridLayout(3, 1));

		for (int i = 0; i < gridCP.length; i++) {
			gridCP[i] = new JPanel();
			gridCP[i].setLayout(new BoxLayout(gridCP[i], BoxLayout.X_AXIS));
			gridC.add(gridCP[i]);
		}

		gridCP[0].add(new JLabel(" 환자선택: "));

		gridCP[1].setLayout(new GridLayout(4, 1));
		for (int i = 0; i < gridCP1.length; i++) {
			gridCP1[i] = new JPanel();
			gridCP1[i].setLayout(new BoxLayout(gridCP1[i], BoxLayout.X_AXIS));
			gridCP[1].add(gridCP1[i]);
		}
		gridCP1[0].setLayout(new FlowLayout(FlowLayout.CENTER));
		gridCP1[0].add(payN);
		gridCP1[0].add(payY);
		payN.setSelected(true);
		gridCP1[1].setLayout(new FlowLayout(FlowLayout.CENTER));

		gridCP[2].setLayout(new GridLayout(4, 1));
		for (int i = 0; i < gridCP2.length; i++) {
			gridCP2[i] = new JPanel();
			gridCP2[i].setLayout(new BoxLayout(gridCP2[i], BoxLayout.X_AXIS));
			gridCP[2].add(gridCP2[i]);
		}

		gridCP1NI = new JPanel[2];
		gridCP2DM = new JPanel[2];
		gridCP2PP = new JPanel[2];

		// 환자성명 및 주민번호
		gridCP1[2].setLayout(new GridLayout(1, 2));
		for (int i = 0; i < gridCP1NI.length; i++) {
			gridCP1NI[i] = new JPanel();
			gridCP1NI[i].setLayout(new BoxLayout(gridCP1NI[i], BoxLayout.X_AXIS));
			gridCP1[2].add(gridCP1NI[i]);
		}

		// 진단질병 및 처방
		gridCP2[0].setLayout(new GridLayout(1, 2));
		for (int i = 0; i < gridCP2DM.length; i++) {
			gridCP2DM[i] = new JPanel();
			gridCP2DM[i].setLayout(new BoxLayout(gridCP2DM[i], BoxLayout.X_AXIS));
			gridCP2[0].add(gridCP2DM[i]);
		}

		// 수납정보
		gridCP2[2].setLayout(new GridLayout(1, 2));
		for (int i = 0; i < gridCP2PP.length; i++) {
			gridCP2PP[i] = new JPanel();
			gridCP2PP[i].setLayout(new BoxLayout(gridCP2PP[i], BoxLayout.X_AXIS));
			gridCP2[2].add(gridCP2PP[i]);
		}

		con = new JPanel[6];
		name = new JPanel();
		idNum = new JPanel();
		disease = new JPanel();
		medicine = new JPanel();
		pay = new JPanel();
		payment = new JPanel();

		pInfo = new JTextField[6];
		nametf = new JTextField();
		idNumtf6 = new JTextField();
		idNumtf7 = new JTextField();
		distf = new JTextField();
		medtf = new JTextField();
		paytf = new JTextField();

		con[0] = name;
		con[1] = idNum;
		con[2] = disease;
		con[3] = medicine;
		con[4] = pay;
		con[5] = payment;

		pInfo[0] = nametf;
		pInfo[1] = idNumtf6;
		pInfo[2] = idNumtf7;
		pInfo[3] = distf;
		pInfo[4] = medtf;
		pInfo[5] = paytf;

		for (int i = 0; i < con.length; i++) {
			con[i].setLayout(new BoxLayout(con[i], BoxLayout.X_AXIS));
		}

		paymentCom = new JComboBox(paymentC);

		name.add(new JLabel(" 환자성명: "));
		name.add(nametf);
		gridCP1NI[0].add(name);

		idNum.add(new JLabel(" 주민번호: "));
		idNum.add(idNumtf6);
		idNum.add(new JLabel(" - "));
		idNum.add(idNumtf7);
		gridCP1NI[1].add(idNum);

		disease.add(new JLabel(" 진단질병: "));
		disease.add(distf);
		gridCP2DM[0].add(disease);

		medicine.add(new JLabel(" 처방내역: "));
		medicine.add(medtf);
		gridCP2DM[1].add(medicine);

		payment.add(new JLabel(" 결제수단: "));
		payment.add(paymentCom);
		gridCP2PP[0].add(payment);

		pay.add(new JLabel(" 결제금액: "));
		pay.add(paytf);
		gridCP2PP[1].add(pay);

		payB = new JButton("수납처리");
		gridS.add(payB);
		payB.setBorder(bbRai);
		payB.setEnabled(false);

		idNumtf6.setEnabled(false);
		idNumtf7.setEnabled(false);
		distf.setEnabled(false);
		medtf.setEnabled(false);

		addInterface();
	}

	public void addInterface() {
		nametf.addKeyListener(this);
		payB.addActionListener(this);
		paytf.addKeyListener(this);
		payY.addActionListener(this);
		payN.addActionListener(this);
	}

	public void menu() {
		ng.grid2C2.add(this);
		payNSelected();
		showBox();
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}

	private void payYSelected() {
		payN.setSelected(false);
		ng.npptg.menu(nametf.getText(), true);
		paytf.setEnabled(false);
		paymentCom.setEnabled(false);
		erase();
	}

	private void payNSelected() {
		payY.setSelected(false);
		ng.npptg.menu(nametf.getText(), false);
		paytf.setEnabled(true);
		paymentCom.setEnabled(true);
		erase();
	}

	public void erase() {
		for (int i = 0; i < pInfo.length; i++) {
			pInfo[i].setText("");
		}
		paymentCom.setSelectedIndex(0);
		ng.npptg.getSelectPInfo().setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(payB)) {
			if (paymentCom.getSelectedIndex() == 0 || paytf.getText().length() < 1) {
				error.showMessageDialog(null, "결제수단 및 결제금액을 모두 지정해주세요.", "수납 오류", JOptionPane.ERROR_MESSAGE);
			} else {
				if (ng.npptg.getPayTable().getSelectedRowCount() == 1
						&& ng.npptg.getPayTable().getSelectedColumnCount() == 1) {
					JTable curTable = ng.npptg.getPayTable();
					int row = curTable.getSelectedRow();
					Patient p = new Patient();
					ResRecord rr = new ResRecord();
					Doctor d = new Doctor();
					p.setName(nametf.getText());
					p.setIdNum(idNumtf6.getText() + idNumtf7.getText());
					rr.setInDate(curTable.getValueAt(row, 2).toString());
					d.setDocName(curTable.getValueAt(row, 3).toString());
					d.setDocNum(curTable.getValueAt(row, 4).toString());
					rr.setPayment(paymentCom.getSelectedItem().toString());
					rr.setPay(paytf.getText());
					rr.setD(d);
					p.getResRecList().add(rr);
					if (ng.ndao.updateProgressPay(p)) {
						success.showMessageDialog(null, "수납처리가 완료되었습니다.", "수납 성공", JOptionPane.INFORMATION_MESSAGE);
					} else {
						error.showMessageDialog(null, "수납에 실패하였습니다.", "수납 오류", JOptionPane.ERROR_MESSAGE);
					}
					if (payY.isSelected()) {
						ng.npptg.menu("", true);
					} else if (payN.isSelected()) {
						ng.npptg.menu("", false);
					}
				}
			}
		}
		if (e.getSource().equals(payY)) {
			if (payY.isSelected() && payN.isSelected()) {
				payYSelected();
			}
		} else if (e.getSource().equals(payN)) {
			if (payN.isSelected() && payY.isSelected()) {
				payNSelected();
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource().equals(nametf)) {
			if (nametf.getText().length() > 0) {
				if (payY.isSelected()) {
					ng.npptg.menu(nametf.getText(), true);
				} else if (payN.isSelected()) {
					ng.npptg.menu(nametf.getText(), false);
				}
				ng.npptg.getSelectPInfo().setText("검색된 환자: " + ng.npptg.getModel().getRowCount() + "건.");
			}
			if (ng.npptg.getModel().getRowCount() == 0) {
				gridCP1[0].remove(ng.npptg.getSelectPInfo());
				ng.npptg.getSelectPInfo().setText("검색된 환자가 없습니다.");
				gridCP1[0].add(ng.npptg.getSelectPInfo());
				ng.npptg.showBox();
			} else {
				gridCP1[0].remove(ng.npptg.getSelectPInfo());
				ng.npptg.getSelectPInfo().setText("검색된 환자: " + ng.npptg.getModel().getRowCount() + "건.");
				gridCP1[0].add(ng.npptg.getSelectPInfo());
				ng.npptg.showBox();
			}
		}
		if (e.getSource().equals(paytf)) {
			paytf.setText(paytf.getText().replaceAll("[^0-9]", ""));
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
