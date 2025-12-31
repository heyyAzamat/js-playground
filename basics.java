public class basics {
    public static void sayHello() {
        System.out.println("Hello!");
    }

    public static void main(String[] args) {
        sayHello();

        int age = 16;
        double pi = 3.14;
        char letter = 'A';
        boolean isJavaFun = true;

        System.out.println(age);
        System.out.println(pi);
        System.out.println(letter);
        System.out.println(isJavaFun);

        int x = 10;

        if (x > 5) {
            System.out.println("x more than 5");
        } else {
            System.out.println("x less than or 5");
        }

        int i = 0;
        while (i < 5) {
            System.out.println(i);
            i++;
        }
        // for (int i = 0; i < 5; i++) {
        //     System.out.println(i);
        // }
    }
}
