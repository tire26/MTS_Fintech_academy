package ru.mts.animalsconstructstarter.config;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import ru.mts.animalsconstructstarter.SetRandomAnimalType;
import ru.mts.animalsconstructstarter.model.AnimalType;
import ru.mts.animalsconstructstarter.service.CreateAnimalService;


import java.lang.reflect.Method;
import java.util.Random;

@Component
public class AnimalTypeInitializerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(bean);
        Class<?> aClass = beanWrapper.getWrappedClass();
        Class<?>[] allInterfaces = ClassUtils.getAllInterfacesForClass(aClass);
        for (Class<?> allInterface : allInterfaces) {
            Method[] methods = allInterface.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(SetRandomAnimalType.class) && !method.isDefault()) {
                    if (bean instanceof CreateAnimalService) {
                        CreateAnimalService animalService = (CreateAnimalService) bean;
                        animalService.setAnimalType(getRandomAnimalType());
                    }
                }
            }
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    private static AnimalType getRandomAnimalType() {
        return AnimalType.values()[new Random().nextInt(AnimalType.values().length)];
    }
}
