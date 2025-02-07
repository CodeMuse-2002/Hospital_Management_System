
public class Patient_tb {
	private int pid;
	private String name;
	private int age;
	private String gender;
	private int count;

	public Patient_tb() {
		pid = ++count;
	}

	public Patient_tb(String name, int age, String gender) {
		pid = ++count;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public Patient_tb(int pid, String name, int age, String gender) {
		super();
		this.pid = pid;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public int getPid() {
		return pid;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public void setPid(int cid) {
		this.pid = pid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Patient Id = " + pid + ", Name = " + name + ", Age = " + age + ", Gender = " + gender;
	}
}
