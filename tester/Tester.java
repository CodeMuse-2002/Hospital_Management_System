
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.chinmayeeh.dao.DoctorDAO;
import com.chinmayeeh.dao.PatientDAO;
import com.chinmayeeh.entities.Doctor_tb;
import com.chinmayeeh.entities.Patient_tb;
import com.chinmayeeh.utils.DbUtil;

public class Tester {
	private static Connection connection;

	public static void addPatient(Scanner sc) {
		System.out.print("Enter Patient Name: ");
		String name = sc.next();
		System.out.print("Enter Patient Age: ");
		int age = sc.nextInt();
		System.out.print("Enter Patient Gender: ");
		String gender = sc.next();
		Patient_tb p = new Patient_tb(name, age, gender);
		try (PatientDAO patientDAO = new PatientDAO()) {
			int affectedRows = patientDAO.addPatient(p);
			if (affectedRows > 0) {
				System.out.println("Patient Added Successfully!");
			} else {
				System.out.println("Failed to add Patient");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void viewPatients() {
		try (PatientDAO patientdao = new PatientDAO()) {
			List<Patient_tb> patientList = patientdao.viewPatients();
			for (Patient_tb patients : patientList) {
				System.out.println(patients);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getPatientById(int id) {
		try (PatientDAO patientdao = new PatientDAO()) {
			List<Patient_tb> patient = patientdao.getPatientbyId(id);
			if (patient.size() > 0) {
				for (Patient_tb e : patient) {
					System.out.println(e.toString());
				}
				return true;
			} else {
				System.out.println("Patient not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void viewDoctors() {
		try (DoctorDAO doctordao = new DoctorDAO()) {
			List<Doctor_tb> doctorList = doctordao.viewDoctor();
			for (Doctor_tb d : doctorList) {
				System.out.println(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean getDoctorById(int id) {
		try (DoctorDAO doctordao = new DoctorDAO()) {
			List<Doctor_tb> doctor = doctordao.getDoctorbyId(id);
			if (doctor.size() > 0) {
				for (Doctor_tb e : doctor) {
					System.out.println(e.toString());
				}
				return true;
			} else {
				System.out.println("Doctor not found.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean checkDoctorAvailable(int doctorId, String appointmentDate) {
		String statement = "SELECT COUNT(*) FROM APPOINTMENTS WHERE doctor_id = ? AND appointment_date = ?";
		try {
			PreparedStatement appointmentST = connection.prepareStatement(statement);
			appointmentST.setInt(1, doctorId);
			appointmentST.setString(2, appointmentDate);
			ResultSet rs = appointmentST.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count == 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void createConnection() throws SQLException {
		connection = DbUtil.getConnection();
	}

	public static void bookAppointment(Scanner sc) {
		System.out.println("Enter Patient id: ");
		int patientId = sc.nextInt();
		System.out.println("Enter Doctor id: ");
		int doctorId = sc.nextInt();
		System.out.println("Enter appointment date(YYYY-MM-DD)");
		String appointmentDate = sc.next();
		if (getPatientById(patientId) && getDoctorById(doctorId)) {
			if (checkDoctorAvailable(doctorId, appointmentDate)) {
				String statement = "INSERT INTO appointments(patient_id,doctor_id,appointment_date) VALUES(?,?,?)";
				try {
					PreparedStatement appointmentST = connection.prepareStatement(statement);
					appointmentST.setInt(1, patientId);
					appointmentST.setInt(2, doctorId);
					appointmentST.setString(3, appointmentDate);
					int rowAffected = appointmentST.executeUpdate();
					if (rowAffected > 0) {
						System.out.println("Appointment Booked!!");
					} else {
						System.out.println("Failed to Book Appointment");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Doctor is not available on this date!!");
			}
		} else {
			System.out.println("Either Doctor or Patient doesn't exists.");
		}
	}

	public static int menu(Scanner sc) {
		System.out.println();
		System.out.println("HOSPITAL MANAGEMENT SYSTEM");
		System.out.println("1. Add Patient");
		System.out.println("2. View Patients");
		System.out.println("3. View Doctors");
		System.out.println("4. Book Appointment");
		System.out.println("5. Exit");
		System.out.println("Enter your choice: ");
		return sc.nextInt();
	}

	public static void main(String[] args) {

		try {
			createConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		int choice;
		while ((choice = menu(sc)) != 5) {
			switch (choice) {
				case 1: {
					addPatient(sc);
					break;
				}
				case 2: {
					System.out.println("Patients Details: ");
					viewPatients();
					break;
				}
				case 3: {
					System.out.println("Doctors Details: ");
					viewDoctors();
					break;
				}
				case 4: {
					bookAppointment(sc);
					break;
				}
			}
		}
	}

}
