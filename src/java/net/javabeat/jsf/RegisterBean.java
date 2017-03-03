package net.javabeat.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;

import net.javabeat.jsf.data.Student;

@Named(value="register")
@FlowScoped(value="register")
public class RegisterBean implements Serializable{
	private int index = 0;
	private String name;
	private String age;
	private String address;

	private String major;
	private String graduateYear;

	private List<Student> students = new ArrayList<Student>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGraduateYear() {
		return graduateYear;
	}

	public void setGraduateYear(String graduateYear) {
		this.graduateYear = graduateYear;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String fillBasicInfo (){
		index++;
		return "registera";
	}

	public String fillAcademicInfo(){
		index++;
		return "registerb";
	}

	public String backIntoBasicInfo(){
		index--;
		return "registera";
	}

	public String save(){
		Student student = new Student();
		student.setName(this.name);
		student.setAddress(this.address);
		student.setAge(this.age);
		student.setMajor(this.major);
		student.setGraduateYear(this.graduateYear);
		this.students.add(student);
		this.name = "";
		this.major = "";
		this.graduateYear = "";
		this.address = "";
		this.age = "";
		this.index = 0;
		return "";
	}
}