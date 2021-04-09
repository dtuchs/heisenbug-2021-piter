package ru.heisenbug.jupiter;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Store.CloseableResource;

public interface AroundTestExtension extends BeforeAllCallback {

    default void onStart(ExtensionContext context) {
    }

    default void onEnd() {
    }

    @Override
    default void beforeAll(ExtensionContext context) throws Exception {
        context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).getOrComputeIfAbsent(this.getClass(), k -> {
            onStart(context);
            return (CloseableResource) this::onEnd;
        });
    }
}
