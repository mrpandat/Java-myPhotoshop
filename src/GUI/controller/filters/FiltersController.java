package GUI.controller.filters;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FiltersController {
    public ArrayList<Class> getClasses() {
        return classes;
    }

    private ArrayList<Class> classes = new ArrayList<Class>();

    public FiltersController() {
        getClassesFromJar(new File("filter/BasicFilter.jar"));
        getClassesFromJar(new File("filter/BonusFilter.jar"));
        return;
    }

    public void getClassesFromJar(File f) {
        Class<?> result = null;
        URL url;
        try {
            url = f.toURI().toURL();
            URL[] urls = new URL[]{url};
            ClassLoader loader = new URLClassLoader(urls);
            JarFile jf = new JarFile(f);
            Enumeration<JarEntry> en = jf.entries();
            while (en.hasMoreElements()) {
                JarEntry je = en.nextElement();
                String name = je.getName();
                if (name.endsWith(".class")) {
                    name = name.substring(0, name.length() - 6);
                    name = name.replace('/', '.');
                    result = loader.loadClass(name);
                    if (result != null) {
                        classes.add(result);
                    }
                }
            }
            jf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
