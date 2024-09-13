package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UsuarioEntity;
import com.app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			PermissionEntity createPermission = new PermissionEntity();
			createPermission.setName("CREATE");
			PermissionEntity readPermission = new PermissionEntity();
			readPermission.setName("READ");
			//Crear roles
			RoleEntity adminRoleEntity = new RoleEntity();
			adminRoleEntity.setRoleEnum(RoleEnum.ADMIN);
			adminRoleEntity.setPermissionEntities(Set.of(createPermission, readPermission));
			RoleEntity userRoleEntity = new RoleEntity();
			userRoleEntity.setRoleEnum(RoleEnum.USER);
			userRoleEntity.setPermissionEntities(Set.of(readPermission));
			UsuarioEntity usuario = new UsuarioEntity();
			usuario.setUsername("Daniel");
			usuario.setPassword("123");
			usuario.setEnabled(true);
			usuario.setAccountNoExpired(true);
			usuario.setAccountNoLocked(true);
			usuario.setCredentialNoExpired(true);
			usuario.setRoles(Set.of(adminRoleEntity));
			UsuarioEntity user = new UsuarioEntity();
			user.setUsername("Jaime");
			user.setPassword("12345");
			user.setEnabled(true);
			user.setAccountNoExpired(true);
			user.setAccountNoLocked(true);
			user.setCredentialNoExpired(true);
			user.setRoles(Set.of(userRoleEntity));
			userRepository.saveAll(Set.of(usuario,user));
		};
	}
}
