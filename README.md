# Bearlyb
...pronounced "bear-lib" is a in progress game-making library for scala. The goal is to be as simple to get started with as possible, and at the same time have enough performance to make bigger games, following the scala philosophy!

## Inspirations
Pygame is the main inspiration for this engine, but there are some things that *will* be different about this library. For it would be nice to have a function that provides consistant updates to have a stable framerate as it is non-trivial to do this yourself.

It is also very inspired by the amazing professor [Björn Regnell](https://github.com/bjornregnell) and his amazing [introprog-scalalib](https://github.com/lunduniversity/introprog-scalalib) which includes an easy-to-get-started-with graphics-api for scala. The nice thing about that library is that the internals are **very** simple. A beginner scala-programmer could probably contribute to that repository without much hassle.

This library will inevitably have more complicated internals because of lwjgl, and it's associated libraries for rendering, input, audio, and more. The goal is to separate the complex stuff from the easy stuff by simply putting it into different modules (folders). The complicated modules will be used in less complicated ones to achieve a beginner friendly codebase at the level of the library that has methods, types, and other stuff that bearlyb exposes to anyone who uses the library. The library will basically be commplicated when it needs to, but hide that complicated code in separate modules which will hopefully not need to change super-often. You should be able to add lots of functionality by simply modifying the API-level code.

## So how do i get started?
Well right now nothing really exists hehe.

But if you want to help you need to install the latest stable version of [Scala](https://www.scala-lang.org/). You can probably install it using a package manager of your choice, but if you are unsure of where to start I recommend using [coursier](https://get-coursier.io/).

Alright! Once that is done you can go ahead and try cloning the project and running an example.
```bash
git clone https://github.com/lego-eden/bearlyb.git
cd bearlyb
./mill bearlyb
```

You should see a window pop up!