package org.vaadin.paul.spring.ui.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.paul.spring.app.security.CustomRequestCache;

@Tag("sa-login-view")
@Route(value = LoginView.ROUTE)
@PageTitle("Login")
public class LoginView extends VerticalLayout {
	public static final String ROUTE = "login";

	private LoginOverlay login = new LoginOverlay();

	@Autowired
	public LoginView(AuthenticationManager authenticationManager,
					 CustomRequestCache requestCache) {
		// configures login dialog and adds it to the main view
		login.setOpened(true);
		login.setTitle("Spring Secured Vaadin");
		login.setDescription("Login Overlay Example");

		add(login);

		login.addLoginListener(e -> {
			try {
				// try to authenticate with given credentials, should always return !null or throw an {@link AuthenticationException}
				final Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(e.getUsername(), e.getPassword()));

				// if authentication was successful we will update the security context and redirect to the page requested first
				if(authentication != null ) {
					login.close();
					SecurityContextHolder.getContext().setAuthentication(authentication);

					//Access to view by role
					if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(role -> role.equals("ROLE_Admin"))) {
						UI.getCurrent().navigate(AdminView.class);
					} else {
						UI.getCurrent().navigate(requestCache.resolveRedirectUrl());
					}
				}
			} catch (AuthenticationException ex) {
				// show default error message
				// Note: You should not expose any detailed information here like "username is known but password is wrong"
				// as it weakens security.
				login.setError(true);
			}
		});
	}
}
