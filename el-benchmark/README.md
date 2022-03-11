# README

## install jar to maven local repo

```bash
cd ./jars
mvn install:install-file -DgroupId=org.wltea -DartifactId=ik-expression -Dversion=2.1.2 -Dpackaging=jar  -Dfile=./IKExpression2.1.2.jar
```

## execute in idea

execute main functions in :

1. AviatorBenchMark 
2. GroovyBenchMark
3. IkExpBenchMark.

## execute in terminal

To run all the benchmarks:

```bash
mvn clean package
java -jar target/el-benchmarks.jar
```

To run individual benchmarks:

```bash
mvn clean package
java -cp target/el-benchmarks.jar info.victorchu.elbenchmark.aviator.AviatorBenchMark
```