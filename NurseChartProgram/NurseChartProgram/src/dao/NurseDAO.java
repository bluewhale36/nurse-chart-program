package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.DocMemo;
import dto.Doctor;
import dto.ExamRec;
import dto.Memo;
import dto.NurMemo;
import dto.Patient;
import dto.ResRecord;
import gui.NurseGUI;

public class NurseDAO {

	Connection conn;
	NurseGUI ng = null;

	public NurseDAO(NurseGUI n) {
		DAO dao = new DAO();
		conn = dao.conn;
		ng = n;
	}

	public void commit() {
		if (conn != null) {
			Statement stmt = null;
			try {
				String sql = "commit";
				stmt = conn.createStatement();
				stmt.executeQuery(sql);
				System.out.println("committed");
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() commit()");
			}
		}
	}

	public String[] selectDocName() {
		if (conn != null) {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				ArrayList<String> docName = new ArrayList<>();
				String sql = "select * from doctor order by docNum";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					docName.add(rs.getString("docName"));
				}
				String[] docNameCom = new String[docName.size()];
				for (int i = 0; i < docNameCom.length; i++) {
					docNameCom[i] = docName.get(i);
				}
				return docNameCom;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectDocName()");
			}
		}
		return null;
	}

	public String[] selectDocNum() {
		if (conn != null) {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				ArrayList<String> docNum = new ArrayList<>();
				String sql = "select * from doctor order by docNum";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					docNum.add(rs.getString("docNum"));
				}
				String[] docNumCom = new String[docNum.size()];
				for (int i = 0; i < docNumCom.length; i++) {
					docNumCom[i] = docNum.get(i);
				}
				return docNumCom;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectDocName()");
			}
		}
		return null;
	}

	public boolean insertPatient(Patient p) {
		if (conn != null) {
			PreparedStatement psmt = null;
			try {
				String sql = "insert into patient values (?,?,?,?,?,default)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, p.getName());
				psmt.setString(2, p.getIdNum());
				psmt.setString(3, p.getTel());
				psmt.setString(4, p.getGender());
				psmt.setString(5, p.getAddr());
				int a = psmt.executeUpdate();
				System.out.println(a + "건 성공.");
				commit();
				return true;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() insertPatient()");
			}
		}
		return false;
	}

	public boolean insertResRec(Patient p) {
		if (conn != null) {
			PreparedStatement psmt = null;
			String sql;
			try {
				if (p.getResRecList().get(0).getInDate() == null) {
					sql = "insert into resRec values(?,?,?,default,?,?,'수납 전', null)";
					psmt = conn.prepareStatement(sql);
					psmt.setString(4, p.getResRecList().get(0).getD().getDocName());
					psmt.setString(5, p.getResRecList().get(0).getD().getDocNum());
				} else if (p.getResRecList().get(0).getInDate() != null) {
					sql = "insert into resRec values (?,?,?,?,?,?,'수납 전',null)";
					psmt = conn.prepareStatement(sql);
					psmt.setString(4, p.getResRecList().get(0).getInDate());
					psmt.setString(5, p.getResRecList().get(0).getD().getDocName());
					psmt.setString(6, p.getResRecList().get(0).getD().getDocNum());
				}
				psmt.setString(1, p.getName());
				psmt.setString(2, p.getIdNum());
				psmt.setString(3, p.getResRecList().get(0).getProgress());
				int a = psmt.executeUpdate();
				System.out.println(a + "건 성공.");
				commit();
				return true;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() insertResRec()");
			}
		}
		return false;
	}

	public ArrayList<Patient> selectePatientWhereLike(String name) {
		if (conn != null) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				ArrayList<Patient> pList = new ArrayList<Patient>();
				String sql = "select * from patient where name like '%'||?||'%' order by name";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				rs = psmt.executeQuery();
				while (rs.next()) {
					Patient p = new Patient();
					p.setName(rs.getString("name"));
					p.setIdNum(rs.getString("idNum"));
					p.setTel(rs.getString("tel"));
					p.setGender(rs.getString("gender"));
					p.setAddr(rs.getString("addr"));
					p.setStDate(rs.getString("stDate"));
					pList.add(p);
				}
				return pList;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectPatientWhereLikeReturnP()");
			}
		}
		return null;
	}

	public Patient selectSpecificPatient(String idNum) {
		if (conn != null) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				Patient p = new Patient();
				String sql = "select * from patient where idNum = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, idNum);
				rs = psmt.executeQuery();
				rs.next();
				p.setName(rs.getString("name"));
				p.setIdNum(rs.getString("idNum"));
				p.setTel(rs.getString("tel"));
				p.setGender(rs.getString("gender"));
				p.setAddr(rs.getString("addr"));
				p.setStDate(rs.getString("stDate"));
				return p;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectSpecificPatient()");
			}
		}
		return null;
	}

	public ArrayList<Patient> selectAllPatient() {
		if (conn != null) {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				ArrayList<Patient> pList = new ArrayList<Patient>();
				String sql = "select * from patient";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Patient p = new Patient();
					p.setName(rs.getString("name"));
					p.setIdNum(rs.getString("idNum"));
					p.setTel(rs.getString("tel"));
					p.setGender(rs.getString("gender"));
					p.setAddr(rs.getString("addr"));
					p.setStDate(rs.getString("stDate"));
					pList.add(p);
				}
				return pList;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectAllPatient()");
			}
		}
		return null;
	}

	public ArrayList<Patient> selectresRec(String progress) {
		if (conn != null) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				ArrayList<Patient> pList = new ArrayList<>();
				String sql = "select * from resRec where progress = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, progress);
				rs = psmt.executeQuery();
				while (rs.next()) {
					Patient p = new Patient();
					ResRecord rr = new ResRecord();
					p.setName(rs.getString("name"));
					p.setIdNum(rs.getString("idNum"));
					rr.setInDate(rs.getString("inDate"));
					rr.getD().setDocName(rs.getString("docName"));
					rr.getD().setDocNum(rs.getString("docNum"));
					rr.setPayment(rs.getString("payment"));
					rr.setPay(rs.getString("pay"));
					p.getResRecList().add(rr);
					pList.add(p);
				}
				return pList;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectresRec()");
			}
		}
		return null;
	}

	public void updateProgress(String progress, String inDate, String docNum) {
		if (conn != null) {
			PreparedStatement psmt = null;
			try {
				String sql = "update resRec set progress = ? where inDate = ? and docNum = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, progress);
				psmt.setString(2, inDate);
				psmt.setString(3, docNum);
				int a = psmt.executeUpdate();
				System.out.println(a + "건 성공.");
				commit();
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() updateProgress()");
			}
		}
	}

	public boolean updateProgressPay(Patient p) {
		if (conn != null) {
			PreparedStatement psmt = null;
			try {
				ResRecord rr = p.getResRecList().get(0);
				String sql = "update resRec set payment = ?, pay = ? where idNum = ? and inDate = ? and docNum = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, rr.getPayment());
				psmt.setString(2, rr.getPay());
				psmt.setString(3, p.getIdNum());
				psmt.setString(4, rr.getInDate());
				psmt.setString(5, rr.getD().getDocNum());
				int a = psmt.executeUpdate();
				System.out.println(a + "건 성공.");
				commit();
				return true;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() updateProgressPay()");
			}
		}
		return false;
	}

	public ArrayList<String[]> selectNurMemoWhere(String idNum) {
		if (conn != null) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				ArrayList<String[]> nmList = new ArrayList<String[]>();
				String sql = "select * from nurMemo where idNum = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, idNum);
				rs = psmt.executeQuery();
				while (rs.next()) {
					String[] m = new String[ng.npmtg.getHeader().length];
					m[0] = rs.getString("nmDate");
					m[1] = rs.getString("nMemo");
					nmList.add(m);
				}
				return nmList;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectNurMemoWhere()");
			}
		}
		return null;
	}

	public ArrayList<String[]> selectDocMemoWhere(String idNum) {
		if (conn != null) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				ArrayList<String[]> dmList = new ArrayList<String[]>();
				String sql = "select * from docMemo where idNum = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, idNum);
				rs = psmt.executeQuery();
				while (rs.next()) {
					String[] dm = new String[ng.dpmtg.getHeader().length];
					dm[0] = rs.getString("dmDate");
					dm[1] = rs.getString("docName");
					dm[2] = rs.getString("docNum");
					dm[3] = rs.getString("dMemo");
					dmList.add(dm);
				}
				return dmList;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectDocMemoWhere()");
			}
		}
		return null;
	}

	public void insertNurMemo(String name, String idNum, String nMemo) {
		if (conn != null) {
			PreparedStatement psmt = null;
			try {
				String sql = "insert into nurMemo values (?,?,?,default)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, idNum);
				psmt.setString(3, nMemo);
				int a = psmt.executeUpdate();
				System.out.println(a + "건 성공.");
				commit();
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() insertNurMemo()");
			}
		}
	}

	public String returnLastDay(String date) {
		if (conn != null) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				String sql = "select extract(day from last_day(?)) as lDate from dual";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, date);
				rs = psmt.executeQuery();
				rs.next();
				String lDate = rs.getString("lDate");
				return lDate;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() returnLastDay()");
			}
		}
		return null;
	}

	public void insertResCancel(Patient p) {
		if (conn != null) {
			PreparedStatement psmt = null;
			try {
				ResRecord rr = p.getResRecList().get(0);
				String sql = "insert into resCancel values (?,?,?,?,?,default,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, p.getName());
				psmt.setString(2, p.getIdNum());
				psmt.setString(3, rr.getInDate());
				psmt.setString(4, rr.getD().getDocName());
				psmt.setString(5, rr.getD().getDocNum());
				psmt.setString(6, rr.getCancelReason());
				int a = psmt.executeUpdate();
				System.out.println(a + "건 성공.");
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() insertResCancel()");
			}
		}
	}

	public ArrayList<Patient> selectEndPatientWhereLike(String name, boolean pay) {
		if (conn != null) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				ArrayList<Patient> pList = new ArrayList<Patient>();
				String sql;
				if (pay == true) {
					sql = "select * from endPatient where name like '%'||?||'%' and pay is not null order by inDate";
				} else {
					sql = "select * from endPatient where name like '%'||?||'%' and pay is null order by inDate";
				}
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				rs = psmt.executeQuery();
				while (rs.next()) {
					Patient p = new Patient();
					ResRecord rr = new ResRecord();
					Doctor d = new Doctor();
					ExamRec er = new ExamRec();
					p.setName(rs.getString("name"));
					p.setIdNum(rs.getString("idNum"));
					rr.setInDate(rs.getString("inDate"));
					d.setDocName(rs.getString("docName"));
					d.setDocNum(rs.getString("docNum"));
					er.setDisease(rs.getString("disease"));
					er.setMedicine(rs.getString("medicine"));
					rr.setPayment(rs.getString("payment"));
					rr.setPay(rs.getString("pay"));
					rr.setEr(er);
					rr.setD(d);
					p.getEndRecList().add(rr);
					pList.add(p);
				}
				return pList;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectEndPatientWhereLike()");
			}
		}
		return null;
	}

	public ArrayList<Patient> selectResCancel() {
		if (conn != null) {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				ArrayList<Patient> pList = new ArrayList<>();
				String sql = "select * from resCancel";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Patient p = new Patient();
					ResRecord rr = new ResRecord();
					p.setName(rs.getString("name"));
					p.setIdNum(rs.getString("idNum"));
					rr.setInDate(rs.getString("inDate"));
					rr.getD().setDocName(rs.getString("docName"));
					rr.getD().setDocNum(rs.getString("docNum"));
					rr.setCancelDate(rs.getString("cancelDate"));
					rr.setCancelReason(rs.getString("cancelReason"));
					p.getResCancelList().add(rr);
					pList.add(p);
				}
				return pList;
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectResCancel()");
			}
		}
		return null;
	}

	public Patient selectPatientAllInfo(Patient p) {
		if (conn != null) {
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				String name = p.getName();
				System.out.println(name);
				String idNum = p.getIdNum();
				System.out.println(idNum);
				String sql = "select * from resRec where name = ? and idNum = ? and progress not in ('취소','완료') order by inDate";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, idNum);
				rs = psmt.executeQuery();
				while (rs.next()) {
					ResRecord rr = new ResRecord();
					Doctor d = new Doctor();
					rr.setProgress(rs.getString("progress"));
					rr.setInDate(rs.getString("inDate"));
					d.setDocName(rs.getString("docName"));
					d.setDocNum(rs.getString("docNum"));
					rr.setPayment(rs.getString("payment"));
					rr.setPay(rs.getString("pay"));
					rr.setD(d);
					p.getResRecList().add(rr);
				}
//				System.out.println("현재 접수 정보 저장 완료.");
				sql = "select * from endPatient where name = ? and idNum = ? order by inDate";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, idNum);
				rs = psmt.executeQuery();
				while (rs.next()) {
					ResRecord rr = new ResRecord();
					Doctor d = new Doctor();
					ExamRec er = new ExamRec();
					rr.setProgress("완료");
					rr.setInDate(rs.getString("inDate"));
					d.setDocName(rs.getString("docName"));
					d.setDocNum(rs.getString("docNum"));
					er.setDisease(rs.getString("disease"));
					er.setMedicine(rs.getString("medicine"));
					rr.setPayment(rs.getString("payment"));
					rr.setPay(rs.getString("pay"));
					rr.setEr(er);
					rr.setD(d);
					p.getEndRecList().add(rr);
				}
//				System.out.println("진료 완료 정보 저장 완료.");
				sql = "select * from resCancel where name = ? and idNum = ? order by cancelDate";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, idNum);
				rs = psmt.executeQuery();
				while (rs.next()) {
					ResRecord rr = new ResRecord();
					Doctor d = new Doctor();
					rr.setInDate(rs.getString("inDate"));
					d.setDocName(rs.getString("docName"));
					d.setDocNum(rs.getString("docNum"));
					rr.setCancelDate(rs.getString("cancelDate"));
					rr.setCancelReason(rs.getString("cancelReason"));
					rr.setD(d);
					p.getResCancelList().add(rr);
				}
//				System.out.println("접수 취소 정보 저장 완료.");
				sql = "select * from docMemo where name = ? and idNum = ? order by dmDate";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, p.getIdNum());
				Memo memo = new Memo();
				p.setMemo(memo);
				rs = psmt.executeQuery();
				while (rs.next()) {
					DocMemo dm = new DocMemo();
					Doctor d = new Doctor();
					d.setDocName(rs.getString("docName"));
					d.setDocNum(rs.getString("docNum"));
					dm.setdMemo(rs.getString("dMemo"));
					dm.setDmDate(rs.getString("dmDate"));
					dm.setDoc(d);
					memo.getDm().add(dm);
				}
//				System.out.println("의사 메모 저장 완료.");
				sql = "select * from nurMemo where name = ? and idNum = ? order by nmDate";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, idNum);
				rs = psmt.executeQuery();
				while (rs.next()) {
					NurMemo nm = new NurMemo();
					nm.setnMemo(rs.getString("nMemo"));
					nm.setNmDate(rs.getString("nmDate"));
					memo.getNm().add(nm);
				}
//				System.out.println("간호사 메모 저장 완료.");
			} catch (Exception e) {
				System.out.println("Exception found at NurseDAO() selectPatientAllInfo()");
			}
		}
		return p;

	}
}
