package gui;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchPatientResRecTableGUI {

	SearchGUI sg;

	private String[] header = { "ȯ�ڼ���", "�ֹι�ȣ", "�����Ȳ", "����(����)����", "�����ǻ�", "�ǻ��ȣ", "��������", "�����ݾ�" };
	private String[][] contents = new String[0][header.length];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};

	private JTable pResRecTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(pResRecTable);

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public SearchPatientResRecTableGUI(SearchGUI s) {
		sg = s;

		pResRecTable.getTableHeader().setResizingAllowed(false);
		pResRecTable.getTableHeader().setReorderingAllowed(false);

	}

	public void showBox() {
		tableScroll.setVisible(false);
		tableScroll.setVisible(true);
	}

	public void menu() {
		sg.grid[1].add(tableScroll);
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
