package HW5_6.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Schema(name = "Library client")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Reader ID")
    private Long id;

    @Column(name = "first_name")
    @Schema(name = "Reader name", minimum = "2", maximum = "30")
    private String name;

    @Column(name = "on_hand")
    @Schema(name = "Have book on hand")
    private Boolean onHand = false;
}
