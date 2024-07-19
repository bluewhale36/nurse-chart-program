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

public class NursePatientResCancelTableGUI implements ActionListener, MouseListener {

	NurseGUI ng = null;

	private String[] header = { "환자성명", "주민번호", "접수(예약)일자", "진료의사", "의사번호", "취소일자", "취소사유" };
	private String[][] contents = new String[0][header.length];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};

	private JLabel selectPInfo = new JLabel();

	private JTable cancelTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(cancelTable);

	private JPopupMenu right;
	private JMenuItem info;

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public NursePatientResCancelTableGUI(NurseGUI n) {
		ng = n;

		cancelTable.getTableHeader().setReorderingAllowed(false);
		cancelTable.getTableHeader().setResizingAllowed(false);
		cancelTable.addMouseListener(this);

		cancelTable.getColumn("환자성명").setPreferredWidth(30);
		cancelTable.getColumn("주민번호").setPreferredWidth(75);
		cancelTable.getColumn("접수(예약)일자").setPreferredWidth(80);
		cancelTable.getColumn("진료의사").setPreferredWidth(30);
		cancelTable.getColumn("의사번호").setPreferredWidth(30);
		cancelTable.getColumn("취소사유").setPreferredWidth(75);

		right = new JPopupMenu();
		info = new JMenuItem("환자 정보");
		right.add(info);

		info.addActionListener(this);

	}

	public void showBox() {
		tableScroll.setVisible(false);
		tableScroll.setVisible(true);
	}

	public void menu() {
		selectPInfo.setText("(환자 선택)");
		delRow();
		addRow(ng.ndao.selectResCancel());
		ng.nprrg.gridC.add(tableScroll);
		ng.nprrg.gridS.add(selectPInfo);
		showBox();
	}

	public void delRow() {
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}

	public void addRow(ArrayList<Patient> pList) {
		for (Patient p : pList) {
			String[] info = new String[header.length];
			info[0] = p.getName();
			info[1] = p.getIdNum();
			info[2] = p.getResCancelList().get(0).getInDate();
			info[3] = p.getResCancelList().get(0).getD().getDocName();
			info[4] = p.getResCancelList().get(0).getD().getDocNum();
			info[5] = p.getResCancelList().get(0).getCancelDate();
			info[6] = p.getResCancelList().get(0).getCancelReason();
			model.addRow(info);
		}
		showBox();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (cancelTable.getSelectedRowCount() == 1 && cancelTable.getSelectedColumnCount() == 1) {
			if (SwingUtilities.isRightMouseButton(e)) {
				right.show(cancelTable, e.getX(), e.getY());
			}
			int row = cancelTable.getSelectedRow();
			int column = cancelTable.getSelectedColumn();
			selectPInfo.setText(cancelTable.getValueAt(row, 0) + "님의 " + cancelTable.getColumnName(column) + ": "
					+ cancelTable.getValueAt(row, column));
			ng.nprrg.gridS.removeAll();
			ng.nprrg.gridS.add(selectPInfo);
			ng.nprrg.showBox();

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
		if (cancelTable.getSelectedRowCount() == 1 && cancelTable.getSelectedColumnCount() == 1) {
			if (e.getSource().equals(info)) {
				ng.sf.sg.searchSpecificPatient(cancelTable.getValueAt(cancelTable.getSelectedRow(), 1).toString());
			}
		}

	}

}
