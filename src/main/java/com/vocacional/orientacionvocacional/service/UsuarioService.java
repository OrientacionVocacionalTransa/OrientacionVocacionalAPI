package com.vocacional.orientacionvocacional.service;

import com.vocacional.orientacionvocacional.dto.UserDTO;
import com.vocacional.orientacionvocacional.model.entity.User;
import com.vocacional.orientacionvocacional.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(UserDTO usuarioDTO) {
        // Convertir el DTO a la entidad User
        User user = new User();
        user.setFirstName(usuarioDTO.getFirstName());
        user.setLastName(usuarioDTO.getLastName());
        user.setEmail(usuarioDTO.getEmail());
        user.setPassword(usuarioDTO.getPassword());

        // Encriptar la contraseña
        user.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        // Guardar el usuario en la base de datos
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


    public void deleteUser(Long id) throws Exception {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id: " + id));

        usuarioRepository.delete(user);
    }


    public User getUserById(Long id) throws Exception {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id: " + id));
    }

    public List<User> getAllUsers() {
        return usuarioRepository.findAll();
    }

}
