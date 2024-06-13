package HW5_6.repository;

import HW5_6.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Transactional
    @Modifying
    @Query("update Issue i set i.returnAt = ?1 where i.id = ?2")
    void setReturnAtByIssueId(LocalDateTime localDateTime, long id);
}
