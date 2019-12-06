package org.vaadin.paul.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
        Button button = new Button("Click me",
                e -> Notification.show(bean.getMessage()));
        add(button);

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        add(upload);
        upload.addSucceededListener(e -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Notification.show(String.format("Upload finished: %s for %sauthenticated user ", e.getFileName(), authentication == null ? "non-" : ""));
        });
    }

}
