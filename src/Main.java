import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Dictionary dictionary;
    static String fileName;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите тип словаря, с которым будете работать: 1 - первый (латиница), 2 - второй (цифры), 0 - выход");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 0) break;
                else if (choice == 1) {
                    dictionary = new Dictionary1();
                    try { loadDictionary(); }
                    catch (IOException e) {
                        System.out.println("Ошибка при загрузке/создании файла. " + e.getMessage());
                        continue;
                    }
                } else if (choice == 2) {
                    dictionary = new Dictionary2();
                    try { loadDictionary(); }
                    catch (IOException e) {
                        System.out.println("Ошибка при загрузке/создании файла. " + e.getMessage());
                        continue;
                    }
                } else {
                    System.out.println("Некорректный выбор. Попробуйте еще раз");
                    continue;
                }
            } else {
                System.out.println("Некорректный ввод данных. Попробуйте еще раз");
                scanner.nextLine();
                continue;
            }

            operateDictionary();
        }
    }
    static private void loadDictionary() throws IOException {
        System.out.println("Введите название файла, который хотите загрузить/создать: ");
        fileName = scanner.nextLine();

        dictionary.load(fileName);
    }
    static private void viewDictionary() {
        String[] dictionaryString = dictionary.view();

        for (String entry : dictionaryString) {
            String[] pair = entry.split(":");
            String key = pair[0];
            String value = pair[1];
            System.out.println(key + ":" + value);
        }
    }
    static private void operateDictionary() {
        while (true) {
            System.out.println("Выберите операцию: 1 - просмотреть содержимое словаря, 2 - добавить запись по ключу, 3 - удалить запись по ключу, 4 - найти запись по ключу, 0 - выход");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice == 0) break;
                else if (choice == 1) viewDictionary();
                else if (choice == 2) addValue();
                else if (choice == 3) removeValue();
                else if (choice == 4) findValue();
                else System.out.println("Некорректный выбор. Попробуйте еще раз");
            } else {
                System.out.println("Некорректный ввод данных. Попробуйте еще раз");
                scanner.nextLine();
            }
        }
    }
    static private void addValue() {
        System.out.println("Введите ключ:");
        String key = scanner.next();
        //scanner.nextLine();
        System.out.println("Введите значение:");
        String value = scanner.next();
        //scanner.nextLine();

        dictionary.put(key, value);

        saveDictionary();
    }
    static private void removeValue() {
        System.out.println("Введите ключ:");
        String key = scanner.next();

        dictionary.remove(key);

        saveDictionary();
    }
    static private void findValue() {
        System.out.println("Введите ключ:");
        String key = scanner.next();

        String value = dictionary.get(key);
        if (value != null) System.out.println(value);
        else System.out.println("Значение не найдено");
    }

    static private void saveDictionary() {
        try { dictionary.save(fileName); }
        catch (IOException e) {
            System.out.println("Ошибка при сохранении словаря. " + e.getMessage());
        }
    }
}