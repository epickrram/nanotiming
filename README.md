# nanotiming

Building
========

./gradlew bundleJar

Running
=======

java -jar ./nanotiming-all-0.0.1.jar 4

Output
======

Measuring time to invoke System.nanoTime() with 4 contending threads.
Available logical CPUs on this machine: 32
Current clocksource is: tsc
15:14:58.751 avg. time between calls to System.nanoTime() 25ns

Context
=======

[http://epickrram.blogspot.co.uk/2016/01/timing-is-everything.html] (Timing is everything)

