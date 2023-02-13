package self.cases.teams.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import self.cases.teams.entity.Users;
import self.cases.teams.handle.CacheHandle;
import self.cases.teams.service.UsersService;
import self.cases.teams.utils.DateUtils;
import self.cases.teams.utils.IDUtils;
import self.cases.teams.msg.R;
import self.cases.teams.msg.PageData;

import self.cases.teams.entity.ActiveLogs;
import self.cases.teams.service.ActiveLogsService;

import java.util.List;
import java.util.Map;

/**
 * 系统请求响应控制器
 * 报名记录
 */
@Controller
@RequestMapping("/activeLogs")
public class ActiveLogsController extends BaseController {

    protected static final Logger Log = LoggerFactory.getLogger(ActiveLogsController.class);

    @Autowired
    private CacheHandle cacheHandle;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ActiveLogsService activeLogsService;

    @RequestMapping("")
    public String index() {

        return "pages/ActiveLogs";
    }

    @GetMapping("/info")
    @ResponseBody
    public R getInfo(String id) {

        Log.info("查找指定报名记录，ID：{}", id);

        ActiveLogs activeLogs = activeLogsService.getOne(id);

        return R.successData(activeLogs);
    }

    @GetMapping("/list")
    @ResponseBody
    public R getList(String activeId) {

        Log.info("获取指定活动的报名记录，活动ID：{}", activeId);

        List<Map<String, Object>> list = activeLogsService.getListByActiveId(activeId);

        return R.successData(list);
    }

    @PostMapping("/add")
    @ResponseBody
    public R addInfo(String token, ActiveLogs activeLogs) {

        Users user = usersService.getOne(cacheHandle.getUserInfoCache(token));

        if(activeLogsService.isActive(activeLogs.getActiveId(), user.getId())){

            activeLogs.setId(IDUtils.makeIDByCurrent());
            activeLogs.setUserId(user.getId());
            activeLogs.setCreateTime(DateUtils.getNowDate());

            Log.info("添加报名记录，传入参数：{}", activeLogs);

            activeLogsService.add(activeLogs);

            return R.success();
        }else{

            return R.warn("该活动您已参与，请勿重复报名");
        }
    }

    @PostMapping("/upd")
    @ResponseBody
    public R updInfo(ActiveLogs activeLogs) {

        Log.info("修改报名记录，传入参数：{}", activeLogs);

        activeLogsService.update(activeLogs);

        return R.success();
    }

    @PostMapping("/del")
    @ResponseBody
    public R delInfo(String id) {

        Log.info("删除报名记录, ID:{}", id);

        ActiveLogs activeLogs = activeLogsService.getOne(id);

        activeLogsService.delete(activeLogs);

        return R.success();
    }
}