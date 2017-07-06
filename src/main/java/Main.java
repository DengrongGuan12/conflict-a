//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.core.util.StatusPrinter;
//import com.cainiao.alphabird.biz.sdk.service.BusinessServiceResultDTO;
//import com.cainiao.chushi.conflictb.LogUtil;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.loader.LaunchedURLClassLoader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by dengrong on 2017/6/2.
 */

public class Main {
    // test revert 
//    static private Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        BizBundler bizBundler = new BizBundler();
//        List<ObjectBundle> list = bizBundler.loadBusinessServiceList("conflict-c");
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
        List<BizServiceInvoker> businessServiceList = bizBundler.loadBusinessServiceList("conflict-c");
//        for (BizServiceInvoker invoker : list
//                ) {
//            System.out.println(invoker.getRequestId("asas", new HashMap<String, Object>()));
//            BusinessServiceResultDTO dto = invoker.execute("sdsdf", new HashMap());
//            System.out.println(dto.getData());
//            System.out.println(dto.getExtraInfo().get("info"));
//            System.out.println(dto.isSuccess());
//            System.out.println(dto.isException());
//            System.out.println(invoker.getServedBizIds()[0]);
//            System.out.println(invoker.getBizServiceSimpleName());
//            System.out.println(invoker.getServicePriority());
//            System.out.println(invoker.getExtTraceParams("sdsdf", new HashMap<String, Object>()).getLinkId());
//        }
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

        FromBean fromBean = new FromBean();
        fromBean.setA("ffasdfsadfsaf");
        fromBean.setB("swerwerwerwer");
        FromInner fromInner = new FromInner();
        fromInner.setI("froaIne=ner");
        fromBean.setInner(fromInner);
        List<String> stringList = new ArrayList<String>();
        stringList.add("sdfsdf");
        stringList.add("wewerrer");
        fromBean.setStringList(stringList);
        List<FromInner> fromInnerList = new ArrayList<FromInner>();
        FromInner fromInner1 = new FromInner();
        fromInner1.setI("I am first inner object");
        FromInner fromInner2 = new FromInner();
        fromInner2.setI("wewerwer");
        fromInnerList.add(fromInner1);
        fromInnerList.add(fromInner2);
        fromBean.setFromInnerList(fromInnerList);
        Map<String,FromInner> map = new HashMap<String, FromInner>();
        map.put("1",fromInner1);
        map.put("2",fromInner2);
        fromBean.setKeyInnerMap(map);

//        ToBean toBean = new ToBean();
//        BeanUtils.copyProperties(fromBean,toBean);
//        BeanCopier bc = BeanCopier.create(FromBean.class, ToBean.class,false);
//        bc.copy(fromBean,toBean,null);
//        Mapper mapper = new DozerBeanMapper();

        String path = "/Users/dengrong/.m2/repository/com/cainiao/chushi/conflict-c/1.0-SNAPSHOT/conflict-c-1.0-SNAPSHOT.jar";
        try {
            ClassLoader classloader = new DependencyArchiveLauncher().getClassLoader(path);
            Class toClass = classloader.loadClass("FromBean");
            long startTime=System.currentTimeMillis();   //获取开始时间
            Object toObject = convert(fromBean,toClass);
//            Object toObject = mapper.map(fromBean, toClass);
//        ToBean toBean = (ToBean) convert(fromBean,ToBean.class);
            long endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
            Method getAMethod = toClass.getMethod("getA");
            Method getStringListMethod = toClass.getMethod("getStringList");
            Method getInnerMethod = toClass.getMethod("getInner");
            System.out.println(getAMethod.invoke(toObject));
            System.out.println("invoke list size:"+((List)getStringListMethod.invoke(toObject)).size());
            Method getFromInnerListMethod = toClass.getMethod("getFromInnerList");
            List list = (List) getFromInnerListMethod.invoke(toObject);
            System.out.println("complex list size"+list.size());
            Class innerClass = classloader.loadClass("FromInner");
            Method getIMethod = innerClass.getMethod("getI");
            Object o = list.get(0);
            Method getKeyInnerMapMethod = toClass.getMethod("getKeyInnerMap");
            Map map2 = (Map) getKeyInnerMapMethod.invoke(toObject);
            Object o2 = map2.get("1");
            System.out.println(getIMethod.invoke(o));
            System.out.println("map: "+getIMethod.invoke(o2));
            Object innerObject = getInnerMethod.invoke(toObject);
            System.out.println("invoke inner get i:"+getIMethod.invoke(innerObject));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(List.class.getClassLoader());
//        System.out.println(Map.class.getClassLoader());
//        if (List.class.isAssignableFrom(LinkedList.class)){
//            System.out.println("sdfsdfsdf");
//        }
//        if (Set.class.isAssignableFrom(HashSet.class)){
//            System.out.println("wererwerwer");
//        }
//        if (Map.class.isAssignableFrom(HashMap.class)){
//            System.out.println("ertertert");
//        }
//        Map map = new HashMap();
//        System.out.println(toBean.getInner().getI());
//
//        String[] a = (String[])null;
//        System.out.println(a);
        Field field = null;
        try {
            Main main = new Main();
            field = Main.class.getField("a");
            Object o = field.get(main);
            System.out.println(field.getType());
            System.out.println(o.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Type genericFieldType = field.getGenericType();

        if(genericFieldType instanceof ParameterizedType){
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for(Type fieldArgType : fieldArgTypes){
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("fieldArgClass = " + fieldArgClass);
            }
        }
    }
    public List<Object> list = new ArrayList();
    public Map<String,Object> map = new HashMap<String, Object>();
    public Object a = new FromBean();

    public static Object convert(Object fromObject,Class toClass){
        if (fromObject.getClass() == toClass || fromObject == null || toClass.isAssignableFrom(fromObject.getClass())){
            return fromObject;
        }
        Object toObject = null;
        try {
            toObject = toClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ;
        Class fromClass = fromObject.getClass();
        Field[] fields = fromClass.getDeclaredFields();
        for (Field fromField:fields
             ) {
            try{
                fromField.setAccessible(true);
                Object fromVal = fromField.get(fromObject);
                if (fromVal == null){
                    continue;
                }
                String fieldName = fromField.getName();
                if("serialVersionUID".equals(fieldName)){
                    continue;
                }
                Field toField = toClass.getDeclaredField(fieldName);
                toField.setAccessible(true);
                Class fromFieldClass = fromVal.getClass();
                Class toFieldClass = toField.getType();
                if(fromFieldClass.getClassLoader() != null){
                    // 复杂对象
                    toField.set(toObject,convert(fromVal,toFieldClass));
                }else{
                    // 判断范型是否是复杂对象,如果范型是object也作为复杂对象
//                    boolean isComplex = false;
//                    boolean hasGenericType = false;
//                    Type genericFieldType = fromField.getGenericType();
//                    if(genericFieldType instanceof ParameterizedType){
//                        hasGenericType = true;
//                        ParameterizedType aType = (ParameterizedType) genericFieldType;
//                        Type[] fieldArgTypes = aType.getActualTypeArguments();
//                        for(Type fieldArgType : fieldArgTypes){
//                            Class fieldArgClass = (Class) fieldArgType;
//                            if (fieldArgClass.getClassLoader() != null || (fieldArgClass == Object.class)){
//                                isComplex = true;
//                                break;
//                            }
//                        }
//                    }
//                    if (hasGenericType && !isComplex){
//                        toField.set(toObject,fromVal);
//                        continue;
//                    }
                    ClassLoader toClassLoader = toClass.getClassLoader();
                    if (List.class.isAssignableFrom(fromFieldClass)){
                        List fromList = (List)fromVal;
                        List toList = (List)fromFieldClass.newInstance();
                        for (Object o : fromList) {
                            if (o.getClass().getClassLoader() != null){
                                // 这边强制使用同类名转换
                                toList.add(convert(o,toClassLoader.loadClass(o.getClass().getName())));
                            }
                        }
                        toField.set(toObject,toList);
                    }else if (Map.class.isAssignableFrom(fromFieldClass)){
                        Map fromMap = (Map)fromVal;
                        Map toMap = (Map)fromFieldClass.newInstance();
                        for (Object fromKey:fromMap.keySet()) {
                            Object fromValue = fromMap.get(fromKey);
                            Object toKey = convert(fromKey,toClassLoader.loadClass(fromKey.getClass().getName()));
                            Object toValue = convert(fromValue,toClassLoader.loadClass(fromValue.getClass().getName()));
                            toMap.put(toKey,toValue);
                        }
                        toField.set(toObject,toMap);
                    }else if (Set.class.isAssignableFrom(fromFieldClass)){
                        Set fromSet = (Set)fromVal;
                        Set toSet = (Set)fromFieldClass.newInstance();
                        for (Object o : fromSet) {
                            if (o.getClass().getClassLoader() != null){
                                // 这边强制使用同类名转换
                                toSet.add(convert(o,toClass.getClassLoader().loadClass(o.getClass().getName())));
                            }
                        }
                        toField.set(toObject,toSet);
                    }else{
                        toField.set(toObject,fromVal);
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return toObject;
    }


}
