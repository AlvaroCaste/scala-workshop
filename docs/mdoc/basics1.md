---
title: Scala Course
subtitle: Basics 1
author: 47 Deg
date: 2021-01-29
---

# Scala Basics

## Scala?

>- Object Oriented/Functional Language
>- Static typing ([wiki](https://en.wikipedia.org/wiki/Type_system#Static_type_checking))
>- Type inference
>- Functional programming capabilities

# Scala Basics

## Why object oriented?

>- Subtyping polymorphism ([wiki](https://en.wikipedia.org/wiki/Subtyping))
>- Everything's an object
>- Inheritance

# Scala Basics

## Why functional?

>- Functions are _first class citizens_
>- Pattern matching
>- Algebraic Data Types (ADT)
>- Higher kinded types
>- Immutability

# Object Oriented vs FP equivalent

::: columns
:::: {.column width=50% }
## pattern/principle

>- SRP
>- Open/Closed Principle
>- Dependency Inversion
>- Interface Segregation
>- Factory pattern
>- Strategy pattern
>- Decorator pattern
>- Visitor pattern

::::
:::: {.column width=50%}
## Fp equivalent

>- Functions
>- Functions
>- Functions
>- Functions
>- Functions
>- Functions
>- Functions
>- Functions

::::
:::

# Object Oriented vs FP equivalent

The problem is Functional patterns are different


# Static typing

::: columns
:::: {.column width=50%}

Static typing means that once a variable has a type, it cannot change.
The opposite of static typing is dynamic typing, as in Python, JS...

::::
:::: {.column width=50%}

**Python**

```python
a = "patata"
a = 3
```

::::
:::

# Static typing

::: columns
:::: {.column width=50%}

Static typing means that once a variable has a type, it cannot change.
The opposite of static typing is dynamic typing, as in Python, JS...

::::
:::: {.column width=50%}

**JS**

```javascript
var a = "patata"
a = 3
```

::::
:::

# Static typing

::: columns
:::: {.column width=50%}

Static typing means that once a variable has a type, it cannot change.
The opposite of static typing is dynamic typing, as in Python, JS...

::::
:::: {.column width=50%}

**Scala**

```scala mdoc:fail
var a = "patata"
a = 3
```

::::
:::

# Type inference

Normally, the compiler will try to guess what type our values have
even if we don't specify it.

```scala mdoc:silent
val hola = "hola"
val three = 3
```

This doesn't mean that if we don't specify a type the declaration
remains untyped, just that we let the compiler guesses it.

# Syntax

## Values - Immutable
```scala mdoc:fail
val immutable: Int = 1
immutable = 2
```

# Syntax

## Variables - Mutable
```scala mdoc
var mutable: Int = 1
mutable = 2
mutable
```

# Syntax

## Methods
```scala mdoc
def identity(i: Int): Int = i
identity(1)
```
- Blocks aren't needed for one-lines
- `return` isn't needed: last statement is returned

# Syntax

## Call by value
We already know this. When we pass a parameter to
a method “normally”
```scala mdoc
def callByValue(i: Int): Int = i
callByValue(1)
```

# Syntax

## Call by name
The parameter won't be evaluated until needed.
```scala mdoc
def callByName(i: => Int): Int = 1
def inf: Int = inf
callByName(inf)
```

# Syntax

## Call by name
The parameter won't be evaluated until needed.
```scala mdoc:fail
def callByName(i: => Int): Int = i
def inf: Int = inf
callByName(inf)
```

# Syntax

## Classes
```scala mdoc
class MyInt(val i: Int) {
  def sum(j: Int): Int = i + j
}
val myInt = new MyInt(1)
myInt.i
myInt.sum(1)
```
- free constructor
- `val` means `i` is public (otherwise it'd be private)

# Syntax

## Classes
```scala mdoc:fail
class MyIntPrivate private(val i: Int) {
  def sum(j: Int): Int = i + j
}
new MyIntPrivate(1)
```
- this is how make the constructor private


# Syntax

## Objects
```scala mdoc
object MyObject {
  def sum(i: Int, j: Int): Int = i + j
}
MyObject.sum(1,1)
```
- Objects are single instances of their own definitions


# Syntax

## Traits
```scala mdoc
trait MyTrait {
  val i: Int
  def sum(j: Int): Int = i + j
}
new MyTrait {
 val i: Int = 1
}.sum(1)
```
- They are like Java interfaces
- You can have default implementations

# Syntax

## Traits
```scala mdoc
class MyClass(j: Int) extends MyTrait {
  val i = j
}
new MyClass(1).sum(1)
```

# Syntax

## type
```scala mdoc
type Money = Int

def gimmeMoney(n: Money): Unit = println(n)

val treeEur: Money = 3

gimmeMoney(3)
```

# Syntax

Although, you can use also the _typed type_

## type
```scala mdoc:reset
type Money = Int

def gimmeMoney(n: Money): Unit = println(n)

val treeEur: Int = 3

gimmeMoney(3)
```

# Syntax

This is the basic. You can find more:  
https://docs.scala-lang.org/tour/basics.html

# Scala Type Hierarchy

![scala type hierarchy](img/scala_hierarchy.png)

- `Any` is the supertype of all types, also called the top type.
- `AnyVal` represents value types: `Int`, `Double`, etc.
- `AnyRef` represents reference types: e.g. `String` (everything else)
- `Null` is a subtype of all reference types (type, not `null` value)
- `Nothing` is a subtype of all types, also called the bottom type.


# The type system

```scala mdoc:fail
val a: Int = "hola!"
```

A type system checks the types of our program to be sure they make
sense.

# Higher order functions

Functions being first class citizens means that they can be used as
any other value in your program.  They have types, they can be
returned, and they can be taken as parameters.

## Functions are values

```scala mdoc:silent
val intToString: Int => String = { a =>
  a.toString
}
intToString(1)
```

# Higher order functions

Actually, this is possible because functions are traits
```scala mdoc
trait Function[-A, +B] {
  def apply(a: A): B
}
```
This is the same as `A => B`. It's syntactic sugar.

# Higher order functions

```scala mdoc:reset
val intToString: Function[Int, String] = 
  new Function[Int, String] {
    def apply(a: Int): String = a.toString
  }
intToString(1)
```

# Higher order functions

```scala mdoc:silent
def applyFunction(
  number: Int,
  fn: Int => Int
): Int =
  fn(number)

applyFunction(3, {x => x*3})
applyFunction(2, {x => x+2})
```

# Higher order functions

Functions being first class citizens means that they can be used as
any other value in your program.  They have types, they can be
returned, and they can be taken as parameters.

```scala mdoc
def genMultiplication(
  times: Int
): Int => Int = { x =>
  x * times
}

val times3: Int => Int = genMultiplication(3)
times3(3)
```

# Higher order functions

## Currification
```scala mdoc:reset
def genMultiplication(
  times: Int
)(x: Int): Int = 
  x * times

val times3: Int => Int = genMultiplication(3)
times3(3)
```

# Generics

Generics are a vital part of abstraction in functional programming. It
allows us to parametrize functions, classes, traits to make them work
for arbitrary types. Let's see an example:

# Generics

Generics are a vital part of abstraction in functional programming. It
allows us to parametrize functions, classes, traits to make them work
for arbitrary types. Let's see an example:

```scala mdoc:silent
def compose(
  f: String => Int,
  g: Int => Boolean
): String => Boolean = { str =>
  g(f(str))
}
```

# Generics

Generics are a vital part of abstraction in functional programming. It
allows us to parametrize functions, classes, traits to make them work
for arbitrary types. Let's see an example:

```scala mdoc:silent
val length: String => Int = _.length
val isEven: Int => Boolean = { i => i % 2 == 0 }

val lengthIsEven = compose(length, isEven)

lengthIsEven("1")
lengthIsEven("10")
lengthIsEven("100")
lengthIsEven("1000")
```

# Generics

Although, in the previous example, do we really care about the specific
types of the functions `f` & `g`? or we just need them to match?

We could use a **generic** implementation for `compose`!

```scala mdoc:silent
def composeGeneric[A, B, C](
  f: A => B,
  g: B => C
): A => C = { a =>
  g(f(a))
}
```

# Generics

And then, we could use `composeGeneric` the same way we used `compose`.

```scala mdoc:nest:silent
val length: String => Int = _.length
val isEven: Int => Boolean = { i => i % 2 == 0 }

val lengthIsEven = composeGeneric(length, isEven)

lengthIsEven("1")
lengthIsEven("10")
lengthIsEven("100")
lengthIsEven("1000")
```

# Algebraic data types

Algebraic data types are composite types made up from smaller ones.
There are two basic constructs for them:

- case classes
- sealed traits

# Case classes

case classes, also called **product types**, encode a grouping of other
fields that should **all** be present in all values.

```scala mdoc:silent
case class Package(
  length: Int,
  width: Int,
  height: Int,
  weight: Int)
```

In this example we know that all packages should have a length, a
width, a height, and a weight.

# Case classes

Case classes have other cool feature, copying.  Copying allows us to
create copies of the object with some fields changed.  This helps
to make programs immutable!

```scala mdoc:silent
// Notice that, for instantiating case classes,
// we don't use `new`.
val pack = Package(10, 15, 20, 3)
```

# Case classes

If we want to change one of the fields of a case class, we just need
to call copy and reassign a new value for the field:

```scala mdoc:silent
val pack2 = pack.copy(weight = 2)
```

# Sealed traits

Sealed traits _(**sealed** means we can only extend it on the file 
we've declared)_, also called **sum types**, encode a type that can
 be **one of** all the different invariants:

```scala mdoc:silent
sealed trait ResponseFromServer
case class OkResponse(
  json: String
) extends ResponseFromServer
case object FailureResponse extends ResponseFromServer
```

Here, we know that a value of the type `ResponseFromServer` can be
either a `OkResponse` or a `FailureResponse`.  
_(**sealed** means we can only extend it on the file we've declared)_

# Recap.

| Scala        | Other languages                       |
| ------------ | ------------------------------------- |
| `trait`      | `interface`(Java), `protocol` (Swift) |
| `class`      | `class`                               |
| `case class` | `POJO/JavaBean` (Java)                |
| `object`     | class containing only static stuff    |
| `val`        | immutable reference (`const` in JS)   |
| `var`        | mutable reference                     |
| `def`        | function/method                       |

# Exercise 1.1

Let's imagine a simple event based application.  We want to define
some events that we can handle:

- A user logs in
- A customer adds an item to the basket
- A user starts payment process
- Payment goes through correctly
- Payment process fails with timeout
- Payment process fails because of Insufficient funds

# solution

One possible solution... not **the** one.

```scala mdoc:silent
import java.util.UUID

sealed trait Event
case class UserLogIn(userId: UUID) extends Event
case class AddItemToBasket(userId: UUID, itemId: UUID) extends Event
case class UserIntentPay(userId: UUID) extends Event
case class PaymentCorrect(userId: UUID, paymentReceipt: String) extends Event

sealed trait PaymentFailure extends Event
case class TimeoutFailure(userId: UUID, intentId: UUID) extends PaymentFailure
case class InsufficientFundsFailure(userId: UUID, intentId: UUID) extends PaymentFailure
```

# Exercise 1.2

We know that all events for this system will have several fields:
- Event ID
- User ID


Refactor your previous exercise to add those.

# solution

```scala mdoc:nest:silent
sealed trait Event {
  def id: UUID
  def userId: UUID
}

case class UserLogIn(id: UUID, userId: UUID) extends Event
case class AddItemToBasket(id: UUID, userId: UUID, itemId: UUID) extends Event
case class UserIntentPay(id: UUID, userId: UUID) extends Event
case class PaymentCorrect(id: UUID, userId: UUID, paymentReceipt: String) extends Event

sealed trait PaymentFailure extends Event
case class TimeoutFailure(id: UUID, userId: UUID, intentId: UUID) extends PaymentFailure
case class InsufficientFundsFailure(id: UUID, userId: UUID, intentId: UUID) extends PaymentFailure
```
