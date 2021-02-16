---
title: Scala Course
subtitle: Tagless Final
author: 47 Deg
date: 2021-02-08
---

# Tagless Final

In recent years, there has been a growing interest in modelling
software in Scala using _Tagless Final_.

**Tagless Final** is a technique that allows us to **define** the 
**general structure** of our code to be executed, such that we can 
**configure different implementations** to inject effects such as 
_Optionality_, _Asynchronicity_, _Parallelism_, _Non-Determinism_, 
_Error Handling_ depending on our needs.

# Tagless Final

Let's see how can we declare our TF Algebras!
