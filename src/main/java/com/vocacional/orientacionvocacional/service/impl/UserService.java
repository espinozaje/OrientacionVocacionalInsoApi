package com.vocacional.orientacionvocacional.service.impl;


import com.vocacional.orientacionvocacional.dto.UserDTO;
import com.vocacional.orientacionvocacional.model.entity.Asesor;
import com.vocacional.orientacionvocacional.model.entity.Estudiante;
import com.vocacional.orientacionvocacional.model.entity.User;
import com.vocacional.orientacionvocacional.model.enums.ERole;
import com.vocacional.orientacionvocacional.repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;


    public void registrarUsuario(UserDTO usuarioDTO) {

        User user;

        // Asignar el tipo de usuario basado en el rol
        if (usuarioDTO.getRole() == ERole.ESTUDIANTE) {
            user = new Estudiante();
        } else if (usuarioDTO.getRole() == ERole.ASESOR) {
            user = new Asesor();
        } else {
            throw new IllegalArgumentException("Rol no válido");
        }

        user.setFirstName(usuarioDTO.getFirstName());
        user.setLastName(usuarioDTO.getLastName());
        user.setEmail(usuarioDTO.getEmail());
        user.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        user.setRole(usuarioDTO.getRole());

        usuarioRepository.save(user);
    }




    public User login(String email, String password) {
        User usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return usuario;  // Devuelve el usuario si las credenciales son correctas
        }
        return null;  // Devuelve null si no se encuentra el usuario o las credenciales son incorrectas
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


    public void updateAndEncryptPassword(Long id, String newPassword) throws Exception {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id: " + id));


        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);


        usuarioRepository.save(user);
    }


    public void generateResetPasswordToken(String email) throws Exception {
        User user = usuarioRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("No se encontró un usuario con ese correo.");
        }

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        usuarioRepository.save(user);

        // Enviar correo electrónico con el token
        sendResetPasswordEmail(user.getEmail(), token);
    }

    public User findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public User updateUser(User user) {
        return usuarioRepository.save(user); // Usa tu repositorio para guardar los cambios
    }

    public void resetPassword(String token, String newPassword) throws Exception {
        User user = usuarioRepository.findByResetPasswordToken(token);
        if (user == null) {
            throw new Exception("Token inválido.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        usuarioRepository.save(user);
    }

    private void sendResetPasswordEmail(String email, String token) throws Exception {
        String resetLink = "http://localhost:4200/reset-password?token=" + token;
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject("Restablecer tu contraseña en OrientacionVocacional");
        helper.setText("<p>Hola,</p>" +
                "<p>Esperamos que estés teniendo un buen día. Hemos recibido una solicitud para restablecer la contraseña de tu cuenta en Orientacion Vocacional, que está diseñada para ayudarte en tu camino de orientación vocacional.</p>" +
                "<p>Si deseas restablecer tu contraseña, haz clic en el enlace de abajo:</p>" +
                "<a href=\"" + resetLink + "\">Restablecer contraseña</a>" +
                "<p>Si no solicitaste este cambio, puedes ignorar este correo.</p>" +
                "<p>¡Gracias por formar parte de nuestra comunidad!</p>" +
                "<p>Atentamente,<br>El equipo de desarrollo de Orientacion Vocacional</p>", true);

        mailSender.send(message);
    }

}

