package test;

public class ValueTransferTest {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        swap(a, b);
        System.out.println("a=" + a + ", b=" + b);

        Person p1 = new Person("Alice");
        Person p2 = new Person("Bob");
        swap(p1, p2);
        System.out.println("p1.name=" + p1.getName() + ", p2.name=" + p2.getName());

        changeName(p1);
        System.out.println("p1.name=" + p1.getName());
    }

    public static void swap(int x, int y) {
        int temp = x;
        x = y;
        y = temp;
    }

    public static void swap(Person x, Person y) {
        Person temp = x;
        x = y;
        y = temp;
    }

    public static void changeName(Person p) {
        p.setName("Chris");
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
