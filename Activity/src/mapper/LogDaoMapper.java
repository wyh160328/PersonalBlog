package mapper;

import bean.LogBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDaoMapper {
    public void addLog(LogBean logBean);
    public List<LogBean> findLog(@Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize, @Param(value="username") String username);
    public int pageCounts(@Param(value="username") String username);
}
