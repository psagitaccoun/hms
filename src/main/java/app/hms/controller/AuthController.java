package app.hms.controller;

import app.hms.entity.User;
import app.hms.payload.SignUpDto;
import app.hms.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("post")
    public ResponseEntity<String> createSignUp(@RequestBody SignUpDto signUpDto){

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

        userRepository.save(user);



        return new ResponseEntity<>("Sign Up successfully!!",HttpStatus.OK);
    }

   /* private User mapToEntity(SignUpDto signUpDto) {
        User user = modelMapper.map(signUpDto, User.class);
        return user;
    }*/
}
