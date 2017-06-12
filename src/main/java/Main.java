import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by dengrong on 2017/6/2.
 */

public class Main {
    // test revert 
    static private Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args){
        Test test = new Test();
        List<ObjectBundle> list = test.loadBusinessServiceList("conflictc");
        for (ObjectBundle ob :list
             ) {
            Class clazz = ob.getClazz();
            Object o = ob.getObject();
            try {
                Method method = clazz.getMethod("execute", String.class);
                method.invoke(o,"conflictc");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // 记录error信息
        logger.error("[info message]");
        // 记录deubg信息
        logger.debug("[debug message]");

    }
}
