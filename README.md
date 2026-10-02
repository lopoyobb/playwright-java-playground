# Automate Demo — Playwright Java Playground

My learning playground for moving from **Manual QA (4 years)** to **Automation QA**.
This repo is **Stage 0** of my plan: get comfortable with the tools before designing a real framework.

## Tech stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 17 | Language |
| Maven | 3.9.x | Build & dependency management |
| JUnit Jupiter | 6.1.3 (via `junit-bom`) | Test runner |
| Playwright for Java | 1.63.0 | Browser automation |

## Getting started

```bash
# 1. Install browsers (one time only)
mvn exec:java -e "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=install"

# 2. Run all tests
mvn clean test

# 3. Run a single test class
mvn test "-Dtest=TheInternetTest"
```

> On PowerShell, wrap every `-D...` argument in double quotes.

## Project structure

```
Automate_demo/
├── pom.xml                         # dependencies (JUnit BOM + Playwright)
└── src/
    ├── main/java/                  # empty on purpose — the app under test lives on the web
    └── test/java/com/wiggle/app/
        └── TheInternetTest.java    # tests for https://the-internet.herokuapp.com
```

## Test cases

| Class | Test | What it checks |
|---|---|---|
| `TheInternetTest` | `checkBrowserTitleShouldBeTheInternet` | Home page title is `The Internet` |

## Learning roadmap

| Stage | Topic | Practice target | Status |
|---|---|---|---|
| 0 | Playground: setup, locators, assertions | the-internet.herokuapp.com | 🟡 In progress (Day 1 ✅) |
| 1 | UI automation framework (Page Object, fixtures) | saucedemo.com | ⬜ |
| 2 | API testing (Postman → code) | restful-booker | ⬜ |
| 3 | Choosing what to automate from manual test cases | saucedemo.com | ⬜ |
| 4 | Git + GitHub Actions CI | — | ⬜ |
| 5 | Hybrid UI + API tests | automationexercise.com | ⬜ |
| 6 | SQL + Docker database | sqlbolt.com, PostgreSQL | ⬜ |
| 7 | Flagship full-stack project (UI + API + DB + CI) | Conduit / Juice Shop | ⬜ |

## What I learned so far

**Stage 0 — Day 1**
- Created a Maven project and managed dependency versions with a BOM (`dependencyManagement` vs `dependencies`, `test` vs `compile` scope)
- Used `mvn dependency:tree` to verify the versions actually resolved
- Wrote my first Playwright test and closed resources with try-with-resources
- Made the test fail on purpose to prove it really checks something; learned that Playwright assertions auto-retry until a 5s timeout
- Java naming conventions (PascalCase classes, camelCase methods, file name = public class name)
- Maven lifecycle: `compile` → `test-compile` → `test`
