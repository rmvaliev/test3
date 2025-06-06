import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/hello", new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080");
    }

    static class MyHandler implements HttpHandler {
        private final Gson gson = new Gson();

        @Override
        public void handle(HttpExchange t) throws IOException {
            if (!t.getRequestMethod().equalsIgnoreCase("POST")) {
                t.sendResponseHeaders(405, -1);
                return;
            }
            InputStream is = t.getRequestBody();
            String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JsonObject requestJson = gson.fromJson(body, JsonObject.class);
            // Можно проверить наличие name и fam, но по заданию не требуется
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("status", "Ok");
            responseJson.addProperty("description", "hello world");
            byte[] response = responseJson.toString().getBytes(StandardCharsets.UTF_8);
            t.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
            t.sendResponseHeaders(200, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        }
    }
} 