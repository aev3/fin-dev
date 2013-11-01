/*
 *
 */

package com.awolart.fin.stats;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 */
public class Portfolio {


    protected List<Folio> foliosList = new ArrayList<Folio>();

    protected static class Folio
    {
        String stock_name;
        int number_shares;
        double initial_price;
        double expected_return;
    }

    public void folio_returns(String name, int shares, double init_prc, double exp_rets)
    {
        Folio folio = new Folio();
        folio.stock_name = name;
        folio.number_shares = shares;
        folio.initial_price = init_prc;
        folio.expected_return = exp_rets;
        foliosList.add(folio);
    }

    public void retInitPrice()
    {

        final int foliosListSize = foliosList.size();

        double initial_portfolio_value = 0.0;
        double portfolio_return = 0.0;

        // Loop 1
        for(int i = 0; i < foliosListSize; ++i)
        {
            Folio folio = foliosList.get(i);
            /* Initial market price */
            Double totals = folio.initial_price;
            /* Number of shares */
            Integer num_shares = folio.number_shares;
            /* Add to initial portfolio values */
            initial_portfolio_value += (totals * num_shares);
        }

        double proportion = 0.0;
        double total_investment = 0.0;

        // Loop 2
        for(int j = 0; j < foliosListSize; ++j)
        {
            double total_initial_investment = 0.0;
            Folio folio = foliosList.get(j);
            Double expected_returns = folio.expected_return;
            Double initial_price = folio.initial_price;
            Integer total_num_shares = folio.number_shares;
            String stk_name = folio.stock_name;

            /* Total investment per share */
            total_initial_investment =  (initial_price * total_num_shares);
            /* As a proportion of the initial portfolio valuation */
            proportion = (total_initial_investment/initial_portfolio_value);
            /* Expected return as a % of investment */
            total_investment = (expected_returns * proportion);
            /* Gross portfolio expected return */
            System.out.println("Portfolio: " + stk_name
                    + " total initial investment " + total_initial_investment
                    + " proportion " + proportion);
            portfolio_return = total_investment;
        }

        portfolio_return += total_investment;

        System.out.println("Initial Portfolio Value: \t\t" + initial_portfolio_value);

        System.out.println("Expected Portfolio Return: \t\t" + portfolio_return);

    }

    public void PortfolioRiskAnalysis()
    {
        SelectPortfolio port = new SelectPortfolio();

        String file = SelectPortfolio.DEFAULT_SEL_PORT_DATA_FILE;
        List<Folio> foliosList = port.getSelectPortfolioData(file);
    }

}
