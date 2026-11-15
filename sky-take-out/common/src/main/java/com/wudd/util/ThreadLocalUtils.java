package com.wudd.util;

public class ThreadLocalUtils {
    private static ThreadLocal<String> nameThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> idThreadLocal = new ThreadLocal<>();

    public static void setName(String name){
        nameThreadLocal.set(name);
    }
    public static String getName(){
        return nameThreadLocal.get();
    }
    public static void remove(){
        nameThreadLocal.remove();
    }
    public static void setId(String id){
        idThreadLocal.set(id);
    }
    public static String getId(){
        return idThreadLocal.get();
    }
    public static void removeId(){
        idThreadLocal.remove();
    }
}
