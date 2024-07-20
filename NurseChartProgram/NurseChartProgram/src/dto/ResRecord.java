package dto;

public class ResRecord {

	private String inDate;
	private Doctor d = new Doctor();
	private ExamRec er = new ExamRec();
	private String progress;
	private String payment;
	private String pay;
	private String cancelDate;
	private String cancelReason;

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public ExamRec getEr() {
		return er;
	}

	public void setEr(ExamRec er) {
		this.er = er;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return "ResRecord [inDate=" + inDate + ", progress=" + progress + ", payment=" + payment + ", pay=" + pay + "]";
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public Doctor getD() {
		return d;
	}

	public void setD(Doctor d) {
		this.d = d;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

}
