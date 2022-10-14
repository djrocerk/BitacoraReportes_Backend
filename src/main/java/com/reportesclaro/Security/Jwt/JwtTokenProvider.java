package com.reportesclaro.Security.Jwt;

import com.reportesclaro.Security.Props.BlogAppException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("3600000")
    private int jwtExpirationInMs;

    //Este token lo que hace es establecer el usuario
    public String generarToken(Authentication authentication) {
        String username = authentication.getName(); //Obtenemos el username del usuario de autenticación
        Date fechaActual = new Date(); //obtenemos la fecha actual
        Date fechaExpiracion = new Date(fechaActual.getTime()+jwtExpirationInMs); //Aqui obtenemos la fecha de expiracion haciendo la sumatoria de fechaActual + timpoExpiracion
        String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion) //(Establece el usuario) Aquí asigamos todos estos datos al token y genera el token con su algorimo y llave secreta
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        return token;
    }

    //Este token obtiene el usuario
    public String obtenerUsernameDelJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean ValidarToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token); //Estamos validando el token con la llave secreta
            return true;
        } catch (SignatureException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Firma JWT no valida");
        }
        catch (MalformedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no valida");
        }
        catch (ExpiredJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
        }
        catch (UnsupportedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
        }
        catch (IllegalArgumentException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT esta vacia");
        }
    }
}
