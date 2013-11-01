/*
 *
 */

package com.awolart.fin.stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 */
public class SelectPortfolio extends Portfolio
{

    protected static final String DEFAULT_SEL_PORT_DATA_FILE = "SelectPortfolioData.csv";

    protected List<Folio> getSelectPortfolioData(final String file)
    {
        List<Folio> folioList = new ArrayList<Folio>();

        InputStream in = this.getClass().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        // file data: AAPL,100,40,16.2
        String line;
        try
        {
            while((line = reader.readLine()) != null)
            {
                if(!line.startsWith("#") && !line.isEmpty())
                {
                    Folio folio = new Folio();
                    String[] array = line.split(",");
                    // for(int i = 0; i < array.length; ++i)
                    folio.stock_name = array[0];
                    folio.initial_price = Double.parseDouble(array[1]);
                    folio.number_shares = Integer.parseInt(array[2]);
                    folio.expected_return = Double.parseDouble(array[3]);
                    folioList.add(folio);
                }
            }

        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        return folioList;
    }


    public static void main(String[] args) throws Exception
    {
        int number_of_shares = 0;
        double initial_price = 0.0;
        double expected_return = 0.0;
        String stock_symbol = "";
        SelectPortfolio port = new SelectPortfolio();

        List<Folio> foliosList = port
                .getSelectPortfolioData(DEFAULT_SEL_PORT_DATA_FILE);
        System.out.println("List size = " + foliosList.size());
        System.out.println("***********************************************");
        for(Folio folio : foliosList)
        {
            System.out.println("Stock symbol: " + folio.stock_name);
            stock_symbol = folio.stock_name;
            System.out
                    .println("Stock number of shares: " + folio.number_shares);
            number_of_shares = folio.number_shares;
            System.out.println("Stock initial price: " + folio.initial_price);
            initial_price = folio.initial_price;
            System.out.println("Stock expected return: "
                    + folio.expected_return);
            expected_return = folio.expected_return;
            System.out
                    .println("***********************************************");
            port.folio_returns(stock_symbol, number_of_shares, initial_price,
                    expected_return);
        }

        port.retInitPrice();

    }
}
