package generics;

public class GenericClass<T,V> {
    private V data2;


    private T data;

    public V getData2() {
        return data2;
    }

    public void setData2(V data2) {
        this.data2 = data2;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public GenericClass(T data,V data2) {
        this.data = data;
        this.data2 = data2;
    }
}
