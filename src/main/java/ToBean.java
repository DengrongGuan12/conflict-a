import java.util.List;

/**
 * Created by dengrong on 2017/6/21.
 */
public class ToBean {
    private String a;
    private String b;
    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    private List<Object> objectList;
    public List<ToInner> getFromInnerList() {
        return fromInnerList;
    }

    public void setFromInnerList(List<ToInner> fromInnerList) {
        this.fromInnerList = fromInnerList;
    }

    private List<ToInner> fromInnerList;

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    private List<String> stringList;

    public ToInner getInner() {
        return inner;
    }

    public void setInner(ToInner inner) {
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

    private ToInner inner;
}
