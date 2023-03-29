package app.hms;

import app.hms.entity.Role;
import app.hms.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HmsApplication implements CommandLineRunner {


	@Autowired
	RoleRepository roleRepository;
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(HmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role role1 = new Role();
		role1.setName("ROLE_ADMIN");
		roleRepository.save(role1);

		Role role2=new Role();
		role2.setName("ROLE_USER");
		roleRepository.save(role2);

	}
}
