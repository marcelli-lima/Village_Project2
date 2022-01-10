package com.example.projeto.model.transport;

import java.math.BigDecimal;

public class BudgetDTO {

	private BigDecimal budget;
	private BigDecimal total;
	private BigDecimal difference;
    private VillageDTO higherCost;

    public BudgetDTO() {
    }

    public BudgetDTO(BigDecimal difference, BigDecimal total, BigDecimal budget, VillageDTO higherCost) {
    	
    	this.budget = budget;
        this.total = total;
        this.difference = difference;
        this.higherCost = higherCost;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }
    public VillageDTO getMostExpensiveResident() {
        return higherCost;
    }

    public void setMostExpensiveResident(VillageDTO higherCost) {
        this.higherCost = higherCost;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "difference=" + difference +
                ", total=" + total +
                ", budget=" + budget +
                ", higherCost=" + higherCost +
                '}';
    }
}
