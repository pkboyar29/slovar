import java.io.*;

public class Dictionary2 extends Dictionary {
    public Dictionary2() {
        keyAlphabet = "1234567890";
        keyLength = 5;
    }
    @Override
    public void load(String fileName) throws IOException {
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
            if (pair.length == 2
                    && pair[0].matches("[" + keyAlphabet + "]+")
                    && pair[0].length() == keyLength
                    && pair[1].matches("[" + valueAlphabet + "]+")) {
                dictionary.put(pair[0], pair[1]);
            }
        }
        reader.close();
    }
    @Override
    public void put(String key, String value) {
        if (key.matches("[" + keyAlphabet + "]+")
                && key.length() == keyLength
                && value.matches("[" + valueAlphabet + "]+")) {
            dictionary.put(key, value);
        }
    }
    @Override
    public boolean checkKey(String key) {
        return key.matches("[" + keyAlphabet + "]+") && key.length() == keyLength;
    }
    @Override
    public boolean checkValue(String value) {
        return value.matches("[" + valueAlphabet + "]+");
    }
}
