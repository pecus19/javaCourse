package ee.taltech.iti0202.computerbuilder.order;

import ee.taltech.iti0202.computerbuilder.computer.Computer;

public class Order {
    private double budget;
    private Computer.UseCase useCase;
    private Computer.ComputerType type;

    public Order(double budget, Computer.UseCase useCase, Computer.ComputerType type) {
        this.budget = budget;
        this.useCase = useCase;
        this.type = type;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Computer.UseCase getUseCase() {
        return useCase;
    }

    public void setUseCase(Computer.UseCase useCase) {
        this.useCase = useCase;
    }

    public Computer.ComputerType getType() {
        return type;
    }

    public void setType(Computer.ComputerType type) {
        this.type = type;
    }
}
