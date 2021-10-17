---
layout: post
title:  Linear regression
date:   2019-02-05
author: Ali Sina
summary: Simple and widely used, linear regression is a supervised learning algorithm that serves as a "gateway" method in most analyses. To understand the rest of statistical or machine learning, a good grasp of linear regression is absolutely necessary.
mathjax: true
tags: [statistics, machine_learning, statistical_learning, linear_regression]
postFooter: Additional information, and maybe a <a href="#">link or two</a>.
---

> This is the second in a [series](https://alisiina.github.io/2019/01/28/statistical-learning-series.html) of posts that I'm doing on statistical learning. All the material is based on [An Introduction to Statistical Learning](http://www-bcf.usc.edu/~gareth/ISL/) by Trevor Hastie and Rob Tibshirani. The aim is to condense the concepts taught in the course and the material in the book to a series of under-10-minute reads.

### What?

Linear regression assumes a linear relationship between the response and predictor variables.

$$ Y = \beta_0 + \beta_1 X + \epsilon $$

Note that this has the form of the equation of a line $y = mx + b$. The intercept $\beta_0$ is the value of $Y$ at $X=0$. The slope $\beta_1$ represents the average increase in $Y$ associated with a one unit increase in $X$. $\epsilon$ is the noise, or what we miss about the relationship between $Y$ and its parameters because, as is often the case, the true relationship of any real-world scenario is not completely linear.[^1] Suppose we want to predict the `sales` of a product based on advertising through `TV`. Then we can express this model as

$$ sales \approx \beta_0 + \beta_1 \times TV. $$

This is an example of Simple Linear Regression. Most models will have multiple predictors $p$. Therefore, a *Multiple* Linear Regression model  accounts for all these predictors and their respective coefficients $\beta_p$.

### Why?

Linear regression is a simple method for both analysis and prediction of data, which is why it mostly doesn't provide accurate results. However, because of its simplicity and interpretability, it is widely used as a first-step approach to most statistical problems. The insights gained from doing linear regression on a data often clarify the next steps to be taken by an analyst.

Linear regression also serves as a framework for other state-of-the-art algorithms, like nearest neighbor methods, support vector machines, and logistic regression. These algorithms, and many others, use linear regression under the hood.

However, there [also exist cases](https://www.youtube.com/watch?v=68ABAU_V8qI) where a simple, even linear, regression proves to be a winning recipe over more complex models for solving real-world problems.

### How?

Linear regression is an example of a parametric method where the task of predicting $Y$ is reduced to the task of estimating its parameters $\beta_0, \beta_1,...,\beta_p$ such that

$$ \hat{y} = \hat{\beta}_0 + \hat{\beta}_1x, $$

where $\hat{y}$ is the prediction of $Y$ on the basis of $X = x$.

To estimate $\hat{y}$, we want to find parameters $\beta_p$ such that the resulting line is as close to all data $X$ as possible. A common method of finding the values for these parameters is knows as the **least squares criterion,** illustrated by figure:

[![fig1](images/stat-learning-series/fig1.png)](https://gist.github.com/alisiina/25167fa6f37383be383cdc48397bac28)

If $\hat{y}_i$ is the predicted value of $Y$ at the $i$th value of $X$, then we can find $e_i = y_i - \hat{y}_i$ where $e_i$ is the $i$th *residual*. This residual value is essentially the distance between the $i$th observation represented by <span style="color:#708ebf">blue</span> dots and the predicted $\hat{y}_i$ that exists on the <span style="color:#ff0000">red</span> line. The least squares criterion minimizes this distance among all dots[^2] to fit the best regression line $\hat{f}$.

The coefficients $\beta_p$ usually do not exist as standalone predictors of the response $Y.$ In most models, it is likely that two or more predictors are related to each other. This is called the *interaction* effect, where the coefficients interact with each other to predict $Y.$ In such a case, an interaction term is added to the model. For example, if we have $x_1$ and $x_2$ variables with corresponding coefficients $\beta_1$ and $\beta_2$, then our model would look like

$$ Y = \beta_0 + \beta_1x_1 + \beta_2x_2 + \beta_3x_1x_2 + \epsilon, $$

where $\beta_3$ is the coefficient of the interaction term $(x_1 \times x_2).$ $\beta_3$ can be interpreted as the increase in effectiveness of $x_1$ for a one unit increase in $x_2$ (or vice-versa).

What if the variables in the model have *non-linear* effects on the response? Then we use a special case of linear regression called *polynomial* regression where our predictors contain exponents. Then our regression model will fit a parabola instead of a straight line.

[![fig2](images/stat-learning-series/fig2.png)](https://gist.github.com/alisiina/99d26435f1d8298aa63fc505f07ae62e)

In Fig 2, the <span style="color:#58aa6b">green</span> line represents a polynomial model that has a quadratic predictor variable with 2 *degrees of freedom*. i.e. it has a variable $x_i^2$. We can clearly see that it fits the data far better than the <span style="color:#4c72b0">blue</span> simple linear regression line. The corresponding [Mean Squared Error](https://en.wikipedia.org/wiki/Mean_squared_error#Regression) (MSE) scores validate this.[^3]

Modeling such non-linear relationships between response and predictors is not limited to exponents. We can also address such non-linearities with $\log{X}$ and $\sqrt{X}$. The pitfall to always avoid is to not [overfit](https://scikit-learn.org/stable/auto_examples/model_selection/plot_underfitting_overfitting.html#sphx-glr-auto-examples-model-selection-plot-underfitting-overfitting-py) the data with such quadratic terms. For example, by choosing higher degrees of freedom, the regression model will fit the training data too closely and, therefore, perform poorly on unseen test data.[^4]

{% include socialsharing.html %}

* * *
##### FOOTNOTES


[^1]: This error term also stands for *any* kind of error, e.g. measurement error.
[^2]: To be accurate, the model minimizes the *residual sum of squares*, which is $RSS = {e_1}^2 + {e_2}^2 + ... + {e_n}^2$ for $n$ number of observations.
[^3]: There is a subtle difference between RSS and MSE, details of which vary between use cases. Simply, $MSE = \frac{1}{n} RSS$ where $n$ is the number of observations. This is to make it *unbiased*, which means that MSE can be compared between models for samples with different $n$.
[^4]: Data for machine learning is usually [broken into parts](https://alisiina.github.io/2019/02/13/resampling-methods.html) called *train* and *test* data. A model is first trained on the former and tested on the latter. We are primarily interested in the performance of our models on test data.
