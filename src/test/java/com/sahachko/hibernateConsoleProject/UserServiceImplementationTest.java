package com.sahachko.hibernateConsoleProject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sahachko.hibernateConsoleProject.model.Region;
import com.sahachko.hibernateConsoleProject.model.Role;
import com.sahachko.hibernateConsoleProject.model.User;
import com.sahachko.hibernateConsoleProject.repository.UserRepository;
import com.sahachko.hibernateConsoleProject.service.implementations.UserServiceImplementation;



@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserServiceImplementation userService;
	
	@Test
	public void testSaveUser_shouldCallRepositorySaveMethod() {
		Region usa = new Region("USA");
		User user = new User("John", "Keghil", usa, Role.USER);
		userService.saveUser(user);
		verify(userRepository).save(user);
	}
	
	@Test
	public void testSaveUser_shouldReturnTheSameObject() {
		Region usa = new Region("USA");
		User user = new User("John", "Keghil", usa, Role.USER);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.saveUser(user));
	}
	
	@Test
	public void testSaveUser_shouldThrowNPE() {
		when(userRepository.save(null)).thenThrow(NullPointerException.class);
		assertThrows(NullPointerException.class, () -> userService.saveUser(null));
	}
	
	@Test
	public void testUpdateUser_shouldCallRepositoryUpdateMethod() {
		Region usa = new Region("USA");
		User user = new User("John", "Keghil", usa, Role.USER);
		userService.updateUser(user);
		verify(userRepository).update(user);
	}
	
	@Test
	public void testUpdateUser_shouldReturnTheSameObject() {
		Region usa = new Region("USA");
		User user = new User("John", "Keghil", usa, Role.USER);
		when(userRepository.update(user)).thenReturn(user);
		assertEquals(user, userService.updateUser(user));
	}
	
	@Test
	public void testUpdateUser_shouldThrowNPE() {
		when(userRepository.update(null)).thenThrow(NullPointerException.class);
		assertThrows(NullPointerException.class, () -> userService.updateUser(null));
	}
	
	@Test
	public void testGetAllUsers_shouldCallRepositoryGetAllMethod() {
		userService.getAllUsers();
		verify(userRepository).getAll();
	}
	
	@Test
	public void testGetAllUsers_shouldReturnListOfAllUsers() {
		Region winterfall = new Region("Winterfall");
		List<User> allUsers = Arrays.asList(new User("John", "Snow", winterfall, Role.MODERATOR )
				, new User("Eddard", "Stark", winterfall, Role.ADMIN));
		when(userRepository.getAll()).thenReturn(allUsers);
		assertEquals(allUsers, userService.getAllUsers());
	}
	
	@Test
	public void testGetUserById_shouldCallRepositoryGetByIdMethod() {
		userService.getUserById(1l);
		verify(userRepository).getById(1l);
	}
	
	@Test
	public void testGetUserById_shouldReturnUserById() {
		Region winterfall = new Region("Winterfall");
		User johnSnow = new User("John", "Snow", winterfall, Role.MODERATOR);
		User eddardStark = new User("Eddard", "Stark", winterfall, Role.ADMIN);
		Map<Integer, User> users = new HashMap<>();
		users.put(1, johnSnow);
		users.put(2, eddardStark);
		when(userRepository.getById(1l)).thenReturn(users.get(1));
		assertEquals(johnSnow, userService.getUserById(1l));
	}
	
	@Test
	
	public void testGetUserById_shouldThrowNPE() {
		Region winterfall = new Region("Winterfall");
		User johnSnow = new User("John", "Snow", winterfall, Role.MODERATOR);
		User eddardStark = new User("Eddard", "Stark", winterfall, Role.ADMIN);
		Map<Integer, User> users = new HashMap<>();
		users.put(1, johnSnow);
		users.put(2, eddardStark);
		when(userRepository.getById(gt(2l))).thenThrow(NullPointerException.class);
		assertThrows(NullPointerException.class, () -> userService.getUserById(3));
	}
	
	@Test
	public void testDeleteUserById_shouldCallRepositoryDeleteByIdMethod() {
		userService.deleteUserById(3l);
		verify(userRepository).deleteById(3l);
	}
}
