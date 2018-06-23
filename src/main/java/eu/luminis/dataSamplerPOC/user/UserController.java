package eu.luminis.dataSamplerPOC.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/echo/{in}", method = RequestMethod.GET)
    public String echo(@PathVariable(value = "in") final String in) {
        return "You said: " + in;
    }

    @RequestMapping("/getUserByEmail")
    public User getUserByEmail(@RequestParam String email){
        return userRepository.findByUsername(email);
    }

    @RequestMapping("/list")
    public List<User> getList(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/save}", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }




}
