package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.DocMemo;
import dto.NurMemo;
import dto.Patient;
import dto.ResRecord;

public class SearchGUI extends JPanel implements ActionListener {

	SearchFrame sf;

	public SearchPatientInfoGUI spig = new SearchPatientInfoGUI(this);
	public SearchPatientResRecTableGUI sprrtg = new SearchPatientResRecTableGUI(this);
	public SearchPatientEndTableGUI spetg = new SearchPatientEndTableGUI(this);
	public SearchPatientResCancelTableGUI sprctg = new SearchPatientResCancelTableGUI(this);
	public SearchPatientDocMemoTableGUI spdmtg = new SearchPatientDocMemoTableGUI(this);
	public SearchPatientNurMemoTableGUI spnmtg = new SearchPatientNurMemoTableGUI(this);

	JPanel north, south, center, grid[], gridG, grid4G[];

	JButton terminate;

	String[] name;
	JComboBox nameCom;

	private ArrayList<Patient> pList;

	public ArrayList<Patient> getpList() {
		return pList;
	}

	public void setpList(ArrayList<Patient> pList) {
		this.pList = pList;
	}

	public SearchGUI(SearchFrame s) {
		sf = s;

		this.setLayout(new BorderLayout());

		north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		south = new JPanel();
		center = new JPanel(new GridLayout(5, 1, 5, 5));

		grid = new JPanel[5];
		for (int i = 0; i < grid.length; i++) {
			grid[i] = new JPanel();
			grid[i].setLayout(new BoxLayout(grid[i], BoxLayout.X_AXIS));
			center.add(grid[i]);
		}
		grid[0].add(new JLabel(" 환자정보: "));
		grid[1].add(new JLabel(" 이용현황: "));
		grid[2].add(new JLabel(" 진료기록: "));
		grid[3].add(new JLabel(" 취소기록: "));
		grid[4].add(new JLabel(" 환자메모: "));

		gridG = new JPanel(new GridLayout(1, 2));
		grid[4].add(gridG);

		grid4G = new JPanel[2];
		for (int i = 0; i < grid4G.length; i++) {
			grid4G[i] = new JPanel();
			grid4G[i].setLayout(new BoxLayout(grid4G[i], BoxLayout.X_AXIS));
			gridG.add(grid4G[i]);
		}

		terminate = new JButton("닫기");

		nameCom = new JComboBox();

		this.add(north, "North");
		this.add(south, "South");
		this.add(center, "Center");

		north.add(nameCom);
		south.add(terminate);

		terminate.addActionListener(this);

	}

	public void nameComRemoveItem() {
		north.remove(nameCom);
		nameCom.remove(nameCom);
		nameCom = new JComboBox(name);
	}

	public void getPatientInfo(String search) {
		pList = sf.ng.ndao.selectePatientWhereLike(search);
		name = new String[pList.size()];
		for (int i = 0; i < pList.size(); i++) {
			name[i] = pList.get(i).getName() + " | " + pList.get(i).getIdNum();
		}
		for (Patient p : pList) {
			p = sf.ng.ndao.selectPatientAllInfo(p);
			System.out.println(p.getName() + " | " + p.getIdNum());
		}
		nameComRemoveItem();
		nameCom.setSelectedIndex(0);
	}

	public void getSpecificPatientInfo(String idNum) {
		pList = new ArrayList<Patient>();
		pList.add(sf.ng.ndao.selectSpecificPatient(idNum));
		name = new String[pList.size()];
		System.out.println(name.length);
		for (int i = 0; i < pList.size(); i++) {
			name[i] = pList.get(i).getName() + " | " + pList.get(i).getIdNum();
		}
		for (Patient p : pList) {
			p = sf.ng.ndao.selectPatientAllInfo(p);
			System.out.println(p.getName() + " | " + p.getIdNum());
		}
		nameComRemoveItem();
		nameCom.setSelectedIndex(0);
	}

	public void spigInfo() {
		Patient p = pList.get(nameCom.getSelectedIndex());
		spig.menu(p);
	}

	public void sprrtgInfo() {
		sprrtg.delRow();
		ArrayList<String[]> sprrtgList = new ArrayList<String[]>();
		Patient p = pList.get(nameCom.getSelectedIndex());
		ArrayList<ResRecord> rrList = p.getResRecList();
		for (int i = 0; i < rrList.size(); i++) {
			String[] info = new String[sprrtg.getHeader().length];
			info[0] = p.getName();
			info[1] = p.getIdNum();
			info[2] = rrList.get(i).getProgress();
			info[3] = rrList.get(i).getInDate();
			info[4] = rrList.get(i).getD().getDocName();
			info[5] = rrList.get(i).getD().getDocNum();
			info[6] = rrList.get(i).getPayment();
			info[7] = rrList.get(i).getPay();
			sprrtgList.add(info);
		}
		sprrtg.addRow(sprrtgList);
		sprrtg.menu();
	}

	public void spetgInfo() {
		spetg.delRow();
		ArrayList<String[]> spetgList = new ArrayList<String[]>();
		Patient p = pList.get(nameCom.getSelectedIndex());
		ArrayList<ResRecord> erList = p.getEndRecList();
		for (int i = 0; i < erList.size(); i++) {
			String[] info = new String[spetg.getHeader().length];
			info[0] = p.getName();
			info[1] = p.getIdNum();
			info[2] = erList.get(i).getInDate();
			info[3] = erList.get(i).getD().getDocName();
			info[4] = erList.get(i).getD().getDocNum();
			info[5] = erList.get(i).getEr().getDisease();
			info[6] = erList.get(i).getEr().getMedicine();
			info[7] = erList.get(i).getPayment();
			info[8] = erList.get(i).getPay();
			spetgList.add(info);
		}
		spetg.addRow(spetgList);
		spetg.menu();
	}

	public void sprctgInfo() {
		sprctg.delRow();
		ArrayList<String[]> sprctgList = new ArrayList<String[]>();
		Patient p = pList.get(nameCom.getSelectedIndex());
		ArrayList<ResRecord> rcList = p.getResCancelList();
		for (int i = 0; i < rcList.size(); i++) {
			String[] info = new String[sprctg.getHeader().length];
			info[0] = p.getName();
			info[1] = p.getIdNum();
			info[2] = rcList.get(i).getInDate();
			info[3] = rcList.get(i).getD().getDocName();
			info[4] = rcList.get(i).getD().getDocNum();
			info[5] = rcList.get(i).getCancelDate();
			info[6] = rcList.get(i).getCancelReason();
			sprctgList.add(info);
		}
		sprctg.addRow(sprctgList);
		sprctg.menu();
	}

	public void spdmtgInfo() {
		spdmtg.delRow();
		ArrayList<String[]> spdmtgList = new ArrayList<String[]>();
		Patient p = pList.get(nameCom.getSelectedIndex());
		ArrayList<DocMemo> dmList = p.getMemo().getDm();
		for (int i = 0; i < dmList.size(); i++) {
			String[] info = new String[spdmtg.getHeader().length];
			info[0] = dmList.get(i).getDmDate();
			info[1] = dmList.get(i).getDoc().getDocName();
			info[2] = dmList.get(i).getDoc().getDocNum();
			info[3] = dmList.get(i).getdMemo();
			spdmtgList.add(info);
		}
		spdmtg.addRow(spdmtgList);
		spdmtg.menu();
	}

	public void spnmtgInfo() {
		spnmtg.delRow();
		ArrayList<String[]> spnmtgList = new ArrayList<String[]>();
		Patient p = pList.get(nameCom.getSelectedIndex());
		ArrayList<NurMemo> nmList = p.getMemo().getNm();
		for (int i = 0; i < nmList.size(); i++) {
			String[] info = new String[spnmtg.getHeader().length];
			info[0] = nmList.get(i).getNmDate();
			info[1] = nmList.get(i).getnMemo();
			spnmtgList.add(info);
		}
		spnmtg.addRow(spnmtgList);
		spnmtg.menu();
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}

	public void menu() {
		north.add(nameCom);
		nameCom.addActionListener(this);
		sf.add(this);
		showBox();
		sf.showBox();
	}

	public void searchPatient(String searchName) {
		getPatientInfo(searchName);
		spigInfo();
		sprrtgInfo();
		spetgInfo();
		sprctgInfo();
		spdmtgInfo();
		spnmtgInfo();
		menu();
	}

	public void searchSpecificPatient(String searchIdNum) {
		getSpecificPatientInfo(searchIdNum);
		spigInfo();
		sprrtgInfo();
		spetgInfo();
		sprctgInfo();
		spdmtgInfo();
		spnmtgInfo();
		menu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(terminate)) {
			sf.dispose();
		}
		if (e.getSource().equals(nameCom)) {
			spigInfo();
			sprrtgInfo();
			spetgInfo();
			sprctgInfo();
			spdmtgInfo();
			spnmtgInfo();
		}

	}

}
