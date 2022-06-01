package com.nutrihouse.app.rest;

import com.nutrihouse.app.security.JWTUtil;
import com.nutrihouse.app.security.UserSecurity;
import com.nutrihouse.app.service.UserDetailServiceImpl;
import com.nutrihouse.app.service.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationCrudController {

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping( "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSecurity user = UsuarioService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("acess-control-expose-headers", "Authorization");
        return ResponseEntity
                .noContent()
                .build();
    }


}
