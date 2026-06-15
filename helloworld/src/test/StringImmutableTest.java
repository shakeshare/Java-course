package test;

public class StringImmutableTest {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = s1;
        s1 = s1 + " world";

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);

        changeString(s1);
        System.out.println("s1 after change = " + s1);

        String s3 = "hello";
        String s4 = "hello";
        System.out.println("s3 == s4 = " + (s3 == s4));

        String s5 = new String("hello");
        System.out.println("s3 == s5 = " + (s3 == s5));
        System.out.println("s3.equals(s5) = " + s3.equals(s5));

        String s6 = s3.substring(0, 2);
        System.out.println("s6 = " + s6);
    }

    public static void changeString(String str) {
        str = str + "!!!";
    }
}
