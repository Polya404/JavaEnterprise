package org.application.reflection;

@Service(name = "AnnotationLazyService", lazyLoad = true)
public class LazyService {
    @Init(isSuppressException = true)
    public void lazyInit() throws Exception {
        System.out.println("I am lazy service");
        throw new Exception("Lazy service exception was throw");
    }
}
