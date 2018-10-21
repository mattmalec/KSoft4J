package net.explodingbush.ksoftapi;

public interface KSoftAction<T> {

/**
 * The thing that executes the requests
 * @return A method corresponding to the request
 */
    T execute();

}
