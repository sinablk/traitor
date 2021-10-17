---
layout: post
title:  What is "statistical learning"?
date:   2019-01-29
author: Ali Sina
mathjax: true
tags: [statistics, machine_learning, statistical_learning]
postFooter: Additional information, and maybe a <a href="#">link or two</a>.
---

> This is the first in a [series](https://alisiina.github.io/2019/01/28/statistical-learning-series.html) of posts that I'm doing on statistical learning. All the material is based on [An Introduction to Statistical Learning](http://www-bcf.usc.edu/~gareth/ISL/) book which was taught by the authors and Stanford University professors Trevor Hastie and Rob Tibshirani. The aim is to condense the concepts taught in the course and the material in the book to a series of under-10-minute reads.

### What?

Statistical learning is comprised of a vast number of tools and techniques for *understanding data*. The foundation of these tools are the fields of mathematics and statistics, applied using computer technologies. These tools can be used to solve a range of real world problems, like [campaigning for president](https://www.technologyreview.com/s/509026/how-obamas-team-used-big-data-to-rally-voters/), [curing cancer](https://news.mit.edu/2018/artificial-intelligence-model-learns-patient-data-cancer-treatment-less-toxic-0810), [self-driving cars](https://www.kdnuggets.com/2017/06/machine-learning-algorithms-used-self-driving-cars.html), [recruitment](https://harver.com/blog/machine-learning-in-recruitment/), [space exploration](https://rctom.hbs.org/submission/2018-a-space-odyssey-how-nasa-uses-machine-learning-for-space-exploration/), and a [bunch of others](https://www.forbes.com/sites/bernardmarr/2018/04/30/27-incredible-examples-of-ai-and-machine-learning-in-practice/).

Suppose $Y$ is the quantitative response variable, or the thing we'd like to find, and $X$ is a set of predictors of $Y$. Then, the  model can be described as:

$$ Y = f(X) + \epsilon, $$

where $f$ is the systematic information that $X$ provides about $Y$ and $\epsilon$ is the error term. Then,

$$ f(X) = E(Y \mid X = x). $$

In plain English, the function $f$ for any given set of $X$ is given by the *expected* value of $Y$ at point $x$. Statistical learning is simply a **set of approaches for estimating this $f$.**

### Why?

Statistical learning is mainly used for two reasons: *prediction* and *inference*. An example of prediction would be a company using statistical learning to find out how a customer (the $Y$ response variable) would respond to an ad based on demographic variables (the set of $X$ predictors). In this case, the company only wants to predict if this customer would respond positively or negatively to a certain ad.

Modeling for inference is when we're interested in understanding the way our response variable $Y$ is affected by the set of $X$ predictors. We're **not** trying to predict, in this case, but simply how changes in $X$ affect $Y$ and what is the relationship between $X$ and $Y$. For example, in product design, we would be interested in how variables such as price, store location, discount levels, competition price, etc., will affect a prospective customer's probability of purchase.

Statistical learning can also be used for a combination of the two. For example, in real estate, we would be interested in how variables like neighborhood crime rate, zoning, and distance from a river affect the house price. At the same time, we would also like to predict the price of the house from these variables.

### How?

$f$ can be estimated using methods that are either *parametric* or *non-parametric*. Parametric methods are those where the task of finding $\hat{f}$ is reduced to that of finding its parameters. For example, if we want to predict the `income` of an individual given their `seniority` and `education`, then we only need to estimate $\beta_0$, $\beta_1$, and $\beta_2$ in the linear model:

$$income \approx \beta_0 + \beta_1 \times seniority + \beta_2 \times education$$

Non-parametric methods do not make any such assumptions on the form of the function $f$ and instead try to fit the data as best as possible without being too rough or wiggly.

For predictive modeling, a flexible method is preferred that can capture the data more accurately by use of more than a few variables. For inferential modeling, a simpler (rigid) method is preferred for interpretability. Therefore, there always exists a tradeoff between flexibility and interpretability.[^1]

We mostly want to find a function $\hat{f}$[^2] such that

$$ \hat{Y} \approx \hat{f}(X). $$

Assuming $\hat{f}$ and $X$ are fixed, we can show that

$$ E(Y - \hat{Y})^2 = \underbrace{[f(X) - \hat{f}(X)]^2}_\text{Reducible} + \underbrace{Var(\epsilon)}_\text{Irreducible} $$

where $E(Y - \hat{Y})^2$ is the *expected value* of the squared differences between the actual and predicted values of $Y$. The variance in the error term $\epsilon$ exists because there is typically a distribution of the possible values of $Y$. Or more simply put, no model can truly capture all real-world variables that predict $Y$. This will always serve as an upper bound to the accuracy of the model, and is almost always unknown.

Statistical learning is principally concerned with techniques for estimating $f$ by minimizing the reducible error.


{% include socialsharing.html %}


* * *
##### FOOTNOTES

[^1]: Another important "tradeoff" is called the Bias-Variance tradeoff. Read [this article](https://scott.fortmann-roe.com/docs/BiasVariance.html) for a comprehensive explanation.
[^2]: The "hat" on top of $\hat{f}$ means that it is an estimated value as opposed to the real value of $f$. This is standard math syntax.
