package com.llg.chatweather.data;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 数据层资源请求类
 *
 * @param <T>
 */
public class Resource<T> {

    //请求状态
    @NonNull
    private final Status mStatus;
    //请求的数据
    @Nullable
    private final T mData;
    //具体消息反应细节
    @Nullable
    private final String mMessage;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        mStatus = status;
        this.mData = data;
        this.mMessage = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(Status.LOADING, data, null);
    }


    public static <T> Resource<T> error(@Nullable T data, String message) {
        return new Resource<>(Status.ERROR, data, message);
    }

    @NonNull
    public Status getStatus() {
        return mStatus;
    }

    @Nullable
    public T getData() {
        return mData;
    }

    @Nullable
    public String getMessage() {
        return mMessage;
    }

    public enum Status {
        /**
         * status loading
         */
        LOADING,
        /**
         * status success
         */
        SUCCESS,
        /**
         * status error
         */
        ERROR
    }
}
