package com.poc1.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	private  JwtService jwtService;
	
	@Autowired private UserInfoDetailsService detailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader=request.getHeader("Authorization"); 
		
		String username=null;
		String jwtToken=null;
		//check null and format 
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			//7 is a length of bearer
			jwtToken=authHeader.substring(7);
			username=jwtService.extractUsername(jwtToken);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=detailService.loadUserByUsername(username);
			if(jwtService.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);		
	}
}
