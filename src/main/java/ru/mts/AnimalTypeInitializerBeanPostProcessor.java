package ru.mts;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import ru.mts.model.AnimalType;
import ru.mts.service.CreateAnimalService;

import java.lang.reflect.Method;

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
                if (method.isAnnotationPresent(SetAnimalType.class) && !method.isDefault()) {
                    SetAnimalType annotation = method.getAnnotation(SetAnimalType.class);
                    AnimalType animalType = annotation.animalType();
                    if (bean instanceof CreateAnimalService animalService) {
                        animalService.setAnimalType(animalType);
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
}
