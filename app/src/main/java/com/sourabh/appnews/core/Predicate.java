package com.sourabh.appnews.core;

import java.util.HashMap;

public interface Predicate<T> {
    boolean apply(T type, HashMap<String, String> criteria);

    boolean apply(T type, String criteria);
}
