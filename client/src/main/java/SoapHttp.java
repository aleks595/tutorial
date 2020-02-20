import freemarker.template.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SoapHttp {

    public static void main(String[] args) throws Exception {
        maker(4);

        File soapRequestFile = new File("./src/main/resources/SoapRequestFile.xml");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8080/ws/bank.wsdl");
        request.addHeader("Content-Type", "text/xml");
        request.setEntity(new InputStreamEntity(new FileInputStream(soapRequestFile)));
        CloseableHttpResponse response =  client.execute(request);
        String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");//Getting the Response body
        System.out.println(responseString);
        soapRequestFile.delete();
    }
        public static void maker(int id) throws IOException, TemplateException {
            Configuration cfg = new Configuration();
            Map<String, Object> input = new HashMap<String, Object>();
            input.put("id", id);
            Template template = cfg.getTemplate("./src/main/resources/Request.ftl");
            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(input, consoleWriter);
            File file = new File("./src/main/resources/SoapRequestFile.xml");
            Writer fileWriter = new FileWriter(file);
            try {
                template.process(input, fileWriter);
            } finally {
                fileWriter.close();
            }
        }

    }


