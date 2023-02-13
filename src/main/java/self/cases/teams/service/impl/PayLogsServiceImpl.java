package self.cases.teams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import self.cases.teams.dao.TeamsDao;
import self.cases.teams.dao.UsersDao;
import self.cases.teams.entity.Teams;
import self.cases.teams.entity.Users;
import self.cases.teams.msg.PageData;
import self.cases.teams.entity.PayLogs;
import self.cases.teams.dao.PayLogsDao;
import self.cases.teams.service.PayLogsService;
import self.cases.teams.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("payLogsService")
public class PayLogsServiceImpl implements PayLogsService {

    @Autowired
    private PayLogsDao payLogsDao;

    @Autowired
    private TeamsDao teamsDao;

    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional
    public void add(PayLogs payLogs) {

        payLogsDao.insert(payLogs);
    }

    @Override
    @Transactional
    public void update(PayLogs payLogs) {

        payLogsDao.updateById(payLogs);
    }

    @Override
    @Transactional
    public void delete(PayLogs payLogs) {

        payLogsDao.deleteById(payLogs);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PayLogs getOne(String id) {

        PayLogs payLogs = payLogsDao.selectById(id);

        return payLogs;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageData getManPageInfo(Long pageIndex, Long pageSize, String userId, String teamName, String userName) {

        Page<Map<String, Object>> page =
                payLogsDao.qryManPageInfo(new Page<Map<String, Object>>(pageIndex, pageSize), userId, teamName, userName);

        return parsePage(page);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageData getPageInfo(Long pageIndex, Long pageSize, String userId, String teamName, String userName) {

        Page<Map<String, Object>> page =
                payLogsDao.qryPageInfo(new Page<Map<String, Object>>(pageIndex, pageSize), userId, teamName, userName);

        return parsePage(page);
    }

    /**
     * 转化分页查询的结果
     */
    public PageData parsePage(Page<Map<String, Object>> p) {

        PageData pageData = new PageData(p.getCurrent(), p.getSize(), p.getTotal(), p.getRecords());

        return pageData;
    }
}