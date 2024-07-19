package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dto.Doctor;
import dto.Patient;
import dto.ResRecord;

public class NursePatientReceptionGUI extends JPanel implements ActionListener, ItemListener, KeyListener {

	NurseGUI ng = null;

	private JOptionPane success = new JOptionPane();
	private JOptionPane error = new JOptionPane();

	JPanel[] gridP = new JPanel[4];

	JPanel[] gridP0 = new JPanel[3];
	JPanel[] gridP2 = new JPanel[3];
	JPanel[] gridP3 = new JPanel[3];

	JPanel name = new JPanel();
	JPanel idNum = new JPanel();
	JPanel inDate = new JPanel();
	JPanel docName = new JPanel();
	JPanel docNum = new JPanel();
	JPanel tableP = new JPanel();
	JPanel pSearch = new JPanel();

	JTextField nametf = new JTextField();
	JTextField idNumtf6 = new JTextField();
	JTextField idNumtf7 = new JTextField();
	JTextField docNumtf = new JTextField("0");

	private String[] docNameCom;
	private JComboBox<String> docNaCom;
	private String[] docNumCom;

	private JComboBox<String> receptionCom, yearCom, monthCom, dayCom, hourCom, minuteCom;

	private String[] recCom = { "��������", "��������" };

	private String[] exYear = { "Year" };

	private String[] exMonth = { "Month" };

	private String[] exDay = { "Day" };
	private String[] day;

	private String[] exHour = { "Hour" };

	private String[] exMinute = { "Minute" };

	private JComboBox[] inDateCom = new JComboBox[5];

	JPanel gridS = new JPanel();

	JButton reception = new JButton("����");
	JButton erase = new JButton("�����");

	private LineBorder bLine;

	public JComboBox<String> getReceptionCom() {
		return receptionCom;
	}

	public void setReceptionCom(JComboBox<String> receptionCom) {
		this.receptionCom = receptionCom;
	}

	public NursePatientReceptionGUI(NurseGUI n) {
		ng = n;

		bLine = new LineBorder(Color.black, 1, true);

		docNameCom = ng.npig.getDocNameCom();
		docNaCom = new JComboBox<String>(docNameCom);
		docNumCom = ng.npig.getDocNumCom();

		receptionCom = new JComboBox<String>(recCom);

		yearCom = new JComboBox<String>(exYear);

		monthCom = new JComboBox<String>(exMonth);

		dayCom = new JComboBox<String>(exDay);

		hourCom = new JComboBox<String>(exHour);

		minuteCom = new JComboBox<String>(exMinute);

		inDateCom[0] = yearCom;
		inDateCom[1] = monthCom;
		inDateCom[2] = dayCom;
		inDateCom[3] = hourCom;
		inDateCom[4] = minuteCom;

		inDateComSetEnabled(false);
		receptionCom.setEnabled(false);

		this.setLayout(new GridLayout(4, 1));
		this.setBorder(bLine);

		for (int i = 0; i < gridP.length; i++) {
			gridP[i] = new JPanel();
			this.add(gridP[i]);
			if (i != 1) { // �ι�°�� jtable
				gridP[i].setLayout(new GridLayout(3, 1));
			}
		}

		for (int i = 0; i < gridP0.length; i++) {
			gridP0[i] = new JPanel();
			gridP[0].add(gridP0[i]);
		}

		gridP0[1].setLayout(new GridLayout(1, 2));

		name.setLayout(new BoxLayout(name, BoxLayout.X_AXIS));
		name.add(new JLabel(" ȯ�ڼ���: "));
		name.add(nametf);
		gridP0[1].add(name);

		nametf.addKeyListener(this);

		idNum.setLayout(new BoxLayout(idNum, BoxLayout.X_AXIS));
		idNum.add(new JLabel(" �ֹι�ȣ: "));
		idNum.add(idNumtf6);
		idNum.add(new JLabel(" - "));
		idNum.add(idNumtf7);
		gridP0[1].add(idNum);

		idNumtf6.setEnabled(false);
		idNumtf7.setEnabled(false);

		for (int i = 0; i < gridP2.length; i++) {
			gridP2[i] = new JPanel();
			gridP[2].add(gridP2[i]);
		}

		gridP2[2].setLayout(new GridLayout(1, 2));

		docName.setLayout(new BoxLayout(docName, BoxLayout.X_AXIS));
		docName.add(new JLabel(" �����ǻ�: "));
		docName.add(docNaCom);
		gridP2[2].add(docName);

		docNaCom.addItemListener(this);

		docNum.setLayout(new BoxLayout(docNum, BoxLayout.X_AXIS));
		docNum.add(new JLabel(" �ǻ��ȣ: "));
		docNum.add(docNumtf);
		docNumtf.setEnabled(false);
		gridP2[2].add(docNum);

		for (int i = 0; i < gridP3.length; i++) {
			gridP3[i] = new JPanel();
			gridP[3].add(gridP3[i]);
		}

		gridP3[1].setLayout(new BoxLayout(gridP3[1], BoxLayout.X_AXIS));
		gridP3[1].add(new JLabel(" �������� : "));
		gridP3[1].add(receptionCom);
		gridP3[1].add(new JLabel(" - "));
		for (int i = 0; i < inDateCom.length; i++) {
			gridP3[1].add(inDateCom[i]);
		}

		gridP[1].setLayout(new BoxLayout(gridP[1], BoxLayout.X_AXIS));
		gridP[1].add(new JLabel(" ȯ�ڼ���: "));

		gridS.add(reception);
		gridS.add(erase);
		gridS.setBorder(bLine);

		addInterface();
		reception.addActionListener(this);
		erase.addActionListener(this);

	}

	public void addInterface() {
		receptionCom.addItemListener(this);
		for (JComboBox jc : inDateCom) {
			jc.addItemListener(this);
		}
	}

	public void removeInterface() {
		receptionCom.removeItemListener(this);
		for (JComboBox jc : inDateCom) {
			jc.removeItemListener(this);
		}
	}

	public void menu() {
		ng.grid1C1.add(this, "Center");
		ng.grid1C1.add(gridS, "South");
		showBox();
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
		dayCom.setVisible(false);
		dayCom.setVisible(true);
	}

	// ����� ��ư
	public void erase() {
		removeInterface();
		nametf.setText("");
		idNumtf6.setText("");
		idNumtf7.setText("");
		docNaCom.setSelectedIndex(0);
		ng.nptg.delRow();
		gridP2[0].remove(ng.nptg.getSelectPInfo());
		rollbackInDateCom(0);
		receptionCom.setSelectedIndex(0);
		receptionCom.setEnabled(false);
		inDateComSetEnabled(false);
		reception.setEnabled(false);
		erase.setEnabled(false);
		addInterface();
	}

	// ��¥ �޺��ڽ� Ȱ��ȭ/��Ȱ��ȭ
	private void inDateComSetEnabled(boolean flag) {
		for (int i = 0; i < inDateCom.length; i++) {
			inDateCom[i].setEnabled(flag);
		}
	}

	// ��¥ ���� �� ���� ���� ��¥ ���� �ʱ�ȭ
	public void rollbackInDateCom(int idx) {
		removeInterface();
		for (int i = idx; i < inDateCom.length; i++) {
			while (inDateCom[i].getItemCount() != 1) {
				inDateCom[i].removeItemAt(1);
			}
		}
		addInterface();
	}

	// ȯ�� ���� ����
	public void patientReception() {
		if (ng.nptg.getpTable().getSelectedColumnCount() != 0 && ng.nptg.getpTable().getSelectedRowCount() != 0) {
			if (docNaCom.getSelectedIndex() != 0) { // �ǻ� �������� ��
				// dto�� ���� ����
				Patient p = new Patient();
				p.setName(nametf.getText());
				p.setIdNum(idNumtf6.getText() + idNumtf7.getText());
				Doctor d = new Doctor();
				d.setDocName(docNameCom[docNaCom.getSelectedIndex()]);
				d.setDocNum(docNumCom[docNaCom.getSelectedIndex()]);
				ResRecord rr = new ResRecord();
				p.getResRecList().add(rr);
				rr.setD(d);
				if (receptionCom.getSelectedIndex() == 1) { // ��������
					rr.setInDate(ng.cd.inDateComToString(inDateCom));
					if (rr.getInDate() == null) { // �������� �� ������ ������ ���� ���
						error.showMessageDialog(null, "�������ڸ� ��Ȯ�� �����ϼ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
						return;
					}
					rr.setProgress("����");
				} else if (receptionCom.getSelectedIndex() == 0) { // ��������
					rr.setProgress("���� ���");
				}
				if (ng.ndao.insertResRec(p)) { // dao�۾� �Ϸ�Ǿ��� �� (return:true)
					success.showMessageDialog(null, "�����Ǿ����ϴ�.", "���� ����", JOptionPane.INFORMATION_MESSAGE);
				} else { // dao�۾� ���� �߻��� (return:false)
					error.showMessageDialog(null, "������ �����߽��ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
				}
				// ������...panel���� ���õǾ��ִ� �κ� jtable ��������
				if (ng.nprrg.getDisabledButton() != null) {
					ng.nprrg.menu(ng.nprrg.getDisabledButton());
				}
				showBox();
			} else {
				error.showMessageDialog(null, "�ǻ縦 �����ϼ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			error.showInternalMessageDialog(null, "ȯ�ڸ� �����ϼ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void receptionComIdx0Selected() {
		removeInterface();
		inDateComSetEnabled(false);
		rollbackInDateCom(0);
		ng.progressButtonSelected(ng.waiting);
		addInterface();
	}

	public void receptionComIdx1Selected() {
		inDateComSetEnabled(true);
		yearComChange();
		ng.progressButtonSelected(ng.reservation);
	}

	private void yearComChange() {
		removeInterface();
		rollbackInDateCom(0);
		String[] year = ng.cd
				.insertYearCom(ng.nptg.getpTable().getValueAt(ng.nptg.getpTable().getSelectedRow(), 5).toString());
		for (int i = 1; i < year.length; i++) {
			yearCom.insertItemAt(year[i], i);
		}
		addInterface();
	}

	private void monthComChange() {
		removeInterface();
		rollbackInDateCom(1);
		String inDate = ng.nptg.getpTable().getValueAt(ng.nptg.getpTable().getSelectedRow(), 5).toString();
		String[] month = ng.cd.insertMonthCom(inDate, yearCom.getSelectedIndex());
		for (int i = 1; i < month.length; i++) {
			monthCom.insertItemAt(month[i], i);
		}
		addInterface();
	}

	private void dayComChange() {
		removeInterface();
		rollbackInDateCom(2);
		String inDate = ng.nptg.getpTable().getValueAt(ng.nptg.getpTable().getSelectedRow(), 5).toString();
		String[] day = ng.cd.insertDayCom(inDate, yearCom.getSelectedItem().toString(),
				monthCom.getSelectedItem().toString());
		for (int i = 1; i < day.length; i++) {
			dayCom.insertItemAt(day[i], i);
		}
		addInterface();
	}

	private void hourComChange() {
		removeInterface();
		rollbackInDateCom(3);
		String inDate = ng.nptg.getpTable().getValueAt(ng.nptg.getpTable().getSelectedRow(), 5).toString();
		String[] hour = ng.cd.insertHourCom(inDate, yearCom.getSelectedIndex(), monthCom.getSelectedIndex(),
				dayCom.getSelectedIndex());
		for (int i = 1; i < hour.length; i++) {
			hourCom.insertItemAt(hour[i], i);
		}
		addInterface();
	}

	private void minComChange() {
		removeInterface();
		rollbackInDateCom(4);
		String inDate = ng.nptg.getpTable().getValueAt(ng.nptg.getpTable().getSelectedRow(), 5).toString();
		String[] min = ng.cd.insertMinCom(inDate, yearCom.getSelectedIndex(), monthCom.getSelectedIndex(),
				dayCom.getSelectedIndex(), hourCom.getSelectedIndex());
		for (int i = 1; i < min.length; i++) {
			minuteCom.insertItemAt(min[i], i);
		}
		addInterface();
	}

	@Override
	public void actionPerformed(ActionEvent e) { // ������ư, ������ư
		// ���� ��ư Ŭ�� -> ȯ�� ����
		if (e.getSource().equals(reception)) {
			patientReception();
		}
		// ����� ��ư Ŭ�� -> �Էµ� ���� �ʱ�ȭ
		if (e.getSource().equals(erase)) {
			erase();
			showBox();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) { // �ǻ����޺��ڽ�, �����������������޺��ڽ�, ��¥�޺��ڽ�
		// �ǻ� ��� combobox ���ÿ� ���� �ǻ� ���� ��ȣ �ڵ� �Է�
		if (e.getSource().equals(docNaCom)) {
			int docIdx = docNaCom.getSelectedIndex();
			docNumtf.setText("");
			docNumtf.setText(docNumCom[docIdx]);
		}
		// �������� �� �������� ���ÿ� ���� ��¥ �޺��ڽ� Ȱ��ȭ/��Ȱ��ȭ
		if (e.getSource().equals(receptionCom)) {
			if (receptionCom.getSelectedItem().equals("��������")) {
				receptionComIdx0Selected();
			} else if (receptionCom.getSelectedItem().equals("��������")) {
				receptionComIdx1Selected();
			}
		}
		// �������� �� ��¥ �޺��ڽ��� ���� ������ ���ο� ���� ���� ���� ������ ��¥ �޺��ڽ��� item insert
		if (e.getSource().equals(yearCom)) {
			monthComChange();
		} else if (e.getSource().equals(monthCom)) {
			dayComChange();
		} else if (e.getSource().equals(dayCom)) {
			hourComChange();
		} else if (e.getSource().equals(hourCom)) {
			minComChange();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) { // ���� ����

	}

	@Override
	public void keyReleased(KeyEvent e) { // �̸�tf�� ȯ�� �˻� �� jtable ���
		if (e.getSource().equals(nametf)) {
			if (nametf.getText().length() > 0) {
				ng.nptg.menu(nametf.getText());
			}
			if (ng.nptg.getModel().getRowCount() == 0) {
				gridP2[0].remove(ng.nptg.getSelectPInfo());
				ng.nptg.getSelectPInfo().setText("�˻��� ȯ�ڰ� �����ϴ�.");
				gridP2[0].add(ng.nptg.getSelectPInfo());
				ng.nptg.showBox();
			} else {
				gridP2[0].remove(ng.nptg.getSelectPInfo());
				ng.nptg.getSelectPInfo().setText("�˻��� ȯ��: " + ng.nptg.getModel().getRowCount() + "��.");
				gridP2[0].add(ng.nptg.getSelectPInfo());
				ng.nptg.showBox();
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) { // �������

	}

}
