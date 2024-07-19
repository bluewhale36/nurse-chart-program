package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dto.Patient;

public class NursePatientResRecTableGUI implements MouseListener, ActionListener {

	NurseGUI ng = null;

	private String[] header = { "환자성명", "주민번호", "접수일자", "진료의사", "의사번호", "결제수단", "결제금액" };
	private String[][] contents = new String[0][header.length];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};

	private JTable resTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(resTable);

	private JLabel selectPInfo = new JLabel();

	private JPopupMenu rightClick;
	private JMenu progress;
	private JMenuItem[] resrec = new JMenuItem[5];
	private JMenuItem info, waiting, surgery, reservation, done, cancellation;

	public JTable getResTable() {
		return resTable;
	}

	public void setResTable(JTable resTable) {
		this.resTable = resTable;
	}

	public JScrollPane getTableScroll() {
		return tableScroll;
	}

	public void setTableScroll(JScrollPane tableScroll) {
		this.tableScroll = tableScroll;
	}

	public JLabel getSelectPInfo() {
		return selectPInfo;
	}

	public void setSelectPInfo(JLabel selectPInfo) {
		this.selectPInfo = selectPInfo;
	}

	public String[] getHeader() {
		return header;
	}

	public NursePatientResRecTableGUI(NurseGUI n) {
		ng = n;

		rightClick = new JPopupMenu();

		progress = new JMenu("진행 상황 갱신");
		info = new JMenuItem("환자 정보");

		waiting = new JMenuItem("진료 대기");
		surgery = new JMenuItem("진료 중");
		reservation = new JMenuItem("예약");
		done = new JMenuItem("완료");
		cancellation = new JMenuItem("취소");

		resTable.getTableHeader().setReorderingAllowed(false);
		resTable.getTableHeader().setResizingAllowed(false);
		resTable.getColumn("환자성명").setPreferredWidth(25);
		resTable.getColumn("주민번호").setPreferredWidth(47);
		resTable.getColumn("진료의사").setPreferredWidth(15);
		resTable.getColumn("의사번호").setPreferredWidth(15);
		resTable.getColumn("결제수단").setPreferredWidth(25);
		resTable.getColumn("결제금액").setPreferredWidth(20);

		resrec[0] = waiting;
		resrec[1] = surgery;
		resrec[2] = reservation;
		resrec[3] = done;
		resrec[4] = cancellation;

		rightClick.add(progress);
		rightClick.add(info);
		for (int i = 0; i < resrec.length; i++) {
			progress.add(resrec[i]);
		}

		addInterface();
	}

	public void menu(String progress) {
		selectPInfo.setText("(환자 선택)");
		delRow();
		addRow(ng.ndao.selectresRec(progress));
		removeThisMenuItem(progress);

		ng.nprrg.gridC.add(tableScroll);
		ng.nprrg.gridS.add(selectPInfo);
		showBox();
	}

	private void addInterface() {
		resTable.addMouseListener(this);
		this.progress.addMouseListener(this);
		this.progress.addActionListener(this);
		info.addActionListener(this);
		for (int i = 0; i < resrec.length; i++) {
			resrec[i].addActionListener(this);
		}
	}

	private void removeInterface() {
		resTable.removeMouseListener(this);
		this.progress.removeMouseListener(this);
		this.progress.removeActionListener(this);
		info.removeMouseListener(this);
		info.removeActionListener(this);
		for (int i = 0; i < resrec.length; i++) {
			resrec[i].removeActionListener(this);
		}
	}

	public void showBox() {
		tableScroll.setVisible(false);
		tableScroll.setVisible(true);
	}

	public void delRow() {
		while (resTable.getRowCount() != 0) {
			model.removeRow(0);
		}
	}

	public void addRow(ArrayList<Patient> pList) {
		for (Patient p : pList) {
			String[] info = new String[header.length];
			info[0] = p.getName();
			info[1] = p.getIdNum();
			info[2] = p.getResRecList().get(0).getInDate();
			info[3] = p.getResRecList().get(0).getD().getDocName();
			info[4] = p.getResRecList().get(0).getD().getDocNum();
			info[5] = p.getResRecList().get(0).getPayment();
			info[6] = p.getResRecList().get(0).getPay();
			model.addRow(info);
		}
		showBox();
	}

	private void removeThisMenuItem(String name) {
		progress.removeAll();
		rightClick.remove(progress);
		if (!(name.equals("완료") && name.equals("진료 중"))) {
			rightClick.add(progress);
			for (int i = 0; i < resrec.length; i++) {
				if (!(resrec[i].getText().equals(name))) {
					progress.add(resrec[i]);
				}
			}
		}
	}

	public void updateProgress(int row, String progress, JTable curTable) {
		String inDate = curTable.getValueAt(row, 2).toString();
		String docNum = curTable.getValueAt(row, 4).toString();
		ng.ndao.updateProgress(progress, inDate, docNum);
	}

	@Override
	public void mouseClicked(MouseEvent e) { // 우클릭 시 메뉴 띄우기, 셀 선택시 해당 정보 표출
		if (resTable.getSelectedColumnCount() == 1 && resTable.getSelectedRowCount() == 1) {
			if (SwingUtilities.isRightMouseButton(e)) {
				rightClick.show(tableScroll, e.getX(), e.getY());
			} else if (e.getSource().equals(resTable)) {
				int row = resTable.getSelectedRow();
				int column = resTable.getSelectedColumn();
				selectPInfo.setText(resTable.getValueAt(row, 0) + "님의 " + resTable.getColumnName(column) + ": "
						+ resTable.getValueAt(row, column));
				ng.nprrg.gridS.removeAll();
				ng.nprrg.gridS.add(selectPInfo);
				ng.nprrg.showBox();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) { // 우클릭 후 진행 상황 갱신
		if (resTable.getSelectedColumnCount() == 1 && resTable.getSelectedRowCount() == 1) {
			int row = resTable.getSelectedRow();
			if (e.getSource().equals(info)) {
				ng.sf.sg.searchSpecificPatient(resTable.getValueAt(row, 1).toString());
			} else {
				if (e.getSource().equals(waiting)) {
					updateProgress(row, waiting.getText(), resTable);
				} else if (e.getSource().equals(surgery)) {
					updateProgress(row, surgery.getText(), resTable);
				} else if (e.getSource().equals(reservation)) {
					updateProgress(row, reservation.getText(), resTable);
				} else if (e.getSource().equals(cancellation)) {
					Patient p = ng.nprrg.progressCancel(resTable);
					if (p == null) {
						ng.nprrg.getWarning().showMessageDialog(null, "접수 / 예약 취소를 중단합니다.", "환자 접수/예약 취소 중단",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					ng.ndao.insertResCancel(p);
					updateProgress(row, cancellation.getText(), resTable);
				} else if (e.getSource().equals(done)) {
					updateProgress(row, done.getText(), resTable);
				}
				ng.nprrg.menu(ng.nprrg.getDisabledButton());
				ng.nprrg.getSuccess().showMessageDialog(null, "진행 상황을 갱신하였습니다.", "진행 상황 갱신 성공",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
