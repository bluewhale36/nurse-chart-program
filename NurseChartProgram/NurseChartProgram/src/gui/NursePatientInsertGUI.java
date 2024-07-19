package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import dto.Doctor;
import dto.Patient;
import dto.ResRecord;

public class NursePatientInsertGUI extends JPanel implements ActionListener, ItemListener, KeyListener {

	NurseGUI ng = null;

	private JOptionPane success = new JOptionPane();
	private JOptionPane error = new JOptionPane();

	JPanel[] gridP = new JPanel[9];
	JPanel name = new JPanel();
	JPanel idNum = new JPanel();
	JPanel gender = new JPanel();
	JPanel bDate = new JPanel();
	JPanel docName = new JPanel();
	JPanel docNum = new JPanel();
	JPanel hp = new JPanel();
	JPanel genderG = new JPanel();
	JPanel genderG1 = new JPanel();
	JPanel genderG2 = new JPanel();

	JPanel grid1C1S = new JPanel();

	JTextField nametf, idNumtf6, idNumtf7, hptf, docNumtf, addrtf, infotfs[];

	private String[] docNameCom;
	private JComboBox<String> docNaCom;
	private String[] docNumCom;

	JRadioButton genderM = new JRadioButton("M     ");
	JRadioButton genderF = new JRadioButton("F");

	JButton insertConfirm = new JButton("ȯ�� ���");
	JButton erase = new JButton("�����");

	JLabel insertionError = new JLabel("�̹� ��ϵ� ȯ���̰ų� �ý��� ������ ��ϵ��� �ʾҽ��ϴ�.");
	JLabel insertionSuccess = new JLabel("��� �� �����Ǿ����ϴ�.");

	private BevelBorder bbRai = new BevelBorder(BevelBorder.RAISED);
	private BevelBorder bbLow = new BevelBorder(BevelBorder.LOWERED);

	private LineBorder bLine;

	public String[] getDocNameCom() {
		return docNameCom;
	}

	public void setDocNameCom(String[] docNameCom) {
		this.docNameCom = docNameCom;
	}

	public String[] getDocNumCom() {
		return docNumCom;
	}

	public void setDocNumCom(String[] docNumCom) {
		this.docNumCom = docNumCom;
	}

	public NursePatientInsertGUI(NurseGUI n) {
		ng = n;

		nametf = new JTextField();
		idNumtf6 = new JTextField();
		idNumtf7 = new JTextField();
		hptf = new JTextField();
		docNumtf = new JTextField("0");
		addrtf = new JTextField();

		infotfs = new JTextField[6];
		infotfs[0] = nametf;
		infotfs[1] = idNumtf6;
		infotfs[2] = idNumtf7;
		infotfs[3] = hptf;
		infotfs[4] = docNumtf;
		infotfs[5] = addrtf;

		bLine = new LineBorder(Color.black, 1, true);

		docNameCom = ng.ndao.selectDocName();
		docNaCom = new JComboBox<String>(docNameCom);
		docNumCom = ng.ndao.selectDocNum();

		this.setLayout(new GridLayout(9, 1));

		for (int i = 0; i < gridP.length; i++) {
			gridP[i] = new JPanel();
			this.add(gridP[i]);
			if (i % 2 == 1 && i != 7) {
				gridP[i].setLayout(new GridLayout(1, 2));
			}
		}

		name.setLayout(new BoxLayout(name, BoxLayout.X_AXIS));
		name.add(new JLabel(" ȯ�ڼ���: "));
		name.add(nametf);
		gridP[1].add(name);

		idNum.setLayout(new BoxLayout(idNum, BoxLayout.X_AXIS));
		idNum.add(new JLabel(" �ֹι�ȣ: "));
		idNum.add(idNumtf6);
		idNum.add(new JLabel(" - "));
		idNum.add(idNumtf7);
		gridP[1].add(idNum);

		idNumtf6.addKeyListener(this);
		idNumtf7.addKeyListener(this);

		hp.setLayout(new BoxLayout(hp, BoxLayout.X_AXIS));
		hp.add(new JLabel(" �޴���ȭ: "));
		hp.add(hptf);
		gridP[3].add(hp);

		hptf.addKeyListener(this);

		gender.setLayout(new BoxLayout(gender, BoxLayout.X_AXIS));
		gender.add(new JLabel(" ȯ�ڼ���: "));
		gender.add(genderG);
		genderG.add(genderM);
		genderG.add(genderF);
		gridP[3].add(gender);

		genderM.addItemListener(this);
		genderF.addItemListener(this);

		docName.setLayout(new BoxLayout(docName, BoxLayout.X_AXIS));
		docName.add(new JLabel(" �����ǻ�: "));
		docName.add(docNaCom);
		gridP[5].add(docName);

		docNaCom.addItemListener(this);

		docNum.setLayout(new BoxLayout(docNum, BoxLayout.X_AXIS));
		docNum.add(new JLabel(" �ǻ��ȣ: "));
		docNum.add(docNumtf);
		gridP[5].add(docNum);
		docNumtf.setEnabled(false);

		gridP[7].setLayout(new BoxLayout(gridP[7], BoxLayout.X_AXIS));
		gridP[7].add(new JLabel(" ȯ���ּ�: "));
		gridP[7].add(addrtf);

		grid1C1S.add(insertConfirm);
		grid1C1S.add(erase);
		insertConfirm.setBorder(bbRai);
		erase.setBorder(bbRai);

		this.setBorder(bLine);
		this.grid1C1S.setBorder(bLine);

		insertConfirm.addActionListener(this);
		erase.addActionListener(this);

	}

	public void addInterface() {
		for (int i = 0; i < infotfs.length; i++) {
			infotfs[i].addKeyListener(this);
		}
	}

	public void menu() {
		ng.grid1C1.add(this, "Center");
		ng.grid1C1.add(grid1C1S, "South");
		showBox();
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}

	public void erase() {
		nametf.setText("");
		idNumtf6.setText("");
		idNumtf7.setText("");
		docNaCom.setSelectedIndex(0);
		hptf.setText("");
		addrtf.setText("");
		genderM.setSelected(false);
		genderF.setSelected(false);
	}

	private void patientInsertReception() {
		Patient p = new Patient();
		p.setName(nametf.getText());
		p.setIdNum(idNumtf6.getText() + idNumtf7.getText());
		p.setTel(hptf.getText());
		if (genderM.isSelected()) {
			p.setGender("M");
		} else if (genderF.isSelected()) {
			p.setGender("F");
		}
		p.setAddr(addrtf.getText());
		Doctor d = new Doctor();
		d.setDocName(docNameCom[docNaCom.getSelectedIndex()]);
		d.setDocNum(docNumCom[docNaCom.getSelectedIndex()]);
		ResRecord rr = new ResRecord();
		rr.setProgress("���� ���");
		p.getResRecList().add(rr);
		rr.setD(d);
		if (ng.ndao.insertPatient(p)) {
			if (ng.ndao.insertResRec(p)) {
				success.showMessageDialog(null, "��� �� �����Ǿ����ϴ�.", "��� �� ���� ����", JOptionPane.INFORMATION_MESSAGE);
			} else {
				error.showMessageDialog(null, "������ �����߽��ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			error.showMessageDialog(null, "�̹� ��ϵ� ȯ���̰ų� �ùٸ��� ���� ������ �Է��߽��ϴ�.", "��� ����", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ȯ�� ��� ��ư Ŭ�� -> ��� �� ����
		if (e.getSource().equals(insertConfirm)) {
			if (docNaCom.getSelectedIndex() != 0) {
				patientInsertReception();
				// ������...panel���� ���õǾ��ִ� �κ� jtable ��������
				if (ng.nprrg.getDisabledButton() != null) {
					ng.nprrg.menu(ng.nprrg.getDisabledButton());
				}
				showBox();
				ng.nsptg.menu();
			} else {
				error.showMessageDialog(null, "�ǻ縦 �����ϼ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
			}
		}
		// ����� ��ư Ŭ�� -> ��� â�� ��� ���� �ʱ�ȭ
		if (e.getSource().equals(erase)) {
			erase();
			showBox();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// �ΰ� �̻��� ���� ���� �Ұ�
		if (genderM.isSelected()) {
			if (e.getSource().equals(genderF) && genderF.isSelected()) {
				genderM.setSelected(false);
			}
		}
		if (genderF.isSelected()) {
			if (e.getSource().equals(genderM) && genderM.isSelected()) {
				genderF.setSelected(false);
			}
		}
		// ������ �ǻ翡 ���� �ǻ� ��ȣ �ڵ� ����
		if (e.getSource().equals(docNaCom)) {
			int docIdx = docNaCom.getSelectedIndex();
			docNumtf.setText("");
			docNumtf.setText(docNumCom[docIdx]);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// �ֹι�ȣ ���ڸ��� ���� ���� �ڵ� ����
		if (e.getSource().equals(hptf)) {
			hptf.setText(hptf.getText().replaceAll("[^0-9]", ""));
		}
		if (e.getSource().equals(idNumtf6)) {
			idNumtf6.setText(idNumtf6.getText().replaceAll("[^0-9]", ""));
		}
		if (e.getSource().equals(idNumtf7)) {
			idNumtf7.setText(idNumtf7.getText().replaceAll("[^0-9]", ""));
			if (idNumtf7.getText().length() != 0) {
				int genderNum = idNumtf7.getText().charAt(0) - '0';
				if (genderNum > 0 && genderNum < 7) {
					if (genderNum % 2 == 1) {
						genderM.setSelected(true);
					} else {
						genderF.setSelected(true);
					}
				} else {
					genderM.setSelected(false);
					genderF.setSelected(false);
				}
			} else {
				genderM.setSelected(false);
				genderF.setSelected(false);
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

		// ���� �� ����
		if (e.getSource().equals(idNumtf6)) {
			if (idNumtf6.getText().length() > 5) {
				e.consume();
			}
		}
		if (e.getSource().equals(idNumtf7)) {
			if (idNumtf7.getText().length() > 6) {
				e.consume();
			}
		}
		if (e.getSource().equals(hptf)) {
			if (hptf.getText().length() > 10) {
				e.consume();
			}
		}
	}

}
