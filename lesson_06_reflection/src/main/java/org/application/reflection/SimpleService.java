package org.application.reflection;

@Service(name = "AnnotationSimpleService")
public class SimpleService {
    @Init
    public void initService(){
        System.out.println("I am simple service");
    }
}
