package dev.vitorzucon.goncalvesaco.application.usecases;

import java.util.List;

import dev.vitorzucon.goncalvesaco.application.gateways.IBudgetGateway;
import dev.vitorzucon.goncalvesaco.domain.entities.Budget;

public class BudgetInteractor {

    private final IBudgetGateway budgetGateway;

    public BudgetInteractor(IBudgetGateway budgetGateway) {
        this.budgetGateway = budgetGateway;
    }

    public Budget creaBudget(Budget budget) {
        return budgetGateway.create(budget);
    }

    public Budget findBudget(Long id) {
        return budgetGateway.findBudget(id);
    }

    public List<Budget> findAllBudgets() {
        return budgetGateway.findAllBudgets();
    }

    public void deleteBudget(Long id) {
        budgetGateway.deleteBudget(id);
    }

    public void updateBudget(Budget budget) {
        budgetGateway.updateBudget(budget);
    }
}
