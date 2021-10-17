---
layout: post
title:  Model selection
date:   2019-02-17
author: Ali Sina
summary: Because most data have a large number of features, it is nearly impossible to manually select features that are statistically significant in a prediction problem. "Model selection and regularization" are efficient alternatives to Ordinary Least Squares fitting that automatically select features to find the best-fitting linear model.
mathjax: true
tags: [statistics, machine_learning, statistical_learning, model_selection, regularization, shrinkage, lasso, ridge, subsets, subset_selection]
postFooter: Additional information, and maybe a <a href="#">link or two</a>.
---

> This is the fifth in a [series](https://alisiina.github.io/2019/01/28/statistical-learning-series.html) of posts that I'm doing on statistical learning. All the material is based on [An Introduction to Statistical Learning](http://www-bcf.usc.edu/~gareth/ISL/) book which was taught by the authors and Stanford University professors Trevor Hastie and Rob Tibshirani. The aim is to condense the concepts taught in the course and the material in the book to a series of under-10-minute reads.

### Subset selection

The principle of subset selection is relatively easy to understand (although the math is not so easy). In such an approach, the algorithm goes through all subsets of the full set of features to find the ones that are most related to the response $Y$. It then fits a least squares model on this reduced set of features.

There are three main kinds of subset selection. **Best subset selection** refers to going through all combinations of each feature and finding the ones that are the best predictors of $Y$. It is important to not choose the best subset based on RSS and $R^2$ because in the case of a linear model, RSS will decrease monotonically and $R^2$ will increase monotonically as $p +1 $ predictors are added. So the final model will always contain all $p$ predictors. Therefore, alternative measures are used to estimate prediction errors on unseen test data.

![fig8](images/stat-learning-series/fig8.png)

**Forward selection** and **backward selection** are two other *stepwise* subset selection methods. Unlike best subset selection which tries to compute $2^P$ models, these two methods compute only $P^2$ best models. So for large datasets, forward and backward selection are preferred.

In forward stepwise selection, we first start with the *null* model $M_0$ which only contains the intercept. At each step, the variable that gives the greatest *additional* improvement is added to the model. In backward stepwise selection, we start with all predictors that fit the least squares model and iteratively remove each predictor until we arrive at the best model.

The criteria for choosing the best model is similar to best subset selection. We choose a model that gives us the lowest *test* predictor error. RSS and $R^2$ are not suitable because, as you can see, each subset produced by stepwise selection will have different number of $p$ predictors. The correct approach is to assess the fit our model using [AIC](https://www.brianomeara.info/tutorials/aic/), [BIC](https://prateekvjoshi.com/2015/06/21/what-is-bayesian-information-criterion/), [Mallow's $C_p$](https://en.wikipedia.org/wiki/Mallows%27s_Cp), or [Adjusted $R^2$](https://prateekvjoshi.com/2015/06/21/what-is-bayesian-information-criterion/). However, the preferred and modern approach to assess the accuracy of the model is to use cross-validated prediction error on test data.[^1]

There are also hybrid approaches that combine two or more of the above approaches for fitting the best model. For example, **bidirectional stepwise selection** is a combination of forward and backward selection, where the algorithm is adding and removing variables at the same time. This sounds similar to best subset selection, and it is, but it has the computational efficiency of forward and backward selection algorithms.

### Shrinkage methods

Shrinkage involves shrinking coefficient estimates of a model. This is also called *regularization*. **Ridge regression** is a regularization technique that seeks coefficient estimates that fit the data well by making RSS as small as possible with the inclusion of a penalty term:

$$ \text{RSS} \ + \underbrace{\lambda \sum_{y = 1}^{p} \beta_{j}^2}_\text{Shrinkage penalty} ,$$

where $\lambda$ is a tuning parameter that is selected by cross validation. The task of the shrinkage penalty is to *penalize* the RSS if more terms are added. So ridge regression tries to find the best fitted model by *shrinking* some of the coefficient estimates as close to zero as possible but never equal to zero. This means that our final model will still have all variables with some of them close to zero.

The **lasso**, on the other hand, has the advantage of shrinking some of the variables exactly to zero:

$$ \text{RSS} \ + \lambda \sum_{y = 1}^{p} | \beta_{j} | $$

Therefore, we say that lasso shrinkage also carries out *feature selection* for us, i.e. it shrinks some of the variables to zero so that we are left with the only a subset of most significant variables.[^2] As a result, lasso gives us simpler models that have decreased variance at the cost of increased bias.

But how to choose the most suitable $\lambda$? This is done by cross validation (CV), which choose a particular $\lambda$ value that gives the smallest test error rate on $K$ validation sets. Therefore, CV determines which one of the predictors is related to $Y$.

Lasso tends to perform better when response is a function of only a small number of predictors, i.e. only a small subset of all $p$ predictors are related to $Y$. Ridge regression performs better otherwise. In statistical parlance, we say lasso is better when the true model is *sparse* while ridge regression is better when the true model is *dense.*


{% include socialsharing.html %}

* * *
##### FOOTNOTES

[^1]: Further reading: [*Model Selection Criterion: AIC and BIC (Appendix E)*](https://onlinelibrary.wiley.com/doi/pdf/10.1002/9781118856406.app5), The Basics of Financial Econometrics, Frank J. Fabozzi, Sergio M. Focardi, Svetlozar T. Rachev and Bala G. Arshanapalli. Published 2014 by John Wiley & Sons, Inc.
[^2]: You might want to look into two other *dimensionality reduction* methods: [Principal Components Analysis](https://joellaity.com/2018/10/18/pca.html) and [t-SNE](https://lvdmaaten.github.io/tsne/). Both these methods also carry out feature selection and are commonly used in image classification problems.
