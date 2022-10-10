package com.example.demo.user.sjmsDemo.clms.test;

import com.example.demo.user.sjmsDemo.clms.test.impl.StrategyA;

public class text {

    public static void main(String[] args) {
        Strategy strategyA = new StrategyA();

        StrategyContext strategyContext = new StrategyContext(strategyA);
        strategyContext.contextInterface();
    }
}
