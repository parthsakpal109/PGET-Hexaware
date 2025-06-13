package Dao;

import Model.Student;

public interface DaoStudentI {
    public void saveData(Student s);
    public void removeData(int rollno);
    public void updateData(int rollno, String name, double marks);
    public Student searchData(int rollno);
    public void showData();
    public void searchByName(String name);
    public void searchByNameAndMarks(String name, double marks);
    public void searchHigherMark(double marks);
}
