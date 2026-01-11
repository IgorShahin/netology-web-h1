package ru.netology;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final var server = new Server();

        server.addHandler("GET", "/messages", (request, responseStream) -> {
            try {
                final var last = request.getQueryParam("last");
                final var response = "Path: " + request.getPath() + "\n" +
                        "Param 'last': " + last + "\n" +
                        "All params: " + request.getQueryParams();

                final var content = response.getBytes();
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/plain\r\n" +
                                "Content-Length: " + content.length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                responseStream.write(content);
                responseStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        server.addHandler("POST", "/messages", (request, responseStream) -> {
            try {
                final var last = request.getQueryParam("last");
                final var response = "POST request\n" +
                        "Path: " + request.getPath() + "\n" +
                        "Param 'last': " + last + "\n" +
                        "All params: " + request.getQueryParams();

                final var content = response.getBytes();
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/plain\r\n" +
                                "Content-Length: " + content.length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                responseStream.write(content);
                responseStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        server.listen(9999);
    }
}