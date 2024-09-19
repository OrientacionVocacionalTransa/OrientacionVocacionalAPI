package com.vocacional.orientacionvocacional.service.impl;

import com.vocacional.orientacionvocacional.dto.UserDTO;
import com.vocacional.orientacionvocacional.model.entity.User;
import com.vocacional.orientacionvocacional.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(UserDTO usuarioDTO) {

        User user = new User();
        user.setFirstName(usuarioDTO.getFirstName());
        user.setLastName(usuarioDTO.getLastName());
        user.setEmail(usuarioDTO.getEmail());
        user.setPassword(usuarioDTO.getPassword());


        user.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));


        usuarioRepository.save(user);
    }
    public boolean login(String email, String password) {
        User usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return passwordEncoder.matches(password, usuario.getPassword());  // Compara contraseñas
        }
        return false;
    }
    public void updateUser(Long id, UserDTO userDTO) throws Exception {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id: " + id));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        usuarioRepository.save(user);
    }
}