package com.test.gcp.security;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.gcp.payload.EmployeeDTO;
import com.test.gcp.payload.Role;
import com.test.gcp.service.EmployeeService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
    	EmployeeDTO presentEmployeeDTO = null;
    	Optional<EmployeeDTO> employeeDTO = EmployeeService.ADMINS.stream().filter(e -> e.getEmail().equalsIgnoreCase(email)).findFirst();
		if(employeeDTO.isPresent()) {
			presentEmployeeDTO = employeeDTO.get();
		} else {
			throw new UsernameNotFoundException("User not found with username or email:" + email);
		}
       return new org.springframework.security.core.userdetails.User(presentEmployeeDTO.getEmail(), presentEmployeeDTO.getPassword(), mapRolesToAuthorities(presentEmployeeDTO.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }
}