package dao;

import bean.LogBean;
import mapper.LogDaoMapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

public class LogDao {
    @Resource
    LogDaoMapper logDaoMapper;
    public void addLog(LogBean logBean) throws Exception{
        logDaoMapper.addLog(logBean);
    }
    public List<LogBean> findLog(@Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize, @Param(value="username") String username){
        return logDaoMapper.findLog(startPos,pageSize,username);
    }
    public int pageCounts(@Param(value="username") String username){
        return logDaoMapper.pageCounts(username);
    }
}
