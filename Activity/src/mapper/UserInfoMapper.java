package mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import bean.UserInfo;

public interface UserInfoMapper {
	//��ȡĳ�����ŵ�ȫ���û������ֺ�jobId
	public List<Map<String, Object>> getAllUserNameAndJobIdOfGroup(@Param("part") int partId, @Param("group") int groupId);
	//����ĳһ���û���ȫ����Ϣ����������
	public int changeUserInfoAllByJobIdWithWhere(UserInfo info);
	//����ĳһ���û���ȫ����Ϣ������������
	public int changeUserInfoAllByJobIdNoWhere(UserInfo info);
	//��ȡ�û�ȫ����Ϣ�������û�jobId
	public UserInfo findByJobId(@Param("jobId") String jobId);
	//��ȡȫ���û����ӵڼ����ڼ�
	public List<Map<String,Object>> getUsersInfoOfAllByPageLimit(@Param("begin") int begin, @Param("end") int end);
	//�����Ż�ȡȫ���û����ӵڼ����ڼ�
	public List<Map<String,Object>> getUsersInfoOfPartByPageLimit(@Param("partId") int partId, @Param("begin") int begin, @Param("end") int end);
	//��ȡȫ���û�����
	public int getMemberNumbersOfAll();
	//��ȡĳ�������û�����
	public int getMemberNumbersOfPart(@Param("partId") int partId);
	
	
	//�����û�������Ϣ���������š�С�顢�û���𣬸���jobId���ġ�
	public int changeUserBaseInfoWithTime(@Param("jobId") String jobId, @Param("part") int part,
                                          @Param("group") int group, @Param("kind") int kind, @Param("joinTime") Date changeTime, @Param("post") String post);
	//�����û�������Ϣ�������û���𣬸���jobId���ġ�
	public int changeUserBaseInfoNoTime(@Param("jobId") String jobId, @Param("kind") int kind, @Param("post") String post);
	
	//��ȡ�û�С��
	public int getUserGroupByJobId(@Param("jobId") String jobId);
	//��ȡ�û�����
	public int getUserPartByJobId(@Param("jobId") String jobId);
	//��ȡ�û���
	public String getUserNameByJobId(@Param("jobId") String jobId);
	//������û�
	public int addNewUser(UserInfo userInfo);
	//�Ƿ�������û�
	public int hasUserByJobId(@Param("jobId") String jobId);
	//��ȡ�û�����
	public int getUserKindByJobId(@Param("jobId") String jobId);
	//����jobId�ҵ��û�
	public Map<String,Object> getUserInfoByJobIdToMap(@Param("jobId") String jobId);
	//��ȡ�û�����
	public String getUserPasswordByJobId(@Param("jobId") String jobId);
	//�޸�����
	public int changePassword(@Param("jobId") String jobId, @Param("password") String password);
	//ͨ��jobId�����û��Լ������䡢�绰����ַ
	public int changeMyPersonInfoByJobId(@Param("jobId") String jobId, @Param("tel") String tel,
                                         @Param("email") String email, @Param("addr") String addr);
	//��ȡĳС�������
	public int getMemberNumbersOfGroup(@Param("partId") int partId, @Param("groupId") int groupId);
	//��ĳһ�������г��������������Ϣ
	public List<UserInfo> findUsersGroupOfGroupId(@Param("partId") int partId, @Param("groupId") int groupId,
                                                  @Param("begin") int begin, @Param("end") int end);
	//ͨ�����ź������Ӧ����
	public UserInfo findByJobIdAndPassword(@Param("jobId") String jobId, @Param("password") String password);
	//��ȡ�û�����
	public int getUserStatusByJobId(@Param("jobId") String jobId);
	//�����û�״̬
	public int changeUserStatusByJobId(@Param("jobId") String jobId, @Param("status") int status);
	//��ȡ�û��������
	public int getUserPasawordErrorTimes(@Param("jobId") String jobId);
	//�ı�������
	public int changeUserPasswordErrorTimes(@Param("jobId") String jobId, @Param("times") int times);
	
	
	//��ȡ���е�jobId
	public List<String> getAllUserJobId();
	//��ȡ���е�jobId�����ŵ�
	public List<String> getAllUserJobIdOfPart(@Param("partId") int partId);
	//��ȡ���е�jobId��С���
	public List<String> getAllUserJobIdOfGroup(@Param("partId") int partId, @Param("groupId") int groupId);
	
}
