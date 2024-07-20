package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dto.Patient;

public class NurseSelectedPatientTableGUI extends JPanel implements MouseListener {

	NurseGUI ng = null;

	private String[] header = { "환자성명", "주민번호", "전화번호", "환자성별", "환자주소", "최초등록일" };
	private String[][] contents = new String[0][6];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};
	private JTable pmTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(pmTable);

	private JLabel selectPInfo = new JLabel();

	private BevelBorder bbRai, bbLow;
	private LineBorder bLine;

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

	public JTable getPmTable() {
		return pmTable;
	}

	public void setPmTable(JTable pmTable) {
		this.pmTable = pmTable;
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

	public NurseSelectedPatientTableGUI(NurseGUI n) {
		ng = n;

		bbRai = new BevelBorder(BevelBorder.RAISED);
		bbLow = new BevelBorder(BevelBorder.LOWERED);
		bLine = new LineBorder(Color.black, 1, true);

		pmTable.getTableHeader().setReorderingAllowed(false);
		pmTable.getTableHeader().setResizingAllowed(false);

		pmTable.getColumn("환자성명").setPreferredWidth(25);
		pmTable.getColumn("주민번호").setPreferredWidth(50);
		pmTable.getColumn("전화번호").setPreferredWidth(40);
		pmTable.getColumn("환자성별").setPreferredWidth(5);
		pmTable.getColumn("환자주소").setPreferredWidth(55);
		pmTable.getColumn("최초등록일").setPreferredWidth(30);

		pmTable.addMouseListener(this);

	}

	public void menu() {
		delRow();
		addRow(ng.ndao.selectAllPatient());
		selectPInfo.setText("");
		ng.npmg.grid1.add(tableScroll);
		showBox();
	}

	public void showBox() {
		tableScroll.setVisible(false);
		tableScroll.setVisible(true);
		selectPInfo.setVisible(false);
		selectPInfo.setVisible(true);
	}

	public void delRow() {
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}

	public void addRow(ArrayList<Patient> pList) {
		for (int i = 0; i < pList.size(); i++) {
			String[] info = new String[header.length];
			info[0] = pList.get(i).getName();
			info[1] = pList.get(i).getIdNum();
			info[2] = pList.get(i).getTel();
			info[3] = pList.get(i).getGender();
			info[4] = pList.get(i).getAddr();
			info[5] = pList.get(i).getStDate();
			model.addRow(info);
		}
		showBox();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = pmTable.getSelectedRow();
		int column = pmTable.getSelectedColumn();
		String name = String.valueOf(pmTable.getValueAt(row, 0));
		String idNum6 = String.valueOf(pmTable.getValueAt(row, 1)).substring(0, 6);
		String idNum7 = String.valueOf(pmTable.getValueAt(row, 1)).substring(7);
		selectPInfo.setText(name + "님의 " + pmTable.getColumnName(column) + ": " + pmTable.getValueAt(row, column));
		ng.npmg.grid2P[0].remove(selectPInfo);
		ng.npmg.grid2P[0].add(selectPInfo);
		ng.npmg.nametf.setText("");
		ng.npmg.idNumtf6.setText("");
		ng.npmg.idNumtf7.setText("");
		ng.npmg.nametf.setText(name);
		ng.npmg.idNumtf6.setText(idNum6);
		ng.npmg.idNumtf7.setText(idNum7);
		showBox();
		// 메모 테이블 채우기
		if (ng.npmg.getMemoCom().getSelectedIndex() == 0) {
			ng.npmg.nMemoBoxSelected();
		} else if (ng.npmg.getMemoCom().getSelectedIndex() == 1) {
			ng.npmg.dMemoBoxSelected();
		}
		ng.npmg.getMemoCom().setEnabled(true);
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

}
