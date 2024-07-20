package gui;

import javax.swing.JComboBox;

public class ChkDate {

	NurseGUI ng;

	public ChkDate(NurseGUI n) {
		ng = n;
	}

	// jtable의 최초등록일을 'yyyymmddhh24mi'로 지정
	public String inDateToDateType(String inDate) {
		String date = "";
		for (int i = 0; date.length() != 12; i += 2) {
			if (inDate.charAt(i) >= 48 && inDate.charAt(i) <= 57) {
				if (inDate.charAt(i + 1) >= 48 && inDate.charAt(i + 1) <= 57) {
					date += inDate.charAt(i);
					date += inDate.charAt(i + 1);
				} else {
					date += "0";
					date += inDate.charAt(i);
				}
			} else {
				i--;
			}
		}
		System.out.println(date); // testcode
		return date;
	}

	// yearCom에 item insert
	public String[] insertYearCom(String inDate) {
		inDate = inDateToDateType(inDate);
		int yearInt = Integer.parseInt(inDate.substring(0, 4));
		String[] year = new String[4];
		for (int i = 1; i < year.length; i++) {
			year[i] = String.valueOf(yearInt - 1 + i);
		}
		return year;
	}

	// monthCom에 item insert
	public String[] insertMonthCom(String inDate, int idxY) {
		inDate = inDateToDateType(inDate);
		int monthInt = Integer.parseInt(inDate.substring(4, 6));
		String[] month = null;
		if (idxY == 1) { // 최초등록한 날의 연도를 선택했을 경우
			month = new String[12 - monthInt + 2];
			for (int i = 1; i < month.length; i++) {
				if (i + monthInt - 1 < 10) {
					month[i] = "0" + (i + monthInt - 1);
				} else {
					month[i] = "" + (i + monthInt - 1);
				}
			}
		} else if (idxY != 0) {
			month = new String[13];
			for (int i = 1; i < month.length; i++) {
				if (i < 10) {
					month[i] = "0" + i;
				} else {
					month[i] = "" + i;
				}
			}
		}
		return month;
	}

	// dayCom에 item insert
	public String[] insertDayCom(String inDate, String yDate, String mDate) {
		inDate = inDateToDateType(inDate);
		int dayInt = Integer.parseInt(inDate.substring(6, 8));
		String[] day = null;
		int lDayInt = Integer.parseInt(ng.ndao.returnLastDay(yDate + mDate + "01"));
		if (yDate.equals(inDate.substring(0, 4)) && mDate.equals(inDate.substring(4, 6))) {
			day = new String[lDayInt - dayInt + 2];
			int dayValue = dayInt;
			for (int i = 1; i < day.length; i++) {
				if (dayValue < 10) {
					day[i] = "0" + dayValue;
				} else {
					day[i] = "" + dayValue;
				}
				dayValue++;
			}
		} else if (!(yDate.equals("Year") && mDate.equals("Month"))) {
			day = new String[lDayInt + 1];
			for (int i = 1; i < day.length; i++) {
				if (i == 0) {
					day[i] = "Day";
				} else {
					if (i < 10) {
						day[i] = "0" + i;
					} else {
						day[i] = "" + i;
					}
				}
			}
		}
		return day;
	}

	// hourCom에 item insert
	public String[] insertHourCom(String inDate, int idxY, int idxM, int idxD) {
		inDate = inDateToDateType(inDate);
		int hourInt = Integer.parseInt(inDate.substring(8, 10));
		String[] hour = null;
		if (idxY == 1 && idxM == 1 && idxD == 1) {
			hour = new String[19 - hourInt];
			for (int i = 1; i < hour.length; i++) {
				if (i + hourInt - 1 < 10) {
					hour[i] = "0" + (i + hourInt - 1);
				} else {
					hour[i] = "" + (i + hourInt - 1);
				}
			}
		} else if (idxY != 0 && idxM != 0 && idxD != 0) {
			hour = new String[10];
			for (int i = 1; i < hour.length; i++) {
				if (i + 8 < 10) {
					hour[i] = "0" + (i + 8);
				} else {
					hour[i] = "" + (i + 8);
				}
			}
		}
		return hour;
	}

	// minCom에 item insert
	public String[] insertMinCom(String inDate, int idxY, int idxM, int idxD, int idxH) {
		inDate = inDateToDateType(inDate);
		int minInt = Integer.parseInt(inDate.substring(10));
		String[] minute = null;
		if (idxY == 1 && idxM == 1 && idxD == 1 && idxH == 1) {
			minute = new String[59 - minInt + 2];
			for (int i = 1; i < minute.length; i++) {
				if (i + minInt - 1 < 10) {
					minute[i] = "0" + (i + minInt - 1);
				} else {
					minute[i] = "" + (i + minInt - 1);
				}
			}
		} else if (idxY != 0 && idxM != 0 && idxD != 0 && idxH != 0) {
			minute = new String[61];
			for (int i = 1; i < minute.length; i++) {
				if (i < 11) {
					minute[i] = "0" + (i - 1);
				} else {
					minute[i] = "" + (i - 1);
				}
			}
		}
		return minute;
	}

	// 예약접수 시 날짜 콤보박스의 아이템 toString 후 return
	public String inDateComToString(JComboBox[] inDateCom) {
		String date = "";
		for (JComboBox jc : inDateCom) {
			if (jc.getSelectedIndex() == 0) { // 선택되지 않은 날짜가 있을 경우 null 반환
				return null;
			}
			date += jc.getSelectedItem().toString();
		}
		return date;
	}
}
