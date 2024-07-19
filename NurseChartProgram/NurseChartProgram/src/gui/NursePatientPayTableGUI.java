package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dto.Patient;
import dto.ResRecord;

public class NursePatientPayTableGUI implements ActionListener, MouseListener {

	NurseGUI ng;

	private String[] header = { "환자성명", "주민번호", "접수(예약)일자", "진료의사", "의사번호", "진단질병", "처방내역", "결제수단", "결제금액" };
	private String[][] contents = new String[0][header.length];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};

	private JTable payTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(payTable);

	private JLabel selectPInfo;

	private JPopupMenu right;
	private JMenuItem info;

	public JLabel getSelectPInfo() {
		return selectPInfo;
	}

	public void setSelectPInfo(JLabel selectPInfo) {
		this.selectPInfo = selectPInfo;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getPayTable() {
		return payTable;
	}

	public void setPayTable(JTable payTable) {
		this.payTable = payTable;
	}

	public JScrollPane getTableScroll() {
		return tableScroll;
	}

	public void setTableScroll(JScrollPane tableScroll) {
		this.tableScroll = tableScroll;
	}

	public NursePatientPayTableGUI(NurseGUI n) {
		ng = n;

		payTable.getTableHeader().setReorderingAllowed(false);
		payTable.getTableHeader().setResizingAllowed(false);

		right = new JPopupMenu();
		info = new JMenuItem("환자 정보");

		right.add(info);

		ng.nppg.gridCP[0].add(tableScroll);

		selectPInfo = new JLabel("(환자 선택)");
		ng.nppg.gridCP1[1].add(selectPInfo);

		addInterface();
	}

	private void addInterface() {
		payTable.addMouseListener(this);
		info.addActionListener(this);
	}

	public void menu(String name, boolean pay) {
		delRow();
		addRow(ng.ndao.selectEndPatientWhereLike(name, pay));
		showBox();
	}

	public void showBox() {
		tableScroll.setVisible(false);
		tableScroll.setVisible(true);
	}

	public void delRow() {
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}

	public void addRow(ArrayList<Patient> pList) {
		for (Patient p : pList) {
			ResRecord rr = p.getEndRecList().get(0);
			String[] pp = new String[header.length];
			pp[0] = p.getName();
			pp[1] = p.getIdNum();
			pp[2] = rr.getInDate();
			pp[3] = rr.getD().getDocName();
			pp[4] = rr.getD().getDocNum();
			pp[5] = rr.getEr().getDisease();
			pp[6] = rr.getEr().getMedicine();
			pp[7] = rr.getPayment();
			pp[8] = rr.getPay();
			model.addRow(pp);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (payTable.getSelectedRowCount() == 1 && payTable.getSelectedColumnCount() == 1) {
			if (SwingUtilities.isRightMouseButton(e)) {
				right.show(tableScroll, e.getX(), e.getY());
			} else {
				int row = payTable.getSelectedRow();
				int column = payTable.getSelectedColumn();
				String name = payTable.getValueAt(row, 0).toString();
				String idNum6 = payTable.getValueAt(row, 1).toString().substring(0, 6);
				String idNum7 = payTable.getValueAt(row, 1).toString().substring(7);
				String disease = payTable.getValueAt(row, 5).toString();
				String medicine = payTable.getValueAt(row, 6).toString();
				selectPInfo.setText(payTable.getValueAt(row, 0) + "님의 " + payTable.getColumnName(column) + ": "
						+ payTable.getValueAt(row, column));
				ng.nppg.gridCP1[1].remove(selectPInfo);
				ng.nppg.gridCP1[1].add(selectPInfo);
				ng.nppg.nametf.setText("");
				ng.nppg.idNumtf6.setText("");
				ng.nppg.idNumtf7.setText("");
				ng.nppg.distf.setText("");
				ng.nppg.medtf.setText("");
				ng.nppg.nametf.setText(name);
				ng.nppg.idNumtf6.setText(idNum6);
				ng.nppg.idNumtf7.setText(idNum7);
				ng.nppg.distf.setText(disease);
				ng.nppg.medtf.setText(medicine);
				if (ng.nppg.payY.isSelected()) {
					ng.nppg.payB.setEnabled(false);
					ng.nppg.paymentCom.setSelectedItem(payTable.getValueAt(row, 7));
					ng.nppg.paytf.setText(payTable.getValueAt(row, 8).toString());
				} else if (ng.nppg.payN.isSelected()) {
					ng.nppg.payB.setEnabled(true);
				}
				showBox();
			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (payTable.getSelectedRowCount() == 1 && payTable.getSelectedColumnCount() == 1) {
			if (e.getSource().equals(info)) {
				ng.sf.sg.searchSpecificPatient(payTable.getValueAt(payTable.getSelectedRow(), 1).toString());
			}
		}

	}

}
