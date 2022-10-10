package com.example.demo.user.sjmsDemo.clms.test;

public class StrategyContext {

    private Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 策略方法
     */
    public void contextInterface() {
        strategy.contextIntegerface();
    }
}
