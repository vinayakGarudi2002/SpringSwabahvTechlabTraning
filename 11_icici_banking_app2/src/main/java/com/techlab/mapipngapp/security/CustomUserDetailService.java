package com.techlab.mapipngapp.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techlab.mapipngapp.entity.Profile;
import com.techlab.mapipngapp.entity.User;
import com.techlab.mapipngapp.repository.ProfileRepository;
import com.techlab.mapipngapp.repository.UserRepository;



@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Profile profile = profileRepository.findByEmailId(emailId);
		if(profile==null) {
			throw new UsernameNotFoundException("user not found");
		}
		User user=userRepository.findByProfile(profile);
		    
		    Set<GrantedAuthority> authorities = user
		             .getRoles()
		             .stream()
		             .map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

		      return new org.springframework.security.core.userdetails.User(profile.getEmailId(),
		             user.getPassword(),
		             authorities);

	}

}
