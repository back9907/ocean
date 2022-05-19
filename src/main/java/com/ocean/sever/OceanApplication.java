package com.ocean.sever;

import com.ocean.sever.service.Sever;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OceanApplication {

    public static void main(String[] args) {
        SpringApplication.run(OceanApplication.class, args);
        Sever sever = new Sever(4242);
        sever.start();
    }

}
