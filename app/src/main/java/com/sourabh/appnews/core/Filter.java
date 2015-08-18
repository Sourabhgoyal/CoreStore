package com.sourabh.appnews.core;

interface Filter<T, E> {
    boolean isMatchedCategory(T object, E text);
}