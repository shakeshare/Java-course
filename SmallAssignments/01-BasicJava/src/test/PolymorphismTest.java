package test;

class Animal {
    public String name = "Animal";
    
    public void eat() {
        System.out.println("Animal eat");
    }
    
    public static void sleep() {
        System.out.println("Animal sleep");
    }
}

class Dog extends Animal {
    public String name = "Dog";
    
    @Override
    public void eat() {
        System.out.println("Dog eat");
    }
    
    public static void sleep() {
        System.out.println("Dog sleep");
    }
    
    public void bark() {
        System.out.println("Dog bark");
    }
}

public class PolymorphismTest {
    public static void main(String[] args) {
        Animal a = new Dog();
        
        System.out.println(a.name);
        a.eat();
        a.sleep();
        // a.bark();  // 这行编译错误，已注释
    }
}