import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

/**
 * Created by dengrong on 2017/6/2.
 */

public class Main {
    static private Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args){
//        LogUtil.log("sdfsdf");
        new Test().test();
//        try {
//            JarLauncher.main(new String[]{});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        // 记录error信息
        logger.error("[info message]");
        // 记录deubg信息
        logger.debug("[debug message]");

    }
}
