package self.cases.teams.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.cases.teams.entity.ApplyLogs;

import java.util.Map;

/**
 * 数据层处理接口
 * 申请记录
 */
@Repository("applyLogsDao")
public interface ApplyLogsDao extends BaseMapper<ApplyLogs> {

    /**
     * 分页查询申请记录
     * @param page 分页参数
     * @param userId 用户ID
     * @param teamName 社团名称
     * @param userName 用户姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "al.id, al.status, al.create_time createTime, al.team_id teamId, al.user_id userId, " +
            "t.name teamName, u.name userName, u.gender userGender, u.age userAge, u.phone userPhone " +
            "FROM apply_logs al, teams t, users u " +
            "<where> " +
            "al.user_id = u.id AND al.team_id = t.id " +
            "<if test='teamName != null and teamName.trim() != &quot;&quot; '>" +
            "AND t.name LIKE CONCAT('%', #{teamName}, '%') " +
            "</if>" +
            "<if test='userName != null and userName.trim() != &quot;&quot; '>" +
            "AND u.name LIKE CONCAT('%', #{userName}, '%') " +
            "</if>" +
            "<if test='userId != null and userId.trim() != &quot;&quot; '>" +
            "AND al.user_id = #{userId} " +
            "</if>" +
            "</where>" +
            "ORDER BY al.create_time DESC " +
            "</script>")
    public Page<Map<String, Object>> qryPageInfo(Page<Map<String, Object>> page,
                                                 @Param("userId") String userId,
                                                 @Param("teamName") String teamName,
                                                 @Param("userName") String userName);

    /**
     * 分页查询申请记录
     * @param page 分页参数
     * @param userId 用户ID
     * @param teamName 社团名称
     * @param userName 用户姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "al.id, al.status, al.create_time createTime, al.team_id teamId, al.user_id userId, " +
            "t.name teamName, u.name userName, u.gender userGender, u.age userAge, u.phone userPhone " +
            "FROM apply_logs al, teams t, users u " +
            "<where> " +
            "al.user_id = u.id AND al.team_id = t.id " +
            "<if test='teamName != null and teamName.trim() != &quot;&quot; '>" +
            "AND t.name LIKE CONCAT('%', #{teamName}, '%') " +
            "</if>" +
            "<if test='userName != null and userName.trim() != &quot;&quot; '>" +
            "AND u.name LIKE CONCAT('%', #{userName}, '%') " +
            "</if>" +
            "<if test='userId != null and userId.trim() != &quot;&quot; '>" +
            "AND (al.user_id = #{userId} OR t.manager = #{userId})  " +
            "</if>" +
            "</where>" +
            "ORDER BY al.create_time DESC " +
            "</script>")
    public Page<Map<String, Object>> qryManPageInfo(Page<Map<String, Object>> page,
                                                 @Param("userId") String userId,
                                                 @Param("teamName") String teamName,
                                                 @Param("userName") String userName);
}