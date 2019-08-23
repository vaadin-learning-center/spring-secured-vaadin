package org.vaadin.paul.spring.ui.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.paul.spring.app.security.SecuredForPaul;

@Route("backdoor")
@SecuredForPaul
public class PaulView extends VerticalLayout {
    @Autowired
    public PaulView() {
        Label label = new Label("Hello Paul!");
        add(label);
    }
}
