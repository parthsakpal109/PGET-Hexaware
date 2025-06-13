package Dao;

import Model.Student;

public interface DaoStudentI {
    void saveData(Student s);
    void removeData(int rno);
    void updateData(int oldRoll, int newRoll, double newMarks);
    Student searchData(int rno);
    void searchByName(String name);
    void showData();
}
