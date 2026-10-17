package com.example.servicioautenticacion.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Almacena temporalmente los usuarios registrados.
    private final Map<String, String> usuarios = new HashMap<>();

    // Registra un usuario nuevo.
    @PostMapping("/registro")
    public String registrar(@RequestBody Usuario usuario) {

        if (usuario.usuario == null || usuario.usuario.isBlank()
                || usuario.contrasena == null || usuario.contrasena.isBlank()) {
            return "El usuario y la contraseña son obligatorios.";
        }

        if (usuarios.containsKey(usuario.usuario)) {
            return "El usuario ya está registrado.";
        }

        usuarios.put(usuario.usuario, usuario.contrasena);

        return "Usuario registrado correctamente.";
    }

    // Comprueba las credenciales para iniciar sesión.
    @PostMapping("/login")
    public String iniciarSesion(@RequestBody Usuario usuario) {

        String contrasenaGuardada = usuarios.get(usuario.usuario);

        if (contrasenaGuardada != null
                && contrasenaGuardada.equals(usuario.contrasena)) {
            return "Autenticación satisfactoria.";
        }

        return "Error en la autenticación.";
    }

    // Recibe los datos enviados en las solicitudes.
    public static class Usuario {
        public String usuario;
        public String contrasena;
    }
}
