import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Dictionary {
    final String valueAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    String keyAlphabet;
    int keyLength;
    protected HashMap<String, String> dictionary = new HashMap<>();
    public void load(String fileName) throws IOException { // загрузка словаря
        if (!fileName.endsWith(".txt")) throw new IOException("Некорректное расширение файла. Должно быть .txt");
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] pair = line.split(":");
            if (pair.length == 2 && checkKey(pair[0]) && checkValue(pair[1])) {
                dictionary.put(pair[0], pair[1]);
            }
        }
        reader.close();
    }
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
    public void put(String key, String value) { // добавление записи с ключом (если ключ уже существует, то новая пара)
        if (checkKey(key) && checkValue(value)) {
            dictionary.put(key, value);
        }
    }
    public boolean checkKey(String key) {
        return key.matches("[" + keyAlphabet + "]+") && key.length() == keyLength;
    }
    public boolean checkValue(String value) {
        return value.matches("[" + valueAlphabet + "]+");
    }
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
