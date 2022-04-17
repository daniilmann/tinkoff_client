package me.daniilmann.tinkoff.application.controller;

import org.hamcrest.core.AnyOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilderSupport;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AllControllersTests {

    @Autowired
    private WebTestClient webTestClient;

    @TestFactory
    public List<DynamicTest> allControllersGetTestFactory() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassPathBeanDefinitionScanner beanScanner = new ClassPathBeanDefinitionScanner(new DefaultListableBeanFactory(), false);
        beanScanner.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        Set<BeanDefinition> beans = beanScanner.findCandidateComponents("me.daniilmann.tinkoff");

        Set<Object> controllers = new HashSet<>();
        Map<Class<?>, Constructor<?>> controllerClasses = new HashMap();
        String className = null;
        Class<?> controllerClass = null;
        Constructor<?> controllerConstructor = null;
        Object[] args = null;
        int i = 0;
        for (BeanDefinition beanDefinition : beans) {
            className = beanDefinition.getBeanClassName();
            controllerClass = ClassLoader.getSystemClassLoader().loadClass(className);

            controllerConstructor = null;
            args = null;
            for (Constructor<?> constructor : controllerClass.getDeclaredConstructors()) {
                if (constructor.getDeclaredAnnotation(Autowired.class) != null) {
                    controllerConstructor = constructor;
                    break;
                }
            }

            if (controllerConstructor != null) {
                controllerClasses.put(controllerClass, controllerConstructor);
                args = new Object[controllerConstructor.getParameterCount()];
                i = 0;
                for (Class<?> param : controllerConstructor.getParameterTypes()) {
                    args[i++] = Mockito.mock(param);
                }
                controllers.add(controllerConstructor.newInstance(args));
            } else {
                controllerClasses.put(controllerClass, null);
            }
        }

        Assertions.assertAll(controllerClasses.entrySet().stream()
                .map(e -> () -> Assertions.assertNotNull(e.getValue(),
                        String.format("%s doesn't have @Autowired constructors", e.getKey().getName()))));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllers.toArray()).build();

        List<DynamicTest> dynamicTests = new ArrayList<>();
        RequestMapping[] requestMappings = null;
        GetMapping[] getMappings = null;
        for (Class<?> controllerClazz : controllerClasses.keySet()) {
            requestMappings = controllerClazz.getAnnotationsByType(RequestMapping.class);
            if (requestMappings.length != 0) {
                for (Method method : controllerClazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(GetMapping.class) && method.getParameterCount() == 0) {
                        getMappings = method.getAnnotationsByType(GetMapping.class);
                        final String path = requestMappings[0].path()[0]
                                + (getMappings[0].path().length != 0
                                ? getMappings[0].path()[0]
                                : "");
                        dynamicTests.add(DynamicTest.dynamicTest(
                                path + " test",
                                () -> mockMvc
                                        .perform(MockMvcRequestBuilders.get(path))
                                        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())));
                    }
                }
            }
        }

        return dynamicTests;
    }

}
