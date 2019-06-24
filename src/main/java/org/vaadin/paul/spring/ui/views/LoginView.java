package org.vaadin.paul.spring.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = LoginView.ROUTE)
@PageTitle("Login")
@NpmPackage(value = "@polymer/iron-form", version = "3.0.1")
@JsModule("@polymer/iron-form/iron-form.js")
public class LoginView extends VerticalLayout {
	public static final String ROUTE = "login";

	public LoginView() {
		TextField userNameTextField = new TextField();
		userNameTextField.getElement().setAttribute("name", "username");
		PasswordField passwordField = new PasswordField();
		passwordField.getElement().setAttribute("name", "password");
		Button submitButton = new Button("Login");
		submitButton.setId("submitbutton");
		UI.getCurrent().getPage().executeJs("document.getElementById('submitbutton').addEventListener('click', () => document.getElementById('ironform').submit());");

		FormLayout formLayout = new FormLayout();
		formLayout.add(userNameTextField, passwordField, submitButton);

		Element formElement = new Element("form");
		formElement.setAttribute("method", "post");
		formElement.setAttribute("action", "login");
		formElement.appendChild(formLayout.getElement());

		Element ironForm = new Element("iron-form");
		ironForm.setAttribute("id", "ironform");
		ironForm.setAttribute("allow-redirect", true);
		ironForm.appendChild(formElement);

		getElement().appendChild(ironForm);

		setClassName("login-view");
	}
}
