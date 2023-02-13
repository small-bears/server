package self.cases.teams.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import self.cases.teams.msg.R;

import javax.servlet.http.HttpSession;

/**
 * 系统控制器抽象类
 * 其他控制器需要继承这个控制器
 */
public abstract class BaseController {

    protected static final String SESSION_USER = "user";

    /**
     * 获取登录用户的信息
     * @param session 会话对象
     * @return
     */
    protected Object getSessionUser(HttpSession session) {

        return session.getAttribute(SESSION_USER);
    }

    /**
     * 将登录用户信息记录到会话对象中
     * @param session 会话对象
     * @param user 登录用户信息
     */
    protected void setSessionUser(HttpSession session, Object user) {

        session.setAttribute(SESSION_USER, user);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exception(Exception e) {

        e.printStackTrace();

        return R.error();
    }
}
