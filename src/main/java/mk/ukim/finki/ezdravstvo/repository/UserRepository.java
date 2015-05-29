package mk.ukim.finki.ezdravstvo.repository;

import org.springframework.data.jpa.repository.Query;

import mk.ukim.finki.ezdravstvo.model.User;

public interface UserRepository extends JpaSpecificationRepository<User>{

	User findByUsername(String username);
}
