package com.demo.security.plugin;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;

/**
 * @auther gzhen
 * @date 2023-10-13  11:31
 * @description
 */
@Configuration
public class TestConfig implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String beanName = "shiroRealm";
        if (registry.containsBeanDefinition(beanName)){
            registry.removeBeanDefinition(beanName);
            GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
            genericBeanDefinition.setBeanClass(TestShiroRealm.class);
            genericBeanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
            registry.registerBeanDefinition(beanName, genericBeanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
