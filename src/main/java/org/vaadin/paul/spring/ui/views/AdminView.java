package org.vaadin.paul.spring.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.paul.spring.MessageBean;

@Route
@Secured("ROLE_Admin")
public class AdminView extends VerticalLayout {

    public AdminView() {
        Label label = new Label("Looks like you are admin!");
        add(label);
    }

}
