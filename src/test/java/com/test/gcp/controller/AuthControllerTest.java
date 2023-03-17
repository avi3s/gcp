package com.test.gcp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.test.gcp.payload.AdminDTO;
import com.test.gcp.payload.JWTAuthResponse;
import com.test.gcp.payload.LoginDTO;
import com.test.gcp.payload.Role;
import com.test.gcp.security.JwtTokenProvider;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;


    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider tokenProvider;

    @InjectMocks
    private AuthController authController;
    
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLoginAdmin() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setPassword("password");
        loginDto.setEmail("testuser@test.com");
        
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        when(tokenProvider.generateToken(authentication)).thenReturn("test-token");

        ResponseEntity<JWTAuthResponse> response = authController.loginAdmin(loginDto);

        assertEquals("test-token", response.getBody().getAccessToken());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

	@Test
	void testRegisterAdmin() {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setEmail("testuser@test.com");
        adminDTO.setName("Test User");
        adminDTO.setPassword("password");
        adminDTO.setAddress("testaddress");
        
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        when(passwordEncoder.encode(adminDTO.getPassword())).thenReturn("hashed-password");

        AdminDTO savedUser = new AdminDTO();
        savedUser.setEmployeeId("1");
        savedUser.setName(adminDTO.getName());
        savedUser.setAddress(adminDTO.getAddress());
        savedUser.setEmail(adminDTO.getEmail());
        savedUser.setPassword("hashed-password");
        savedUser.setRoles(Collections.singleton(role));

        ResponseEntity<?> response = authController.registerAdmin(adminDTO);

        assertEquals("Admin registered successfully", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}