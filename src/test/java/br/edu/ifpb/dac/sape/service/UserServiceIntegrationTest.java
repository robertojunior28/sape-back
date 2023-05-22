package br.edu.ifpb.dac.sape.service;


import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import br.edu.ifpb.dac.sape.business.service.SportService;
import br.edu.ifpb.dac.sape.business.service.UserService;
import br.edu.ifpb.dac.sape.model.entity.Sport;
import br.edu.ifpb.dac.sape.model.entity.User;
import br.edu.ifpb.dac.sape.model.repository.SportRepository;
import br.edu.ifpb.dac.sape.model.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceIntegrationTest {
	
	private User exUser;
	private Sport exSport;
	private Sport exSport2;
	
	@LocalServerPort
	private int port;
	@Autowired
	private UserService userService;
	@Autowired
	private SportService sportService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SportRepository sporRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		exUser = new User();
		
		exUser.setName("Ytallo");
		exUser.setRegistration(111112L);
		userService.save(exUser);
		
		exSport = new Sport();
		
		exSport.setName("Ping Pong");
		sportService.save(exSport);
		
		
		exSport2 = new Sport();
		
		exSport2.setName("Vôlei");
		sportService.save(exSport2);
	}
	
	@AfterEach
    public void tearDown() throws Exception {
		userRepository.deleteAll();
    	sporRepository.deleteAll();
     }
	
//	@BeforeEach
//	public void beforeEach() throws Exception{
//		
//	}
//	
//	@Test
//    public void testRemoveSportsFavorite_SportNoIntheList() throws Exception {
//		
//		exUser.setSportsFavorite(new ArrayList<>());
//		exUser.getSportsFavorite().add(exSport);
//		int initialSize = exUser.getFavorateSports().size();
//		
//		userService.removeSportsFavorite(exUser.getId(), 2);
//		int finalSize = exUser.getSportsFavorite().size();
//		
//		assertNotEquals(initialSize, finalSize);
//	}
	
	@Test
	@Transactional
	public void testRemoveSportsFavorite_SportNoIntheList() throws Exception {
	  
	    exUser.setSportsFavorite(new ArrayList<>());
	    exUser.getSportsFavorite().add(exSport);
	    
	    int initialSize = exUser.getSportsFavorite().size();    
	  
	    userService.removeSportsFavorite(exUser.getId(), exSport.getId());
	 
	    int finalSize = exUser.getSportsFavorite().size();
	    System.out.println(initialSize);
	    System.out.println(finalSize);
	    assertNotEquals(initialSize, finalSize);
	}

	
//	@Test
//	public void testRemoveSportsFavorite_UserNotFound() {
//		exSport.setId(1);
//		exUser.setId(1);
//		
//		when(repository.findById(exUser.getId())).thenReturn(Optional.empty());
//		
//		assertThrows(IllegalArgumentException.class, 
//                () -> service.removeSportsFavorite(exUser.getId(), exSport.getId()));
//	}
}