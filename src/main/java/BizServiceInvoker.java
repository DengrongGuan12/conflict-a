//import com.cainiao.alphabird.biz.sdk.service.BusinessServiceResultDTO;
//import com.cainiao.alphabird.biz.sdk.service.ExtTraceParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by dengrong on 2017/6/13.
 */
public class BizServiceInvoker<R> {
    private static final Logger logger = LoggerFactory.getLogger(BizServiceInvoker.class);
    private Object bizService;
    private Method executeMethod;
    private Method getServedBizIdsMethod;
    private Method getServicePriorityMethod;
    private Method getRequestIdMethod;
    private Method getExtTraceParamsMethod;
    private static String resultDTOName = "com.cainiao.alphabird.biz.sdk.service.BusinessServiceResultDTO";
    private static String traceParamsName = "com.cainiao.alphabird.biz.sdk.service.ExtTraceParams";

    public BizServiceInvoker(Object bizService){
        this.bizService = bizService;
        try {
//            this.executeMethod = bizService.getClass().getMethod("execute",String.class,Map.class);
            this.getServedBizIdsMethod = bizService.getClass().getMethod("getServedBizIds");
            this.getServicePriorityMethod = bizService.getClass().getMethod("getServicePriority");
            this.getRequestIdMethod = bizService.getClass().getMethod("getRequestId",String.class,Map.class);
//            this.getExtTraceParamsMethod = bizService.getClass().getMethod("getExtTraceParams",String.class,Map.class);
        } catch (NoSuchMethodException e) {
            logger.error("can not find method of BusinessService:"+e.getMessage());
        }
    }

//    public BusinessServiceResultDTO<R> execute(String var1, Map var2){
//        Object object = null;
//        try {
//            object = this.executeMethod.invoke(this.bizService,var1,var2);
//        } catch (Exception e) {
//            logger.error("can not invoke execute method of BusinessService:"+e.getMessage());
//        }
//        BusinessServiceResultDTO result = new BusinessServiceResultDTO();
//        try {
//            Class fromClass = this.bizService.getClass().getClassLoader().loadClass(resultDTOName);
//            result = (BusinessServiceResultDTO) Convertor.convert(fromClass,BusinessServiceResultDTO.class,object);
//        } catch (ClassNotFoundException e) {
//            logger.error("can not find class BusinessServiceResultDo in fatjar:"+e.getMessage());
//        }
//
//        return result;
//    }

    public String[] getServedBizIds(){
        String[] bizIds = null;
        try {
            bizIds = (String[]) this.getServedBizIdsMethod.invoke(this.bizService);
        } catch (Exception e) {
            logger.error("can not invoke getServedBizIds method of BusinessService:"+e.getMessage());
        }
        return bizIds;
    }

    public int getServicePriority(){
        int p = 0;
        try {
            p = (Integer) this.getServicePriorityMethod.invoke(this.bizService);
        }catch (Exception e){
            logger.error("can not invoke getServicePriority method of BusinessService:"+e.getMessage());
        }
        return p;
    }

    public String getRequestId(String var1, Map<String, Object> var2){
        String requestId = "";
        try{
            requestId = (String)this.getRequestIdMethod.invoke(this.bizService,var1,var2);
        }catch (Exception e){
            logger.error("can not invoke getReuestId method of BusinessService:"+e.getMessage());
        }
        return requestId;
    }

//    public ExtTraceParams getExtTraceParams(String var1, Map<String, Object> var2){
//        Object object = null;
//        try{
//            object = this.getExtTraceParamsMethod.invoke(this.bizService,var1,var2);
//        }catch (Exception e){
//            logger.error("can not invoke getExtTraceParams method of BusinessService:"+e.getMessage());
//        }
//        Class fromClass = null;
//        try {
//            fromClass = this.bizService.getClass().getClassLoader().loadClass(traceParamsName);
//        } catch (ClassNotFoundException e) {
//            logger.error("can not find class ExtTraceParams in fatjar:"+e.getMessage());
//        }
//        ExtTraceParams extTraceParams =  (ExtTraceParams)Convertor.convert(fromClass,ExtTraceParams.class,object);
//        return extTraceParams;
//    }

    public String getBizServiceSimpleName(){
        return this.bizService.getClass().getSimpleName();
    }


}
