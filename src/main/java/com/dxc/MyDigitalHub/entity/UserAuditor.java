package com.dxc.MyDigitalHub.entity;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuditor implements AuditorAware<String> {
	
	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String principalName = authentication.getName();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
//        	principalName = "admin";
        	System.out.println("Inside not authenticated principal");
//        	return Optional.ofNullable(principalName).filter(s -> !s.isEmpty());
            return Optional.empty();
        }
        System.out.println("Outside authenticated principal");
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        System.out.println("userPrincipal.getName(): " + userPrincipal.getName());
        return Optional.ofNullable(userPrincipal.getName());
        
//        return Optional.ofNullable(principalName).filter(s -> !s.isEmpty());
	}
	
}