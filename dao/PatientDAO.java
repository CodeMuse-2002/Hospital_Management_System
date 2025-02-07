
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinmayeeh.entities.Patient_tb;
import com.chinmayeeh.utils.DbUtil;

public class PatientDAO implements AutoCloseable {

	private Connection connection;

	public PatientDAO() throws SQLException {
		connection = DbUtil.getConnection();
	}

	public int addPatient(Patient_tb p) throws SQLException {
		String sql = "INSERT INTO PATIENTS(name,age,gender) VALUES (?,?,?)";
		int affectedRows;
		try (PreparedStatement insertSt = connection.prepareStatement(sql)) {
			insertSt.setString(1, p.getName());
			insertSt.setInt(2, p.getAge());
			insertSt.setString(3, p.getGender());
			affectedRows = insertSt.executeUpdate();
		}
		return affectedRows;
	}

	public List<Patient_tb> viewPatients() throws SQLException {
		String sql = "SELECT * FROM PATIENTS";
		List<Patient_tb> patientList = new ArrayList<Patient_tb>();
		try (PreparedStatement selectSt = connection.prepareStatement(sql)) {
			ResultSet rs = selectSt.executeQuery();
			while (rs.next()) {
				Patient_tb p = new Patient_tb(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				patientList.add(p);
			}
		}
		return patientList;
	}

	public List<Patient_tb> getPatientbyId(int id) throws SQLException {
		String sql = "SELECT * FROM PATIENTS WHERE id = ?";
		List<Patient_tb> patientList = new ArrayList<Patient_tb>();
		try (PreparedStatement selectSt = connection.prepareStatement(sql)) {
			selectSt.setInt(1, id);
			ResultSet rs = selectSt.executeQuery();
			if (rs.next()) {
				Patient_tb p = new Patient_tb(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				patientList.add(p);
			}
		}
		return patientList;
	}

	@Override
	public void close() throws Exception {
		if (connection != null) {
			connection.close();
		}
	}
}
