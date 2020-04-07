package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import bean.ProcedureSubmit;

public interface ProcedureSubmitMapper {
	public int addNewSubmit(ProcedureSubmit procedureSubmit);
	public int getNumberOfSubmitByCreatePerson(@Param("createPerson") String jobId);
	public List<Map<String, Object>> getAllMyProcedureSimpleFromNumToNum(@Param("createPerson") String jobId, @Param("begin") int begin, @Param("end") int end);
	
	public ProcedureSubmit findById(@Param("id") int id);
	public Map<String, Object> getMainInfoById(@Param("id") int id);
	public String getCreatePerson(@Param("id") int id);
	public int updateStatus(@Param("id") int id, @Param("status") int status);
	public int getNumberByUserJobIdAndWork(@Param("userJobId") String jobId, @Param("work") int work);
	
	public int delOneSubmit(@Param("id") int id);

	public int getSearchNumById(@Param("id") int id);
	public int getSearchNumByName(@Param("name") String name);
	public int getSearchNumByProject(@Param("projectName") String projectName);

	public List<Map<String, Object>> getSearchListById(@Param("id") int id);
	public List<Map<String, Object>> getSearchListByName(@Param("name") String name, @Param("begin") int begin, @Param("end") int end);
	public List<Map<String, Object>> getSearchListByProject(@Param("projectName") String projectName, @Param("begin") int begin, @Param("end") int end);


}
