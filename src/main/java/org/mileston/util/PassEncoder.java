package org.mileston.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassEncoder {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Genera password per "password"
        String encodedPassword = encoder.encode("password");
        System.out.println("Password 'password' criptata: " + encodedPassword);
        
        String encodedSimple = encoder.encode("1234");
        System.out.println("Password '1234' criptata: " + encodedSimple);
    }
    
}
