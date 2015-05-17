package mk.ukim.finki.ezdravstvo.service;

import mk.ukim.finki.ezdravstvo.model.User;

public interface UserService extends BaseEntityCrudService<User>{

	User findByUsername(String username);
}
