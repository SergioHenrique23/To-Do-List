package br.com.sergiohenrique.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.sergiohenrique.todolist.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter  {


    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
                var sevletPath = request.getServletPath();

                if(sevletPath.equals("/tasks/")){
                    var authorization = request.getHeader("Authorization");
                    var authEncoded = authorization.substring("Basic".length()).trim();
                    
                    byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                    var authString = new String(authDecode);

                    String[] credentials = authString.split(":");
                    var username = credentials[0];
                    var password = credentials[1];

                    var user = this.userRepository.findByUsername(username);
                    if(ObjectUtils.isEmpty(user)){
                        response.sendError(401);
                    } else {
                    var passwordVerify =  BCrypt.verifyer().verify(password.toCharArray(), user.get(0).getPassword());
                    if(passwordVerify.verified){
                        request.setAttribute("idUser", user.get(0).getId());
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401);
                    }
                    }
                } else{
                     filterChain.doFilter(request, response);
                }
                

    }
    
}
