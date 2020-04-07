package aspect;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import bean.LogBean;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;


import jdk.nashorn.internal.ir.RuntimeNode.Request;
import service.LogLogService;
import sun.rmi.runtime.Log;

@Aspect
public class SystemLogAspect {
	@Autowired
	LogLogService logService;
	@Autowired
	HttpServletRequest request;

	/**
	 * ���ҵ���߼����������
     * ֻ���½����޸ģ�ɾ���������м�¼
	 */
	@Pointcut("execution(* service.*.*create*(..))")
	public void insertServiceCall() {
	}

	@Pointcut("execution(* service.*.*change*(..))")
	public void updateServiceCall() {
	}

	@Pointcut("execution(* service.*.del*(..))")
	public void deleteServiceCall() {
	}


	@AfterReturning(value = "insertServiceCall()")
	public void insertServiceCallCalls(JoinPoint joinPoint) throws Throwable {
		// ��ȡ��¼����Ա����
		String username = (String) request.getSession().getAttribute("userName");
		System.out.println("username" + username);
		if (username == null) {// û�й���Ա��¼
			return;
		}
		// �жϲ���
		if (joinPoint.getArgs() == null) {// û�в���
			return;
		}
		
		// ��ȡ������
		String methodName = joinPoint.getSignature().getName();
		System.out.println("methodName" + methodName);
		// ��ȡ��������
		String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
		System.out.println("opContent" + opContent);
		// ��ȡϵͳ��ǰʱ��
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creatDate = dateFormat.format(date);
		// ������־����
		LogBean logBean = new LogBean();
		logBean.setT_operationlog_username(username);
		logBean.setT_operationlog_time(creatDate);
		logBean.setT_operationlog_content(opContent);
		logBean.setT_operationlog_name("���");
		logService.add(logBean);
	}

	@AfterReturning(value = "updateServiceCall()")
	public void updateServiceCallCalls(JoinPoint joinPoint) throws Throwable {
		// ��ȡ��¼����Ա����
		String username = (String) request.getSession().getAttribute("userName");
		System.out.println("username" + username);
		if (username == null) {// û�й���Ա��¼
			return;
		}
		// �жϲ���
		if (joinPoint.getArgs() == null) {// û�в���
			return;
		}
		// ��ȡ������
		String methodName = joinPoint.getSignature().getName();
		System.out.println("methodName" + methodName);
		// ��ȡ��������
		String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
		System.out.println("opContent" + opContent);
		// ��ȡϵͳ��ǰʱ��
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creatDate = dateFormat.format(date);
		// ������־����
		LogBean logBean = new LogBean();
		logBean.setT_operationlog_username(username);
		logBean.setT_operationlog_time(creatDate);
		logBean.setT_operationlog_content(opContent);
		logBean.setT_operationlog_name("�޸�");
		logService.add(logBean);
	}
	@AfterReturning(value = "deleteServiceCall()")
	public void deleteServiceCallCalls(JoinPoint joinPoint) throws Throwable {
		// ��ȡ��¼����Ա����
		String username = (String) request.getSession().getAttribute("userName");
		System.out.println("username" + username);
		if (username == null) {// û�й���Ա��¼
			return;
		}
		// �жϲ���
		if (joinPoint.getArgs() == null) {// û�в���
			return;
		}
		// ��ȡ������
		String methodName = joinPoint.getSignature().getName();
		System.out.println("methodName" + methodName);
		// ��ȡ��������
		String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
		System.out.println("opContent" + opContent);
		// ��ȡϵͳ��ǰʱ��
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creatDate = dateFormat.format(date);
		// ������־����
		LogBean logBean = new LogBean();
		logBean.setT_operationlog_username(username);
		logBean.setT_operationlog_time(creatDate);
		logBean.setT_operationlog_content(opContent);
		logBean.setT_operationlog_name("�h��");
		logService.add(logBean);
	}


	/**
	 * ʹ��Java��������ȡ�����ط���(insert��update)�Ĳ���ֵ�� ������ֵƴ��Ϊ��������
	 */
	public String adminOptionContent(Object[] args, String mName) throws Exception {

		if (args == null) {
			return null;
		}

		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		String className = null;
		int index = 1;
		// ������������
		for (Object info : args) {

			// ��ȡ��������
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[����" + index + "�����ͣ�" + className + "��ֵ��");

			// ��ȡ��������з���
			Method[] methods = info.getClass().getDeclaredMethods();

			// �����������ж�get����
			for (Method method : methods) {

				String methodName = method.getName();
				// �ж��ǲ���get����
				if (methodName.indexOf("get") == -1) {// ����get����
					continue;// ������
				}

				Object rsValue = null;
				try {

					// ����get��������ȡ����ֵ
					rsValue = method.invoke(info);

					if (rsValue == null) {// û�з���ֵ
						continue;
					}

				} catch (Exception e) {
					continue;
				}

				// ��ֵ����������
				rs.append("(" + methodName + " : " + rsValue.toString() + ")");
			}

			rs.append("]");

			index++;
		}

		return rs.toString();
	}
}
