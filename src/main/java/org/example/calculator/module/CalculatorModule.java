package org.example.calculator.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.calculator.service.CalculatorService;

public class CalculatorModule extends AbstractModule {

    @Provides
    @Singleton
    private CalculatorService provideCalculatorService() {
        return new CalculatorService();
    }
}
