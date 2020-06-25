package com.khalid.assignment;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackageClasses = {DiscountsApplication.class})
public class DbConfiguration {
}
