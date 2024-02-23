package com.tqs;

import java.util.List;
import java.util.ArrayList;

public class StocksPortfolio {

    private IStockmarketService stockmarketService;
    private List<Stock> stocks = new ArrayList<Stock>();

    public StocksPortfolio(IStockmarketService stockmarketService) {
		this.stockmarketService = stockmarketService;
	}

	public void addStock(Stock stock) {
		stocks.add(stock);
	}

	public double getTotalValue() {
		double total = 0;
		for (Stock stock : stocks) {
			total += stockmarketService.lookUpPrice(stock.getLabel()) * stock.getQuantity();
		}
		return total;
	}
}
