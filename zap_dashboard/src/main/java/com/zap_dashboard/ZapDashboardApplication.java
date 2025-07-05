package com.zap_dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// Temporarily exclude security autoconfiguration to debug
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ZapDashboardApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ZapDashboardApplication.class, args);
    }
}