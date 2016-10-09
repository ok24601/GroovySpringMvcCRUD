import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses() {
        Class<?>[] clazz = []
        return clazz
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class<?>[] clazz = [ConfigMain.class]

        return clazz
    }

    @Override
    protected String[] getServletMappings() {

        return ["/"]
    }
}
