import com.cainiao.chushi.sdk.service.BusinessService;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.loader.ExecutableArchiveLauncher;
import org.springframework.boot.loader.JarLauncher;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.ExplodedArchive;
import org.springframework.boot.loader.archive.JarFileArchive;
import org.springframework.boot.loader.jar.JarFile;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.*;
import java.util.jar.Manifest;

/**
 * Created by dengrong on 2017/6/5.
 */
public class Test {
    public void  test(){
        //filePath 是jar的绝对路径
//        String filePath = "jar:file:/Users/dengrong/.m2/repository/com/cainiao/chushi/conflict-c/1.0-SNAPSHOT/conflict-c-1.0-SNAPSHOT.jar!/BOOT-INF/classes/";
//        String jarFile1 = "jar:file:/Users/dengrong/.m2/repository/com/cainiao/chushi/conflict-c/1.0-SNAPSHOT/conflict-c-1.0-SNAPSHOT.jar!/BOOT-INF/lib/slf4j-simple-1.7.24.jar!/";
//        String jarFile2 = "jar:file:/Users/dengrong/.m2/repository/com/cainiao/chushi/conflict-c/1.0-SNAPSHOT/conflict-c-1.0-SNAPSHOT.jar!/BOOT-INF/lib/slf4j-api-1.7.24.jar!/";
//        URL url1,url2,url3 = null;
        try {
//            url1 = new URL(filePath);
//            url2 = new URL(jarFile1);
//            url3 = new URL(jarFile2);
            //里面是一个url的数组，可以同时加载多个

//            URLClassLoader loader = new URLClassLoader( new URL[]{ url1,url2,url3 } ,null);
            String path = "/Users/dengrong/.m2/repository/com/cainiao/chushi/conflict-c/1.0-SNAPSHOT/conflict-c-1.0-SNAPSHOT.jar";
            ClassLoader loader = new DependArchiveLauncher().getClassLoader(path);

            //根据类名加载指定类，例：
//            Class logFactoryClazz = loader.loadClass("org.slf4j.LoggerFactory");
//            Method method1 = logFactoryClazz.getMethod("getLogger",Class.class);
//            Class loggerClazz = loader.loadClass("org.slf4j.Logger");
//            Method errorMethod = loggerClazz.getMethod("error",String.class);
//            Method method2 = loggerClazz.getMethod("trace",String.class);
            Class clazz = loader.loadClass("com.cainiao.chushi.util.LogUtil");
//            Object logger = method1.invoke(null,clazz);
//            method2.invoke(logger,"[trace message]");
            Object o = clazz.newInstance();
            Method method = clazz.getMethod("log", String.class);
            method.invoke(o,"chushi");
        } catch (Exception e) {
            e.printStackTrace();
        }


        // /Users/dengrong/.m2/repository/com/cainiao/chushi/conflict-c/1.0-SNAPSHOT/conflict-c-1.0-SNAPSHOT.jar!/BOOT-INF/classes
    }

    private List<BusinessService> loadBusinessServiceList(String bizBundleName) {
        PathMatchingResourcePatternResolver pmrl = new PathMatchingResourcePatternResolver(applicationContext.getClassLoader());
        Resource resource = pmrl.getResource("classpath:" + "com/cainiao/alphabird/biz/" + bizBundleName + "/alphabird-biz.xml");

        GenericApplicationContext genericApplicationContext = new GenericApplicationContext(applicationContext);
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(genericApplicationContext);
        beanDefinitionReader.loadBeanDefinitions(resource);
        genericApplicationContext.refresh();

        Map<String, BusinessService> businessServiceMap = genericApplicationContext.getBeansOfType(BusinessService.class);

        return new LinkedList<BusinessService>(businessServiceMap.values());
    }
    public Test(){

    }

}
