package gui;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchPatientEndTableGUI {

	SearchGUI sg;

	private String[] header = { "ȯ�ڼ���", "�ֹι�ȣ", "����(����)����", "�����ǻ�", "�ǻ��ȣ", "��������", "ó��", "��������", "�����ݾ�" };
	private String[][] contents = new String[0][header.length];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};

	private JTable pEndTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(pEndTable);

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public SearchPatientEndTableGUI(SearchGUI s) {
		sg = s;

		pEndTable.getTableHeader().setReorderingAllowed(false);
		pEndTable.getTableHeader().setResizingAllowed(false);

	}

	public void showBox() {
		tableScroll.setVisible(false);
		tableScroll.setVisible(true);
	}

	public void menu() {
		sg.grid[2].add(tableScroll);
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
