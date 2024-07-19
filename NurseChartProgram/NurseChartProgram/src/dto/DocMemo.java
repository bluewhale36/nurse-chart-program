package dto;

public class DocMemo {

	private Doctor doc = new Doctor();
	private String dMemo;
	private String dmDate;

	public Doctor getDoc() {
		return doc;
	}

	public void setDoc(Doctor d) {
		this.doc = d;
	}

	public String getdMemo() {
		return dMemo;
	}

	public void setdMemo(String dMemo) {
		this.dMemo = dMemo;
	}

	public String getDmDate() {
		return dmDate;
	}

	public void setDmDate(String dmDate) {
		this.dmDate = dmDate;
	}

}
