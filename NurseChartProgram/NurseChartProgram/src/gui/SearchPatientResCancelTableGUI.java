package gui;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchPatientResCancelTableGUI {

	SearchGUI sg;

	private String[] header = { "ȯ�ڼ���", "�ֹι�ȣ", "����(����)����", "�����ǻ�", "�ǻ��ȣ", "�������", "��һ���" };
	private String[][] contents = new String[0][header.length];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};

	private JTable pResCancelTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(pResCancelTable);

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public SearchPatientResCancelTableGUI(SearchGUI s) {
		sg = s;

		pResCancelTable.getTableHeader().setReorderingAllowed(false);
		pResCancelTable.getTableHeader().setResizingAllowed(false);
	}

	public void showBox() {
		tableScroll.setVisible(false);
		tableScroll.setVisible(true);
	}

	public void menu() {
		sg.grid[3].add(tableScroll);
		showBox();
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
	}
}
