package packagetest3.com.hexa.Hibernate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
 
public class Student {
	@Id
	  int rollno;
	@Column
	String nameString;
	@Column
	double marks;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Student(int rollno, String nameString, double marks) {
		super();
		this.rollno = rollno;
		this.nameString = nameString;
		this.marks = marks;
	}
 
 
 
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", nameString=" + nameString + ", marks=" + marks + "]";
	}
	
 
}