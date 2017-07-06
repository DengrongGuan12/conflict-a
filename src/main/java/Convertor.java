import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;

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
    public static Object convert(Class expectedClass, Object value) {
        if (Object.class.equals(expectedClass) ||
                (value != null && expectedClass.isAssignableFrom(value.getClass()))) {
            return value;
        }
        if (String.class.equals(expectedClass)) {
            return (value != null) ? value.toString() : null;
        }
        if (Boolean.class.equals(expectedClass) || Boolean.TYPE.equals(expectedClass)) {
            if (value instanceof Boolean) {
                return value;
            }
            return Boolean.valueOf(booleanValue(value, false));
        }
        Converter converter = ConvertUtils.lookup(expectedClass);
        if (converter != null) {
            if (value != null) {
                return converter.convert(expectedClass, value);
            }
            if (expectedClass.isPrimitive() || Number.class.isAssignableFrom(expectedClass)) {
                if (BigInteger.class.isAssignableFrom(expectedClass)) {
                    return BigInteger.ZERO;
                } else if (BigDecimal.class.isAssignableFrom(expectedClass)) {
                    return BigDecimal.valueOf(0);
                }
                return converter.convert(expectedClass, value);
            }
        }
        return value;
    }
    public static boolean booleanValue(Object obj, boolean defaultValue) {
        if (obj != null) {
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }

            String stringValue = obj.toString().toLowerCase();
            if (isTrueString(stringValue)) {
                return true;
            } else if (isFalseString(stringValue)) {
                return false;
            }
        }
        return defaultValue;
    }
    private static boolean isTrueString(String lowerCase) {
        return lowerCase.equals("true") || lowerCase.equals("yes")
                || lowerCase.equals("y") || lowerCase.equals("on")
                || lowerCase.equals("1");
    }

    private static boolean isFalseString(String lowerCase) {
        return lowerCase.equals("false") || lowerCase.equals("no")
                || lowerCase.equals("n") || lowerCase.equals("off")
                || lowerCase.equals("0");
    }

}
