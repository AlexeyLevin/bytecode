test:
    javap -c -verbose Main
    javac src\main\java\branching\dobuleb\ClassGen.java -cp libs/asm-5.0.4.jar -d out
    javap -c -verbose out\branching\dobuleb\ClassGen