package com.tqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {
    
    @Mock
    IStockmarketService stockmarketService;

    @InjectMocks
    StocksPortfolio portfolio;

    @BeforeEach
    public void setUp() {
        portfolio = new StocksPortfolio(stockmarketService);
    }

    @Test
    public void getTotalValueTest() {
        Stock microsoftStock = new Stock("MSFT", 10);
        Stock amazonStock = new Stock("AMZN", 20);

        portfolio.addStock(microsoftStock);
        portfolio.addStock(amazonStock);

        when(stockmarketService.lookUpPrice("MSFT")).thenReturn(411.65);
        when(stockmarketService.lookUpPrice("AMZN")).thenReturn(174.58);

        double result = portfolio.getTotalValue();

        assertEquals(7608.1, result);
    }

}
