package com.wiggle.app;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class LoginTest {
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @BeforeEach
    void setUp() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @Test
    void checkTitleShouldBeTestLoginPracticeTestAutomation(){
        assertThat(page).hasTitle(Pattern.compile("Test Login \\| Practice Test Automation", Pattern.CASE_INSENSITIVE));
    }

    @Test 
    void fillUserNameAndPasswordThenLoginShouldDisplaySuccessLabelInTheNextPage(){
        page.getByLabel("Username").fill("student");
        page.getByLabel("Password").fill("Password123");
        //Click login button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Pattern.compile("Submit", Pattern.CASE_INSENSITIVE))).click();

        //Check Login success
        assertThat(page.locator(".post-title")).containsText("Logged In Successfully");
        //Check url
        assertThat(page).hasURL(Pattern.compile("logged-in-successfully"));
    }

    @Test 
    void fillWrongPasswordThenLoginShouldDisplayFailLabelInTheNextPage(){
        page.getByLabel("Username").fill("student");
        page.getByLabel("Password").fill("Password1234");
        //Click login button
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Pattern.compile("Submit", Pattern.CASE_INSENSITIVE))).click();

        //Check Login fail
        assertThat(page.locator("#error")).containsText("Your password is invalid!");
        //Check url
        assertThat(page).hasURL(Pattern.compile("practice-test-login"));
    }
}
