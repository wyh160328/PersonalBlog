package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import bean.ModelShen;

public interface ModelShenMapper {
	//���һ���µ�����
	public int addNewShen(ModelShen modelShen);
	//��ȡһ��ģ���ȫ������
	public List<ModelShen> getShensByProcedureId(@Param("modelId") int modelId);
	//��ȡһ��ģ���������
	public int getNumberOfOneModel(@Param("modelId") int modelId);
	//ɾ��һ��ģ���ȫ������
	public int delAllByModelId(@Param("modelId") int modelId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
