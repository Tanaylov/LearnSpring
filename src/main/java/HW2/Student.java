package HW2;

import lombok.Data;

@Data
public class Student {
    private static int idCounter = 1;
    private final int ID;
    private final String NAME;
    private String groupName;

    public Student(String NAME) {
        this.ID = idCounter++;
        this.NAME = NAME;
    }
    public Student(String NAME, String groupName) {
        this(NAME);
        this.groupName = groupName;
    }

}
