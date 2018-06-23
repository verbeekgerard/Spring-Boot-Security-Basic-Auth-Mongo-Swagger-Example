package eu.luminis.dataSamplerPOC;

import eu.luminis.dataSamplerPOC.user.Role;
import eu.luminis.dataSamplerPOC.user.User;
import eu.luminis.dataSamplerPOC.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DataSamplerPocApplication {
//
//	@Autowired
//	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DataSamplerPocApplication.class, args);

	}

	@Bean
	public CommandLineRunner demoData(UserRepository repo) {
		return args -> {
			User user = repo.findByUsername("gerard.verbeek@luminis.eu");
			if(user == null) {
				repo.save(new User("gerard.verbeek@luminis.eu", new BCryptPasswordEncoder().encode("password"), Arrays.asList(new SimpleGrantedAuthority(Role.USER.toString()))));
			}
		};
	}

}
