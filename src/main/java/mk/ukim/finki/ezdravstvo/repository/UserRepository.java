package mk.ukim.finki.ezdravstvo.repository;

import mk.ukim.finki.ezdravstvo.model.User;

public interface UserRepository extends JpaSpecificationRepository<User>{

	User findByUsername(String username);
}
