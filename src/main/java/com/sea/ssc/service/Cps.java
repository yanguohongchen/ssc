package com.sea.ssc.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sea.dao.CpCourrentMapper;
import com.sea.dao.CpHistoryMapper;
import com.sea.dao.CqStageMapper;
import com.sea.dao.JxStageMapper;
import com.sea.dao.UserMapper;
import com.sea.model.CpCourrent;
import com.sea.model.CpHistory;
import com.sea.model.CqStage;
import com.sea.model.JxStage;
import com.sea.model.User;

@Component
public class Cps
{
	@Autowired
	private CpCourrentMapper cpCourrentMapperDao;

	@Autowired
	private CpHistoryMapper cpHistoryMapperDao;

	@Autowired
	private CqStageMapper cqStageMapperDao;

	@Autowired
	private JxStageMapper jxStageMapperDao;

	@Autowired
	private UserMapper userMapperDao;

	@Autowired
	private Ssc ssc;

	/**
	 * 中奖金额计算
	 * 
	 * @param money
	 * @return
	 */
	private long winMoney(long money)
	{
		return money / 2 * 7;

	}

	/**
	 * 记录购买信息
	 */
	public void recordBuyInfo(long userid, long money, int cpType, int groupType, String stageNum,String content)
	{
		CpCourrent cpCourrent = new CpCourrent();
		cpCourrent.setBuytime(System.currentTimeMillis());
		cpCourrent.setCptype(cpType);
		cpCourrent.setGrouptype(groupType);
		cpCourrent.setUserid(userid);
		cpCourrent.setMoney(money);
		cpCourrent.setStagenum(stageNum);
		cpCourrent.setContent(content);
		cpCourrentMapperDao.insert(cpCourrent);
	}

	private boolean isWin(int groupType, String context,String winNum)
	{
		int res = ssc.countTwoSmallAndBigGroup(winNum);
		if (Integer.parseInt(context) == res)
		{
			return true;
		} else
		{
			return false;
		}
	}

	private void returnMoneyToUser(long userid, long money)
	{
		User user = userMapperDao.selectByPrimaryKey(userid);
		user.setMoney((user.getMoney() + money));
		userMapperDao.updateByPrimaryKeySelective(user);
	}

	/**
	 * 兑奖
	 */
	public void priizes()
	{
		// 兑奖号码
		// 兑奖人员
		// 取出当前兑奖表中。一一兑奖，兑奖完后将移动到历史表格中。
		List<CpCourrent> cpCourrents = cpCourrentMapperDao.getList();
		if (cpCourrents != null)
		{
			for (CpCourrent cpCourrent : cpCourrents)
			{
				int cpType = cpCourrent.getCptype();
				String winNum = "";//中奖号码
				if (cpType == 1)
				{
					CqStage cqStage = cqStageMapperDao.selectByPrimaryKey(cpCourrent.getStagenum());
					winNum = cqStage.getStagenumber();
				} else if (cpType == 2)
				{
					JxStage jxStage = jxStageMapperDao.selectByPrimaryKey(cpCourrent.getStagenum());
					winNum = jxStage.getStagenumber();
				}

				// 中奖返回钱
				if (isWin(cpCourrent.getGrouptype(), cpCourrent.getContent(),winNum))
				{
					returnMoneyToUser(cpCourrent.getUserid(), winMoney(cpCourrent.getMoney()));
				}
				cpCourrentMapperDao.deleteByPrimaryKey(cpCourrent.getId());
				CpHistory cpHistory = new CpHistory();
				BeanUtils.copyProperties(cpCourrent, cpHistory);
				cpHistoryMapperDao.insert(cpHistory);

			}
		}

	}

}
