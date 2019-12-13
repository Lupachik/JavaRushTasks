package com.javarush.task.task35.task3501;

public class GenericStatic {
    /*
    В треугольных скобках при объявлении метода мы перечисляем типы, которые будем использовать.
     <T> говорит о том, что все T в методе будут одного типа. При этом,
      если <T> было объявлено до этого на уровне класса, то мы перекрываем этот тип другим, локальным.
      Может быть конструкция "public static <T> void methodName" или "public <T, R> T methodName".
     */

    public static <T> T someStaticMethod(T genericObject) {
        System.out.println(genericObject);
        return genericObject;
    }
}
