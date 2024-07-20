package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dto.Patient;

public class SearchPatientInfoGUI extends JPanel {

	SearchGUI sg;

	JPanel grid[], grid0[], grid1[], grid2[], info[], name, idNum, gender, age, hp, stDate, addr;

	JLabel pInfo[], pName, pIdNum, pGender, pAge, pHp, pStDate, pAddr;

	private LineBorder bLine1 = new LineBorder(Color.black, 1, true);

	Date now = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	String formattedNow = formatter.format(now);
	int curDate = Integer.parseInt(formattedNow);

	public SearchPatientInfoGUI(SearchGUI s) {
		sg = s;

		this.setLayout(new GridLayout(4, 1));
		this.setBorder(bLine1);

		System.out.println(curDate);

		grid = new JPanel[4];
		for (int i = 0; i < grid.length; i++) {
			grid[i] = new JPanel();
			if (i != 3) {
				grid[i].setLayout(new GridLayout(1, 2));
			} else {
				grid[i].setLayout(new BoxLayout(grid[i], BoxLayout.X_AXIS));
			}
			this.add(grid[i]);
		}

		grid0 = new JPanel[2];
		for (int i = 0; i < grid0.length; i++) {
			grid0[i] = new JPanel();
			grid0[i].setLayout(new BoxLayout(grid0[i], BoxLayout.X_AXIS));
			grid[0].add(grid0[i]);
		}

		grid1 = new JPanel[2];
		for (int i = 0; i < grid1.length; i++) {
			grid1[i] = new JPanel();
			grid1[i].setLayout(new BoxLayout(grid1[i], BoxLayout.X_AXIS));
			grid[1].add(grid1[i]);
		}

		grid2 = new JPanel[2];
		for (int i = 0; i < grid2.length; i++) {
			grid2[i] = new JPanel();
			grid2[i].setLayout(new BoxLayout(grid2[i], BoxLayout.X_AXIS));
			grid[2].add(grid2[i]);
		}

		name = new JPanel();
		idNum = new JPanel();
		gender = new JPanel();
		age = new JPanel();
		hp = new JPanel();
		stDate = new JPanel();
		addr = new JPanel();

		info = new JPanel[7];
		info[0] = name;
		info[1] = idNum;
		info[2] = gender;
		info[3] = age;
		info[4] = hp;
		info[5] = stDate;
		info[6] = addr;

		for (int i = 0; i < info.length; i++) {
			info[i].setLayout(new BoxLayout(info[i], BoxLayout.X_AXIS));
		}

		pName = new JLabel();
		pIdNum = new JLabel();
		pGender = new JLabel();
		pAge = new JLabel();
		pHp = new JLabel();
		pStDate = new JLabel();
		pAddr = new JLabel();

		pInfo = new JLabel[7];
		pInfo[0] = pName;
		pInfo[1] = pIdNum;
		pInfo[2] = pGender;
		pInfo[3] = pAge;
		pInfo[4] = pHp;
		pInfo[5] = pStDate;
		pInfo[6] = pAddr;

		name.add(new JLabel(" 환자성함: "));
		name.add(pName);
		grid0[0].add(name);

		idNum.add(new JLabel(" 주민번호: "));
		idNum.add(pIdNum);
		grid0[1].add(idNum);

		gender.add(new JLabel(" 환자성별: "));
		gender.add(pGender);
		grid1[0].add(gender);

		age.add(new JLabel(" 환자나이: "));
		age.add(pAge);
		grid1[1].add(age);

		hp.add(new JLabel(" 휴대전화: "));
		hp.add(pHp);
		grid2[0].add(hp);

		stDate.add(new JLabel(" 최초등록일: "));
		stDate.add(pStDate);
		grid2[1].add(stDate);

		addr.add(new JLabel(" 환자주소: "));
		addr.add(pAddr);
		grid[3].add(addr);

	}

	public String returnAge(String idNum) {
		int bDate = Integer.parseInt(idNum.substring(0, 6));
		int yearIdx = Integer.parseInt(idNum.substring(7, 8));
		if (yearIdx == 3 || yearIdx == 4 || yearIdx == 7 || yearIdx == 8) {
			bDate += 20000000;
		} else {
			bDate += 19000000;
		}
		System.out.println(bDate);
		System.out.println(yearIdx);
		String age = String.valueOf(((curDate - bDate) / 10000));
		return age;
	}

	public void addText(Patient p) {
		pName.setText(p.getName());
		pIdNum.setText(p.getIdNum());
		pGender.setText(p.getGender());
		pAge.setText(("(만) ") + returnAge(p.getIdNum()) + "세");
		pHp.setText(p.getTel());
		pStDate.setText(p.getStDate());
		pAddr.setText(p.getAddr());
		showBox();
	}

	public void menu(Patient p) {
		addText(p);
		sg.grid[0].add(this);
		showBox();
	}

	public void showBox() {
		this.setVisible(false);
		this.setVisible(true);
		for (int i = 0; i < pInfo.length; i++) {
			pInfo[i].setVisible(false);
			pInfo[i].setVisible(true);
		}
	}

}
