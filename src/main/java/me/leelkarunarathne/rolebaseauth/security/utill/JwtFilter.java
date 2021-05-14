package me.leelkarunarathne.rolebaseauth.security.utill;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.leelkarunarathne.rolebaseauth.security.model.DecodedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final String HEADER_STRING = "Authorization";
    private final String TOKEN_PREFIX = "Bearer";

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ObjectMapper mapper;

    public JwtFilter() {

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.debug("authentication filtering");

        String authorizationHeader = request.getHeader(HEADER_STRING);

        String userName = null;

        String authToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {

            log.debug("extracting the jwt token");

            authToken = authorizationHeader.replace(TOKEN_PREFIX, "");

            try {

                userName = jwtUtil.extractUsername(authToken);
            } catch (IllegalArgumentException ex) {

            } catch (ExpiredJwtException ex) {
                log.warn("the token is expired and not valid anymore", ex);
            } catch (SignatureException ex) {
                log.error("Authentication Failed. Username or Password not valid.");
            }

        } else {
            log.warn("couldn't find bearer string, will ignore the header");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            log.info("start to set up the security context for user [{}]", userName);



            Claims claims = jwtUtil.extractAllClaims(authToken);

            DecodedToken decodedToken = mapper.
                    convertValue(claims, DecodedToken.class);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(null, null,
                            Arrays.asList(new SimpleGrantedAuthority(claims.get("scope").toString())));

            usernamePasswordAuthenticationToken.
                    setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            request.setAttribute("decodedToken", decodedToken);



            request.setAttribute("user", decodedToken.getUserName());
        }

        filterChain.doFilter(request, response);
    }
}
