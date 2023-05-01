package ee.taltech.iti0202.computerbuilder.order;

import ee.taltech.iti0202.computerbuilder.computer.Computer;

public class Order {
    private Integer budget;
    private Computer.UseCase useCase;
    private Computer.ComputerType type;

    public Order(Integer budget, Computer.UseCase useCase, Computer.ComputerType type) {
        this.budget = budget;
        this.useCase = useCase;
        this.type = type;
    }

    public Integer getBudget() {
        return budget;
    }

    public Computer.UseCase getUseCase() {
        return useCase;
    }

    public Computer.ComputerType getType() {
        return type;
    }

}
