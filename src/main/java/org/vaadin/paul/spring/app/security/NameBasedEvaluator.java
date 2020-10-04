package org.vaadin.paul.spring.app.security;

import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.NotFoundException;
import org.ilay.Access;
import org.ilay.AccessEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class NameBasedEvaluator implements AccessEvaluator<SecuredForPaul> {
    @Override
    public Access evaluate(Location location, Class navigationTarget, SecuredForPaul annotation) {
        if(SecurityUtils.isUserLoggedIn()) {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final UserDetails principal = (UserDetails)authentication.getPrincipal();
            if(principal.getUsername().equals("paul")) {
                return Access.granted();
            }
        }

        return Access.restricted(NotFoundException.class);
    }
}