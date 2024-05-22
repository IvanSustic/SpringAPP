package hr.tvz.sustic.rentacarapp.controller;


import hr.tvz.sustic.rentacarapp.dto.AuthRequestDTO;
import hr.tvz.sustic.rentacarapp.dto.JwtResponseDTO;
import hr.tvz.sustic.rentacarapp.dto.RefreshTokenRequestDTO;
import hr.tvz.sustic.rentacarapp.model.RefreshToken;
import hr.tvz.sustic.rentacarapp.service.JwtService;
import hr.tvz.sustic.rentacarapp.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    private RefreshTokenService refreshTokenService;

    @PostMapping("/api/v1/login")
    public JwtResponseDTO authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                    .token(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/api/v1/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

    @PostMapping("/api/v1/logout")
    public void logout(@RequestBody String token) {
        System.out.println("Logout...");
        refreshTokenService.logout(refreshTokenService.findByToken(token).get());
    }

    @GetMapping("/api/v1/user/{username}")
    public String getUserRole(@PathVariable String username) {
        System.out.println(username);
       String a=  refreshTokenService.getAuthoristation(username);
        System.out.println("\n RETURN: "+a);
     return a;
    }
}
