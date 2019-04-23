package org.vaadin.paul.spring.ui.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
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
				// try to authenticate with given credentials
				final Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(e.getUsername(), e.getPassword()));

				// if authentication was successful we will update the security context and redirect to the page requested first
				if(authentication != null ) {
					login.close();
					SecurityContextHolder.getContext().setAuthentication(authentication);
					UI.getCurrent().navigate(resolveRedirectUrl(requestCache));
				}

			} catch (AuthenticationException ex) {
				// something went wrong
				login.setError(true);
			}
		});
	}

	/**
	 * Uses the custom request cache to resolve the correct redirect URL.
	 *
	 * @param requestCache
	 * @return
	 */
	private String resolveRedirectUrl(CustomRequestCache requestCache) {
		final DefaultSavedRequest savedRequest = requestCache.getSavedRequest();
		if(savedRequest != null) {
			final String requestURI = savedRequest.getRequestURI();
			if (requestURI != null && requestURI.length() > 0 && !requestURI.contains(ROUTE)) {
				return requestURI.startsWith("/") ? requestURI.substring(1) : requestURI;
			}
		}

		return "";
	}
}
