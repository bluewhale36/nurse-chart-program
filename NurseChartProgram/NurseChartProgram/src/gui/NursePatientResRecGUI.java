package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import dto.Doctor;
import dto.Patient;
import dto.ResRecord;

public class NursePatientResRecGUI extends JPanel {

	NurseGUI ng = null;

	private JOptionPane success = new JOptionPane();
	private JOptionPane error = new JOptionPane();
	private JOptionPane warning = new JOptionPane();

	JPanel gridC = new JPanel();
	JPanel gridS = new JPanel();

	private String disabledButton = null;

	private LineBorder bLine;

	public String getDisabledButton() {
		return disabledButton;
	}

	public void setDisabledButton(String disabledButton) {
		this.disabledButton = disabledButton;
	}

	public JOptionPane getSuccess() {
		return success;
	}

	public void setSuccess(JOptionPane success) {
		this.success = success;
	}

	public JOptionPane getWarning() {
		return warning;
	}

	public void setWarning(JOptionPane warning) {
		this.warning = warning;
	}

	public NursePatientResRecGUI(NurseGUI n) {
		ng = n;

		bLine = new LineBorder(Color.black, 1, true);

		this.setLayout(new BorderLayout());

		this.add(gridC, "Center");
		this.add(gridS, "South");

		gridC.setLayout(new BoxLayout(gridC, BoxLayout.X_AXIS));

		gridS.setBorder(bLine);

	}

	public void menu(String progress) {
		disableButton(progress);
		gridC.removeAll();
		gridS.removeAll();
		if (progress.equals("취소")) {
			ng.nprctg.menu();
		} else if (progress.equals("예약")) {
			ng.nprtg.menu(progress);
		} else {
			ng.nprrtg.menu(progress);
		}
		ng.grid2C1.add(this, "Center");
		showBox();
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}

	public void disableButton(String name) {
		if (name.equals("완료")) {
			disabledButton = null;
		} else {
			disabledButton = name;
		}
	}

	public Patient progressCancel(JTable curTable) {
		int row = curTable.getSelectedRow();
		String name = curTable.getValueAt(row, 0).toString();
		String idNum = curTable.getValueAt(row, 1).toString();
		String inDate = curTable.getValueAt(row, 2).toString();
		String docName = curTable.getValueAt(row, 3).toString();
		String docNum = curTable.getValueAt(row, 4).toString();
		String warnMessage = "환자: " + name + " | 주민번호: " + idNum + "\n접수(예약)일자: " + inDate + " | 진료의사: " + docName
				+ " | 의사번호: " + docNum + "\n접수 / 예약을 취소할 시 복구 불가합니다.\n취소사유를 입력하세요.(필수사항) :";
		String cancelReason = warning.showInputDialog(null, warnMessage, "환자 접수/예약 취소", JOptionPane.WARNING_MESSAGE);
		System.out.println(cancelReason);
		if (cancelReason == null || cancelReason.length() == 0) {
			return null;
		}
		Patient p = new Patient();
		p.setName(name);
		p.setIdNum(idNum);
		ResRecord rr = new ResRecord();
		rr.setInDate(inDate);
		rr.setCancelReason(cancelReason);
		rr.setProgress("취소");
		Doctor d = new Doctor();
		d.setDocName(docName);
		d.setDocNum(docNum);
		rr.setD(d);
		p.getResRecList().add(rr);
		return p;
	}

}
