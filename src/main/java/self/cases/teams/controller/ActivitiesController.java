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
import self.cases.teams.utils.IDUtils;
import self.cases.teams.msg.R;
import self.cases.teams.msg.PageData;

import self.cases.teams.entity.Activities;
import self.cases.teams.service.ActivitiesService;

/**
 * 系统请求响应控制器
 * 活动信息
 */
@Controller
@RequestMapping("/activities")
public class ActivitiesController extends BaseController {

    protected static final Logger Log = LoggerFactory.getLogger(ActivitiesController.class);


    @Autowired
    private CacheHandle cacheHandle;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ActivitiesService activitiesService;

    @RequestMapping("")
    public String index() {

        return "pages/Activities";
    }

    @GetMapping("/info")
    @ResponseBody
    public R getInfo(String id) {

        Log.info("查找指定活动信息，ID：{}", id);

        Activities activities = activitiesService.getOne(id);

        return R.successData(activities);
    }

    @GetMapping("/page")
    @ResponseBody
    public R getPageInfos(Long pageIndex, Long pageSize,
                          String token, String teamName, String activeName) {

        Users user = usersService.getOne(cacheHandle.getUserInfoCache(token));

        if (user.getType() == 0) {

            Log.info("分页查找活动信息，当前页码：{}，"
                            + "每页数据量：{}, 模糊查询，社团名称：{}，活动名称：{}", pageIndex,
                    pageSize, teamName, activeName);

            PageData page = activitiesService.getPageAll(pageIndex, pageSize, teamName, activeName);

            return R.successData(page);
        } else {

            Log.info("分页查找活动信息，当前页码：{}，"
                            + "每页数据量：{}, 模糊查询，社团名称：{}，活动名称：{}", pageIndex,
                    pageSize, teamName, activeName);

            PageData page = activitiesService.getPageByUserId(pageIndex, pageSize, user.getId(), teamName, activeName);

            return R.successData(page);
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public R addInfo(Activities activities) {

        activities.setId(IDUtils.makeIDByCurrent());

        Log.info("添加活动信息，传入参数：{}", activities);

        activitiesService.add(activities);

        return R.success();
    }

    @PostMapping("/upd")
    @ResponseBody
    public R updInfo(Activities activities) {

        Log.info("修改活动信息，传入参数：{}", activities);

        activitiesService.update(activities);

        return R.success();
    }

    @PostMapping("/del")
    @ResponseBody
    public R delInfo(String id) {

        Log.info("删除活动信息, ID:{}", id);

        Activities activities = activitiesService.getOne(id);

        activitiesService.delete(activities);

        return R.success();
    }
}