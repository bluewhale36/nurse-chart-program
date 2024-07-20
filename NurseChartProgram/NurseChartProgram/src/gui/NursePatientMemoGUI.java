package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class NursePatientMemoGUI extends JPanel implements KeyListener, ItemListener, ActionListener {

	NurseGUI ng = null;

	JOptionPane error, success;

	JPanel gridC = new JPanel();
	JPanel gridS = new JPanel();
	JPanel grid1 = new JPanel();
	JPanel grid2 = new JPanel();
	JPanel grid3 = new JPanel();
	JPanel grid3T = new JPanel();
	JPanel[] grid2P = new JPanel[3];
	JPanel[] grid2P1 = new JPanel[2];
	JPanel[] grid2P2P = new JPanel[2];

	JTextField nametf, idNumtf6, idNumtf7, memotf;
	JButton write = new JButton("�ۼ�");

	private String[] mCom = { "��ȣ��޸�", "�ǻ�޸�" };
	private JComboBox<String> memoCom = new JComboBox<String>(mCom);

	private LineBorder bLine = new LineBorder(Color.black, 1, true);

	public JComboBox<String> getMemoCom() {
		return memoCom;
	}

	public void setMemoCom(JComboBox<String> memoCom) {
		this.memoCom = memoCom;
	}

	public NursePatientMemoGUI(NurseGUI n) {
		ng = n;

		error = new JOptionPane();
		success = new JOptionPane();

		nametf = new JTextField();
		idNumtf6 = new JTextField();
		idNumtf7 = new JTextField();
		memotf = new JTextField(45);

		this.setLayout(new BorderLayout(5, 5));
		this.add(gridC, "Center");
		this.add(gridS, "South");

		gridC.setLayout(new GridLayout(3, 1));
		gridC.setBorder(bLine);
		gridC.add(grid1);
		gridC.add(grid2);
		gridC.add(grid3);

		grid1.setLayout(new BoxLayout(grid1, BoxLayout.X_AXIS));
		grid1.add(new JLabel(" ȯ�ڼ���: "));

		grid2.setLayout(new GridLayout(3, 1));
		for (int i = 0; i < grid2P.length; i++) {
			grid2P[i] = new JPanel();
			grid2.add(grid2P[i]);
		}
//		grid2P[0].setLayout(new BoxLayout(grid2P[0], BoxLayout.X_AXIS));

		grid2P[1].setLayout(new GridLayout(1, 2));
		// ȯ�ڼ���, �ֹι�ȣ �κ� �׸��� �� �ڽ� ���̾ƿ� ���� �� ��ü �߰�
		for (int i = 0; i < grid2P1.length; i++) {
			grid2P1[i] = new JPanel();
			grid2P[1].add(grid2P1[i]);
			grid2P1[i].setLayout(new BoxLayout(grid2P1[i], BoxLayout.X_AXIS));
		}
		grid2P1[0].add(new JLabel(" ȯ�ڼ���: "));
		grid2P1[0].add(nametf);
		grid2P1[1].add(new JLabel(" �ֹι�ȣ: "));
		grid2P1[1].add(idNumtf6);
		grid2P1[1].add(new JLabel(" - "));
		grid2P1[1].add(idNumtf7);
		idNumtf6.setEnabled(false);
		idNumtf7.setEnabled(false);

		// �޸� �޺��ڽ�
		grid2P[2].setLayout(new FlowLayout(FlowLayout.RIGHT));
		grid2P[2].add(memoCom);
		memoCom.setEnabled(false);

		grid3.setLayout(new BoxLayout(grid3, BoxLayout.X_AXIS));
		grid3.add(new JLabel(" ȯ�ڸ޸�: "));
		grid3.add(grid3T);

		grid3T.setLayout(new BoxLayout(grid3T, BoxLayout.X_AXIS));

		gridS.setLayout(new BoxLayout(gridS, BoxLayout.X_AXIS));
		gridS.setBorder(bLine);
		gridS.add(new JLabel(" �޸� �ۼ�: "));
		gridS.add(memotf);
		gridS.add(write);

		write.setEnabled(false);

		addInterface();
	}

	public void menu() {
		ng.grid1C2.add(this);
		ng.nsptg.menu();
		erase();
		showBox();
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
	}

	public void addInterface() {
		nametf.addKeyListener(this);
		memoCom.addItemListener(this);
		memotf.addKeyListener(this);
		write.addActionListener(this);
	}

	public void nMemoBoxSelected() {
		memotf.setEnabled(true);
		grid3T.removeAll();
		ng.npmtg.menu(idNumtf6.getText() + "-" + idNumtf7.getText());
		showBox();
	}

	public void dMemoBoxSelected() {
		memotf.setEnabled(false);
		write.setEnabled(false);
		grid3T.removeAll();
		ng.dpmtg.menu(idNumtf6.getText() + "-" + idNumtf7.getText());
		showBox();
	}

	public void erase() {
		nametf.setText("");
		idNumtf6.setText("");
		idNumtf7.setText("");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource().equals(nametf)) {
			if (nametf.getText().length() > 0) {
				ng.nsptg.delRow();
				ng.nsptg.addRow(ng.ndao.selectePatientWhereLike(nametf.getText()));
			}
			if (ng.nsptg.getModel().getRowCount() == 0) {
				grid2P[0].remove(ng.nsptg.getSelectPInfo());
				ng.nsptg.getSelectPInfo().setText("�˻��� ȯ�ڰ� �����ϴ�.");
				grid2P[0].add(ng.nsptg.getSelectPInfo());
				ng.nsptg.showBox();
			} else {
				grid2P[0].remove(ng.nsptg.getSelectPInfo());
				ng.nsptg.getSelectPInfo().setText("�˻��� ȯ��: " + ng.nsptg.getModel().getRowCount() + "��.");
				grid2P[0].add(ng.nsptg.getSelectPInfo());
				ng.nsptg.showBox();
			}
		}
		if (e.getSource().equals(memotf)) {
			if (memoCom.getSelectedIndex() == 0) {
				if (memotf.getText().length() > 0) {
					write.setEnabled(true);
				} else {
					write.setEnabled(false);
				}
			}

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(memoCom)) {
			if (memoCom.getSelectedIndex() == 0) {
				nMemoBoxSelected();
			} else if (memoCom.getSelectedIndex() == 1) {
				dMemoBoxSelected();
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(write)) {
			if (ng.nsptg.getPmTable().getSelectedColumnCount() == 1
					&& ng.nsptg.getPmTable().getSelectedRowCount() == 1) {
				if (memotf.getText().length() > 0) {
					JTable curTable = ng.nsptg.getPmTable();
					int row = curTable.getSelectedRow();
					String name = curTable.getValueAt(row, 0).toString();
					String idNum = curTable.getValueAt(row, 1).toString();
					String nMemo = memotf.getText();
					ng.ndao.insertNurMemo(name, idNum, nMemo);
					ng.npmtg.menu(idNum);
					success.showMessageDialog(null, "�޸� �ԷµǾ����ϴ�.", "�޸� �Է� ����", JOptionPane.INFORMATION_MESSAGE);
				} else {
					error.showMessageDialog(null, "�޸�� �ѱ��� �̻� �ۼ��� �� �ֽ��ϴ�.", "�޸� �Է� ����", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				error.showMessageDialog(null, "�޸� �ۼ��� ȯ�ڴ� �Ѹ� ������ �� �ֽ��ϴ�.", "�޸� �Է� ����", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
