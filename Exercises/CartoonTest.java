class FredFlintstone {

   // Properties of the class...
   static String name            = "Fred Flintstone";
   static String favouriteColour = "blue";
   static int    favouriteNumber = 42;

   // Methods of the class...
   static void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}

class WilmaFlintstone {

   // Properties of the class...
   static String name            = "Wilma Flintstone";
   static String favouriteColour = "red";
   static int    favouriteNumber = 63;

   // Methods of the class...
   static void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}

class BarneyRubble {

   // Properties of the class...
   static String name            = "Barney Rubble";
   static String favouriteColour = "yellow";
   static int    favouriteNumber = 2;

   // Methods of the class...
   static void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}

class CartoonTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      // Q2
      // FredFlintstone.displayMe();

      // Q3
      // System.out.println("My (Barney Rubble) favourite colour is: " + BarneyRubble.favouriteColour);

      // Q4
      CartoonCharacter f = new CartoonCharacter("Fred Flinstone", "blue", 42);
      CartoonCharacter w = new CartoonCharacter("Wilma Flinstone", "red", 63);
      CartoonCharacter b = new CartoonCharacter("Barney Rubble", "yellow", 2);

      // Q5
      f.displayMe();
      w.displayMe();
      b.displayMe();

      // Q6
      System.out.println("My (Barney Rubble) favourite colour is: " + b.favouriteColour);

      // Q7: Count must be static, because otherwise objectName.count will always
      // be one and we still don't know the number of cartoon characters created
      System.out.println("Number of objects/characters: " + CartoonCharacter.count);

      // Q8
      System.out.println("PI property: " + Math.PI);
   }
}

