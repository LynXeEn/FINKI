package mk.ukim.finki.wp.kol2022.g3.service.impl;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUserType;
import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidForumUserIdException;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidInterestIdException;
import mk.ukim.finki.wp.kol2022.g3.repository.ForumUserRepository;
import mk.ukim.finki.wp.kol2022.g3.repository.InterestRepository;
import mk.ukim.finki.wp.kol2022.g3.service.ForumUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForumUserServiceImpl implements ForumUserService {

    private final ForumUserRepository forumUserRepository;
    private final InterestRepository interestRepository;
    private final PasswordEncoder passwordEncoder;

    public ForumUserServiceImpl(ForumUserRepository forumUserRepository, InterestRepository interestRepository, PasswordEncoder passwordEncoder) {
        this.forumUserRepository = forumUserRepository;
        this.interestRepository = interestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ForumUser> listAll() {
        return forumUserRepository.findAll();
    }

    @Override
    public ForumUser findById(Long id) {
        return forumUserRepository.findById(id).orElseThrow(InvalidForumUserIdException::new);
    }

    @Override
    public ForumUser create(String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday) {
        List<Interest> interests = interestRepository.findAllById(interestId);
        return forumUserRepository.save(new ForumUser(name,
                email, passwordEncoder.encode(password), type, interests, birthday));
    }

    @Override
    public ForumUser update(Long id, String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday) {
        ForumUser forumUser = this.findById(id);
        List<Interest> interests = interestRepository.findAllById(interestId);
        forumUser.setName(name);
        forumUser.setEmail(email);
        forumUser.setPassword(passwordEncoder.encode(password));
        forumUser.setType(type);
        forumUser.setInterests(interests);
        forumUser.setBirthday(birthday);

        return forumUserRepository.save(forumUser);
    }

    @Override
    public ForumUser delete(Long id) {
        ForumUser forumUser = this.findById(id);
        forumUserRepository.delete(forumUser);

        return forumUser;
    }

    @Override
    public List<ForumUser> filter(Long interestId, Integer age) {
        if (interestId != null && age != null){
            Interest interest = interestRepository.findById(interestId).orElseThrow(InvalidInterestIdException::new);
            LocalDate x = LocalDate.now().minusYears(age);
            return forumUserRepository.findForumUsersByInterestsContainingAndBirthdayBefore(interest, x);
        } else if (interestId != null){
            Interest interest = interestRepository.findById(interestId).orElseThrow(InvalidInterestIdException::new);
            return forumUserRepository.findForumUsersByInterestsContaining(interest);
        } else if (age != null){
            LocalDate x = LocalDate.now().minusYears(age);
            return forumUserRepository.findForumUsersByBirthdayBefore(x);
        } else {
            return forumUserRepository.findAll();
        }
    }
}
