package HW2;

import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    private final List<Student> students = new ArrayList<>(10);
    {
        students.add(new Student("Ivan", "IT"));
        students.add(new Student("Elena", "Management"));
        students.add(new Student("Egor", "Biology"));
        students.add(new Student("Sara", "History"));
        students.add(new Student("Tom", "IT"));
    }

    public List<Student> getAll() {return List.copyOf(students);}
    public List<Student> getStudentsByGroup(String groupName) {
        return students.stream()
                .filter(el -> el.getGroupName().equalsIgnoreCase(groupName))
                .toList();
    }
    public Student getStudentById(int id) {
        return students.stream()
                .filter(el -> el.getID() == id)
                .findFirst()
                .orElse(null);
    }
    public Student getStudentByName(String name) {
        return students.stream()
                .filter(el -> el.getNAME().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void addStudent(Student student) {students.add(student);}

    public Student deleteStudentById(int studentId) {
        Student studentToDelete = getStudentById(studentId);
        if (studentToDelete == null) return null;
        students.remove(studentToDelete);
        return studentToDelete;
    }

    public List<Student> getStudentsByPartOfName(String subName) {
        return students.stream()
                .filter(el -> el.getNAME().contains(subName))
                .toList();
    }
}
