package com.auth_service.spring.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaDependsOnFlywayConfig {

    @Bean
    public static BeanFactoryPostProcessor jpaDependsOnFlywayPostProcessor() {
        return beanFactory -> addDependsOn(beanFactory, "entityManagerFactory", "flyway");
    }

    private static void addDependsOn(ConfigurableListableBeanFactory beanFactory,
                                     String beanName,
                                     String dependsOnBeanName) throws BeansException {
        if (beanFactory.containsBeanDefinition(beanName)) {
            var beanDefinition = beanFactory.getBeanDefinition(beanName);
            beanDefinition.setDependsOn(dependsOnBeanName);
        }
    }
}
