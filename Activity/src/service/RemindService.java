package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ProcedureDao;
import dao.RemindDao;

@Transactional(readOnly = true)
@Service
public class RemindService {
	private RemindDao remindDao;
	private ProcedureDao procedureDao;
	public RemindDao getRemindDao() {
		return remindDao;
	}
	public void setRemindDao(RemindDao remindDao) {
		this.remindDao = remindDao;
	}
	public ProcedureDao getProcedureDao() {
		return procedureDao;
	}
	public void setProcedureDao(ProcedureDao procedureDao) {
		this.procedureDao = procedureDao;
	}
	/**
	 * ɾ��һ��ָ��������
	 * @param jobId
	 * @param msgId
	 * @param isMag
	 * @return
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public boolean takeIdRead(String jobId, int msgId, boolean isMag) throws Exception{
		return remindDao.takeIdRead(jobId, msgId, isMag);
	}
	
	/**
	 * �Ƿ��Ķ���
	 * @param jobId
	 * @param msgId
	 * @param isMag
	 * @return
	 */
	public boolean isRead(String jobId, int msgId, boolean isMag) {
		return remindDao.isRead(jobId,msgId,isMag);
	}
	
	
	/**
	 * ��ȡĳ������Ҫ���ѵ���ϢID��
	 * @param jobId
	 * @return
	 */
	public int getRemindMsgNumberById(String jobId){
		return remindDao.getNeedRemindMessageNumberByJobId(jobId);
	}
	
	/**
	 * ��ȡĳ������Ҫ���ѵĹ���ID��
	 * @param jobId
	 * @return
	 */
	public int getRemindNoticeNumberById(String jobId){
		return remindDao.getNeedRemindNoticeByJobId(jobId);
	}
	
	/**
	 * ��ȡĳ������Ҫ���ѵ�����ID��
	 * @param jobId
	 * @return
	 */
	public int getRemindProcedureNumberById(String jobId){
		return procedureDao.getNeedRemindProcedureNumberByJobId(jobId);
	}
	
	
}
