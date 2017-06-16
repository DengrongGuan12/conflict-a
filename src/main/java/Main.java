//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.core.util.StatusPrinter;
//import com.cainiao.alphabird.biz.sdk.service.BusinessServiceResultDTO;
//import com.cainiao.chushi.conflictb.LogUtil;
import com.cainiao.alphabird.biz.sdk.service.BusinessServiceResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengrong on 2017/6/2.
 */

public class Main {
    // test revert 
//    static private Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Test test = new Test();
//        List<ObjectBundle> list = test.loadBusinessServiceList("conflict-c");
//        for (ObjectBundle ob :list
//             ) {
//            Class clazz = ob.getClazz();
//            Object o = ob.getObject();
//            try {
//                Method method = clazz.getMethod("execute", String.class, Map.class);
//                method.invoke(o,"conflictc",new HashMap<String,String>());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
        List<BizServiceInvoker> list = test.loadBusinessServiceList("demo");
        for (BizServiceInvoker invoker : list
                ) {
            System.out.println(invoker.getRequestId("asas", new HashMap<String, Object>()));
            BusinessServiceResultDTO dto = invoker.execute("sdsdf", new HashMap());
            System.out.println(dto.getData());
            System.out.println(dto.getExtraInfo().get("info"));
            System.out.println(invoker.getServedBizIds()[0]);
            System.out.println(invoker.getBizServiceSimpleName());
            System.out.println(invoker.getServicePriority());
            System.out.println(invoker.getExtTraceParams("sdsdf", new HashMap<String, Object>()).getLinkId());
        }
        // 记录error信息
//        logger.error("[info message]");
        // 记录deubg信息
//        logger.debug("[debug message]");
//        ClassLoader appClassLoader = Main.class.getClassLoader();
//        System.out.println("-----MainClassLoader-----------");
//        while(appClassLoader != null){
//
//            System.out.println(appClassLoader.toString());
//            appClassLoader = appClassLoader.getParent();
//        }
//        System.out.println("-------MainClassLoader---------");
//
//        System.out.println("");
//
//        ClassLoader stringClassLoader = String.class.getClassLoader();
//        System.out.println("---------StringClassloader------------");
//        while(stringClassLoader !=null){
//            System.out.println(stringClassLoader.toString());
//            stringClassLoader = stringClassLoader.getParent();
//        }
//        System.out.println("---------StringClassloader------------");
//
//
//        String path = "/Users/dengrong/.m2/repository/com/cainiao/chushi/conflict-b/1.0-SNAPSHOT/conflict-b-1.0-SNAPSHOT.jar";
//        try {
//            System.out.println("----FatJarClassloader------------");
//            ClassLoader fatjarClassLoader = new DependencyArchiveLauncher().getClassLoader(path);
//            Class clazz = fatjarClassLoader.loadClass("com.cainiao.chushi.conflictb.LogUtil");
//            Object o = clazz.newInstance();
//            Method method = clazz.getMethod("execute",String.class);
//            method.invoke(o,"sdfsdfsdf");
//            while(fatjarClassLoader != null){
//                System.out.println(fatjarClassLoader.toString());
//                fatjarClassLoader = fatjarClassLoader.getParent();
//            }
//            System.out.println("-------FatJarClassloader---------");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            if (BusinessServiceResultDTO.class == Main.class.getClassLoader().loadClass("com.cainiao.alphabird.biz.sdk.service.BusinessServiceResultDTO")) {
//                System.out.println("sdf234234234234234234sadfsadfasdfasdfasdf");
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        String a = "demo/demo";
//        String[] as = a.split("/");
//        System.out.println(as[0]);


    }
}
