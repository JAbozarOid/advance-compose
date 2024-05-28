package com.example.advancecompose.feature.interview.kotlinQ.inner;

public class TestInnerClass {

    private String fName;
    private String lName;

    public TestInnerClass(String firstname, String lastName) {
        this.fName = firstname;
        this.lName = lastName;
        System.out.println("the firstname and lastname is" + fName + lName);
    }

    public class TestInner {

        void t() {
            fName = "";
        }

    }

    public static void main(String[] args) {
        TestInnerClass testInnerClass = new TestInnerClass("abozar", "raghibdoust");

    }

}




