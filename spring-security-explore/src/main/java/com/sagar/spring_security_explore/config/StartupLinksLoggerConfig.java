package com.sagar.spring_security_explore.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class StartupLinksLoggerConfig implements ApplicationListener<ApplicationReadyEvent> {


    /**
     * Logs useful links to the console when the application is ready.
     * This includes Swagger UI and application home links.
     *
     * @param event the ApplicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Environment env = event.getApplicationContext().getEnvironment();
        String port = env.getProperty("server.port", "8080");
        String contextPath = env.getProperty("server.servlet.context-path", "");
        if (contextPath.equals("/")) contextPath = "";
        String baseUrl = "http://localhost:" + port + contextPath;
        String ip = "localhost";
        try {
            ip = java.net.InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            // ignore
        }
        String ipBaseUrl = "http://" + ip + ":" + port + contextPath;
        System.out.println("\n==== Useful Links ====");
        System.out.println("Swagger UI: " + baseUrl + "/swagger-ui.html");
        System.out.println("Swagger UI (IP): " + ipBaseUrl + "/swagger-ui.html");
        System.out.println("App Home:   " + baseUrl + "/");
        System.out.println("App Home (IP): " + ipBaseUrl + "/");
        System.out.println("======================\n");
    }
}
