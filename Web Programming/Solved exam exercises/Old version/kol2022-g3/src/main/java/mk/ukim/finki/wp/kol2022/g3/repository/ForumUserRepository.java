package mk.ukim.finki.wp.kol2022.g3.repository;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ForumUserRepository extends JpaRepository<ForumUser, Long> {

    List<ForumUser> findForumUsersByInterestsContaining(Interest interestId);
    List<ForumUser> findForumUsersByInterestsContainingAndBirthdayBefore(Interest interestId, LocalDate date);
    List<ForumUser> findForumUsersByBirthdayBefore(LocalDate date);
    ForumUser findForumUserByEmail(String email);
}
