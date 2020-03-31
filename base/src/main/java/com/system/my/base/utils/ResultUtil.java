package com.system.my.base.utils;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/26 16:51
 * @describe
 **/
public class ResultUtil {
    /**
     * 成功且带数据
     **/
    public static <T>Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.setStatus(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 成功带数据返回自定义msg
     * @param msg
     * @param object
     * @param <T>
     * @return
     */
    public static <T>Result<T> success(String msg,T object){
        Result<T> result = new Result<T>();
        result.setStatus(ResultEnum.SUCCESS.getCode());
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    /**
     * 成功返回msg不带数据
     * @param msg
     * @return
     */
    public static <T>Result<T> success(String msg){
        Result<T> result = new Result<T>();
        result.setStatus(ResultEnum.SUCCESS.getCode());
        result.setMsg(msg);
        return result;
    }

    /**
     * 成功但不带数据
     **/
    public static <T>Result<T> success() {

        return success(null);
    }
    /**
     * 失败
     **/
    public static <T>Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<T>();
        result.setStatus(code);
        result.setMsg(msg);
        return result;
    }
}
