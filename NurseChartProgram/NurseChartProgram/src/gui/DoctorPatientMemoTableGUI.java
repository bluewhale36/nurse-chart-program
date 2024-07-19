package gui;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DoctorPatientMemoTableGUI {

	NurseGUI ng = null;

	private String[] header = { "작성일", "의사이름", "의사번호", "의사메모" };
	private String[][] contents = new String[0][header.length];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};

	private JTable dmTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(dmTable);

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public JScrollPane getTableScroll() {
		return tableScroll;
	}

	public void setTableScroll(JScrollPane tableScroll) {
		this.tableScroll = tableScroll;
	}

	public DoctorPatientMemoTableGUI(NurseGUI n) {
		ng = n;

		dmTable.getTableHeader().setReorderingAllowed(false);
		dmTable.getTableHeader().setResizingAllowed(false);

		dmTable.getColumn("작성일").setPreferredWidth(50);
		dmTable.getColumn("의사이름").setPreferredWidth(15);
		dmTable.getColumn("의사번호").setPreferredWidth(15);
		dmTable.getColumn("의사메모").setPreferredWidth(250);

	}

	public void menu(String idNum) {
		delRow();
		addRow(ng.ndao.selectDocMemoWhere(idNum));
		ng.npmg.grid3T.add(tableScroll);
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

	public void addRow(ArrayList<String[]> pp) {
		for (String[] p : pp) {
			model.addRow(p);
		}
		showBox();
	}
}
