

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinmayeeh.entities.Doctor_tb;
import com.chinmayeeh.utils.DbUtil;

public class DoctorDAO implements AutoCloseable{
private Connection connection;
	
	public DoctorDAO() throws SQLException {
		connection = DbUtil.getConnection();
	}
	
	public List<Doctor_tb> viewDoctor() throws SQLException{
		String sql = "SELECT * FROM DOCTORS";
		List<Doctor_tb> doctorList = new ArrayList<Doctor_tb>();
		try(PreparedStatement selectSt = connection.prepareStatement(sql)){
			ResultSet rs = selectSt.executeQuery();
			while(rs.next()) {
				Doctor_tb p = new Doctor_tb(rs.getInt(1),rs.getString(2),rs.getString(3));
				doctorList.add(p);
			}
		}
		return doctorList;
	}
	
	public List<Doctor_tb> getDoctorbyId(int id) throws SQLException{
		String sql = "SELECT * FROM DOCTORS WHERE id = ?";
		List<Doctor_tb> doctorList = new ArrayList<Doctor_tb>();
		try(PreparedStatement selectSt = connection.prepareStatement(sql)){
			selectSt.setInt(1,id);
			ResultSet rs = selectSt.executeQuery();
			if(rs.next()) {
				Doctor_tb p = new Doctor_tb(rs.getInt(1),rs.getString(2),rs.getString(3));
				doctorList.add(p);
			}
		}
		return doctorList;
	}
	
	@Override
	public void close() throws Exception {
		if(connection != null)
		{
			connection.close();
		}
	}
}
