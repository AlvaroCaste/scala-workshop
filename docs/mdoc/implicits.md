---
title: Scala Course
subtitle: Implicits
author: 47 Deg
date: 2021-02-15
---

# Implicits

**Implicits** provide us the ability to **pass arguments** to function
**without provide** them **explicitly**.

The simplest way for understanding them is to see a simple example:

```scala mdoc
implicit val name: String = "John"

def printImplicitName(implicit name: String): Unit =
  println("Hello " + name)

printImplicitName
```

# Implicits

Any value (vals, functions, objects, etc) can be declared to be implicit by
using them, you guessed it, **implicit** keyword. 

```scala mdoc:silent
implicit val myImplicitInt = 1
implicit def myImplicitFunction(s: String) = s
implicit class MyImplicitClass(i: Int)
```

# Implicits

By itself, **implicit** keyword doesn't change the behavior of the value, so
above values can be used as usual.

```scala mdoc
myImplicitInt + 1
myImplicitFunction("hi")
new MyImplicitClass(1)
```

# Types of implicits

- Implicit conversions
- Type expansion
- Type class pattern

# Implicit conversions

One of the most used features of implicits are **implicit conversions**.
Using them, we'll be able to use values of a type as values of other
type **if there's an implicit conversion between them**.

We'll declare implicit conversions as `implicit def`.

# Implicit conversions

```scala mdoc
import java.util.UUID

implicit def uuidAsString(uuid: UUID): String =
  uuid.toString

val id: UUID = UUID.randomUUID

def printString(str: String): Unit =
  println(str)

printString(id)
```

# Type expansion

We'll use **type expansion** when we want to **add new methods to
types we don't control**.

As an example... let's copy Ruby's `n.times do...` pattern.

```scala mdoc:fail
3.times {
  println("it worked!")
}
```

# Type expansion

We can add new methods to a type we don't control using `implicit
classes`:

```scala mdoc
implicit class IntTimes(val x: Int) {
  def times(action: => Unit): Unit =
    (1 to x).foreach(_ => action)
}

3.times {
  println("it worked!")
}
```

# Type Class Pattern

We can go further, and not only expanding types but abstract types.

We need to see first what a **Typeclass** is, though.

# Resolution order

When finding an implicit parameter, Scala will look for candidates for
the given type in:

- Current scope
- Imports
- Companion objects
- Parameters arguments
- Type parameters
- ...

Check this [reference][] in the Scala docs for more details.

[reference]: https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html

# Resolution order

## Companion objects

```scala mdoc
class MyInt(val n: Int)
object MyInt {
  implicit def toStr(mi: MyInt) = s"MyInt: ${mi.n}"
}
val myInt = new MyInt(1)
val str: String = myInt
```

# Resolution order

## Companion objects

```scala mdoc:reset
class MyInt(val n: Int)
object MyInt {
  implicit def toStr(mi: MyInt) = s"MyInt: ${mi.n}"
}
val myInt = new MyInt(1)
implicit def toNewStr(mi: MyInt) = s"MyNewInt: ${mi.n}"
val str: String = myInt
```

# Resolving ambiguity

- If there are more than one matching implicits, the most specific one is chosen
- If there is no unique most specific candidate, a compile error is reported

# Resolving ambiguity

```scala
import scala.concurrent.Future
import scala.concurrent.{ExecutionContext => EC}
import scala.concurrent.ExecutionContext.Implicits.global
// ^ we import an EC which is implicit
def myFuture(s: String)(implicit ec: EC): Future[String] = 
  Future(s) // Future.apply receives an implicit EC
myFuture("hi") // ???







```

# Resolving ambiguity

```scala mdoc:fail
import scala.concurrent.Future
import scala.concurrent.{ExecutionContext => EC}
import scala.concurrent.ExecutionContext.Implicits.global
// ^ we import an EC which is implicit
def myFuture(s: String)(implicit ec: EC): Future[String] = 
  Future(s) // Future.apply receives an implicit EC
myFuture("hi")
```

# Resolving ambiguity

```scala mdoc:reset
import scala.concurrent.Future
import scala.concurrent.{ExecutionContext => EC}
import scala.concurrent.ExecutionContext.Implicits.global

def myFuture(s: String): Future[String] =
  Future(s) 
  
myFuture("hi")
```

# Resolving ambiguity

```scala mdoc:reset
import scala.concurrent.Future
import scala.concurrent.{ExecutionContext => EC}

implicit val ec: EC = EC.global

def myFuture(s: String)(implicit ec: EC): Future[String] =
  Future(s) 
  
myFuture("hi")
```

# Resolving ambiguity

```scala mdoc:reset
import scala.concurrent.Future
import scala.concurrent.{ExecutionContext => EC}

def myFuture(s: String)(implicit ec: EC): Future[String] =
  Future(s) 

import scala.concurrent.ExecutionContext.Implicits.global
myFuture("hi")
```

# Implicits

This is an overview.  

Check this [reference][] in the Scala docs for more details.

[reference]: https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html

