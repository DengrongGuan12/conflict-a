
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.*;

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
            String path="";
            String path2="";
            String filePath = System.getProperty("java.class.path");
            String[] jars = filePath.split(":");
            System.out.println(jars.length);

            for (String jarPath:jars
                 ) {
                if(jarPath.contains("conflict-c")){
                    path = jarPath;
                }else if(jarPath.contains("conflict-b")){
                    path2 = jarPath;
                }
            }

            ClassLoader loader = new DependencyArchiveLauncher().getClassLoader(path);
            ClassLoader loader2 = new DependencyArchiveLauncher().getClassLoader(path2);
            //TODO 读取xml配置，反射加载类并创建对象

            //根据类名加载指定类，例：
//            Class logFactoryClazz = loader.loadClass("org.slf4j.LoggerFactory");
//            Method method1 = logFactoryClazz.getMethod("getLogger",Class.class);
//            Class loggerClazz = loader.loadClass("org.slf4j.Logger");
//            Method errorMethod = loggerClazz.getMethod("error",String.class);
//            Method method2 = loggerClazz.getMethod("trace",String.class);
            Class clazz = loader.loadClass("com.cainiao.chushi.conflictc.LogUtil");
            Class clazz2 = loader2.loadClass("com.cainiao.chushi.conflictb.LogUtil");
//            Object logger = method1.invoke(null,clazz);
//            method2.invoke(logger,"[trace message]");
            Object o = clazz.newInstance();
            Object o2 = clazz2.newInstance();
            if(o instanceof Object){
                System.out.println("o is instance of Object");
            }
            if(o2 instanceof Object){
                System.out.println("o2 is instance of Object");
            }
//            BusinessService businessService = (BusinessService)o;
            Method method = clazz.getMethod("execute", String.class);
            Method method2 = clazz2.getMethod("execute", String.class);
            method.invoke(o,"conflictc");
            method2.invoke(o2,"conflictb");
            InputStream inputStream = loader.getResourceAsStream("com/cainiao/chushi/"+"conflictc"+"/alphabird-biz.xml");
            DocumentBuilderFactory builderFactory =  DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("bean");
            for(int i =0;i<nList.getLength();i++){
                Node node = nList.item(i);
                Element ele = (Element)node;
                String id = ele.getAttribute("id");
                String classname = ele.getAttribute("class");

            }
//            loadBusinessServiceList("conflictb",loader2);

        } catch (Exception e) {
            e.printStackTrace();
        }


        // /Users/dengrong/.m2/repository/com/cainiao/chushi/conflict-c/1.0-SNAPSHOT/conflict-c-1.0-SNAPSHOT.jar!/BOOT-INF/classes
    }

    public List<ObjectBundle> loadBusinessServiceList(String bizBundleName) {
        String filePath = System.getProperty("java.class.path");
        String[] jars = filePath.split(":");
        System.out.println(jars.length);
        String path = "";
        for (String jarPath:jars
                ) {
            if(jarPath.contains(bizBundleName)){
                path = jarPath;
                break;
            }
        }
        //TODO 这一步如果包名和工程名严格相等是不需要的
        bizBundleName = bizBundleName.replace("-","");
        List<ObjectBundle> list = new LinkedList<ObjectBundle>();
        try {
            ClassLoader loader = new DependencyArchiveLauncher().getClassLoader(path);
            InputStream inputStream = loader.getResourceAsStream("com/cainiao/chushi/"+bizBundleName+"/alphabird-biz.xml");
            DocumentBuilderFactory builderFactory =  DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("bean");
            for(int i =0;i<nList.getLength();i++){
                Node node = nList.item(i);
                Element ele = (Element)node;
                String id = ele.getAttribute("id");
                String classname = ele.getAttribute("class");
                Class clazz = loader.loadClass(classname);
                Object o = clazz.newInstance();
                ObjectBundle objectBundle = new ObjectBundle(o,clazz);
                list.add(objectBundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Test(){

    }

}
