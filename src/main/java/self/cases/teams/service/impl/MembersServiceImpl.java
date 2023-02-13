package self.cases.teams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import self.cases.teams.dao.*;
import self.cases.teams.entity.*;
import self.cases.teams.msg.PageData;
import self.cases.teams.service.MembersService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("membersService")
public class MembersServiceImpl implements MembersService {

    @Autowired
    private TeamsDao teamsDao;

    @Autowired
    private MembersDao membersDao;

    @Autowired
    private PayLogsDao payLogsDao;

    @Autowired
    private ActiveLogsDao activeLogsDao;

    @Autowired
    private ApplyLogsDao applyLogsDao;

    @Override
    @Transactional
    public void add(Members members) {

        membersDao.insert(members);
    }

    @Override
    @Transactional
    public void update(Members members) {

        membersDao.updateById(members);
    }

    @Override
    @Transactional
    public void delete(Members members) {

        QueryWrapper<PayLogs> qw_pay = new QueryWrapper<PayLogs>();
        qw_pay.eq("user_id", members.getUserId());
        payLogsDao.delete(qw_pay);

        QueryWrapper<ActiveLogs> qw_active = new QueryWrapper<ActiveLogs>();
        qw_active.eq("user_id", members.getUserId());
        activeLogsDao.delete(qw_active);

        QueryWrapper<ApplyLogs> qw_apply = new QueryWrapper<ApplyLogs>();
        qw_apply.eq("user_id", members.getUserId());
        applyLogsDao.delete(qw_apply);

        membersDao.deleteById(members);

        Teams team = teamsDao.selectById(members.getTeamId());
        team.setTotal(team.getTotal() - 1);
        teamsDao.updateById(team);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Members getOne(String id) {

        Members members = membersDao.selectById(id);

        return members;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Boolean isManager(String teamId, String userId){

        QueryWrapper<Teams> qw = new QueryWrapper<Teams>();
        qw.eq("manager", userId);
        qw.eq("id", teamId);

        return teamsDao.selectCount(qw) > 0;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageData getPageAll(Long pageIndex, Long pageSize, String teamName, String userName) {

        Page<Map<String, Object>> page =
                membersDao.qryPageAll(new Page<Map<String, Object>>(pageIndex, pageSize), teamName, userName);

        return parsePage(page);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageData getPageByManId(Long pageIndex, Long pageSize, String manId, String teamName, String userName) {

        Page<Map<String, Object>> page =
                membersDao.qryPageByManId(new Page<Map<String, Object>>(pageIndex, pageSize), manId, teamName, userName);

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