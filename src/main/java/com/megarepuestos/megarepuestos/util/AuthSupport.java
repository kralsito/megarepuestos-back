package com.megarepuestos.megarepuestos.util;


import com.megarepuestos.megarepuestos.config.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthSupport {

    public static Long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal instanceof CustomUserDetails userDetails){
                //TODO checkear si es null, tirar error si es null
                return userDetails.getId();
            }
        }
        //TODO: tirar error
        return null;
    }
}
