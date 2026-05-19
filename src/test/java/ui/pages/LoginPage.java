package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page Object для страницы входа Keycloak.
 */
public class LoginPage {

    private final SelenideElement pageTitle = $("#kc-page-title");
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement signInButton = $("#kc-login");
    private final SelenideElement registerLink = $("a[href*='registration']");
    private final SelenideElement forgotPasswordLink = $("a[href*='reset-credentials']");

    public SelenideElement getPageTitle() {
        return pageTitle;
    }

    public SelenideElement getUsernameInput() {
        return usernameInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getSignInButton() {
        return signInButton;
    }

    public SelenideElement getRegisterLink() {
        return registerLink;
    }

    public SelenideElement getForgotPasswordLink() {
        return forgotPasswordLink;
    }
}
