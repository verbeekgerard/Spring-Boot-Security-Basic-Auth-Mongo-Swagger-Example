package eu.luminis.dataSamplerPOC.user;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Api(value = "User", description = "Endpoint for Users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/echo/{in}", method = RequestMethod.GET)
    public String echo(@PathVariable(value = "in") final String in) {
        return "You said: " + in;
    }

    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
    public User getUserByEmail(@RequestParam String email) {
        return userRepository.findByUsername(email);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public List<User> getList() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/save}", method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody UserValueObject userValueObject) {
        logger.debug("received user: " + userValueObject);

        List<GrantedAuthority> authorities = userValueObject
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());

        User user = new User(userValueObject.getUsername(), new BCryptPasswordEncoder().encode(userValueObject.getPassword()), authorities);
        User savedUser = userRepository.save(user);
        logger.debug("saved user: " + savedUser);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

//    @RequestMapping(value = "/logout", method =  RequestMethod.GET)
//    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response){
//
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        if (auth != null){
////            new SecurityContextLogoutHandler().logout(request, response, auth);
////        }
////
////        for(Cookie cookie : request.getCookies()) {
////            cookie.setMaxAge(0);
////        }
//
//        SessionUtil.logout(request);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
