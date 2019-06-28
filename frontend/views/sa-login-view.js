import '@polymer/iron-form/iron-form';
import '@vaadin/vaadin-button/vaadin-button';
import '@vaadin/vaadin-text-field/vaadin-text-field';
import '@vaadin/vaadin-text-field/vaadin-password-field';
import '@vaadin/vaadin-ordered-layout/vaadin-vertical-layout';
import {PolymerElement} from '@polymer/polymer/polymer-element.js';
import {html} from '@polymer/polymer/lib/utils/html-tag.js';

class LoginView extends PolymerElement {
    static get template() {
        return html`
        <div class="container">
            <iron-form class="login" id="form" allow-redirect>
                <form method="post" action="login">
                    <vaadin-vertical-layout>
                        <vaadin-text-field id="username" name="username" autofocus required></vaadin-text-field>
                        <vaadin-password-field id="password" name="password" required></vaadin-password-field>
                        <vaadin-button on-click="login" theme="primary">
                            Login
                        </vaadin-button>
                    </vaadin-vertical-layout>
                </form>
            </iron-form>
        </div>`;
    }

    static get is() {
        return 'sa-login-view';
    }

    login() {
        if (!this.$.username.invalid && !this.$.password.invalid) {
            this.$.form.submit();
        }
    }
}

window.customElements.define(LoginView.is, LoginView);
