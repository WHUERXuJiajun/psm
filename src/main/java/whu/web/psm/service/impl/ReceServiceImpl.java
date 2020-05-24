package whu.web.psm.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import whu.web.psm.dao.MissionTableMapper;
import whu.web.psm.dao.ReceMapper;
import whu.web.psm.pojo.MissionTable;
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

	@Autowired
	MissionTableMapper missionTableMapper;
	
	@Transactional
	@Override
	public boolean insertRece(ReceKey receKey) {
		try {
			//插入用户-任务数据
			receMapper.insert(receKey);
			//获取任务
			MissionTable missionTable = missionTableMapper.selectByPrimaryKey(receKey.getMid());
			//任务state改为1（正在进行）
			missionTable.setState(1);
			//更新任务列表
			missionTableMapper.updateByExampleSelective(missionTable, null);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<MissionTable> getMissionsByPhone(String phone) {
		List<MissionTable> missionTables = new ArrayList<>();
		//获取用户接收的任务id
		List<Integer> mids = receMapper.getMidByPhone(phone);
		for (Integer mid: mids) {
			missionTables.add(missionTableMapper.selectByPrimaryKey(mid));
		}
		return missionTables;
	}

	@Override
	public boolean cancelMission(ReceKey receKey) {
		try {
			//删除用户-任务
			receMapper.deleteByPrimaryKey(receKey);
			//获取任务
			MissionTable missionTable = missionTableMapper.selectByPrimaryKey(receKey.getMid());
			//任务state改为01（正在进行）
			missionTable.setState(0);
			//更新任务列表
			missionTableMapper.updateByExampleSelective(missionTable, null);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
