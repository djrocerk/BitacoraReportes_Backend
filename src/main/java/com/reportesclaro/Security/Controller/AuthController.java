package com.reportesclaro.Security.Controller;

import com.reportesclaro.Security.Dto.LoginUserDto;
import com.reportesclaro.Security.Dto.UserRegistrationDto;
import com.reportesclaro.Security.Entity.EmailEntity;
import com.reportesclaro.Security.Entity.PersonEntity;
import com.reportesclaro.Security.Entity.RoleEntity;
import com.reportesclaro.Security.Entity.UserEntity;
import com.reportesclaro.Security.Exceptions.ErrorMessage;
import com.reportesclaro.Security.Jwt.JwtAuthReponseDto;
import com.reportesclaro.Security.Jwt.JwtTokenProvider;
import com.reportesclaro.Security.Repository.RolRepository;
import com.reportesclaro.Security.Repository.UserRepository;
import com.reportesclaro.Security.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private RolRepository rolRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Autowired
   private JwtTokenProvider jwtTokenProvider;

   @Autowired
   private PersonService personService;

   @PostMapping(value = {"/iniciarSesion"})
   public ResponseEntity<JwtAuthReponseDto> authenticateUser(@RequestBody LoginUserDto loginUserDto){
      UserEntity userEntity = userRepository.findByUsernameOrEmail(loginUserDto.getUsernameOrEmail(), loginUserDto.getUsernameOrEmail()).
              orElseThrow(() -> new ErrorMessage("Correo o usuario de ingreso no existen en el sistema"));

      boolean password = passwordEncoder.matches(loginUserDto.getPassword(), userEntity.getPassword());
      if(password == false){
         throw new ErrorMessage("Contraseña incorrecta");
      }
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getUsernameOrEmail(), loginUserDto.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      //Obtenemos el token de jwtTokenProvider
      String token = jwtTokenProvider.generarToken(authentication);
      userEntity.setPassword("**********");
      return ResponseEntity.ok(new JwtAuthReponseDto(token,userEntity));

   }

   @PostMapping(value = {"/registrar"})
   public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto userRegistrationDto){
      if(userRepository.existsByUsername(userRegistrationDto.getUsername())){
         return new ResponseEntity<>("Esta dirección de correo ya existe", HttpStatus.BAD_REQUEST);
      }
      if(userRepository.existsByEmail(userRegistrationDto.getEmail())){
         return new ResponseEntity<>("Esta dirección de correo ya existe", HttpStatus.BAD_REQUEST);
      }

//      System.out.println(userRegistrationDto.getPersonEntity().getPer_Name());
      PersonEntity personEntity = new PersonEntity();
      //personEntity.setPer_DocumentType(userRegistrationDto.getPersonEntity().getPer_DocumentType());
      //personEntity.setPer_DocumentNumber(userRegistrationDto.getPersonEntity().getPer_DocumentNumber());
      personEntity.setPer_Name(userRegistrationDto.getPersonEntity().getPer_Name());
      personEntity.setPer_LastName(userRegistrationDto.getPersonEntity().getPer_LastName());
      personEntity.setPer_City(userRegistrationDto.getPersonEntity().getPer_City());
      personEntity.setPer_Modificationdate(new Date());
      personEntity.setPer_State("A");
      PersonEntity personEntity1 = personService.save(personEntity);

      UserEntity userEntity = new UserEntity();
      userEntity.setOnePerson(personEntity1);
      userEntity.setUsername(userRegistrationDto.getUsername());
      userEntity.setEmail(userRegistrationDto.getEmail());
      userEntity.generatePassword();

      String contrasena = userEntity.getPassword();

      userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
      System.out.println("Wini" + userRegistrationDto.getOneRol().getId());
      Optional<RoleEntity> roles = rolRepository.findById(userRegistrationDto.getOneRol().getId());
      userEntity.setRoles(Collections.singleton(roles.get()));
      userRepository.save(userEntity);
      sendNewPasswordEmail(userEntity,contrasena );
      return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.OK);

   }

   @PostMapping(value = {"/updatePassword"})
   public ResponseEntity<JwtAuthReponseDto> updatePassword(@RequestBody LoginUserDto loginUserDto){
      UserEntity userEntity = userRepository.findByUsernameOrEmail(loginUserDto.getUsernameOrEmail(), loginUserDto.getUsernameOrEmail()).
              orElseThrow(() -> new ErrorMessage("Correo o usuario de ingreso no existen en el sistema"));

      boolean password = passwordEncoder.matches(loginUserDto.getPassword(), userEntity.getPassword());
      if(password == false){
         throw new ErrorMessage("Contraseña incorrecta");
      }
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getUsernameOrEmail(), loginUserDto.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      //Obtenemos el token de jwtTokenProvider
      String token = jwtTokenProvider.generarToken(authentication);
      userEntity.setPassword("**********");
      return ResponseEntity.ok(new JwtAuthReponseDto(token,userEntity));

   }

  @PutMapping(value = {"/recuperar"})
  public ResponseEntity<JwtAuthReponseDto> recuperar(@RequestBody LoginUserDto loginUserDto){
     UserEntity userEntity = userRepository.findByUsernameOrEmail(loginUserDto.getUsernameOrEmail(), loginUserDto.getUsernameOrEmail()).
             orElseThrow(() -> new ErrorMessage("Correo o usuario de ingreso no existen en el sistema"));
     return null;
  }


   private void sendNewPasswordEmail(UserEntity userEntity2, String password){

      String asunto = " Credenciales De Acceso - Usuario De Acceso A Reportes & Incidentes Claro ";

      String mensaje =
              "<br><a>Las credenciales de acceso al software Reportes & Incidentes Claro </a>"
                      + "<a>se realizaron con exito.</a>"
                      + "<br><b>       </b>"
                      + "<br><h2>Los datos de acceso del usuario(a) " +userEntity2.getOnePerson().getPer_Name()+" "+userEntity2.getOnePerson().getPer_LastName()+" son: </h2>"
                      + "<br><b>       </b>"
                      + "<br><b>Usuario: </b>" + userEntity2.getUsername()
                      + "<br><b>Correo: </b>" + userEntity2.getEmail()
                      + "<br><b>Contraseña: </b>" + password
                      + "<br><a>Una vez ingrese el usuario con la  contraseña, se sugiere realizar el cambio por </a>"
                      + "<a>una contraseña de fácil acceso .   <a>"
                      +"<br><a> Este es el link para su facil acceso http://100.562.14.23/ReporteClaro </a>"
                      + "<br>"
                      + "<hr>"
                      + "<br><b>       </b>"
                      + "<br><b>REPORTES-INCIDENTES CLARO 2022 </b>"
                      +"<br><br><img src = 'https://i.ibb.co/zSZ4VY6/Claro-svg.png' with='300px' height='220px'/>";

      EmailEntity.sendMessage(userEntity2.getEmail(), asunto, mensaje  );
   }
   }




