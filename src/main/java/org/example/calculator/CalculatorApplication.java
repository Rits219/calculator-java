package org.example.calculator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.example.calculator.config.CalculatorConfiguration;
import org.example.calculator.module.CalculatorModule;
import org.example.calculator.resource.CalculatorResource;

import java.util.EnumSet;

public class CalculatorApplication extends Application<CalculatorConfiguration> {

    private void configureCors(Environment environment) {
        FilterRegistration.Dynamic cors =
                environment.servlets()
                        .addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter(
                "allowedOrigins",
                "http://127.0.0.1:5500,http://localhost:5500"
        );

        cors.setInitParameter(
                "allowedMethods",
                "GET,POST,PUT,DELETE,OPTIONS,HEAD"
        );

        cors.setInitParameter(
                "allowedHeaders",
                "Content-Type,Accept,Origin,X-Requested-With"
        );

        cors.addMappingForUrlPatterns(
                EnumSet.allOf(DispatcherType.class),
                true,
                "/*"
        );
    }

    public static void main(String[] args) throws Exception {
        new CalculatorApplication().run(args);
    }

    @Override
    public void run(CalculatorConfiguration calculatorConfiguration, Environment environment) throws Exception {
        configureCors(environment);
        Injector injector = Guice.createInjector(new CalculatorModule());
        environment.jersey().register(injector.getInstance(CalculatorResource.class));
    }
}
