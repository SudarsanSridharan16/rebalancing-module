# Rebalancing Module

This module reads in an asset portfolio from a csv and prints out the required trades to rebalance the portfolio to a target allocation.

There are two algorithms included to rebalance: `rebalance()` and `rebalanceRound()`. Both functions return a list of trades to be executed that can then be applied to a portfolio in order to execute. The default method to use is `rebalance()` as it makes sure that assets never exceed the available capital. However, in cases of increased liquidity and circumstances where excess funds are available, `rebalanceRound()` will provide a slightly more accurate allocation as it applies a basic `Math.round()` rather than a `Math.floor()`.

## Excess Capital Allocation

In order to act on opportunities where excess cash is available after a floor rebalance is applied, `rebalance()` will look to increase the trades using the excess cash shouold it get the asset closer to it's target allocation.

## Use

In order to use the module, adjust the data saved in [portfolio.csv](/src/portfolio.csv) to reflect the target portfolio. Then open the module using any Java IDE (ie. Eclipse), build the project and run. After making any changes to the portfolio, re-run the module in order to view a new rebalancing strategy.

## Trade-Offs

Due to the discrete nature of shares, rebalancing must be done in integers, however, this eliminates the possibility of a truly optimal rebalancing. This module attempts to mitigate the risk of this through excess cash allocation however it does not guarentee a truly optimal allocation. 

In order to minimize the total distance from the target allocation, an non-linear integer optimization could be applied. In a production environment, however, this calculation would take too long to apply to many portfolios, possibly taking up to a minute per portfolio in order to be sure the allocation is close to optimal.

An [Excel Solver model](/PortfolioRebalance.xlsx) can be found in this repo that performs this optimization. Solver must be set to perform an integer optimization and it's best to reset the `New Shares` fields to `0` in order to eliminate bias from the non-linear solver.
