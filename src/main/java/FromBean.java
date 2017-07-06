import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dengrong on 2017/6/21.
 */
public class FromBean {
    private String a;
    private String b;

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    private List<Object> objectList;


    public List<FromInner> getFromInnerList() {
        return fromInnerList;
    }

    public void setFromInnerList(List<FromInner> fromInnerList) {
        this.fromInnerList = fromInnerList;
    }

    private List<FromInner> fromInnerList;

    public Map getKeyInnerMap() {
        return keyInnerMap;
    }

    public void setKeyInnerMap(Map keyInnerMap) {
        this.keyInnerMap = keyInnerMap;
    }

    private Map keyInnerMap;


    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    private List<String> stringList;



    public FromInner getInner() {
        return inner;
    }

    public void setInner(FromInner inner) {
        this.inner = inner;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    private FromInner inner;


}
