package com.pcs.tradingapp.config;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestResponseLoggingFilter extends HttpFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LogManager.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        if (req.getRequestURI().endsWith(".css")) {
        	chain.doFilter(request, response);
        	return;
        }

        logger.info("[HTTP REQUEST] {} {}, from {}", req.getMethod(), req.getRequestURI(), req.getRemoteAddr());
        
        chain.doFilter(request, response);

        logger.info(
                "[HTTP RESPONSE] {}", res.getStatus());
    }

}
