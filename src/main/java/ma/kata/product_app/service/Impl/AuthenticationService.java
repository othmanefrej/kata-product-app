package ma.kata.product_app.service.Impl;

import ma.kata.product_app.dto.auth.LoginUserDto;
import ma.kata.product_app.dto.auth.RegisterUserDto;
import ma.kata.product_app.dto.user.UserResponseDto;
import ma.kata.product_app.mapper.UserMapper;
import ma.kata.product_app.model.User;
import ma.kata.product_app.model.enums.MessageCode;
import ma.kata.product_app.repository.UserRepository;
import ma.kata.product_app.security.jwt.service.CustomUserDetailsService;
import ma.kata.product_app.service.Impl.observer.UserCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static ma.kata.product_app.util.Util.UtilCustomErrorException;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            CustomUserDetailsService userDetailsService,
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public UserResponseDto signup(RegisterUserDto input) {
        if(userRepository.existsByEmail(input.getEmail())){
            throw UtilCustomErrorException(MessageCode.EMAIL_ALREADY_EXISTS);
        }

        User user = new User();
        user.setFirstname(input.getFirstname());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        User registredUser = userRepository.save(user);
        applicationEventPublisher.publishEvent(new UserCreatedEvent(this, registredUser));
        return UserMapper.toResponse(registredUser);
    }

    public UserDetails authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userDetailsService.loadUserByUsername(input.getEmail());
    }
}
