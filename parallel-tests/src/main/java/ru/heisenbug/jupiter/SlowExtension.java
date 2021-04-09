package ru.heisenbug.jupiter;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SlowExtension implements BeforeEachCallback, AfterEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Thread.sleep(5000);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Thread.sleep(5000);
    }
}
