package com.llg.chatweather.http;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 处理错误情况返回的不同实体
 *
 * @param <T>
 */
public final class WeatherResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    WeatherResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        //解析错误是返回的json结构，抛出自定义的异常信息
        String response = value.string();
        try {
            JSONObject json = new JSONObject(response);
            int errcode = json.optInt("errcode", 0);
            if (errcode != 0) {
                String errmsg = json.optString("errmsg");
                throw new ResultException(errmsg, "errcode" + errcode);
            }
        } catch (JSONException e) {
            throw new JsonParseException("response parse error:" + response);
        }
        return gson.fromJson(response, type);
    }
}
