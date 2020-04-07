package service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bean.Part;
import dao.PartDao;

@Transactional(readOnly = true)
@Service
public class PartService {
	public static int PAGE_NUMBER = 5;
	private PartDao partDao;
	public PartDao getPartDao() {
		return partDao;
	}
	public void setPartDao(PartDao partDao) {
		this.partDao = partDao;
	}
	
	/**
	 * ͨ�����ֻ�ȡ����ID
	 * @param name
	 * @return
	 */
	public int getPartByName(String name){
		return partDao.getPartByName(name);
	}
	
	/**
	 * ��ȡĳ�����ŵ�����
	 * @param partId
	 * @return
	 */
	public int getMemberOfPartNumbers(int partId){
		return partDao.getMemberOfPartNumbers(partId);
	}
	
	/**
	 * ��ȡĳ�����ŵ�С����
	 * @param partId
	 * @return
	 */
	public int getGroupOfPartNumbers(int partId){
		return partDao.getGroupOfPartNumbers(partId);
	}
	
	/**
	 * ɾ������
	 * @param partId
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public boolean delPart(int partId) throws Exception{
		return partDao.delPart(partId);
	}
	
	/**
	 * ��Ӳ���
	 * @param name
	 * @param person
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public boolean addPart(String name,String person) throws Exception{
		return partDao.addPart(name, person);
	}
	
	/**
	 * ��ȡ�ڼ�ҳ������
	 * @param page
	 * @return
	 */
	public List<Part> getPartByPage(int page){
		if(page<1){
			page = 1;
		}
		int begin = (page-1)*PAGE_NUMBER;
		int end = PAGE_NUMBER;
		return partDao.getPartsAllInfoByNumber(begin, end);
	}
	
	/**
	 * ��ȡ��ҳ��
	 * @return
	 */
	public int getAllPage(){
		int number = partDao.allPartsCount();
		return (int)Math.ceil(1.0*number/PAGE_NUMBER);
	}
	
	/**
	 * ��ȡ���еĲ���id������
	 * @return
	 */
	public List<Map<String, Object>> getAllPartsAndNames(){
		return partDao.getAllPartsAndNames();
	}
	
	/**
	 * ����ID��ȡ��������
	 * @param id
	 * @return
	 */
	public String getNameById(int id){
		return partDao.getNameById(id);
	}
	
}
