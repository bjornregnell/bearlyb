# Bearlyb
...pronounced "bear-lib" is a in progress game-making library for scala. The goal is to be as simple to get started with as possible, and at the same time have enough performance to make bigger games, following the scala philosophy!

## Inspirations
Pygame is the main inspiration for this engine, but there are some things that *will* be different about this library. For it would be nice to have a function that provides consistant updates to have a stable framerate as it is non-trivial to do this yourself.

It is also very inspired by the amazing professor [Björn Regnell](https://github.com/bjornregnell) and his amazing [introprog-scalalib](https://github.com/lunduniversity/introprog-scalalib) which includes an easy-to-get-started-with graphics-api for scala. The nice thing about that library is that the internals are **very** simple. A beginner scala-programmer could probably contribute to that repository without much hassle.

This library will inevitably have more complicated internals because of [lwjgl](https://www.lwjgl.org/), and it's associated libraries for rendering, input, audio, and more. The goal is to separate the complex stuff from the easy stuff by simply putting it into different modules (folders). 

The complicated modules will be used in less complicated ones to achieve a beginner friendly codebase at the level of the library that has methods, types, and other stuff that bearlyb exposes to anyone who uses the library. The library will basically be complicated when it needs to, but hide that complicated code in separate modules which will hopefully not need to change super-often. You should be able to add lots of functionality by simply modifying the API-level code.

## Getting started

### Using directives and import

* You need the latest stable version of [Scala](https://www.scala-lang.org/). You can probably install it using a package manager of your choice, but if you are unsure of where to start I recommend using [coursier](https://get-coursier.io/).

```scala
//> using scala 3.7.3
//> using dep io.github.lego-eden::bearlyb::0.1.2 
```

* Check latest version of bearlyb in Relases.

### Demo

```scala
//> using scala 3.7.3
//> using dep io.github.lego-eden::bearlyb::0.1.2 

import bearlyb.*

@main
def BearlybDemo(): Unit =
  bearlyb.init(Init.Video)

  val (width, height, moveDelta, randomDelta) = (800, 600, 10, 2)
  val (pink, purple) = (242, 128, 161, 0) -> (153, 102, 204, 0)

  val (window, renderer) = bearlyb
    .createWindowAndRenderer("hello bearlyb!", width, height)

  var running = true
  val step = 2
  var x = width / 2
  var y = height / 2
  var count = 1
  val measureTimeEvery = 2000
  println("HELLO bearlyb! Press Q to quit. Press Arrow Keys to move rectangle.")
  var t0 = System.nanoTime()
  while running do
    for e <- Event.pollEvents() do e match
      case Event.Quit(_) | Event.Key.Down(key = Keycode.Q) =>
        println("quitting")
        running = false
      case Event.Key.Down(key = Keycode.Right) => x += moveDelta
      case Event.Key.Down(key = Keycode.Left)  => x -= moveDelta
      case Event.Key.Down(key = Keycode.Up)    => y -= moveDelta
      case Event.Key.Down(key = Keycode.Down)  => y += moveDelta
      case other => println(other)
    end for
    x += util.Random.nextInt(2 * randomDelta + 1) - randomDelta
    y += util.Random.nextInt(2 * randomDelta + 1) - randomDelta
    x = math.floorMod(x, width)
    y = math.floorMod(y, height)
    renderer.drawColor = pink
    renderer.clear()
    renderer.drawColor = purple
    renderer.fillRect(Rect(x, y, width / 8, width / 8))
    renderer.present()
    if (count % measureTimeEvery) == 0 then 
      val time = System.nanoTime() - t0
      val fps = 1e9 / time
      t0 = System.nanoTime()
      println(s"Time between frames: ${time / 1e9} ns")
      println(s"Frames per second  : ${(fps + 10).round / 10.0} fps")
    end if
    count += 1
  end while
  bearlyb.quit()
end BearlybDemo
```


## How to build and contribute

* You need the latest stable version of [Scala](https://www.scala-lang.org/). You can probably install it using a package manager of your choice, but if you are unsure of where to start I recommend using [coursier](https://get-coursier.io/).

* You need [Mill](https://mill-build.org) to build this repo.

* Clone the project and build it:
 
```bash
git clone https://github.com/lego-eden/bearlyb.git
cd bearlyb
./mill bearlyb
```

* Contributions are welcome! First open and issue and we can discuss your contributions there.
