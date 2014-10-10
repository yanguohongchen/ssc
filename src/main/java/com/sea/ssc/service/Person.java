package com.sea.ssc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sea.dao.CqStageMapper;
import com.sea.dao.UserMapper;
import com.sea.model.CqStage;
import com.sea.model.User;

@Component
public class Person
{

	@Autowired
	private Cps cps;

	@Autowired
	private CqStageMapper cqStageMapperDao;

	@Autowired
	private UserMapper userMapperDao;

	@Autowired
	private Ssc ssc;

	public void bugCp(long userid, long money, int cpType, int groupType, String stageNum, String content)
	{
		// 买彩票
		cps.recordBuyInfo(userid, money, cpType, groupType, stageNum, content);
		// 付钱
		User user = userMapperDao.selectByPrimaryKey(userid);
		user.setMoney((user.getMoney() - money));
		userMapperDao.updateByPrimaryKeySelective(user);
	}

	public void virtualPersonBuy()
	{
		// 从历史中买开始买
		long userid = 1;
		int cpType = 1;
		int groupType = 1;

		String content = "3";

		CqStage cqStage = cqStageMapperDao.selectByPrimaryKey("141011-072");

		int i = 1;

		long stageTime = cqStage.getStagetime();
		while (true)
		{

			Map<String, Object> map = new HashMap<>();
			map.put("start", 0);
			map.put("end", 1);
			map.put("stagetime", stageTime);
			List<CqStage> cqStages = cqStageMapperDao.getListByPage(map);
			if (cqStage != null)
			{
				for (CqStage cqS : cqStages)
				{
					int res = ssc.countTwoSmallAndBigGroup(cqS.getStagenumber());
					if (i != 6)
					{
						double money = Math.pow(2, i);
						bugCp(userid, (long) money, cpType, groupType, cqS.getId(), content);
						i++;
					}
					if (res == Integer.parseInt(content))
					{
						// 重置金额
						i = 1;
					}
					stageTime = cqS.getStagetime();
				}
			}
			
//			cps.priizes();
		}

	}
}
