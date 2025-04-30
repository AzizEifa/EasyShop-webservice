package com.shop.EasyShop.Auth;

import com.shop.EasyShop.Dtos.RegisterRequest;
import com.shop.EasyShop.Entity.User;
import com.shop.EasyShop.Enums.Role;
import com.shop.EasyShop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().equals("customer") ? Role.CUSTOMER : Role.SELLER)
                .build();

        userRepository.save(user);
    }

}

