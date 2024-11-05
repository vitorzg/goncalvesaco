package dev.vitorzucon.goncalvesaco.application.gateways;

import java.util.List;

import dev.vitorzucon.goncalvesaco.domain.entities.Budget;

public interface IBudgetGateway {

    Budget create(Budget budget);

    Budget findBudget(Long id);

    List<Budget> findAllBudgets();

    void deleteBudget(Long id);

    void updateBudget(Budget budget);

}
