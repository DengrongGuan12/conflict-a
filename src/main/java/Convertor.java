import java.lang.reflect.Field;

/**
 * Created by dengrong on 2017/6/13.
 */
public class Convertor {
    public static Object convert(Class fromClass,Class toClass, Object fromObject){
        Object toObject = null;
        try {
            toObject = toClass.newInstance();
            Field[] fields = fromClass.getDeclaredFields();
            for (Field field:fields
                 ) {
                field.setAccessible(true);
                Object val = field.get(fromObject);
                String fieldName = field.getName();
                if("serialVersionUID".equals(fieldName)){
                    continue;
                }
                Field toField = toClass.getDeclaredField(fieldName);
                toField.setAccessible(true);
                toField.set(toObject,val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toObject;
    }
}
