package self.cases.teams.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import self.cases.teams.dao.MembersDao;
import self.cases.teams.dao.TeamsDao;
import self.cases.teams.entity.Members;
import self.cases.teams.entity.Teams;
import self.cases.teams.msg.PageData;
import self.cases.teams.entity.Users;
import self.cases.teams.dao.UsersDao;
import self.cases.teams.service.UsersService;
import self.cases.teams.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private MembersDao membersDao;

    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional
    public void add(Users users) {

        usersDao.insert(users);
    }

    @Override
    @Transactional
    public void update(Users users) {

        usersDao.updateById(users);
    }

    @Override
    @Transactional
    public void delete(Users users) {

        usersDao.deleteById(users);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Boolean isRemove(String userId){

        QueryWrapper<Members> qw = new QueryWrapper<Members>();
        qw.eq("user_id", userId);

        Integer total = membersDao.selectCount(qw);

        return total <= 0;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Users getOne(String id) {

        Users users = usersDao.selectById(id);

        return users;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Users getUserByUserName(String userName) {

        QueryWrapper<Users> qw = new QueryWrapper<Users>();
        qw.eq("user_name", userName);

        Users user = usersDao.selectOne(qw);

        return user;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageData getPageInfo(Long pageIndex, Long pageSize, Users users) {

        QueryWrapper<Users> qw = new QueryWrapper<Users>();

        if (StringUtils.isNotNullOrEmpty(users.getUserName())) {

            qw.like("user_name", users.getUserName());
        }

        if (StringUtils.isNotNullOrEmpty(users.getName())) {

            qw.like("name", users.getName());
        }

        if (StringUtils.isNotNullOrEmpty(users.getPhone())) {

            qw.like("phone", users.getPhone());
        }

        qw.orderByDesc("create_time");

        Page<Users> page =
                usersDao.selectPage(new Page<Users>(pageIndex, pageSize), qw);

        return parsePage(page);
    }

    /**
     * 转化分页查询的结果
     */
    public PageData parsePage(Page<Users> p) {

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        for (Users users : p.getRecords()) {

            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("id", users.getId());
            temp.put("userName", users.getUserName());
            temp.put("passWord", users.getPassWord());
            temp.put("name", users.getName());
            temp.put("gender", users.getGender());
            temp.put("age", users.getAge());
            temp.put("phone", users.getPhone());
            temp.put("address", users.getAddress());
            temp.put("status", users.getStatus());
            temp.put("createTime", users.getCreateTime());
            temp.put("type", users.getType());
            resl.add(temp);
        }

        PageData pageData = new PageData(p.getCurrent(), p.getSize(), p.getTotal(), resl);

        return pageData;
    }
}