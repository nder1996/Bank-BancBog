package bdb.Backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Collections;

public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private  String expectedApiKey;
    private  String expectedClientId;

    public ApiKeyAuthFilter(String expectedApiKey, String expectedClientId) {
        this.expectedApiKey = expectedApiKey;
        this.expectedClientId = expectedClientId;
    }

    public ApiKeyAuthFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");

            // Check if Authorization header exists and has correct format
            if (authHeader == null || !authHeader.startsWith("Basic ")) {
                handleAuthenticationFailure(response, "Invalid authentication header");
                return;
            }

            // Safely decode and validate credentials
            String credentials;
            String[] values;
            try {
                String base64Credentials = authHeader.substring("Basic ".length()).trim();
                credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
                values = credentials.split(":", 2); // Limit split to 2 parts
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                handleAuthenticationFailure(response, "Invalid credentials format");
                return;
            }

            // Validate credential parts
            if (values.length != 2) {
                handleAuthenticationFailure(response, "Missing credentials");
                return;
            }

            // Use constant-time comparison to prevent timing attacks
            boolean apiKeyValid = MessageDigest.isEqual(
                    values[0].getBytes(StandardCharsets.UTF_8),
                    expectedApiKey.getBytes(StandardCharsets.UTF_8)
            );

            boolean clientIdValid = MessageDigest.isEqual(
                    values[1].getBytes(StandardCharsets.UTF_8),
                    expectedClientId.getBytes(StandardCharsets.UTF_8)
            );

            if (!apiKeyValid || !clientIdValid) {
                handleAuthenticationFailure(response, "Invalid credentials");
                return;
            }

            // Set authentication token
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList())
            );

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // Log the error (consider using a proper logging framework)
            logger.error("Authentication error", e);
            handleAuthenticationFailure(response, "Authentication failed");
        } finally {
            // Clear the security context to prevent memory leaks
            if (response.getStatus() >= HttpServletResponse.SC_BAD_REQUEST) {
                SecurityContextHolder.clearContext();
            }
        }
    }

    private void handleAuthenticationFailure(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
        response.addHeader("WWW-Authenticate", "Basic realm=\"Access to API\"");
    }
}
