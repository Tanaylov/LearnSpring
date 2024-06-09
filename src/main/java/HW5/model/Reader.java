package HW5.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "on_hand")
    private Boolean onHand = false;
}
