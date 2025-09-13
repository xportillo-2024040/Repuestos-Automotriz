package com.xavierportillo.SportFinal.service;


import com.xavierportillo.SportFinal.model.User;
import com.xavierportillo.SportFinal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User saverUser(User user) {
        if (userRepository.existsByFirstName(user.getFirstName())) {
            throw new RuntimeException("El nombre ya está en uso");
        }
        if (userRepository.existsByLastName(user.getLastName())) {
            throw new RuntimeException("El apellido ya está en uso");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        if (userRepository.existsByFirstNameAndIdNot(user.getFirstName(), id)) {
            throw new RuntimeException("El nombre ya está en uso por otro usuario");
        }
        if (userRepository.existsByLastNameAndIdNot(user.getLastName(), id)) {
            throw new RuntimeException("El apellido ya está en uso por otro usuario");
        }
        if (userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
            throw new RuntimeException("El correo ya está en uso por otro usuario");
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            System.out.println("El usuario con el ID " + id + " fue eliminado correctamente.");
        } else {
            throw new RuntimeException("El id no existe");
        }
    }



    }
