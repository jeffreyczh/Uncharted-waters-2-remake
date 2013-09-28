package com.jackyjjc.aoe;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Tuple<T,N> {

    public final T _1;
    public final N _2;

    public Tuple(T t, N n) {
        this._1 = t;
        this._2 = n;
    }
}
