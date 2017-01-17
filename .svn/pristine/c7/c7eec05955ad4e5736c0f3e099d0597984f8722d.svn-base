package com.baofeng.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * 功能：程序内部通道传输对像
 * */
public class Message implements Serializable {

	private static final long serialVersionUID = 8811418225925124930L;

	public enum Head {

		MANAGER, THIRDMANAGER, ACTORES, MONTHSOUTLAY, EMPLOYEE_ZHUANZHENG, EMPLOYEE_ADDACTORES, ACTORES_MID, MANAGER_MID, PLATFORMS, EMPLOYEE_LIZHI, MANAGER_LIZHI, MANAGER_DEL, FIN_SALARY_MANAGER, CHECK_SALARYONLINE, CHECK_SALARYTHIRDMANAGER, FIN_SALARY_OFFLINE, MANAGER_PDEL, ACTORES_PDEL, 
		CHECK_SALARY_FINANCIALREPORTS, FIN_SALARY_EMPLOYEE,EMPLOYEE_ADDMANAGERS ,EMPLOYEE_TALENT

	}

	private Head head;
	private Object data;
	private EventListener eventListener;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public void onEventListener(EventListener event, Map<String, Object> instance) {
		event.onCallback(this, instance);
	}

	public interface EventListener {
		void onCallback(Message message, Map<String, Object> instance);
	}

	public EventListener getEventListener() {
		return eventListener;
	}

	public void setEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
