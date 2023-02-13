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
import self.cases.teams.service.TeamsService;
import self.cases.teams.utils.DateUtils;
import self.cases.teams.utils.IDUtils;
import self.cases.teams.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("teamsService")
public class TeamsServiceImpl implements TeamsService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private TeamTypesDao teamTypesDao;

    @Autowired
    private TeamsDao teamsDao;

    @Autowired
    private MembersDao membersDao;

    @Autowired
    private NoticesDao noticesDao;

    @Autowired
    private ActivitiesDao activitiesDao;

    @Autowired
    private ActiveLogsDao activeLogsDao;

    @Autowired
    private ApplyLogsDao applyLogsDao;

    @Autowired
    private PayLogsDao payLogsDao;


    @Override
    @Transactional
    public void add(Teams teams) {

        teamsDao.insert(teams);

        Members member = new Members();
        member.setId(IDUtils.makeIDByCurrent());
        member.setUserId(teams.getManager());
        member.setTeamId(teams.getId());
        member.setCreateTime(DateUtils.getNowDate());
        membersDao.insert(member);

        Users user = usersDao.selectById(teams.getManager());
        user.setType(1);
        usersDao.updateById(user);
    }

    @Override
    @Transactional
    public void update(Teams teams) {

        teamsDao.updateById(teams);
    }

    @Override
    @Transactional
    public void delete(Teams teams) {

        QueryWrapper<Notices> qw_notice = new QueryWrapper<Notices>();
        qw_notice.eq("team_id", teams.getId());
        noticesDao.delete(qw_notice);

        QueryWrapper<PayLogs> qw_pay = new QueryWrapper<PayLogs>();
        qw_pay.eq("team_id", teams.getId());
        payLogsDao.delete(qw_pay);

        QueryWrapper<ApplyLogs> qw_apply = new QueryWrapper<ApplyLogs>();
        qw_apply.eq("team_id", teams.getId());
        applyLogsDao.delete(qw_apply);

        QueryWrapper<Members> qw_members = new QueryWrapper<Members>();
        qw_members.eq("team_id", teams.getId());
        membersDao.delete(qw_members);

        QueryWrapper<Activities> qw_active = new QueryWrapper<Activities>();
        qw_active.eq("team_id", teams.getId());
        for(Activities activitie : activitiesDao.selectList(qw_active)){

            QueryWrapper<ActiveLogs> qw_active_log = new QueryWrapper<ActiveLogs>();
            qw_active_log.eq("active_id", activitie.getId());
            activeLogsDao.delete(qw_active_log);
        }
        activitiesDao.delete(qw_active);

        teamsDao.deleteById(teams);

        QueryWrapper<Teams> qw_team = new QueryWrapper<Teams>();
        qw_team.eq("manager", teams.getManager());
        if(teamsDao.selectCount(qw_team) <= 0){

            Users user = usersDao.selectById(teams.getManager());
            user.setType(2);
            usersDao.updateById(user);
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Teams getOne(String id) {

        Teams teams = teamsDao.selectById(id);

        return teams;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Teams> getAll(){

        QueryWrapper<Teams> qw = new QueryWrapper<Teams>();

        qw.orderByDesc("create_time");

        List<Teams> list = teamsDao.selectList(qw);

        return list;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Teams> getListByManId(String manId){

        QueryWrapper<Teams> qw = new QueryWrapper<Teams>();
        qw.eq("manager", manId);
        qw.orderByDesc("create_time");

        List<Teams> list = teamsDao.selectList(qw);

        return list;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageData getPageInfo(Long pageIndex, Long pageSize, Teams teams) {

        QueryWrapper<Teams> qw = new QueryWrapper<Teams>();

        if(StringUtils.isNotNullOrEmpty(teams.getName())){

            qw.like("name", teams.getName());
        }

        if(StringUtils.isNotNullOrEmpty(teams.getTypeId())){

            qw.eq("type_id", teams.getTypeId());
        }

        if(StringUtils.isNotNullOrEmpty(teams.getManager())){

            qw.eq("manager", teams.getManager());
        }

        qw.orderByDesc("create_time");

        Page<Teams> page =
                teamsDao.selectPage(new Page<Teams>(pageIndex, pageSize), qw);

        return parsePage(page);
    }

    /**
     * 转化分页查询的结果
     */
    public PageData parsePage(Page<Teams> p) {

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        for (Teams teams : p.getRecords()) {

            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("id", teams.getId());
            temp.put("name", teams.getName());
            temp.put("createTime", teams.getCreateTime());
            temp.put("total", teams.getTotal());

            Users user = usersDao.selectById(teams.getManager());
            temp.put("manager", teams.getManager());
            temp.put("managerName", user.getName());
            temp.put("managerPhone", user.getPhone());
            temp.put("managerAddress", user.getAddress());

            TeamTypes teamType = teamTypesDao.selectById(teams.getTypeId());
            temp.put("typeId", teams.getTypeId());
            temp.put("typeName", teamType.getName());
            resl.add(temp);
        }

        PageData pageData = new PageData(p.getCurrent(), p.getSize(), p.getTotal(), resl);

        return pageData;
    }
}