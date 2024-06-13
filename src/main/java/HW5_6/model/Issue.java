package HW5_6.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reader_id")
//    @ManyToOne
    private Long readerId;

    @Column(name = "book_id")
//    @ManyToOne
    private Long bookId;

    @Column(name = "issue_at")
    private LocalDateTime issueAt = LocalDateTime.now();

    @Column(name = "return_at")
    private LocalDateTime returnAt;
}
