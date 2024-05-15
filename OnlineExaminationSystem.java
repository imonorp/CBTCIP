import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


class Query {

    String text;
    List<String> choices;
    int rightAnswer;

    public Query(String text, List<String> choices, int rightAnswer) {
        this.text = text;
        this.choices = choices;
        this.rightAnswer = rightAnswer;
    }
}

class Participant {

    String user;
    String pass;

    public Participant(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
}

public class OnlineExamSystem {

    static List<Query> queries = new ArrayList<>();
    static List<Participant> participants = new ArrayList<>();
    static Participant activeUser;
    static boolean examSession = false;

    public static void main(String[] args) {

        loadQueries();

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("\nChoose an option: ");
            int option = input.nextInt();
            input.nextLine(); 

            switch (option) {

                case 1:
                    signIn(input);
                    break;

                case 2:
                    signUp(input);
                    break;

                case 3:
                    System.out.println("Shutting down the system.");
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose a correct option!");
            }
        }
    }

    public static void loadQueries() {

        List<String> choices1 = new ArrayList<>();
        choices1.add("1991");
        choices1.add("1995");
        choices1.add("2000");
        choices1.add("2005");

        List<String> choices2 = new ArrayList<>();
        choices2.add("Sun Microsystems");
        choices2.add("IBM");
        choices2.add("Microsoft");
        choices2.add("Google");

        List<String> choices3 = new ArrayList<>();
        choices3.add("James Gosling");
        choices3.add("Bjarne Stroustrup");
        choices3.add("Guido van Rossum");
        choices3.add("Dennis Ritchie");

        List<String> choices4 = new ArrayList<>();
        choices4.add("JavaScript");
        choices4.add("Python");
        choices4.add("Java");
        choices4.add("C++");

        List<String> choices5 = new ArrayList<>();
        choices5.add("try-catch");
        choices5.add("if-else");
        choices5.add("for-loop");
        choices5.add("switch-case");

        Query query1 = new Query("When was Java first released?", choices1, 1);
        Query query2 = new Query("Which company developed Java?", choices2, 0);
        Query query3 = new Query("Who is known as the father of Java?", choices3, 0);
        Query query4 = new Query("Which programming language is used for Android development?", choices4, 2);
        Query query5 = new Query("Which of the following is used for exception handling in Java?", choices5, 0);

        queries.add(query1);
        queries.add(query2);
        queries.add(query3);
        queries.add(query4);
        queries.add(query5);
    }

    public static void signUp(Scanner input) {

        System.out.print("Create a Username: ");
        String user = input.nextLine();
        System.out.print("Create a Password: ");
        String pass = input.nextLine();

        
        for (Participant participant : participants) {
            if (participant.user.equals(user)) {
                System.out.println("Username is already in use. Please choose another one.");
                return;
            }
        }

        Participant newParticipant = new Participant(user, pass);
        participants.add(newParticipant);
        System.out.println("Sign-up successful! You can log in now.");
    }

    public static void signIn(Scanner input) {

        System.out.print("Enter Username: ");
        String user = input.nextLine();
        System.out.print("Enter Password: ");
        String pass = input.nextLine();

        
        for (Participant participant : participants) {
            if (participant.user.equals(user) && participant.pass.equals(pass)) {
                activeUser = participant;
                examSession = true;
                beginExam(input);
                return;
            }
        }

        System.out.println("Invalid credentials. Please try again!");
    }

    public static void beginExam(Scanner input) {

        System.out.println("\nWelcome, " + activeUser.user + "!");
        for (int i = 0; i < queries.size(); i++) {

            Query query = queries.get(i);
            System.out.println("Question " + (i + 1) + ": " + query.text);
            for (int j = 0; j < query.choices.size(); j++) {
                System.out.println((j + 1) + ". " + query.choices.get(j));
            }

            System.out.print("Select your answer (1-" + query.choices.size() + "): ");
            int chosenAnswer = input.nextInt();
            if (chosenAnswer == query.rightAnswer + 1) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        }

        System.out.println("Exam finished!");
        examSession = false;
    }
}
