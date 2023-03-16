package shop.mtcoding.hiberapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.hiberapp.model.User;
import shop.mtcoding.hiberapp.model.UserRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<?> addUser(User user){
        User userPS = userRepository.save(user);
        return new ResponseEntity<>(userPS, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, User user){
        if(ObjectUtils.isEmpty(id)){
            // id가 들어왔는지 
        }
        User userPS = userRepository.findById(id);
        if(ObjectUtils.isEmpty(userPS)){
            return new ResponseEntity<>("해당 유저가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        userPS.update(user.getPassword(), user.getEmail());
        User updateUserPS = userRepository.update(userPS);
        return new ResponseEntity<>(updateUserPS, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if(ObjectUtils.isEmpty(id)){
            // id가 들어왔는지 
        }
        User userPS = userRepository.findById(id);
        if(ObjectUtils.isEmpty(userPS)){
            return new ResponseEntity<>("해당 유저가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        userRepository.delete(userPS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> findUsers(@RequestParam(defaultValue = "0") int page){
        List<User> userList = userRepository.findAll(page, 2);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserOne(@PathVariable Long id){
        if(ObjectUtils.isEmpty(id)){
            // id가 들어왔는지 
        }
        User userPS = userRepository.findById(id);
        if(ObjectUtils.isEmpty(userPS)){
            return new ResponseEntity<>("해당 유저가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userPS, HttpStatus.OK);
    }
}
