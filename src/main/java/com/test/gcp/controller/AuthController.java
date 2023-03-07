package com.test.gcp.controller;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.gcp.exception.ResourceFoundException;
import com.test.gcp.payload.AdminDTO;
import com.test.gcp.payload.JWTAuthResponse;
import com.test.gcp.payload.LoginDTO;
import com.test.gcp.security.JwtTokenProvider;
import com.test.gcp.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger LOGGER = LogManager.getLogger(AuthController.class);
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> loginAdmin(@Valid @RequestBody LoginDTO loginDto){
    	
    	LOGGER.log(Level.INFO, () -> "loginAdmin method starts ===>>> "+ loginDto);
    	
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        LOGGER.log(Level.INFO, () -> "loginAdmin method ends ===>>> "+ token);
        
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody AdminDTO signUpDto){

    	LOGGER.log(Level.INFO, () -> "registerAdmin method starts ===>>> "+ signUpDto);
    	Optional<AdminDTO> adminDTO = EmployeeService.ADMINS.stream().filter(e -> e.getEmail().equalsIgnoreCase(signUpDto.getEmail())).findFirst();
		if(adminDTO.isPresent()) {
			throw new ResourceFoundException("Email", signUpDto.getEmail());
		}
		
		String emplyeeId = String.valueOf(EmployeeService.ADMINS.size() + 1);
		signUpDto.setEmployeeId(emplyeeId);
		signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		EmployeeService.ADMINS.add(signUpDto);
		
		LOGGER.log(Level.INFO, () -> "registerAdmin method ends ===>>> "+ signUpDto);

        return new ResponseEntity<>("Admin registered successfully", HttpStatus.CREATED);
    }
}