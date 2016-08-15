test:
    javac src\main\java\branching\dobuleb\ClassGen.java -cp libs/asm-5.0.4.jar -d out
    java -cp libs/asm-5.0.4.jar;./out branching.dobuleb.ClassGen out/Math.class

    javap -c -verbose out\Math.class
    javap -c -verbose out\branching\dobuleb\ClassGen
    javap -c -verbose Main
