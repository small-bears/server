package self.cases.teams.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.cases.teams.entity.PayLogs;

import java.util.Map;

/**
 * 数据层处理接口
 * 缴费记录
 */
@Repository("payLogsDao")
public interface PayLogsDao extends BaseMapper<PayLogs> {

    /**
     * 分页查询费用记录
     * @param page 分页参数
     * @param userId 用户ID
     * @param teamName 社团名称
     * @param userName 用户姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "pl.id, pl.create_time createTime, pl.total, pl.team_id teamId, pl.user_id userId, " +
            "t.name teamName, u.name userName, u.gender userGender, u.age userAge, u.phone userPhone " +
            "FROM pay_logs pl, teams t, users u " +
            "<where> " +
            "pl.user_id = u.id AND pl.team_id = t.id " +
            "<if test='teamName != null and teamName.trim() != &quot;&quot; '>" +
            "AND t.name LIKE CONCAT('%', #{teamName}, '%') " +
            "</if>" +
            "<if test='userName != null and userName.trim() != &quot;&quot; '>" +
            "AND u.name LIKE CONCAT('%', #{userName}, '%') " +
            "</if>" +
            "<if test='userId != null and userId.trim() != &quot;&quot; '>" +
            "AND pl.user_id = #{userId} " +
            "</if>" +
            "</where>" +
            "ORDER BY pl.create_time DESC " +
            "</script>")
    public Page<Map<String, Object>> qryPageInfo(Page<Map<String, Object>> page,
                                                 @Param("userId") String userId,
                                                 @Param("teamName") String teamName,
                                                 @Param("userName") String userName);

    @Select("<script>" +
            "SELECT " +
            "pl.id, pl.create_time createTime, pl.total, pl.team_id teamId, pl.user_id userId, " +
            "t.name teamName, u.name userName, u.gender userGender, u.age userAge, u.phone userPhone " +
            "FROM pay_logs pl, teams t, users u " +
            "<where> " +
            "pl.user_id = u.id AND pl.team_id = t.id " +
            "<if test='teamName != null and teamName.trim() != &quot;&quot; '>" +
            "AND t.name LIKE CONCAT('%', #{teamName}, '%') " +
            "</if>" +
            "<if test='userName != null and userName.trim() != &quot;&quot; '>" +
            "AND u.name LIKE CONCAT('%', #{userName}, '%') " +
            "</if>" +
            "<if test='userId != null and userId.trim() != &quot;&quot; '>" +
            "AND (pl.user_id = #{userId} OR t.manager = #{userId})  " +
            "</if>" +
            "</where>" +
            "ORDER BY pl.create_time DESC " +
            "</script>")
    public Page<Map<String, Object>> qryManPageInfo(Page<Map<String, Object>> page,
                                                 @Param("userId") String userId,
                                                 @Param("teamName") String teamName,
                                                 @Param("userName") String userName);
}