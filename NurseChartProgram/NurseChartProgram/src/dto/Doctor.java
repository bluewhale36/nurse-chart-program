package dto;

public class Doctor {

	private String docName;
	private String docNum;
	private String subject;

	@Override
	public String toString() {
		return "Doctor [docName=" + docName + ", docNum=" + docNum + ", subject=" + subject + "]";
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
