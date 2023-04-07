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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.gcp.exception.ResourceFoundException;
import com.test.gcp.payload.AdminDTO;
import com.test.gcp.payload.JWTAuthResponse;
import com.test.gcp.payload.LoginDTO;
import com.test.gcp.payload.StudentDTO;
import com.test.gcp.payload.TeacherDTO;
import com.test.gcp.security.JwtTokenProvider;
import com.test.gcp.service.EmployeeService;
import com.test.gcp.util.CustomValidator;

import jakarta.validation.Valid;

@RestController @RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomValidator customValidator;

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> loginAdmin(@Valid @RequestBody final LoginDTO loginDto) {

        logger.log(Level.INFO, () -> "loginAdmin method starts ===>>> " + loginDto);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        logger.log(Level.INFO, () -> "loginAdmin method ends ===>>> " + token);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody final AdminDTO signUpDto) {

        logger.log(Level.INFO, () -> "registerAdmin method starts ===>>> " + signUpDto);
        Optional<AdminDTO> adminDTO = EmployeeService.ADMINS.stream().filter(e -> e.getEmail().equalsIgnoreCase(signUpDto.getEmail())).findFirst();
        if (adminDTO.isPresent()) {
            throw new ResourceFoundException("Email", signUpDto.getEmail());
        }

        String emplyeeId = String.valueOf(EmployeeService.ADMINS.size() + 1);
        signUpDto.setEmployeeId(emplyeeId);
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        EmployeeService.ADMINS.add(signUpDto);

        logger.log(Level.INFO, () -> "registerAdmin method ends ===>>> " + signUpDto);

        return new ResponseEntity<>("Admin registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@Valid @RequestBody final StudentDTO studentDTO) {

        logger.log(Level.INFO, () -> "addStudent method starts ===>>> " + studentDTO);

        logger.log(Level.INFO, () -> "addStudent method ends ===>>> " + studentDTO);

        return new ResponseEntity<>("Student registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@Valid @RequestBody final TeacherDTO teacherDTO) {

        logger.log(Level.INFO, () -> "addTeacher method starts ===>>> " + teacherDTO);

        logger.log(Level.INFO, () -> "addTeacher method ends ===>>> " + teacherDTO);

        return new ResponseEntity<>("Teacher registered successfully", HttpStatus.CREATED);
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(customValidator);
    }
}
