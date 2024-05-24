package HW1;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
    private final StudentDB studentDB;

    public StudentController(StudentDB studentDB) {
        this.studentDB = studentDB;
    }

    //region Get:
    @GetMapping(path = "/all")
    public List<Student> getAllStudent() {return studentDB.getAll();}
    @GetMapping(path = "/student_id/{id}")
    public Student getStudentById(@PathVariable(name = "id") int studentId) {return studentDB.getStudentById(studentId);}
    @GetMapping(path = "/student_name/{name}")
    public Student getStudentByName(@PathVariable(name = "name") String studentName) {return studentDB.getStudentByName(studentName);}
    @GetMapping("/search")
    public List<Student> getStudentContainsStringInName(@RequestParam(name = "name") String subName) {
        return studentDB.getStudentsByPartOfName(subName);
    }
    @GetMapping
    public List<Student> getStudentsByGroup(@RequestParam String groupName) {return studentDB.getStudentsByGroup(groupName);}

    /*@GetMapping("/{group}/student")
    public List<Student> getStudentsByGroup(@PathVariable(name = "group") String groupName) {return studentDB.getStudentsByGroup(groupName);}*/

    //endregion
    @PostMapping
    public void addStudent(@RequestBody Student student) {studentDB.addStudent(student);}
    @DeleteMapping("/delete")
    public Student deleteStudentById(@RequestParam(name = "id") int studentId) {return studentDB.deleteStudentById(studentId);}
}
