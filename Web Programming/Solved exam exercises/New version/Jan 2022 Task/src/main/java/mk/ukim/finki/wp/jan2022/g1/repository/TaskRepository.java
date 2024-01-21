package mk.ukim.finki.wp.jan2022.g1.repository;


import mk.ukim.finki.wp.jan2022.g1.model.Task;
import mk.ukim.finki.wp.jan2022.g1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTaskByAssigneesContaining(User user);
    List<Task> findTaskByDueDateBefore(LocalDate date);
    List<Task> findTaskByAssigneesContainingAndDueDateBefore(User user, LocalDate date);
}
