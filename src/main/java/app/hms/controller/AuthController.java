package app.hms.controller;

import app.hms.entity.Role;
import app.hms.entity.User;
import app.hms.payload.SignInDto;
import app.hms.payload.SignUpDto;
import app.hms.repository.RoleRepository;
import app.hms.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto){

        if (userRepository.existsByUsername(signUpDto.getUsername())){
             return new ResponseEntity<>("Username is already present!!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already present!!", HttpStatus.BAD_REQUEST);
        }
        User user=new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Role role = roleAdmin.get();

        user.setRoles(Collections.singleton(role));

        userRepository.save(user);
        return new ResponseEntity<>("Sign Up successfully!!",HttpStatus.OK);
    }


    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody SignInDto signInDto){
        User user = userRepository.findByUsernameOrEmail(signInDto.getUsernameOrEmail(), signInDto.getUsernameOrEmail()).orElseThrow(
                () -> new UsernameNotFoundException("Username/Email is not available with this :" + signInDto.getUsernameOrEmail())
        );

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getUsernameOrEmail(), signInDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

}

    /* private User mapToEntity(SignUpDto signUpDto) {
        User user = modelMapper.map(signUpDto, User.class);
        return user;
    }*/

