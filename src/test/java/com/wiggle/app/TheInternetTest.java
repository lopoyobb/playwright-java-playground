package com.wiggle.app;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.Test;

public class TheInternetTest {

    @Test
    void checkBrowserTitleShouldBeTheInternet() {

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("https://the-internet.herokuapp.com");

            assertThat(page).hasTitle("The Internet");
        }
    }
}
