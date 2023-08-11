package com.amplifierconsultancy.ugmk.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.primavera")
@Data
public class PrimaveraProps {
    public static final int DEFAULT_MODE = 0;
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 1433;

    private String bootstrapHome = "";
    private int mode = DEFAULT_MODE;
    private String host = DEFAULT_HOST;
    private int port = DEFAULT_PORT;
    private String username = "";
    private String password = "";
}
