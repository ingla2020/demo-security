package demo.security.restcontroller;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.security.model.UserEntity;
import demo.security.services.UserEntityService;

@RestController
@RequestMapping("/api")
public class ApiRes {

	private UserEntityService userEntityService;

	public ApiRes(UserEntityService userEntityService) {
		this.userEntityService = userEntityService;
	}
	
//Optional<UserEntity>
	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public Optional<UserEntity> Prueba(@AuthenticationPrincipal UserEntity user) {
		return userEntityService.findByUsername("leonardo");
//		return "Test";
	}
	
	
	
}
