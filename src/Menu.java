import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static Scanner scanner;

    public static void Start(){
        mainMenu();
    }
    public static int readChoise(){
        Scanner scanner = new Scanner(System.in);
        int readed =-1;
        try {
            readed = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException ex){
            System.out.println("Вводите только пункты меню, пж:)");
            readed = readChoise();
        }
        return readed;
    }
    private static  void mainMenu(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Выберите программу:\n ");
        Worker worker = new Worker("Oleg",24,"teacher");
        Engineer engineer = new Engineer("Vadim",40,"HighEngineer");
        Administration administration = new Administration("Antonio",55,"Admin");
        Group group = new Group("Work-NAME");
        int choice = -1;
        do {
            System.out.println("Введите пункт меню:");
            System.out.println("1 - добавить всех в группу ");
            System.out.println("2 - добавить работника");
            System.out.println("3 - удалить по номеру в списке");
            System.out.println("4 - распечатать группу");
            System.out.println("5 - удалить работника по имени");
            System.out.println("6 - отсортировать по алфавиту и распечатать группу");

            choice = readChoise();
            int choice2;
            switch (choice) {
                case 1:
                    group.addCadres(worker);
                    group.addCadres(engineer);
                    group.addCadres(administration);
                    System.out.println(group);
                    break;
                case 2:
                    group.addCadres();
                    break;
                case 3:
                    try {
                        int a = keyboard.nextInt();
                        group.delCadres(a);
                    }
                    catch (InputMismatchException e){
                        System.out.println("Вводите только пункты меню");
                    }
                    break;
                case 4:
                    System.out.println(group);
                    break;
                case 5:
                    group.delCadres(keyboard.nextLine());
                    break;
                case 6:
                    group.sortNameAndPrint();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Введите существующий пункт меню от 0 до 6.");
            }
        }while (choice != 0);
    }
}
