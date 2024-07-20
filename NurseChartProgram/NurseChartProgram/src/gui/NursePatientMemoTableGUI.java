package gui;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class NursePatientMemoTableGUI {

	NurseGUI ng = null;

	private String[] header = { "작성일", "간호사메모" };
	private String[][] contents = new String[0][header.length];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};

	private JTable nmTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(nmTable);

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String[][] getContents() {
		return contents;
	}

	public void setContents(String[][] contents) {
		this.contents = contents;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getNmTable() {
		return nmTable;
	}

	public void setNmTable(JTable nmTable) {
		this.nmTable = nmTable;
	}

	public JScrollPane getTableScroll() {
		return tableScroll;
	}

	public void setTableScroll(JScrollPane tableScroll) {
		this.tableScroll = tableScroll;
	}

	public NursePatientMemoTableGUI(NurseGUI n) {
		ng = n;

		nmTable.getTableHeader().setReorderingAllowed(false);
		nmTable.getTableHeader().setResizingAllowed(false);
		nmTable.getColumn("작성일").setPreferredWidth(75);
		nmTable.getColumn("간호사메모").setPreferredWidth(335);
		ng.npmg.grid3T.add(tableScroll);
	}

	public void menu(String idNum) {
		delRow();
		addRow(ng.ndao.selectNurMemoWhere(idNum));
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

	public void addRow(ArrayList<String[]> mm) {
		for (String[] m : mm) {
			model.addRow(m);
		}
		showBox();
	}

}
