# Example code for a Spring secured Vaadin web application
This project is based on the the Vaadin Spring starter (https://vaadin.com/start). You can find the corresponding tutorial at https://vaadin.com/tutorials/securing-your-app-with-spring-security

Import the project to the IDE of your choosing as a Maven project.

Run application using mvn spring-boot:run or directly running Application class from your IDE.

Open http://localhost:8080/ in browser

The `master` branch contains the general Spring Security configuration and for each specialized implementation we have created a dedicated branch. All examples use Vaadin 14.

Form/POST based approaches:
- `polymer-form` - How to create a custom Polymer based form.
- `java-form`- How to create a Java-only form.
- `login-component-form` - How to use the free login form component.
- `login-overlay-form` - How to use the free login dialog component.
- `login-overlay-form-fine-access-control` - Extend the example for fine grained view access control
- `login-overlay-form-ilay` - Using ILAY for fine grained view access control

Logging in without posting a form:
- `form-less` - How to use the free login dialog and login without a page reload.
