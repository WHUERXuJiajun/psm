package whu.web.psm.service;

import java.util.List;

/**
 * 
 * @description: “用户接受任务"服务模块接口
 * @author	   : xsy
 * @date	   : 2020年5月23日
 */

public interface ReceService {
	
	/**
	 * 
	 * @description: 用户接收任务
	 * @param phone
	 * @param mid
	 * @return
	 */
	boolean insertRece(String phone, String mid);
	
	
	/**
	 * 
	 * @description: 获取某个用户接收的任务
	 * @param phone
	 * @return
	 */
	List<Mission> getMissionsByPhone(String phone);
}
