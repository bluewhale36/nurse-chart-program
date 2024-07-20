package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import dao.NurseDAO;

public class NurseGUI extends JPanel implements ActionListener {

	MainFrame mf = null;

	public NurseDAO ndao = new NurseDAO(this);

	public ChkDate cd = new ChkDate(this);
	public NursePatientInsertGUI npig;
	public NursePatientReceptionGUI nprg;
	public NursePatientResRecGUI nprrg;
	public NursePatientMemoGUI npmg;
	public NursePatientPayGUI nppg;
	public NursePatientResRecTableGUI nprrtg;
	public NursePatientResTableGUI nprtg;
	public NursePatientTableGUI nptg;
	public NurseSelectedPatientTableGUI nsptg;
	public NursePatientMemoTableGUI npmtg;
	public NursePatientResCancelTableGUI nprctg;
	public DoctorPatientMemoTableGUI dpmtg;
	public NursePatientPayTableGUI npptg;
	public SearchFrame sf;

	private JOptionPane error = new JOptionPane();
	private JOptionPane success = new JOptionPane();

	JPanel grid1 = new JPanel();
	JPanel grid1N = new JPanel();
	JPanel grid1C = new JPanel();
	JPanel grid1C1 = new JPanel();
	JPanel grid1C1N = new JPanel(new GridLayout(1, 2));
	JPanel grid1C1C = new JPanel();
	JPanel grid1C2 = new JPanel();
	JPanel grid1C2C = new JPanel();
	JPanel grid1C2S = new JPanel();

	JPanel grid2 = new JPanel();
	JPanel grid2N = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel grid2C = new JPanel();
	JPanel grid2C1 = new JPanel();
	JPanel grid2C2 = new JPanel();
	JPanel grid2C1N = new JPanel(new GridLayout(1, 5));
	JPanel grid2C1C = new JPanel();
	JPanel grid2C1S = new JPanel();

	JButton waiting = new JButton("���� ���");
	JButton surgery = new JButton("���� ��");
	JButton reservation = new JButton("����");
	JButton end = new JButton("�Ϸ�");
	JButton cancellation = new JButton("���");
	JButton[] progressButton = new JButton[5];

	JButton back = new JButton("�ڷ� ����");
	JButton terminate = new JButton("����");

	JTextField memotf = new JTextField(40);
	JButton write = new JButton("�ۼ�");

	JLabel searchPL = new JLabel("ȯ�� ��ȸ");
	JTextField searchPtf = new JTextField(40);
	JButton searchPB = new JButton("��ȸ");
	JButton insert = new JButton("���");
	JButton reception = new JButton("����");

	private BevelBorder bbRai = new BevelBorder(BevelBorder.RAISED);
	private BevelBorder bbLow = new BevelBorder(BevelBorder.LOWERED);
	private LineBorder bLine = new LineBorder(Color.black, 1, true);

	private void objectNew() {
		npig = new NursePatientInsertGUI(this);
		nprg = new NursePatientReceptionGUI(this);
		nprrg = new NursePatientResRecGUI(this);
		npmg = new NursePatientMemoGUI(this);
		nppg = new NursePatientPayGUI(this);
		nprrtg = new NursePatientResRecTableGUI(this);
		nprtg = new NursePatientResTableGUI(this);
		nptg = new NursePatientTableGUI(this);
		nsptg = new NurseSelectedPatientTableGUI(this);
		npmtg = new NursePatientMemoTableGUI(this);
		nprctg = new NursePatientResCancelTableGUI(this);
		dpmtg = new DoctorPatientMemoTableGUI(this);
		npptg = new NursePatientPayTableGUI(this);
		sf = new SearchFrame(this);
	}

	public NurseGUI(MainFrame f) {
		mf = f;

		objectNew();

		this.setLayout(new GridLayout(1, 2, 5, 5));
		this.add(grid1);
		this.add(grid2);

		// ��ü ȭ���� ���� �κ� =========================
		grid1.setLayout(new BorderLayout(10, 10));
		grid1.add(grid1N, "North");
		grid1.add(grid1C, "Center");

		grid1N.setBorder(bLine);
		grid1N.add(searchPL);
		grid1N.add(searchPtf);
		grid1N.add(searchPB);

		grid1C.setLayout(new GridLayout(2, 1, 10, 10));
		grid1C.add(grid1C1);
		grid1C.add(grid1C2);

		// ��ü ȭ���� ���� ���κ� ===================
		grid1C1.setLayout(new BorderLayout());
		grid1C1.add(grid1C1N, "North");
		grid1C1N.setBorder(bLine);
		// ���, ���� ��ư
		grid1C1N.add(insert);
		grid1C1N.add(reception);
		insert.setBorder(bbRai);
		reception.setBorder(bbRai);

		// NursePatientInsertGUI, NursePatientRecepionGUI
		grid1C1.add(grid1C1C, "Center");
		// ���, ������ ���� ȭ��
		grid1C1C.setBorder(bLine);

		// ��ü ȭ���� ���� �Ʒ��κ� ====================
		grid1C2.setLayout(new BorderLayout(5, 5));

		// ��ü ȭ���� ������ �κ� ========================
		grid2.setLayout(new BorderLayout(10, 10));
		grid2.add(grid2N, "North");
		grid2.add(grid2C, "Center");

		// �ڷΰ���, ����
		grid2N.add(back);
		grid2N.add(terminate);
		grid2N.setBorder(bLine);

		grid2C.setLayout(new GridLayout(2, 1, 10, 10));
		grid2C.add(grid2C1);
		grid2C.add(grid2C2);

		grid2C1.setLayout(new BorderLayout());
		grid2C1.add(grid2C1N, "North"); // ��ư ����
		grid2C1.add(grid2C1C, "Center");

		progressButton[0] = waiting;
		progressButton[1] = surgery;
		progressButton[2] = reservation;
		progressButton[3] = end;
		progressButton[4] = cancellation;

		// ���� ���, ���� ��, ����, �Ϸ�, ���
		grid2C1N.setBorder(bLine);
		for (int i = 0; i < progressButton.length; i++) {
			grid2C1N.add(progressButton[i]);
			progressButton[i].setBorder(bbRai);
		}

		grid2C1C.setLayout(new BoxLayout(grid2C1C, BoxLayout.X_AXIS));
		grid2C1C.setBorder(bLine);

		grid2C2.setLayout(new BoxLayout(grid2C2, BoxLayout.X_AXIS));

		addInterface();

	}

	private void addInterface() {
		searchPB.addActionListener(this);

		insert.addActionListener(this);
		reception.addActionListener(this);

		waiting.addActionListener(this);
		surgery.addActionListener(this);
		reservation.addActionListener(this);
		end.addActionListener(this);
		cancellation.addActionListener(this);

		back.addActionListener(this);
		terminate.addActionListener(this);

		write.addActionListener(this);
	}

	public void menu() {
		mf.add(this);
		insertButtonSelected(); // ���.���� ȭ���ʱⰪ
		progressButtonSelected(waiting); // ������..ȭ���ʱⰪ
		npmg.menu(); // �޸� ȭ��
		nppg.menu();
		showBox();
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}

	private void buttonChangeInResRec() {
		waiting.setBorder(bbRai);
		surgery.setBorder(bbRai);
		reservation.setBorder(bbRai);
		end.setBorder(bbRai);
		cancellation.setBorder(bbRai);
		waiting.setEnabled(true);
		surgery.setEnabled(true);
		reservation.setEnabled(true);
		end.setEnabled(true);
		cancellation.setEnabled(true);
	}

	private void buttonChangeInPatient() {
		insert.setBorder(bbRai);
		reception.setBorder(bbRai);
		insert.setEnabled(true);
		reception.setEnabled(true);
	}

	public void insertButtonSelected() {
		buttonChangeInPatient();
		insert.setBorder(bbLow);
		insert.setEnabled(false);
		mf.ng.grid1C1.remove(grid1C1C);
		mf.ng.grid1C1.remove(nprg);
		mf.ng.grid1C1.remove(nprg.gridS);
		npig.erase();
		npig.menu();
		showBox();
	}

	private void receptionButtonSelected() {
		buttonChangeInPatient();
		reception.setBorder(bbLow);
		reception.setEnabled(false);
		mf.ng.grid1C1.remove(grid1C1C);
		mf.ng.grid1C1.remove(npig);
		mf.ng.grid1C1.remove(npig.grid1C1S);
		nprg.erase();
		nprg.menu();
		showBox();
	}

	public void progressButtonSelected(JButton progressI) {
		buttonChangeInResRec();
		progressI.setBorder(bbLow);
		progressI.setEnabled(false);
		mf.ng.grid2C1.remove(grid2C1C);
		nprrg.menu(progressI.getText());
	}

	public void backButtonSelected() {
		mf.remove(this);
		mf.lg.menu();
		mf.lg.showBox();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �ڷΰ���, ����
		if (e.getSource().equals(back)) {
			backButtonSelected();
		} else if (e.getSource().equals(terminate)) {
			System.exit(0);
		}
		// �˻�
		if (e.getSource().equals(searchPB)) {
			sf.sg.searchPatient(searchPtf.getText());
		}
		// ���, ����
		if (e.getSource().equals(insert)) {
			insertButtonSelected();
		} else if (e.getSource().equals(reception)) {
			receptionButtonSelected();
		}
		// ���� ���, ���� ��, ����, �Ϸ�, ���
		for (int i = 0; i < progressButton.length; i++) {
			if (e.getSource().equals(progressButton[i])) {
				progressButtonSelected(progressButton[i]);
				break;
			}
		}

	}

}
