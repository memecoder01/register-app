import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

class User {
    public  String email;
    public String password;
    public String fullName;
    public Integer age;
    public String gender;

    public User(String email, String password, String fullName, Integer age, String gender) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}

public class UserRegistration {
    private static final List<User> users = new ArrayList<>();
//main strukturasi
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n=== Boshlash ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Tanlang: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    System.out.println("Chiqish");
                    scanner.close();
                    return;
                default:
                    System.out.println("Xato tip");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Email kiriting ");
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Xato tip");
            return;
        }

        System.out.print("Pasword kiriting: ");
        String password = scanner.nextLine();
        if (!isValidPassword(password)) {
            System.out.println("Xato pasword");
            return;
        }

        System.out.print("To'liq isim: ");
        String fullName = scanner.nextLine();

        System.out.print("Yosh: ");
        Integer age;
        try {
            age = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Yosh xato tipda.");
            return;
        }

        System.out.print("Rol kiriting: (Male/Female): ");
        String gender = scanner.nextLine();

        if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
            System.out.println("Xato rol");
            return;
        }
            System.out.println("Yosh: " + gender);

        users.add(new User(email, password, fullName, age, gender));
        System.out.println("Muvafaqqiyatli");
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Parol: ");
        String password = scanner.nextLine();
//        String fullName = scanner.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Xush kelibsiz! " + user.getFullName() + "!");
                return;
            }
        }

        System.out.println("Xato email yo parol");
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    private static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }
}
