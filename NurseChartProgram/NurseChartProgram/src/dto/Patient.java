package dto;

import java.util.ArrayList;

public class Patient {

	private String name;
	private String idNum;
	private String tel;
	private String gender;
	private String addr;
	private String stDate;
	private ArrayList<ResRecord> resRecList = new ArrayList<>();
	private ArrayList<ResRecord> endRecList = new ArrayList<>();
	private ArrayList<ResRecord> resCancelList = new ArrayList<>();
	private Memo memo = new Memo();

	public ArrayList<ResRecord> getEndRecList() {
		return endRecList;
	}

	public void setEndRecList(ArrayList<ResRecord> examRecList) {
		this.endRecList = examRecList;
	}

	public ArrayList<ResRecord> getResCancelList() {
		return resCancelList;
	}

	public void setResCancelList(ArrayList<ResRecord> resCancelList) {
		this.resCancelList = resCancelList;
	}

	public ArrayList<ResRecord> getResRecList() {
		return resRecList;
	}

	public void setResRecList(ArrayList<ResRecord> resRecList) {
		this.resRecList = resRecList;
	}

	public String getStDate() {
		return stDate;
	}

	public void setStDate(String stDate) {
		this.stDate = stDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		String info = chkIdTel(idNum);
		String idNum1 = "";
		for (int i = 0; i < info.length(); i++) {
			if (i == 6) {
				idNum1 += "-";
			}
			idNum1 += "" + info.charAt(i);
		}
		this.idNum = idNum1;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		String info = chkIdTel(tel);
		String tel1 = "";
		for (int i = 0; i < info.length(); i++) {
			if (i == 3 || i == 7) {
				tel1 += "-";
			}
			tel1 += "" + info.charAt(i);
		}
		this.tel = tel1;
	}

	public String chkIdTel(String info) {
		String pInfo = "";
		for (int i = 0; i < info.length(); i++) {
			if (info.charAt(i) != '-') {
				pInfo += "" + info.charAt(i);
			}
		}
		return pInfo;
	}

	public String chkDate(String info) {
		String pInfo = "";
		for (int i = 2; i < 10; i++) {
			if (info.charAt(i) == '-') {
				pInfo += "/";
				continue;
			}
			pInfo += "" + info.charAt(i);
		}
		return pInfo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Memo getMemo() {
		return memo;
	}

	public void setMemo(Memo memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Patient [name=" + name + ", idNum=" + idNum + ", tel=" + tel + ", gender=" + gender + ", addr=" + addr
				+ ", stDate=" + stDate + "]";
	}

}
