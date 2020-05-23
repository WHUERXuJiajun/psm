package whu.web.psm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whu.web.psm.dao.ReceMapper;
import whu.web.psm.pojo.Mission;
import whu.web.psm.pojo.ReceKey;
import whu.web.psm.service.ReceService;

/**
 * 
 * @description: “用户接受任务”服务模块接口实现
 * @author	   : xsy
 * @date	   : 2020年5月23日
 */
@Service
public class ReceServiceImpl implements ReceService {

	@Autowired
	ReceMapper receMapper;
	
	@Override
	public boolean insertRece(String phone, String mid) {
		try {
			ReceKey receKey = new ReceKey();
			receKey.setMid(mid);
			receKey.setPhone(phone);
			receMapper.insert(receKey);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Mission> getMissionsByPhone(String phone) {
		
		return null;
	}

}
