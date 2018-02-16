class Animal {

   // Properties of the class...
   public int     numberOfLegs;
   public boolean hasWings;

   // Constructor of the class...
   public Animal() {
      numberOfLegs = 4;
      hasWings = false;
   }

   // Methods of the class...
   public void talk() {
      System.out.println("Hello");
   }
}

class Bird extends Animal {

   // Properties of the class...
   public boolean canFly;

   // Constructor of the class...
   public Bird() {
      numberOfLegs = 2;
      hasWings = true;
      canFly = true;
   }

   // Methods of the class...
   public void fly() {
      System.out.println("flap flap");
   }
}

class Eagle extends Bird {

   // Properties of the class...
   private int numberOfKills;

   // Constructor of the class...
   Eagle() {
      numberOfKills = 0;
   }

   // Methods of the class...
   public void attack() {
      numberOfKills++;
   }
}

class AnimalTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      Animal a = new Animal();
      System.out.println(a.numberOfLegs);
      System.out.println(a.hasWings);
      a.talk();
      // a.fly(); won't work because fly is a method of the subclass bird (not the superclass animal)

      Bird b = new Bird();
      System.out.println(b.numberOfLegs);
      System.out.println(b.hasWings);
      System.out.println(b.canFly);
      // System.out.println(b.numberOfKills); won't work because it's a method of the subclass eagle (not the superclass bird)
      b.talk();
      // b.attack(); won't work because it's a method of the subclass eagle (not the superclass bird)

      Eagle e = new Eagle();
      // System.out.println(e.numberOfKills); won't work because numberOfKills is private so you can't access it outside the class
      System.out.println(e.numberOfLegs);
      System.out.println(e.hasWings);
      e.talk();
      e.attack();

      // Q 1d: Eagle has no constructor, so it uses the Bird construcor (which it inherenced)
      // and in that constructor the numberOfLegs is set to 2.

      // Q 1e
      a = b;
      a.talk();
      // a.fly(); a is still an animal class object so it has no fly method

      // Q 1f
      // b = a; b is an object of the class bird and a of animal so this won't work
      b.talk();
      b.fly();
   }
}
