package mk.ukim.finki.ezdravstvo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.ezdravstvo.model.User;
import mk.ukim.finki.ezdravstvo.repository.UserRepository;
import mk.ukim.finki.ezdravstvo.service.UserService;

@Service
public class UserServiceImpl extends BaseEntityCrudServiceImpl<User, UserRepository> implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	protected UserRepository getRepository() {
		return userRepository;
	}

}
