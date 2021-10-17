---
layout: post
title: Other classification methods
date: 2019-03-05
author: Ali Sina
summary: Classification methods such as decision trees, random forests, and support vector machines are powerful methods used by industry professionals to solve a wide range of real-world problems. For example, Microsoft's Kinect gaming platform uses decision trees under the hood for real-time human pose recognition.
mathjax: true
tags:
  [
    statistics,
    machine_learning,
    statistical_learning,
    nonlinear,
    trees,
    decision_trees,
    random_forests,
    bagging,
    boosting,
    support_vector_machines,
    unsupervised_learning,
  ]
---

> This is the sixth in a [series](https://alisiina.github.io/2019/01/28/statistical-learning-series.html) of posts that I'm doing on statistical learning. All the material is based on [An Introduction to Statistical Learning](http://www-bcf.usc.edu/~gareth/ISL/) book which was taught by the authors and Stanford University professors Trevor Hastie and Rob Tibshirani. The aim is to condense the concepts taught in the course and the material in the book to a series of under-10-minute reads.

### Decision trees

A decision tree is built by dividing the feature space $X_1, X_2,...,X_p$ into distinct and non-overlapping regions. The data is then split at certain cut points so as to minimize the RSS. The resulting graphical representation of this division into regions looks very much like an (upside down) tree. Figure 9 shows a decision tree for the [`Titanic`](https://vincentarelbundock.github.io/Rdatasets/doc/carData/TitanicSurvival.html) dataset. Each point at which the data splits in called a _node_ and the last point (`survived` and `died` in Fig. 9) is called a _leaf_. In a more accurate decision tree, each leaf would also have probability estimates attached to it as well.

![fig9](images/stat-learning-series/fig9.png)

Tree-diagrams like this are quite self-explanatory, which is why tree-based methods such as decision trees are commonly used by analysts when the interpretation of the algorithm is important in the analysis or prediction process.

Although decision trees can be used for both classification and regression problems, they are primarily used in classification settings where they have been known to perform quite well. This is because decisions trees are said to mimic human decision making more closely than other classification algorithms.

However, decision trees do not have good prediction accuracy on unseen data. Their performance can be improved by a method known as **bagging** which takes [bootstrapped](https://alisiina.github.io/2019/02/13/resampling-methods.html) samples $B$ of the data, trains the model on $b$th sample and, in a regression setting, averages the predictions given by:

$$\hat{f}_{bag}(x) = \frac{1}{B} \sum_{b=1}^B \hat{f}^{\ast b}(x)$$

In a classification setting, however, a _majority vote_ is taken to estimate accuracy, which is simply the most commonly occurring class in $B$ predictions.

### Random forests

Suppose in a dataset, one of predictors has a strong relationship with the response variable. In such a situation, our decision trees from each bootstrapped sample will look the same and, therefore, averaging the predictions through bagging will not lead to a great reduction in the prediction error.

Random forests overcome this problem by choosing a random sample of $m$ predictors from the full set of $p$ predictors each time a split is made. This is equivalent to saying that random forests _decorrelate_ the trees, which makes the resulting trees less variable and, thus, more reliable.

The prediction accuracy of random forests, and decision trees as well, can be improved with a method known as **boosting**, although it can be used with pretty much any other learning algorithm. Boosting works similarly to bagging. The difference is that boosting builds decision trees by slightly modifying the original dataset with information from previously grown trees. So, unlike bagging, boosting is _sequential_.

In a regression setting, boosting fits a decision tree to the current residual instead of the response $Y$. This decision tree is then added to the fitted function in order to update the residuals. Boosting for classification works in a slightly similar but far more complex way. Much of the behavior of the boosting is controlled by three tuning parameters.[^1]

### Support vector machines

Separable data can be classified by separating the different $k$ classes by fitting a decision boundary between them. This is known as a _margin classifier_. But what if we have classes where no such separable boundary exists? In such a case, we use _support vector classifier_, which is simply the idea of maximal margin extended but now with a _soft margin_, meaning that some of observations are allowed to lie inside the margin.

Support vector machines (SVM), an extension of the support vector classifier, separate classes by fitting a _hyperplane_ through the different $k$ classes.[^2] In a multi-dimensional setting, there are an infinite number of possible hyperplanes to fit between any given number of classes. To decide on a suitable margin, we use the _maximal margin classifier_ which computes the perpendicular distance from each observation to the separating hyperplane while maximizing the _margin_ of the plane between all points of each class $k$.

SVMs are a powerful machine learning tool and used often in both academia and industry. The above two paragraphs do not do justice to the power of SVMs, primarily because the details of how they work cannot be properly explained without advanced mathematics training. For interested readers, the [book](https://www-bcf.usc.edu/~gareth/ISL/) dedicates an entire chapter to support vectors.

{% include socialsharing.html %}

---

##### FOOTNOTES

[^1]: The number of trees $B$, a shrinkage parameter $\lambda$, and the number $d$ of splits in each tree. Because of its complexity, boosting for classification is omitted in the book _[Introduction to Statistical Learning](https://www-bcf.usc.edu/~gareth/ISL/)_. You can consult the more advanced _[Elements of Statistical Learning](https://web.stanford.edu/~hastie/ElemStatLearn/)_ by the same authors for the technical details.
[^2]: A [hyperplane](https://en.wikipedia.org/wiki/Hyperplane) is a _subspace_ with dimension $p-1$ for a dataset with $p$ predictors. In a three-dimensional setting, this is simply a [two-dimensional plane](https://medium.com/data-science-group-iitr/support-vector-machines-svm-unraveled-e0e7e3ccd49b) separating the classes.
