package com.totbun.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		
		Map<String, Object> body = new HashMap<>();
		body.put("Status", HttpServletResponse.SC_FORBIDDEN);
		body.put("Error", "Forbidden");
		body.put("Message", accessDeniedException.getMessage());
		body.put("Path", request.getServletPath());
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.writeValue(response.getOutputStream(), body);
		
		
	}

}
