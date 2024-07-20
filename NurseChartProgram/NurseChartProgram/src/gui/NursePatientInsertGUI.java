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

	JButton insertConfirm = new JButton("환자 등록");
	JButton erase = new JButton("지우기");

	JLabel insertionError = new JLabel("이미 등록된 환자이거나 시스템 오류로 등록되지 않았습니다.");
	JLabel insertionSuccess = new JLabel("등록 및 접수되었습니다.");

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
		name.add(new JLabel(" 환자성명: "));
		name.add(nametf);
		gridP[1].add(name);

		idNum.setLayout(new BoxLayout(idNum, BoxLayout.X_AXIS));
		idNum.add(new JLabel(" 주민번호: "));
		idNum.add(idNumtf6);
		idNum.add(new JLabel(" - "));
		idNum.add(idNumtf7);
		gridP[1].add(idNum);

		idNumtf6.addKeyListener(this);
		idNumtf7.addKeyListener(this);

		hp.setLayout(new BoxLayout(hp, BoxLayout.X_AXIS));
		hp.add(new JLabel(" 휴대전화: "));
		hp.add(hptf);
		gridP[3].add(hp);

		hptf.addKeyListener(this);

		gender.setLayout(new BoxLayout(gender, BoxLayout.X_AXIS));
		gender.add(new JLabel(" 환자성별: "));
		gender.add(genderG);
		genderG.add(genderM);
		genderG.add(genderF);
		gridP[3].add(gender);

		genderM.addItemListener(this);
		genderF.addItemListener(this);

		docName.setLayout(new BoxLayout(docName, BoxLayout.X_AXIS));
		docName.add(new JLabel(" 진료의사: "));
		docName.add(docNaCom);
		gridP[5].add(docName);

		docNaCom.addItemListener(this);

		docNum.setLayout(new BoxLayout(docNum, BoxLayout.X_AXIS));
		docNum.add(new JLabel(" 의사번호: "));
		docNum.add(docNumtf);
		gridP[5].add(docNum);
		docNumtf.setEnabled(false);

		gridP[7].setLayout(new BoxLayout(gridP[7], BoxLayout.X_AXIS));
		gridP[7].add(new JLabel(" 환자주소: "));
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
		rr.setProgress("진료 대기");
		p.getResRecList().add(rr);
		rr.setD(d);
		if (ng.ndao.insertPatient(p)) {
			if (ng.ndao.insertResRec(p)) {
				success.showMessageDialog(null, "등록 및 접수되었습니다.", "등록 및 접수 성공", JOptionPane.INFORMATION_MESSAGE);
			} else {
				error.showMessageDialog(null, "접수에 실패했습니다.", "접수 오류", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			error.showMessageDialog(null, "이미 등록된 환자이거나 올바르지 않은 정보를 입력했습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 환자 등록 버튼 클릭 -> 등록 및 접수
		if (e.getSource().equals(insertConfirm)) {
			if (docNaCom.getSelectedIndex() != 0) {
				patientInsertReception();
				// 진료대기...panel에서 선택되어있는 부분 jtable 리프레시
				if (ng.nprrg.getDisabledButton() != null) {
					ng.nprrg.menu(ng.nprrg.getDisabledButton());
				}
				showBox();
				ng.nsptg.menu();
			} else {
				error.showMessageDialog(null, "의사를 선택하세요.", "접수 오류", JOptionPane.ERROR_MESSAGE);
			}
		}
		// 지우기 버튼 클릭 -> 등록 창의 모든 정보 초기화
		if (e.getSource().equals(erase)) {
			erase();
			showBox();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// 두개 이상의 성별 선택 불가
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
		// 선택한 의사에 따른 의사 번호 자동 선택
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
		// 주민번호 뒷자리에 따른 성별 자동 선택
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

		// 글자 수 제한
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
