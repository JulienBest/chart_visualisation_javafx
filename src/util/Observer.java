package util;

public interface Observer<T> {
    void update(T changed);
}
