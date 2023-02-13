package self.cases.teams.msg;

import java.util.HashMap;

/**
 * 系统响应信息
 */
public class R extends HashMap<String, Object> {

    /** 处理成功，响应码 */
    public static final Integer SUCCESS_CODE = 0;

    /** 处理成功，默认响应消息 */
    public static final String SUCCESS_MSG = "处理成功";

    /** 操作预警，响应码 */
    public static final Integer WARN_CODE = 1;

    /** 操作预警，默认响应消息 */
    public static final String WARN_MSG = "操作错误";

    /** 操作异常，响应码 */
    public static final Integer ERROR_CODE = 2;

    /** 操作异常，默认响应消息 */
    public static final String ERROR_MSG = "系统异常";

    /**
     * 处理成功默认响应信息
     * @return
     */
    public static R success(){

        R r = new R();
        r.put("code", SUCCESS_CODE);
        r.put("msg", SUCCESS_MSG);
        return r;
    }

    /**
     * 处理成功，返回指定的消息和数据
     * @param msg 指定消息
     * @param data 指定数据
     * @return
     */
    public static R success(String msg, Object data){

        R r = new R();
        r.put("code", SUCCESS_CODE);
        r.put("msg", msg);
        r.put("data", data);
        return r;
    }

    /**
     * 处理成功，返回指定的消息
     * @param msg 指定消息
     * @return
     */
    public static R successMsg(String msg){

        R r = new R();
        r.put("code", SUCCESS_CODE);
        r.put("msg", msg);
        return r;
    }

    /**
     * 处理成功，返回内容中附加数据
     * @param data 指定响应的数据
     * @return
     */
    public static R successData(Object data){

        R r = new R();
        r.put("code", SUCCESS_CODE);
        r.put("msg", SUCCESS_MSG);
        r.put("data", data);
        return r;
    }

    /**
     * 操作警告，返回默认的告警信息
     * @return
     */
    public static R warn(){

        R r = new R();
        r.put("code", WARN_CODE);
        r.put("msg", WARN_MSG);
        return r;
    }

    /**
     * 操作警告，返回指定的告警信息
     * @param msg 指定信息
     * @return
     */
    public static R warn(String msg){

        R r = new R();
        r.put("code", WARN_CODE);
        r.put("msg", msg);

        return r;
    }

    /**
     * 系统异常，返回默认的异常信息
     * @return
     */
    public static R error(){

        R r = new R();
        r.put("code", ERROR_CODE);
        r.put("msg", ERROR_MSG);

        return r;
    }

    /**
     * 系统异常，返回指定的异常信息
     * @param msg 异常信息
     * @return
     */
    public static R error(String msg){

        R r = new R();
        r.put("code", ERROR_CODE);
        r.put("msg", msg);

        return r;
    }
}
