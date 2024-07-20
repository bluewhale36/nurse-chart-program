package dto;

import java.util.ArrayList;

public class Memo {

	private ArrayList<DocMemo> dm = new ArrayList<>();
	private ArrayList<NurMemo> nm = new ArrayList<>();

	public ArrayList<DocMemo> getDm() {
		return dm;
	}

	public void setDm(ArrayList<DocMemo> dm) {
		this.dm = dm;
	}

	public ArrayList<NurMemo> getNm() {
		return nm;
	}

	public void setNm(ArrayList<NurMemo> nm) {
		this.nm = nm;
	}

}
