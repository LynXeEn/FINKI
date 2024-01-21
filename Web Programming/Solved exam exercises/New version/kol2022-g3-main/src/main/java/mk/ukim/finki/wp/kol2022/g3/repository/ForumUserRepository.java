package mk.ukim.finki.wp.kol2022.g3.repository;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ForumUserRepository extends JpaRepository<ForumUser, Long> {

    List<ForumUser> findForumUserByInterestsContaining(Interest interest);
    List<ForumUser> findForumUserByBirthdayBefore(LocalDate date);
    List<ForumUser> findForumUserByInterestsContainingAndBirthdayBefore(Interest interest, LocalDate date);
    ForumUser findForumUserByEmail(String email);
}
