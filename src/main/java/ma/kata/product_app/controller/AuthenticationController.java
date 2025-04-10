package ma.kata.product_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import ma.kata.product_app.dto.api.ApiResponse;
import ma.kata.product_app.dto.auth.LoginResponseDto;
import ma.kata.product_app.dto.auth.LoginUserDto;
import ma.kata.product_app.dto.auth.RegisterUserDto;
import ma.kata.product_app.dto.user.UserResponseDto;
import ma.kata.product_app.mapper.ApiResponseMapper;
import ma.kata.product_app.model.enums.MessageCode;
import ma.kata.product_app.security.jwt.service.JwtService;
import ma.kata.product_app.service.Impl.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    @Operation(summary = "Register a new user", description = "This endpoint allows a new user to register.")
    public ApiResponse<UserResponseDto> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return ApiResponseMapper.FromMessageCodetoResponse(
                MessageCode.USER_CREATED_SUCCESS,
                authenticationService.signup(registerUserDto)
        );
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate a user", description = "This endpoint allows a user to login and receive a JWT token.")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserDetails authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
