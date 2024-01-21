package mk.ukim.finki.wp.june2022.g1.repository;

import mk.ukim.finki.wp.june2022.g1.model.User;
import mk.ukim.finki.wp.june2022.g1.model.VirtualServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VirtualServerRepository extends JpaRepository<VirtualServer, Long> {

    List<VirtualServer> findVirtualServerByOwnersContaining(User user);
    List<VirtualServer> findVirtualServerByLaunchDateBefore(LocalDate date);
    List<VirtualServer> findVirtualServerByOwnersContainingAndLaunchDateBefore(User user, LocalDate date);
}
