package self.cases.teams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import self.cases.teams.dao.ActivitiesDao;
import self.cases.teams.dao.UsersDao;
import self.cases.teams.entity.Activities;
import self.cases.teams.entity.Users;
import self.cases.teams.msg.PageData;
import self.cases.teams.entity.ActiveLogs;
import self.cases.teams.dao.ActiveLogsDao;
import self.cases.teams.service.ActiveLogsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("activeLogsService")
public class ActiveLogsServiceImpl implements ActiveLogsService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ActiveLogsDao activeLogsDao;

    @Autowired
    private ActivitiesDao activitiesDao;

    @Override
    @Transactional
    public void add(ActiveLogs activeLogs) {

        Activities activitie = activitiesDao.selectById(activeLogs.getActiveId());
        activitie.setTotal(activitie.getTotal() + 1);
        activitiesDao.updateById(activitie);

        activeLogsDao.insert(activeLogs);
    }

    @Override
    @Transactional
    public void update(ActiveLogs activeLogs) {

        activeLogsDao.updateById(activeLogs);
    }

    @Override
    @Transactional
    public void delete(ActiveLogs activeLogs) {

        activeLogsDao.deleteById(activeLogs);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Boolean isActive(String activeId, String userId){

        QueryWrapper<ActiveLogs> qw = new QueryWrapper<ActiveLogs>();
        qw.eq("active_id", activeId);
        qw.eq("user_id", userId);

        return activeLogsDao.selectCount(qw) <= 0;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ActiveLogs getOne(String id) {

        ActiveLogs activeLogs = activeLogsDao.selectById(id);

        return activeLogs;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Map<String, Object>> getListByActiveId(String activeId){

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        QueryWrapper<ActiveLogs> qw = new QueryWrapper<ActiveLogs>();
        qw.eq("active_id", activeId);
        qw.orderByDesc("create_time");

        List<ActiveLogs> activeLogs = activeLogsDao.selectList(qw);

        for (ActiveLogs activeLog : activeLogs) {

            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("id", activeLog.getId());
            temp.put("createTime", activeLog.getCreateTime());
            temp.put("activeId", activeLog.getActiveId());

            Users user = usersDao.selectById(activeLog.getUserId());
            temp.put("userId", activeLog.getUserId());
            temp.put("userName", user.getName());
            temp.put("userGender", user.getGender());
            temp.put("userPhone", user.getPhone());

            resl.add(temp);
        }

        return resl;
    }
}