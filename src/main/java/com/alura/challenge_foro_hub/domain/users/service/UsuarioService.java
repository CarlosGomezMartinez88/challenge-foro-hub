package com.alura.challenge_foro_hub.domain.users.service;

import com.alura.challenge_foro_hub.domain.users.RegistroUsuarioDTO;
import com.alura.challenge_foro_hub.domain.users.RegistroUsuarioRespuestaDTO;
import com.alura.challenge_foro_hub.domain.users.Role;
import com.alura.challenge_foro_hub.domain.users.Usuario;
import com.alura.challenge_foro_hub.domain.users.repository.IRoleRepository;
import com.alura.challenge_foro_hub.domain.users.repository.IUsuarioRepository;
import com.alura.challenge_foro_hub.infra.errors.DuplicateResourceException;
import com.alura.challenge_foro_hub.infra.errors.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public RegistroUsuarioRespuestaDTO registrarUsuario(RegistroUsuarioDTO registroUsuarioDTO) {
        String encodedPassword = passwordEncoder.encode(registroUsuarioDTO.contrasena());
        Usuario usuario = new Usuario(registroUsuarioDTO.nombre(), registroUsuarioDTO.correoElectronico(), encodedPassword);

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Rol USER no encontrado"));

        usuario.getRoles().add(userRole);

        try {
            Usuario usuarioGuardado = usuarioRepository.save(usuario);
            return new RegistroUsuarioRespuestaDTO(usuarioGuardado.getId(), usuarioGuardado.getNombre(), usuarioGuardado.getCorreoElectronico(), usuarioGuardado.getContrasena());
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("El usuario con el correo " + registroUsuarioDTO.correoElectronico() + " ya está registrado.");
        }
    }

    public Usuario findUsuarioByCorreoElectronico(String correoElectronico) {
        return usuarioRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el correo: " + correoElectronico));
    }
}
