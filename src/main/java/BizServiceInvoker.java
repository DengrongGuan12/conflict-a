//import com.cainiao.alphabird.biz.sdk.service.BusinessServiceResultDTO;
//import com.cainiao.alphabird.biz.sdk.service.ExtTraceParams;
import com.cainiao.alphabird.biz.sdk.service.BusinessServiceResultDTO;
import com.cainiao.alphabird.biz.sdk.service.ExtTraceParams;
//import org.apache.commons.beanutils.BeanUtils;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
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
            this.executeMethod = bizService.getClass().getMethod("execute",String.class,Map.class);
            this.getServedBizIdsMethod = bizService.getClass().getMethod("getServedBizIds");
            this.getServicePriorityMethod = bizService.getClass().getMethod("getServicePriority");
            this.getRequestIdMethod = bizService.getClass().getMethod("getRequestId",String.class,Map.class);
            this.getExtTraceParamsMethod = bizService.getClass().getMethod("getExtTraceParams",String.class,Map.class);
        } catch (NoSuchMethodException e) {
            logger.error("can not find method of BusinessService:"+e.getMessage());
        }
    }

    public BusinessServiceResultDTO<R> execute(String var1, Map var2){
        Object object = null;
        try {
            object = this.executeMethod.invoke(this.bizService,var1,var2);
        } catch (Exception e) {
            logger.error("can not invoke execute method of BusinessService:"+e.getMessage());
        }
        BusinessServiceResultDTO result = new BusinessServiceResultDTO();
        try {
            Class fromClass = this.bizService.getClass().getClassLoader().loadClass(resultDTOName);
            Mapper mapper = new DozerBeanMapper();
            long startTime=System.currentTimeMillis();   //获取开始时间
//            PropertyUtils.copyProperties(result,object);

//            org.apache.commons.beanutils.BeanUtils.copyProperties(object,result);
//            BeanUtils.copyProperties(object,result);
//            BeanCopier bc = BeanCopier.create(fromClass, BusinessServiceResultDTO.class,false);
//            bc.copy(object,result,null);
//            ObjectMapper objectMapper = new ObjectMapper();
//            result = objectMapper.convertValue(object,BusinessServiceResultDTO.class);


            result =
                    mapper.map(object, BusinessServiceResultDTO.class);
            long endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

//
//            ConvertUtils.register(new Converter() {
//                public Object convert(Class type, Object value) {
//                    Object toObject = null;
//                    try {
//                        toObject = type.newInstance();
//                        Field[] fields = value.getClass().getDeclaredFields();
//                        for (Field field:fields
//                                ) {
//                            field.setAccessible(true);
//                            Object val = field.get(value);
//                            String fieldName = field.getName();
//                            if("serialVersionUID".equals(fieldName)){
//                                continue;
//                            }
//                            Field toField = type.getDeclaredField(fieldName);
//                            toField.setAccessible(true);
//                            toField.set(toObject,val);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                    return toObject;
//                }
//            },BusinessServiceResultDTO.class);
//            result = (BusinessServiceResultDTO) ConvertUtils.convert(object,BusinessServiceResultDTO.class);
        } catch (Exception e) {
//            logger.error("can not find class BusinessServiceResultDo in fatjar:"+e.getMessage());
            e.printStackTrace();
        }

        return (BusinessServiceResultDTO<R>) result;
    }

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

    public ExtTraceParams getExtTraceParams(String var1, Map<String, Object> var2){
        Object object = null;
        try{
            object = this.getExtTraceParamsMethod.invoke(this.bizService,var1,var2);
        }catch (Exception e){
            logger.error("can not invoke getExtTraceParams method of BusinessService:"+e.getMessage());
        }
//        Class fromClass = null;
//        try {
//            fromClass = this.bizService.getClass().getClassLoader().loadClass(traceParamsName);
//        } catch (ClassNotFoundException e) {
//            logger.error("can not find class ExtTraceParams in fatjar:"+e.getMessage());
//        }
        ExtTraceParams extTraceParams =  new ExtTraceParams();
        try {
            PropertyUtils.copyProperties(extTraceParams,object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return extTraceParams;
    }

    public String getBizServiceSimpleName(){
        return this.bizService.getClass().getSimpleName();
    }


}
