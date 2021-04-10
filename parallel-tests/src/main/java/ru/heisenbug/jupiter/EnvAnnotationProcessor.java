package ru.heisenbug.jupiter;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class EnvAnnotationProcessor implements BeforeAllCallback, BeforeEachCallback, AfterAllCallback {

    public static final ExtensionContext.Namespace envSpace = ExtensionContext.Namespace.create(EnvAnnotationProcessor.class);

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Env env = context.getRequiredTestClass().getAnnotation(Env.class);
        context.getStore(envSpace).put("env", env);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        context.getStore(envSpace).remove("env");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Env methodEnv = context.getRequiredTestMethod().getAnnotation(Env.class);
        Env classEnv = context.getStore(envSpace).get("env", Env.class);
        if (methodEnv == null) {
            methodEnv = classEnv;
        }
        if (methodEnv != null) {
            WebDriverRunner.setWebDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getCap(methodEnv)));
        }
    }

    private Capabilities getCap(Env methodEnv) {
        if (methodEnv.browser() == Env.Browser.chrome) {
            return new ChromeOptions();
        } else if (methodEnv.browser() == Env.Browser.firefox) {
            return new FirefoxOptions();
        } else throw new IllegalArgumentException();
    }
}
