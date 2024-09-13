package com.app.service;

import com.app.persistence.entity.UsuarioEntity;
import com.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = userRepository.findUsuarioEntityByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario " +username+" no fue encontrado"));
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        usuario.getRoles()
                .forEach(role -> {
                    authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())));
                });
        usuario.getRoles().stream()
                .flatMap(role-> role.getPermissionEntities().stream())
                .forEach(permissionEntity -> authorityList.add(new SimpleGrantedAuthority(permissionEntity.getName())));

        return new User(usuario.getUsername(),
                usuario.getPassword(),
                usuario.isEnabled(),
                usuario.isAccountNoExpired(),
                usuario.isCredentialNoExpired(),
                usuario.isAccountNoLocked(),
                authorityList);
    }
}
