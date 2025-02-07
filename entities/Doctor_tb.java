
public class Doctor_tb {
	private int did;
	private String name;
	private String specialization;
	private int count;

	public Doctor_tb() {
		did = ++count;
	}

	public Doctor_tb(String name, String specialization) {
		did = ++count;
		this.name = name;
		this.specialization = specialization;
	}

	public Doctor_tb(int did, String name, String specialization) {
		super();
		this.did = did;
		this.name = name;
		this.specialization = specialization;
	}

	public int getDid() {
		return did;
	}

	public String getName() {
		return name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return "Doctor Id = " + did + ", Name = " + name + ", Specialization = " + specialization;
	}

}
