---
title: Scala Course
subtitle: Implicits
author: 47 Deg
date: 2021-02-15
---

# Implicits

Implicits provide us the ability to pass arguments to function in an
_implicit_ way.

The simplest way for understanding them is to see a simple example:

```scala mdoc
implicit val name: String = "John"

def printImplicitName(implicit name: String): Unit =
  println("Hello " + name)

printImplicitName
```

# Implicits

## But, how does it work?

When finding an implicit parameter, Scala will look for candidates for
the given type in:

>- The lexical scope, as any other variable
>- The implicit scope
>- The companion object of the datatype
>- The companion object of the typeclass
>- In the imports

. . .

[reference][] in the Scala docs.

[reference]: https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html


# Implicit conversions

One of the most used features of implicits are implicit conversions.
Using implicit conversions, we'll be able to use values of a type as
values of other type **if there's an implicit conversion between
them**.

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

# Datatype expansion

We'll use datatype expansion when we want to add new methods to
datatypes we don't control.

As an example... let's copy Ruby's `n.times do...` pattern.

```scala mdoc:fail
5.times {
  println("it worked!")
}
```

# Datatype expansion

We can add new methods to a datatype we don't control using `implicit
classes`:

```scala mdoc
implicit class IntTimes(val x: Int) {
  def times(action: => Unit): Unit = {
    (1 to x).foreach(_ => action)
  }
}
```

# Datatype expansion

```scala mdoc
5.times {
  println("it worked!")
}
```

# Typeclasses

## Typeclass declaration

```scala mdoc
trait ToString[A] {
  def toString(a: A): String
}

object ToString {
  def apply[A](
    implicit TS: ToString[A]
  ): ToString[A] = TS
}
```

# Typeclasses

## Instance declaration

```scala mdoc

implicit val toStringInt: ToString[Int] =
  new ToString[Int] {
    def toString(a: Int): String =
      a.toString
  }

// Scala can also use Single Abstract Method syntax,
// as Java
implicit val toStringFloat: ToString[Float] =
  _.toString
```

# Typeclasses

## Usage

```scala mdoc
def print[A: ToString](a: A): Unit =
  println(ToString[A].toString(a))

print(1)
print(2f)
```

```scala mdoc:fail
print(true)
```
