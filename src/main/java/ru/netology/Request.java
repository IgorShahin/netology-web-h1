package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Request {
    private final String method;
    private final String path;
    private final Map<String, String> headers;
    private final InputStream body;
    private final List<NameValuePair> queryParams;

    public Request(String method, String path, Map<String, String> headers, InputStream body) {
        this.method = method;
        this.headers = headers;
        this.body = body;

        if (path.contains("?")) {
            final var idx = path.indexOf("?");
            this.path = path.substring(0, idx);
            final var query = path.substring(idx + 1);
            this.queryParams = URLEncodedUtils.parse(query, StandardCharsets.UTF_8);
        } else {
            this.path = path;
            this.queryParams = List.of();
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public InputStream getBody() {
        return body;
    }

    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }

    public String getQueryParam(String name) {
        return queryParams.stream()
                .filter(param -> param.getName().equals(name))
                .map(NameValuePair::getValue)
                .findFirst()
                .orElse(null);
    }
}