# Scala workshop

This is a course crafted for OO developers that want to learn Scala &
functional programming.

**Table of Contents**

- Scala workshop
    - Sections
        - Basics 1
        - Basics 2
        - Abstraction
        - Typeclasses
        - Implicits
        - Tagless Final
    - exercises
        - tags
        - compiling
    - building


## Sections

This course has the following sections, delivered during two/three days
normally.

### Basics 1

Learn the basics of the language, the most interesting features and
how to deal with data using ADTs.

### Basics 2

Learn how to apply pattern matching and recursion to solve problems on
algebraic data types.

### Abstraction

In this section we'll start to dig deeper in the functional
programming aspects of Scala, and to learn new functional
abstractions.

### Typeclasses

In this section we'll learn how to implement _Ad hoc polymorphism_ in
Scala using typeclasses, and all the benefits it provides.

## Implicits

In this section we'll see what implicits are and how work.

## Tagless Final

In this section we'll learn what tagless final is and how to use it.

## exercises

For the exercises, go to the `exercises` folder & select the exercise
you want to do.

### tags

This project is built to follow a story of exercises and their solutions.

### compiling

You can compile the exercises with one of:

```sbt
$ ./sbt exercise1/compile
$ ./sbt exercise2/compile
$ ./sbt exercise3/compile
$ ./sbt exercise4/compile
$ ./sbt exercise5/compile
$ ./sbt taglessfinal/compile
```

## building

To build the slides for the course you'll need `pandoc`. Get it from
your package manager.

Once you have it:

```sh
$ make all
```

This will generate PDFs with the presentations in the `/slides`
folder.