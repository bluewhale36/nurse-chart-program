package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dto.Patient;

public class NursePatientTableGUI extends JPanel implements MouseListener {

	NurseGUI ng = null;

	private String[] header = { "환자성명", "주민번호", "전화번호", "환자성별", "환자주소", "최초등록일" };
	private String[][] contents = new String[0][header.length];

	private DefaultTableModel model = new DefaultTableModel(contents, header) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};
	private JTable pTable = new JTable(model);
	private JScrollPane tableScroll = new JScrollPane(pTable);

	private JLabel selectPInfo = new JLabel();

	private BevelBorder bbRai, bbLow;
	private LineBorder bLine;

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getpTable() {
		return pTable;
	}

	public void setpTable(JTable pTable) {
		this.pTable = pTable;
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

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String[][] getContents() {
		return contents;
	}

	public void setContents(String[][] contents) {
		this.contents = contents;
	}

	public NursePatientTableGUI(NurseGUI n) {
		ng = n;

		bbRai = new BevelBorder(BevelBorder.RAISED);
		bbLow = new BevelBorder(BevelBorder.LOWERED);
		bLine = new LineBorder(Color.black, 1, true);

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(tableScroll);

		pTable.addMouseListener(this);

		ng.nprg.gridP[1].add(this);
		pTable.getTableHeader().setReorderingAllowed(false);
		pTable.getTableHeader().setResizingAllowed(false);
		pTable.getColumn("환자성명").setPreferredWidth(25);
		pTable.getColumn("주민번호").setPreferredWidth(50);
		pTable.getColumn("전화번호").setPreferredWidth(40);
		pTable.getColumn("환자성별").setPreferredWidth(5);
		pTable.getColumn("환자주소").setPreferredWidth(55);
		pTable.getColumn("최초등록일").setPreferredWidth(30);

	}

	public void menu(String name) {
		delRow();
		addRow(ng.ndao.selectePatientWhereLike(name));
		showBox();
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}

	public void delRow() {
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}

	public void addRow(ArrayList<Patient> pList) {
		ArrayList<String[]> pp = new ArrayList<String[]>();
		for (int i = 0; i < pList.size(); i++) {
			String[] info = new String[header.length];
			info[0] = pList.get(i).getName();
			info[1] = pList.get(i).getIdNum();
			info[2] = pList.get(i).getTel();
			info[3] = pList.get(i).getGender();
			info[4] = pList.get(i).getAddr();
			info[5] = pList.get(i).getStDate();
			pp.add(info);
		}
		for (String[] p : pp) {
			model.addRow(p);
		}
		showBox();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = pTable.getSelectedRow();
		int column = pTable.getSelectedColumn();
		String name = String.valueOf(pTable.getValueAt(row, 0));
		String idNum6 = String.valueOf(pTable.getValueAt(row, 1)).substring(0, 6);
		String idNum7 = String.valueOf(pTable.getValueAt(row, 1)).substring(7);
		selectPInfo.setText(pTable.getValueAt(row, 0) + "님의 " + pTable.getColumnName(column) + ": "
				+ pTable.getValueAt(row, column));
		ng.nprg.gridP2[0].remove(selectPInfo);
		ng.nprg.gridP2[0].add(selectPInfo);
		ng.nprg.nametf.setText("");
		ng.nprg.idNumtf6.setText("");
		ng.nprg.idNumtf7.setText("");
		ng.nprg.nametf.setText(name);
		ng.nprg.idNumtf6.setText(idNum6);
		ng.nprg.idNumtf7.setText(idNum7);
		ng.nprg.getReceptionCom().setEnabled(true);
		ng.nprg.receptionComIdx0Selected();
		ng.nprg.reception.setEnabled(true);
		ng.nprg.erase.setEnabled(true);
		showBox();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

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
