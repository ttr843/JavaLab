package context;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContextReflectionBased implements ApplicationContext {

    public Map<String, Component> getMap() {
        return map;
    }

    private Map<String, Component> map = new HashMap<>();


    @Override
    public <T> T getComponent(Class<T> componentType) {

        if (map.containsKey(componentType.getName())) {
            return (T) map.get(componentType.getName());
        }
        T ob = null;
        if (componentType.isInterface()) {
            ob = findComponent(componentType);
        } else {
            try {
                ob = componentType.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException();
            }

        }
        Field[] fields = ob.getClass().getDeclaredFields();

        for (Field field : fields) {
            Class clazz = field.getType();
            if (Component.class.isAssignableFrom(clazz)) {
                try {
                    field.setAccessible(true);
                    field.set(ob, getComponent(clazz));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException();
                }
            }
        }
        map.put(componentType.getName(), (Component) ob);
        return ob;
    }

    public <T> T findComponent(Class<T> componentType) {
        ScanResult scanResult = new ClassGraph().enableAllInfo().whitelistPackages().scan();
        ClassInfoList controlClasses = scanResult.getClassesImplementing(componentType.getName());
        if (controlClasses.size() > 1) {
            throw new IllegalStateException();
        } else {
            try {
                return (T) (controlClasses.loadClasses().get(0).newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException();
            }
        }
    }
}

