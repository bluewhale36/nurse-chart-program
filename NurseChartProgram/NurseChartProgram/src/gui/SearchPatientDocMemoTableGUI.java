package gui;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchPatientDocMemoTableGUI {

	SearchGUI sg;

	private String[] header = { "작성일(의사)", "의사이름", "의사번호", "의사메모" };
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

	public SearchPatientDocMemoTableGUI(SearchGUI s) {
		sg = s;

		dmTable.getTableHeader().setReorderingAllowed(false);
		dmTable.getTableHeader().setReorderingAllowed(false);
		dmTable.getColumn("의사메모").setPreferredWidth(250);
	}

	public void delRow() {
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}

	public void showBox() {
		tableScroll.setVisible(false);
		tableScroll.setVisible(true);
	}

	public void menu() {
		sg.grid4G[0].add(tableScroll);
		showBox();
	}

	public void addRow(ArrayList<String[]> pp) {
		for (String[] p : pp) {
			model.addRow(p);
		}
	}
}
