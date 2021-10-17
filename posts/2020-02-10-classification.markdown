---
layout: post
title:  Classification
date:   2019-02-10
author: Ali Sina
summary: In most situations, we want to predict whether something is or isn't. Male or female, student or employee. Classification methods are responsible for classifying a given data between such groups.
mathjax: true
tags: [statistics, machine_learning, statistical_learning, classification]
postFooter: Additional information, and maybe a <a href="#">link or two</a>.
---

> This is the third in a [series](https://alisiina.github.io/2019/01/28/statistical-learning-series.html) of posts that I'm doing on statistical learning. All the material is based on [An Introduction to Statistical Learning](http://www-bcf.usc.edu/~gareth/ISL/) book which was taught by the authors and Stanford University professors Trevor Hastie and Rob Tibshirani. The aim is to condense the concepts taught in the course and the material in the book to a series of under-10-minute reads.

### What?

In [linear regression](https://alisiina.github.io/2019/02/05/linear-regression.html), the response variable $Y$ was quantitative or continuous, meaning it could take any value between a range of values. In most real-world situations, the problems presented to us are usually classification problems. For example, we would like to know if someone is lying or telling the truth, if a customer will buy or not buy our product, or to [which species does a flower belong to](https://en.wikipedia.org/wiki/Iris_flower_data_set). In such a situation, we say our response is categorical or qualitative; we are merely interested in categorizing our data between groups. Mathematically, if we're trying to predict if a person is telling the truth or not, we write:

$$
Y =
\begin{cases}
1,  & \text{if telling the truth;} \\
0, & \text{if lying.}
\end{cases}
$$

This binary *encoding* of our response tells us that the value of $Y$ can be either $1$ or $0$, but not both.

Take for example the famous [Iris flower dataset](https://en.wikipedia.org/wiki/Iris_flower_data_set). This is a classification problem, where we wish to predict the class `Species` of the dataset based on two predictors `Sepal.Length` and `Sepal.Width`.

![fig3](images/stat-learning-series/fig3.png)

In this problem, our response $Y$ can only take one of three values: `setosa`, `versicolor`, or `virginica`, all of which are values of the `Species` response variable. Classification methods allow us to categorize this kind of data into their corresponding groups based on a number of statistical models and approaches.

### Why?

Suppose we want to predict a response $Y$ which has more than two levels so that we cannot have a binary response of $1$ and $0$. Linear regression would not be appropriate in this situation. To see why, lets suppose we want to predict if a person has gender `Male`, `Female`, or `Other`. Then our response needs to be encoded:

$$
Gender =
\begin{cases}
1,  & \text{if Male;} \\
2, & \text{if Female;} \\
3, & \text{if Other.}
\end{cases}
$$

In this case, linear regression will assume, e.g., that `Other` is greater than `Female` and the distance between `Male` and `Female` is smaller than that between `Male` and `Other`. In truth, the encoding is completely arbitrary and we could've chosen a different order of the predictors. Because of this *ordering*, linear regression would give different predictions when we choose a different encoding of the response $Y$. Thus, linear regression would perform poorly in such a situation.

The following plots visually illustrate why a linear regression in such a classification setting would fail and why a logistic regression is more suitable. In this situation we have *continuous* predictor variable $x$ and a *discrete* response variable $Y$. The <span style="color:#b9dde9">light blue</span> lines illustrate regression and logistic functions for both plots, respectively, while the <span style="color:#ffa500">orange</span> dots are the values that the qualitative response $Y$ takes over the given range of $x$.

[![fig3.5.png](images/stat-learning-series/fig3.5.png)](https://gist.github.com/alisiina/956bebcad42991d8a86ceca5279dd94d)


Classification methods in statistical learning, such as logistic regression, model the *probability* that $Y$ belongs to a particular category. Such methods are more suitable for categorization problems, where our task is to classify something into groups, even if the number of groups $K$ is large, based on a given set of predictors.

### How?

In a classification problem, we first set a probability *threshold* that a given observation belongs to a particular class. For example, if the threshold is set at $0.5$, then if the probability that a given observation $x$ belongs to $k$th class is above or lower than $0.5$, it will belong to one class or the other, respectively.

Logistic regression is a widely used method  that outputs values between $0$ and $1$ for probability that a predictor $P(X)$ belongs to a class $K$ of response $Y$ by using the [logistic function](https://en.wikipedia.org/wiki/Logistic_function#In_statistics_and_machine_learning),

$$
P(X) = \frac{e^{\beta_0 + \beta_1X}}{1 + e^{\beta_0 + \beta_1X}},
$$

where $e$ is Euler's number, and $\beta_1$ is the coefficient of our predictor $X$. This formula assumes $p=1$. If we had $p>1$, then the exponent of $e$ would simply be $\sum\beta_pX_p$. The unknown coefficients $\beta_p$ are estimated by a method known as *maximum likelihood*.

Linear discriminant analysis (LDA), on the other hand, uses a *density function* $f_k(x)$ to find the probability that an observation of $X$ belongs to the $k$th class where $K \geq 2$. LDA does this by using [Bayes' theorem](https://stats.stackexchange.com/questions/31366/linear-discriminant-analysis-and-bayes-rule-classification), a popular and widely used statistical approach.

K-nearest neighbors (KNN) is another important and simple classification method. KNN identifies K points in the data that are closest to an observation $x$ and estimates its conditional probability. It then applies  Bayes' classifier to classify $x$ to the group with the largest probability.[^1]

[![fig4](images/stat-learning-series/fig4.png)](https://gist.github.com/alisiina/0ed8f3b02dabc3219fc3c8f86b5fb9ad)

In Fig 2, a KNN classifier is fit to a portion of the iris flower data. The colored regions can be thought of as the response variable $Y$. Any new observation will be classified as belonging to the $k$th class depending on the region that it falls in.

Logistic regression and LDA are very similar parametric methods and often give similar results. The only different is the way both fit the model. KNN, on the other hand, is a very different and non-parametric method. Logistic regression and LDA are better for data that don't have too much overlap. KNN will outperform both these when the relationship is more complex and there is considerable overlap between the classes. However, KNN and other non-parametric approaches that use nearest neighbor classification will yield increasingly inaccurate results as $p$ gets large. This is a well-known phenomenon known as [curse of dimensionality](https://towardsdatascience.com/curse-of-dimensionality-2092410f3d27).[^2]

There are many other classification algorithms, such as support vector machines and additive models, but these three are the simplest to understand. Many of the other more complex classification algorithms use these methods under the hood in one way or another.

{% include socialsharing.html %}


* * *
##### FOOTNOTES


[^1]: All three of these approaches have underlying assumptions, e.g. LDA assumes $X$ comes from a normal distribution. They will not work if those assumptions are untrue.
[^2]: Dimensionality reduction methods are used mitigate such problems associated with high-dimensional datasets.
