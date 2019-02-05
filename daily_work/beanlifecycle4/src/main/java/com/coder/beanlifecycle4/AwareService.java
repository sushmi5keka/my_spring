package com.coder.beanlifecycle4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;

public class AwareService implements ApplicationContextAware, ApplicationEventPublisherAware,
        BeanClassLoaderAware, BeanFactoryAware, BeanNameAware, EnvironmentAware, ImportAware,
        ResourceLoaderAware {

    @Override
    public void setApplicationContext(ApplicationContext actx) throws BeansException {
        System.out.println("setApplicationContext called");
        System.out.println("setApplicationContext:Bean Definition Name" + Arrays.toString(actx.getBeanDefinitionNames()));
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("setBeanClassLoader called");
        System.out.println("setBeanClassLoader:: ClassLoader Name" + classLoader.getClass().getName());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory called");
        System.out.println("setBeanFactory:: employee bean singleton=" + beanFactory.isSingleton("employee"));
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("setBeanName called");
        System.out.println("setBeanName :: Bean Name defined in context =" + beanName);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("setApplicationEventPublisher called");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("setEnvironment called");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("setResourceLoader called");
        Resource resource = resourceLoader.getResource("classpath:spring.xml");
        System.out.println("setResourceLoader:: Resource File Name = " + resource.getFilename());
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        System.out.println("setImportMetadata called");
    }
}
