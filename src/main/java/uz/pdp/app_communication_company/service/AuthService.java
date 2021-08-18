package uz.pdp.app_communication_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.app_communication_company.entity.Employer;
import uz.pdp.app_communication_company.payload.ApiResponse;
import uz.pdp.app_communication_company.payload.LoginDto;
import uz.pdp.app_communication_company.security.JwtProvider;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;




    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByUsernameFromCard(String username) {
        return null;
    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()));
            Employer employer =(Employer) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUsername(), employer.getRoles());
            return new ApiResponse("Token",true,token);

        }catch (BadCredentialsException exception){
            return new ApiResponse("parol yoki login xato",false);
        }
    }
}
