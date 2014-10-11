package com.sea.ssc.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sea.dao.CountTimeMapper;
import com.sea.dao.CqStageMapper;
import com.sea.dao.CurrentMapper;
import com.sea.dao.HistoryMapper;
import com.sea.dao.JxStageMapper;
import com.sea.model.CountTime;
import com.sea.model.CqStage;
import com.sea.model.Current;
import com.sea.model.History;
import com.sea.model.JxStage;

@Component
public class Ssc
{

	private final static Logger logger = LoggerFactory.getLogger(Ssc.class.getName());

	@Autowired
	private CqStageMapper cqStageMapperDao;

	@Autowired
	private JxStageMapper jxStageMapperDao;

	@Autowired
	private CurrentMapper currentMapperDao;

	@Autowired
	private HistoryMapper historyMapperDao;

	@Autowired
	private CountTimeMapper countTimeMapperDao;

	private DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public void queryStageCountInfo(int cpTypeId)
	{
		// 先查詢当前表格，查看当前时间。
		Current current = currentMapperDao.selectByPrimaryKey(cpTypeId);
		if (current == null)
		{
			current = new Current();
			current.setId(cpTypeId);
			current.setBb(0);
			current.setBs(0);
			current.setSs(0);
			current.setSb(0);
			current.setStagetime((long) 0);
			currentMapperDao.insert(current);
		}
		if (cpTypeId == 1)
		{
			countCq(cpTypeId);
		} else
		{
			countJx(cpTypeId);
		}
	}

	/**
	 * 循环读取
	 * @param cpTypeId
	 */
	private void countCq(int cpTypeId)
	{
		int size = 1;
		do
		{
			Current current = currentMapperDao.selectByPrimaryKey(cpTypeId);
			Map<String, Object> map = new HashMap<>();
			map.put("start", 0);
			map.put("end", 1);
			map.put("stagetime", current.getStagetime());
			List<CqStage> cqStages = cqStageMapperDao.getListByPage(map);
			size = cqStages.size();
			if (cqStages != null)
			{
				for (CqStage cqStage : cqStages)
				{
					countCqGroupLongestTimes(cqStage, cpTypeId);
				}
			}
		} while (size != 0);
	}

	/**
	 * 循环读取
	 * @param cpTypeId
	 */
	private void countJx(int cpTypeId)
	{
		int size = 1;
		do
		{
			Current current = currentMapperDao.selectByPrimaryKey(cpTypeId);
			Map<String, Object> map = new HashMap<>();
			map.put("start", 0);
			map.put("end", 1);
			map.put("stagetime", current.getStagetime());
			List<JxStage> JxStages = jxStageMapperDao.getListByPage(map);
			size = JxStages.size();
			if (JxStages != null)
			{
				for (JxStage jxStage : JxStages)
				{
					countJxGroupLongestTimes(jxStage, cpTypeId);
				}
			}
		} while (size != 0);
	}

	/**
	 * 计算重庆开奖次数
	 * @param cqStage
	 * @param cpTypeId
	 */
	private void countCqGroupLongestTimes(CqStage cqStage, int cpTypeId)
	{
		String stageNum = cqStage.getStagenumber();
<<<<<<< HEAD
=======
		String[] numArr = stageNum.split(",", Integer.MAX_VALUE);
		int four = Integer.parseInt(numArr[3].equals("") ? "0" : numArr[3]);
		int five = Integer.parseInt(numArr[4].equals("") ? "0" : numArr[4]);
>>>>>>> 9189bfa903b8bae49f7d2d85b2595ded6a784441

		Current current = currentMapperDao.selectByPrimaryKey(cpTypeId);
		current.setStagetime(cqStage.getStagetime());
		current.setStageid(cqStage.getId());
<<<<<<< HEAD
		dealTwoGroup(stageNum, current, cpTypeId);
=======
		dealTwoGroup(four, five, current, cpTypeId);
>>>>>>> 9189bfa903b8bae49f7d2d85b2595ded6a784441
	}

	/**
	 * 计算江西开奖次数
	 * @param jxStage
	 * @param cpTypeId
	 */
	private void countJxGroupLongestTimes(JxStage jxStage, int cpTypeId)
	{
		String stageNum = jxStage.getStagenumber();
<<<<<<< HEAD
		
=======
		String[] numArr = stageNum.split(",", Integer.MAX_VALUE);
		int four = Integer.parseInt(numArr[3].equals("") ? "0" : numArr[3]);
		int five = Integer.parseInt(numArr[4].equals("") ? "0" : numArr[4]);
>>>>>>> 9189bfa903b8bae49f7d2d85b2595ded6a784441

		Current current = currentMapperDao.selectByPrimaryKey(cpTypeId);
		current.setStagetime(jxStage.getStagetime());
		current.setStageid(jxStage.getId());
<<<<<<< HEAD
		dealTwoGroup(stageNum, current, cpTypeId);
=======
		dealTwoGroup(four, five, current, cpTypeId);
>>>>>>> 9189bfa903b8bae49f7d2d85b2595ded6a784441
	}

	/**
	 * 处理后俩位组合
	 * 
	 * @param four
	 * @param five
	 * @param current
	 * @param history
	 */
<<<<<<< HEAD
	private void dealTwoGroup(String stageNum, Current current, int cpTypeId)
=======
	private void dealTwoGroup(int four, int five, Current current, int cpTypeId)
>>>>>>> 9189bfa903b8bae49f7d2d85b2595ded6a784441
	{

		History history = historyMapperDao.selectByPrimaryKey(cpTypeId);
		if (history == null)
		{
			history = new History();
			history.setId(cpTypeId);
			history.setBb(0);
			history.setBs(0);
			history.setSs(0);
			history.setSb(0);
			historyMapperDao.insert(history);
		}

		CountTime countTime = new CountTime();
		countTime.setCptype(cpTypeId);

		// 计算大小组合
		int key = countTwoSmallAndBigGroup(stageNum);
		switch (key)
		{
		case 0:// 小小
				// 将值设置为零，并写入到历史表中，并跟最大值对比。如果比最大值大，则取目前值
			if (history.getSs() < current.getSs())
			{
				history.setSs(current.getSs());
			}
			countTime.setTimes(current.getSs());
			countTime.setGrouptype(0);
			current.setSs(0);
			current.setSb(current.getSb() + 1);
			current.setBb(current.getBb() + 1);
			current.setBs(current.getBs() + 1);
			break;
		case 1:// 小大
			if (history.getSb() < current.getSb())
			{
				history.setSb(current.getSb());
			}
			countTime.setGrouptype(1);
			countTime.setTimes(current.getSb());
			current.setSs(current.getSs() + 1);
			current.setSb(0);
			current.setBb(current.getBb() + 1);
			current.setBs(current.getBs() + 1);
			break;
		case 2:// 大小
			if (history.getBs() < current.getBs())
			{
				history.setBs(current.getBs());
			}
			countTime.setGrouptype(2);
			countTime.setTimes(current.getBs());
			current.setSs(current.getSs() + 1);
			current.setSb(current.getSb() + 1);
			current.setBb(current.getBb() + 1);
			current.setBs(0);
			break;
		case 3:// 大大
			if (history.getBb() < current.getBb())
			{
				history.setBb(current.getBb());
			}
			countTime.setGrouptype(3);
			countTime.setTimes(current.getBb());
			current.setSs(current.getSs() + 1);
			current.setSb(current.getSb() + 1);
			current.setBb(0);
			current.setBs(current.getBs() + 1);
			break;
		default:
			break;
		}
		currentMapperDao.updateByPrimaryKeySelective(current);
		historyMapperDao.updateByPrimaryKeySelective(history);
		countTimeMapperDao.insert(countTime);
	}

	/**
	 * 计算俩位的组合
	 * 
	 * @param num4
	 * @param num5
	 * @return
	 */
	public int countTwoSmallAndBigGroup(String stageNum)
	{
		
		String[] numArr = stageNum.split(",", Integer.MAX_VALUE);
		int num4 = Integer.parseInt(numArr[3].equals("") ? "0" : numArr[3]);
		int num5 = Integer.parseInt(numArr[4].equals("") ? "0" : numArr[4]);
		if (SmallOrBig(num4) && SmallOrBig(num5))// 小小
		{
			return 0;
		} else if (SmallOrBig(num4) && !SmallOrBig(num5))// 小大
		{
			return 1;
		} else if (!SmallOrBig(num4) && SmallOrBig(num5))// 大小
		{
			return 2;
		} else if (!SmallOrBig(num4) && !SmallOrBig(num5))// 大大
		{
			return 3;
		}
		return 0;
	}

	/**
	 * 计算单双
	 * 
	 * @param num
	 * @return
	 */
	// private boolean SingerOrDouble(int num)
	// {
	// if (num % 2 == 0)
	// {
	// return true;
	// } else
	// {
	// return false;
	// }
	// }

	/**
	 * 计算大小
	 * 
	 * @param num
	 * @return
	 */
	private boolean SmallOrBig(int num)
	{
		if (num < 5)
		{
			return true;
		} else
		{
			return false;
		}
	}

	private void saveToCqDB(String sta) throws ParseException
	{
		String[] rlArr = sta.split("\r\n");
		CqStage cqStage = new CqStage();
		cqStage.setId(rlArr[0].split(" ")[1]);
		cqStage.setStagetime(df.parse(rlArr[1]).getTime());
		cqStage.setStagenumber(rlArr[2]);
		// 将信息保存到数据库中
		CqStage queryCqStage = cqStageMapperDao.selectByPrimaryKey(rlArr[0].split(" ")[1]);
		if (queryCqStage == null)
		{
			cqStageMapperDao.insert(cqStage);
		} else
		{
			logger.debug("该期已经存在！");
		}
	}

	private void saveToJxDB(String sta) throws ParseException
	{
		String[] rlArr = sta.split("\r\n");
		JxStage jxStage = new JxStage();
		jxStage.setId(rlArr[0].split(" ")[1]);
		jxStage.setStagetime(df.parse(rlArr[1]).getTime());
		jxStage.setStagenumber(rlArr[2]);
		// 将信息保存到数据库中
		JxStage queryCqStage = jxStageMapperDao.selectByPrimaryKey(rlArr[0].split(" ")[1]);
		if (queryCqStage == null)
		{
			jxStageMapperDao.insert(jxStage);
		} else
		{
			logger.debug("该期已经存在！");
		}
	}

	/**
	 * 抓取信息，并保存到数据库中
	 * 
	 * @param url
	 *            网页地址
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void catchInfoAndSaveToDB(String url, String type) throws ClientProtocolException, IOException, ParseException
	{
		// cookie存储工具
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		try
		{
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response1 = httpclient.execute(httpget);
			try
			{
				HttpEntity entity = response1.getEntity();
				String entityString = EntityUtils.toString(entity, "gbk");
				Pattern patternTr = Pattern.compile("<TR align=middle>.*</TR>", Pattern.MULTILINE | Pattern.DOTALL);
				Pattern patternTd = Pattern.compile("<TD>.*</TD>", Pattern.DOTALL | Pattern.MULTILINE);
				Matcher matcherTr = patternTr.matcher(entityString);

				if (matcherTr.find())
				{
					String result1 = matcherTr.group(0);
					Matcher matcherTd = patternTd.matcher(result1);
					if (matcherTd.find())
					{
						// 去除html标签
						String result = matcherTd.group(0).replaceAll("</TR>", "").replaceAll("<TR align=middle>", "").replaceAll("<TD>", "").replaceAll("</TD>", "");
						// 分割信息
						String[] resArr = result.split("\r\n\r\n\r\n\r\n");
						for (int i = 0; i < resArr.length; i++)
						{

							if (type.equals("jx"))
							{
								saveToJxDB(resArr[i]);

							} else if (type.equals("cq"))
							{
								saveToCqDB(resArr[i]);
							}
						}
					}
				}

			} finally
			{
				response1.close();
			}

		} finally
		{
			httpclient.close();
		}

	}

}
