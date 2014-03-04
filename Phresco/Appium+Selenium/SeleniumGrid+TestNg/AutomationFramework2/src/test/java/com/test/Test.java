package com.test;

@MyAnnotation(author="Azim",date="22/10/2011,23/10/2011")
public class Test
{
    // Applying annotation to the method
    @MyAnnotation(author="Azim",date="22/10/2011")
    public static void testMethod()
    {
        System.out.println("Welcome to Java ");
        System.out.println("This is an example of Annotations");
    }
    public static void main(String args[])
    {
        testMethod();
//        showAnnotations();
    }
}