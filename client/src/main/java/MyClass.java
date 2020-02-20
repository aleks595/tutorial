import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import entity.Bank;
import entity.Client;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MyClass {
    public static void main(String[] args) throws Exception {
        String param ="2";
        String json = readUrl("http://localhost:8080/bank/get?id="+param);
        gson(json);
        jackson(json);
        jsonToXml(json);
    }

    private static void gson (String url){
        Gson gson = new Gson();
        Bank bank = gson.fromJson(url, Bank.class);
        for (Client client : bank.getClients()) {
            if (client.getId() == 27) {
                System.out.println("Найден клиент 27");
            }
            System.out.println(bank.toString() + " " + client.toString());
        }
    }

    private static void jackson (String url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Bank bank = mapper.readValue(url, Bank.class);
        for (Client client1 : bank.getClients()) {
            if (client1.getId() == 32) {
                System.out.println("Найден клиент 32");
            }
            System.out.println(bank.toString() + " " + client1.toString());
        }
    }

    private static void jsonToXml (String url){
        JSONObject jsonX = new JSONObject(url);
        String xml = XML.toString(jsonX);
        System.out.println(xml);
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}