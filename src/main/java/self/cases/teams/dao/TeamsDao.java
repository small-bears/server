package self.cases.teams.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.cases.teams.entity.Teams;

/**
 * 数据层处理接口
 * 社团信息
 */
@Repository("teamsDao")
public interface TeamsDao extends BaseMapper<Teams> {
	

}