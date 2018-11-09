package ru.study.simpleshop.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {

}