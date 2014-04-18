Welcome to Pattern Matching!
============================

This repository holds the exercises (and supporting scripts/tests) for the LambdaConf 2014 session titled
_Pattern Matching in Depth_. This repository is structured with the following important files:

* sbt - This is the script that lets you build the project and run tests. If you just type ./sbt from the command
  prompt you'll load the SBT tool and get a prompt that looks like `sbt>`. From there you can issue commands like
  `clean`, `compile`, and `test`. The latter two are the most important ones while you work toward your solution. If
  you use the command `~test`, SBT will watch for changes in your source code files and re-run compilation and tests
  any time your files change.
* src/main/scala - This is the directory where all of the exercise source code lives. The suggested order of the
  exercises is:
    * FuzzyFizzBuzzer.scala - an introduction to basic pattern matching
    * RPNCalc.scala - a more involved pattern matching exercise that involves both case class patterns as well as List
      patterns
    * IPExtraction.scala - An exercise for customer extractors, including both unapply and unapplySeq
* src/test/scala - This is the directory where all of the exercise test code lives. Each exercise has a corresponding
  Spec written, and while you don't need to do anything with this code (other than run `test` to verify it), feel
  free to peruse.
* build.sbt - The build definition file. Again, while not required, feel free to take a look at it.