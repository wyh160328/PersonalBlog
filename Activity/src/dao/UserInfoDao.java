package dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import bean.UserInfo;
import mapper.UserFrozenMapper;
import mapper.UserInfoMapper;

public class UserInfoDao {
	
	@Resource
	private UserFrozenMapper ufMapper;
	@Resource
	private UserInfoMapper uiMapper;

	
	/**
	 * ��ȡĳ�����ŵ�ȫ���û������ֺ�jobId
	 * @param partId
	 * @param groupId
	 * @return
	 */
	public List<Map<String, Object>> getAllUserNameAndJobIdOfGroup(int partId, int groupId) {
		List<Map<String, Object>> result = null;
		result = uiMapper.getAllUserNameAndJobIdOfGroup(partId, groupId);
		if(result!=null && result.size()==0){
			result = null;
		}
		return result;
	}
	
	
	/**
	 * ����ĳһ���û���ȫ����Ϣ������������������true��
	 * @param info
	 * @param changeTime 
	 * @return
	 * @throws Exception 
	 */
	public boolean changeUserInfoAllByJobId(UserInfo info,boolean changeTime) throws Exception{
		boolean result = false;
			//״̬�Ƿ����
		if(getUserStatusByJobId(info.getJobId())!=info.getStatus()){
			//����״̬
			if(!changeUserStatusByJobId(info.getJobId(), info.getStatus())){
				return false;
			}
		}
		int returnNum = 0;
		if(changeTime){
			info.setJoinTime(new Date());
			returnNum = uiMapper.changeUserInfoAllByJobIdWithWhere(info);
		}else{
			returnNum = uiMapper.changeUserInfoAllByJobIdNoWhere(info);
		}
		if(returnNum==1){
			result = true;
		}else{
			throw new Exception();
		}
		return result;
	}
	
	/**
	 * ��ȡ�û�ȫ����Ϣ�������û�jobId
	 * @param jobId
	 * @return
	 */
	public UserInfo getUserInfoByJobId(String jobId){
		UserInfo result = null;
		result = uiMapper.findByJobId(jobId);
		return result;
	}
	
	/**
	 * ��ȡȫ���û����ӵڼ����ڼ�
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Map<String,Object>> getUsersInfoOfAllByPageLimit(int begin, int end) {
		List<Map<String,Object>> result = null;
		result = uiMapper.getUsersInfoOfAllByPageLimit(begin, end);
		return result;
	}
	
	/**
	 * �����Ż�ȡȫ���û����ӵڼ����ڼ�
	 * @param partId
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Map<String,Object>> getUsersInfoOfPartByPageLimit(int partId, int begin, int end) {
		List<Map<String,Object>> result = null;
		result = uiMapper.getUsersInfoOfPartByPageLimit(partId, begin, end);
		if(result!=null && result.size()==0){
			result = null;
		}
		return result;
	}
	
	/**
	 * ��ȡȫ���û�����
	 * @return
	 */
	public int getMemberNumbersOfAll(){
		int result = -1;
		result = uiMapper.getMemberNumbersOfAll();
		return result;
	}
	
	/**
	 * ��ȡĳ�������û�����
	 * @param partId
	 * @return
	 */
	public int getMemberNumbersOfPart(int partId){
		int result = -1;
		result = uiMapper.getMemberNumbersOfPart(partId);
		return result;
	}
	
	/**
	 * �����û�������Ϣ���������š�С�顢�û���𣬸���jobId���ġ�
	 * @param jobId
	 * @param part
	 * @param group
	 * @param kind
	 * @return
	 * @throws Exception 
	 */
	public boolean changeUserBaseInfo(String jobId,int part,int group,int kind,String post,boolean changeTime) throws Exception{
		boolean result = false;
		if(changeTime){
			//�ı�������
			if(uiMapper.changeUserBaseInfoWithTime(jobId, part, group, kind, new Date(),post)==1){
				result = true;
			}
		}else{
			//û������
			if(uiMapper.changeUserBaseInfoNoTime(jobId, kind,post)==1){
				result = true;
			}
		}
		if(result){
			return true;
		}else{
			throw new Exception();
		}
	}
	
	/**
	 * ��ȡ�û�С�顣ʧ���򷵻�-1
	 * @param jobId
	 * @return
	 */
	public int getUserGroupByJobId(String jobId) {
		int result = -1;
			result = uiMapper.getUserGroupByJobId(jobId);
			return result;
	}
	
	/**
	 * ��ȡ�û����š�ʧ���򷵻�-1
	 * @param jobId
	 * @return
	 */
	public int getUserPartByJobId(String jobId){
		int result = -1;
			result = uiMapper.getUserPartByJobId(jobId);
			return result;
	}
	
	/**
	 * ��ȡ�û���
	 * @param jobId
	 * @return
	 */
	public String getUserNameByJobId(String jobId){
		String result = null;
			result = uiMapper.getUserNameByJobId(jobId);
			return result;
	}
	
	/**
	 * ��������û�
	 * @param usersList
	 * @return
	 * @throws Exception
	 */
	public boolean createNewUsers(List<UserInfo> usersList) throws Exception{
		boolean res = false;
		for(int i=0;i<usersList.size();i++){
			usersList.get(i).setErrorTimes(0);
			usersList.get(i).setJoinTime(new Date());
//			System.out.println("Dao:("+i+")"+usersList.get(i).getKind());
			int insertRes = uiMapper.addNewUser(usersList.get(i));
			if(insertRes==0){
				res = false;
				break;
			}else if(i+1==usersList.size()){
				res = true;
			}
		}
		if(res){
			return true;
		}else{
			throw new Exception();
		}
	}
	
	/**
	 * �Ƿ�������û�
	 * @param jobId
	 * @return
	 */
	public boolean hasUserByJobId(String jobId){
		boolean res = false;
		int num = uiMapper.hasUserByJobId(jobId);
		if(num!=0){
			res = true;
		}
		return res;
	}
	
	/**
	 * ͨ��jobId��ȡ�û����ͣ�int��
	 * @param id  jobId
	 * @return ʧ��Ϊ-1
	 */
	public int getUserKindByJobId(String id){
		int res = -1;
			res = uiMapper.getUserKindByJobId(id);
			return res;
	}
	
	/**
	 * ����jobId�ҵ�������
	 * @param jobId
	 * @return
	 */
	public Map<String,Object> getOtherInfoByJobId(String jobId){
		Map<String,Object> resMap = null;
			resMap = uiMapper.getUserInfoByJobIdToMap(jobId);
			return resMap;
	}
	
	/**
	 * ��֤����
	 * @param jobId
	 * @param password
	 * @return
	 */
	public boolean validatePasswordByJobId(String jobId,String password){
		boolean result = false;
			String correctPwd = uiMapper.getUserPasswordByJobId(jobId);
			if(correctPwd.equals(password)){
				result = true;
			}
			return result;
	}
	
	/**
	 * �޸�����
	 * @param jobId
	 * @param password ������
	 * @return �Ƿ�ɹ�
	 * @throws Exception 
	 */
	public boolean changePassword(String jobId,String password) throws Exception{
		int num = uiMapper.changePassword(jobId, password);
		if(num>0){
			return true;
		}else{
			throw new Exception();
		}
	}
	
	/**
	 * ͨ��jobId�����û��Լ������䡢�绰����ַ
	 * @param jobId
	 * @param tel
	 * @param email
	 * @param addr
	 * @return
	 * @throws Exception 
	 */
	public boolean changeMyPersonInfoByJobId(String jobId,String tel,String email,String addr) throws Exception{
		int n = uiMapper.changeMyPersonInfoByJobId(jobId, tel, email, addr);
		if(n>=1){
			return true;
		}else{
			throw new Exception();
		}
	}
	
	/**
	 * ��ȡĳС�������
	 * @param partId
	 * @param groupId
	 * @return -1Ϊ����ʧ��
	 */
	public int getMemberNumbersOfGroup(int partId,int groupId){
		int result = -1;
			result = uiMapper.getMemberNumbersOfGroup(partId, groupId);
			return result;
	}
	
	/**
	 * ��ĳһ�������г��������������Ϣ
	 * @param groupId
	 * @return
	 */
	public List<UserInfo> findUsersGroupOfGroupId(int partId,int groupId,int begin,int end){
		List<UserInfo> result = null;
			result = uiMapper.findUsersGroupOfGroupId(partId, groupId, begin, end);
			//�������
			if(result!=null && result.size()!=0){
				for(int i=0;i<result.size();i++){
					result.get(i).setPassword(null);
				}
			}
			return result;
	}
	
	/**
	 * ͨ����������Ϣ
	 * @param jobId
	 * @return
	 */
	public Map<String, Object> findUserByJobId(String jobId){
		Map<String, Object> result = null;
		result = uiMapper.getUserInfoByJobIdToMap(jobId);
		if(result!=null){
			result.remove("password");
		}
		return result;
	}
	
	/**
	 * ͨ�����ŵ�¼
	 * @param jobId
	 * @param password
	 * @return
	 */
	public UserInfo checkLoginByJobId(String jobId,String password){
		UserInfo info = null;
			info = uiMapper.findByJobIdAndPassword(jobId, password);
			return info;
	}

	
	/**
	 * ��ѯ�û�״̬
	 * @param jobId
	 * @return
	 */
	@SuppressWarnings("finally")
	public int getUserStatusByJobId(String jobId){
		int result = -1;
		try{
			result = uiMapper.getUserStatusByJobId(jobId);
		}catch (Exception e) {
		}finally{
			return result;
		}
		
	}
	
	/**
	 * �����û�״̬
	 * @param jobId
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	public boolean changeUserStatusByJobId(String jobId, int status) throws Exception {
		boolean result = false;
		boolean canGo = true;
//		System.out.println("�����״̬"+",status:"+status+",jobId��"+jobId);
		//������ᣬ��ӵ�����
		if(status==UserInfo.STATUS_FROZEN_15_MINUTE || status==UserInfo.STATUS_FROZEN_30_MINUTE || status==UserInfo.STATUS_FROZEN_24_HOUR){
			if(!changeUserStatusToFrozenByJobId(jobId,status)){
				canGo = false;
			}
		}
		if(canGo){
			int num = uiMapper.changeUserStatusByJobId(jobId, status);
			if(num==1){
				result = true;
			}else{
				throw new Exception();
			}
		}
		return result;
	}
	
	/**
	 * ��ӵ������б�
	 * @param jobId
	 * @param status
	 * @return
	 */
	public boolean changeUserStatusToFrozenByJobId(String jobId, int status) throws Exception {
		boolean result = false;
		Long times = new Date().getTime();
		//������ᣬ��ӵ�����
		if(status==UserInfo.STATUS_FROZEN_15_MINUTE){
			times += 1000*60*15;
		}else if(status==UserInfo.STATUS_FROZEN_30_MINUTE){
			times += 1000*60*30;
		}else if(status==UserInfo.STATUS_FROZEN_24_HOUR){
			times += 1000*60*60*24;
		}else{
			return false;
		}
		int num = ufMapper.changeUserStatusToFrozenByJobId(jobId, times);
		if(num>1){
			result = true;
		}
		return result;
	}
	
	/**
	 * �ⶳ
	 * @param jobId
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	public boolean changeUserStatusOutOfFrozenByJobId(String jobId) throws Exception {
		boolean result = false;
		//���б���ɾ��
		int num = ufMapper.delUserFromFrozenByJobId(jobId);
		if(num==1){
			//����״̬
			if(uiMapper.changeUserStatusByJobId(jobId, UserInfo.STATUS_NORMAL)==1){
				result = true;
			}else{
				throw new Exception();
			}
		}
		return result;
	}
	
	/**
	 * ����Ҫ�ⶳ���û�ô
	 * @return
	 */
	public String hasNeedToOutOfFrozen(){
		long nowTime = new Date().getTime();
		String result = null;
		if(ufMapper.getNeedOutOfFrozenNumber(nowTime) != 0){
			result = ufMapper.getNeedOutOfFrozenJobId();
		}
		return result;
	}
	
	/**
	 * ��ô������
	 * @param jobId
	 * @return
	 */
	public int getUserPasawordErrorTimes(String jobId) {
		int result = -1;
		result = uiMapper.getUserPasawordErrorTimes(jobId);
		return result;
	}
	
	/**
	 * �ı�������
	 * @param jobId
	 * @param times
	 * @return
	 * @throws Exception 
	 */
	public boolean changeUserPasswordErrorTimes(String jobId, int times) throws Exception {
		int num = uiMapper.changeUserPasswordErrorTimes(jobId, times);
		if(num==1){
			return true;
		}else{
			throw new Exception("���ص�����Ϊ��"+num+",times:"+times+",jobId:"+jobId);
		}
	}
	
	
	
}
