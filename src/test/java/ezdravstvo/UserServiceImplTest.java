package ezdravstvo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import mk.ukim.finki.ezdravstvo.model.Diagnose;
import mk.ukim.finki.ezdravstvo.model.Medication;
import mk.ukim.finki.ezdravstvo.model.Patient;
import mk.ukim.finki.ezdravstvo.model.Prescription;
import mk.ukim.finki.ezdravstvo.model.User;
import mk.ukim.finki.ezdravstvo.model.User.Role;
import mk.ukim.finki.ezdravstvo.service.impl.PatientServiceImpl;
import mk.ukim.finki.ezdravstvo.service.impl.PrescriptionServiceImpl;
import mk.ukim.finki.ezdravstvo.service.impl.UserServiceImpl;

public class UserServiceImplTest {

	@Test
	public void findByUsername() {
		User  user = new User();
		user.setUsername("petkop");
		user.setId((long)1);
		user.setRole(Role.ROLE_ADMIN);
		
		UserServiceImpl service = mock(UserServiceImpl.class);
		Mockito.when(service.findByUsername("petkop")).thenReturn(user);
		assertEquals(service.findByUsername("petkop"),user);
	}
	@Test
	public void findByEmptyUsername() {
		User  user = new User();
		user.setUsername("");
		user.setId((long)1);
		user.setRole(Role.ROLE_ADMIN);
		
		UserServiceImpl service = mock(UserServiceImpl.class);
		Mockito.when(service.findByUsername("")).thenReturn(user);
		assertEquals(service.findByUsername(""),user);
	}
}
