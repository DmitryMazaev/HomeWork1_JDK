package server;

import client.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Server {
    /**
     * Список клиентов
     */
    private ArrayList<Client> clientList = new ArrayList<>();
    /**
     * Статус работы сервера
     */
    private boolean isServerStart = false;
    public ArrayList<Client> getClientList() {
        return clientList;
    }
    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }
    public static final String LOG_PATH = "src/server/log.txt";
    public boolean isServerStart() {
        return isServerStart;
    }
    public void setServerStart(boolean serverStart) {
        isServerStart = serverStart;
    }
    /**
     * Запись в файл
     */
    public void whriteInLogFile(String string)
    {
        try {
            FileWriter writer = new FileWriter(LOG_PATH, true);
            writer.write(string +"\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
    /**
     * Чтение из файла, метод возвращает чат
     */
    public String readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(LOG_PATH));
        String line;
        String chart = "";
        while ((line = br.readLine()) != null) {
            //System.out.println(line);
            chart = chart + line + "\n";
            //return line;
        }
        return chart;
    }
    /**
     * Включение клиента в список клиентов
     */
    public void connectUser (Client client)
    {
        clientList.add(client);
    }

}