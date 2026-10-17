package org.example.calculator.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.calculator.request.CalculatorRequest;
import org.example.calculator.service.CalculatorService;

@Path("/calculator")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalculatorResource {
    private final CalculatorService calculatorService;

    @Inject
    public CalculatorResource(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @POST
    @Path("/add")
    public int add(CalculatorRequest request) {
        return calculatorService.add(request.getA(), request.getB());
    }

    @POST
    @Path("/subtract")
    public int subtract(CalculatorRequest request) {
        return calculatorService.subtract(request.getA(), request.getB());
    }

    @POST
    @Path("/multiply")
    public int multiply(CalculatorRequest request) {
        return calculatorService.multiply(request.getA(), request.getB());
    }

    @POST
    @Path("/devide")
    public int devide(CalculatorRequest request) {
        return calculatorService.devide(request.getA(), request.getB());
    }
}
