import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Dictionary {
    final String valueAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    protected HashMap<String, String> dictionary = new HashMap<>();
    public abstract void load(String fileName) throws IOException; // загрузка словаря
    public void save(String fileName) throws IOException { // сохранение словаря
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
        }
        writer.close();
    }
    public String get(String key) { // поиск записи по ключу
        return dictionary.get(key);
    }
    public abstract void put(String key, String value); // добавление записи с ключом (если ключ уже существует, то новая пара)
    public void remove(String key) { // удаление записи по ключу
        dictionary.remove(key);
    }
    public String[] view() { // просмотр всех пар ключ-значение
        String[] dictionaryString = new String[dictionary.size()];

        int i = 0;
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            dictionaryString[i] = entry.getKey() + ":" + entry.getValue();
            i++;
        }
        return dictionaryString;
    }
}
