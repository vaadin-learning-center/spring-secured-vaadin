package org.vaadin.paul.spring.ui.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("sa-login-view")
@JsModule("./views/sa-login-view.js")
@NpmPackage(value="@polymer/iron-form", version = "3.0.1")
@Route(value = LoginView.ROUTE)
@PageTitle("Login")
public class LoginView extends Component {
	public static final String ROUTE = "login";

	public interface Model extends TemplateModel {
		void setError(boolean error);
	}
}
