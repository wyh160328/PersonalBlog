package service;

import bean.LogBean;
import dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = false)
@Service
public class LogLogService {
	private LogDao logDao;
	public LogDao getLogDao() {
		return logDao;
	}
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
	public void add(LogBean logBean) throws Exception{

		logDao.addLog(logBean);
	}

	public List<LogBean> selectLogBean(Integer startPos, Integer pageSize, String username){
		return logDao.findLog(startPos,pageSize,username);
	}
	public int pageCounts(String username) {
		return logDao.pageCounts(username);
	}
}
