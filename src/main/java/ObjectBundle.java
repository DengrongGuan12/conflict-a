/**
 * Created by dengrong on 2017/6/11.
 */
public class ObjectBundle {
    public ObjectBundle(Object object,Class clazz){
        this.object = object;
        this.clazz = clazz;
    }
    private Object object;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    private Class clazz;

}
