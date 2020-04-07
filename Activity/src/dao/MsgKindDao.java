package dao;

import java.util.List;

import javax.annotation.Resource;
import bean.MessageKind;
import mapper.MessageKindMapper;

public class MsgKindDao {
	
	@Resource
	private MessageKindMapper mapper;
	
	/**
	 * ���������û�����
	 * @return
	 */
	public List<MessageKind> getAllUserKind(){
		List<MessageKind> result = null;
		result = mapper.getAllKinds();
		return result;
	}
}
