package com.nirvana.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.area.WorkType;
import com.nirvana.domain.backend.goal.sdo.AgentAreaSdoGoal;
import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.domain.backend.goal.sdo.SdoExcel;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.store.Store;
import com.nirvana.mongo.document.VisitRecordDocument;
import com.nirvana.service.pojo.web.BigAreaGoalResult;
import com.nirvana.service.pojo.web.DirectAreaGoalResult;
import com.nirvana.service.pojo.web.GlobalGoalResult;
import com.nirvana.service.pojo.web.ManagerAreaGoalResult;
import com.nirvana.service.pojo.web.NodeSet;
import com.nirvana.service.pojo.web.WebProductivityStatsResult;
import com.nirvana.service.pojo.web.WebSalesVolumeStatsResult;

/**
 * ҵ��������ģ�顣
 * 
 * ���乤����·�ߣ��ŵ�ȵȡ�
 * 
 * */
public interface AgentManageService {

	/**
	 * ��ȡ�������������
	 * 
	 * Ȩ�ޣ����Ρ�
	 * 
	 * */
	public List<DirectorArea> getManagedDirectorArea();

	/**
	 * ��ȡ�����ְλ����SIS,��������,����������,����������
	 * 
	 * Ȩ�ޣ�SIS,����������������������������
	 * 
	 * */
	public List<Position> getHasPositions();

	/**
	 * ��ȡ��ҳ��С�����ݡ�
	 * 
	 * Ȩ�ޣ�SIS,��Ա��
	 * 
	 * @param employeeId
	 *            ������Ա��ID�����������ΪSIS,��ô����������С�������������Ϊ��Ա����ô�������Լ������С����
	 * @param page
	 *            ҳ��
	 * @param size
	 *            ��ҳ��С
	 * 
	 * */
	public Page<AgentArea> getAgentAreas(int page, int size);

	/**
	 * �ر�С����
	 * 
	 * Ȩ�ޣ�SIS��
	 * 
	 * @param agentAreaId
	 *            С��ID
	 * 
	 * */
	public void closeAgentArea(int agentAreaId);

	/**
	 * ���¼���С����
	 * 
	 * Ȩ�ޣ�SIS��
	 * 
	 * @param agentAreaId
	 *            С��ID
	 * 
	 * */
	public void activateAgentArea(int agentAreaId);

	/**
	 * �༭С������С��ָ��ҵ��Ա��
	 * 
	 * Ȩ�ޣ�����Ա����Ա��
	 * 
	 * @param agentAreaId
	 *            С��ID
	 * @param ECode
	 *            Ա������
	 * 
	 * */
	public void editAgentArea(int agentAreaId, String ECode);

	/**
	 * ������С����
	 * 
	 * Ȩ�ޣ�SIS��
	 * 
	 * @param areaCode
	 *            С�����
	 * @param directorAreaId
	 *            ������������
	 * 
	 * */
	public void createAgentArea(String areaCode, String desc, WorkType type, int directorAreaId);

	/**
	 * ��ȡ��С���ѹ���ľ����̡�
	 * 
	 * Ȩ�ޣ���Ա��
	 * 
	 * */
	public List<Dealer> getAgentAreaDealers(int agentAreaId);

	/**
	 * ��ȡ�ɷ������С���ľ����̡�
	 * 
	 * Ȩ�ޣ���Ա��
	 * 
	 * */
	public List<Dealer> getCanAllotedDealers(int agentAreaId);

	/**
	 * ��С�����侭���̡�
	 * 
	 * Ȩ�ޣ���Ա��
	 * 
	 * @param agentAreaId
	 * @param outIds
	 * @param inIds
	 * 
	 * */
	public void allotDealersToAgentArea(int agentAreaId, List<Long> outIds, List<Long> inIds);

	/**
	 * ��ȡ��С���ѹ����ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա ��
	 * 
	 * */
	public List<Dealer> getAgentAreaDirectStores(int agentAreaId);

	/**
	 * ��ȡ�ɷ������С����ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա��
	 * 
	 * */
	public List<Dealer> getCanAllotedDirectStores(int agentAreaId);

	/**
	 * ��С������ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ���Ա��
	 * 
	 * @param agentAreaId
	 * @param outIds
	 * @param inIds
	 * 
	 * */
	public void allotDirectStoresToAgentArea(int agentAreaId, List<Long> outIds, List<Long> inIds);

	/**
	 * ��ȡ��С���ѹ����С�ꡣ
	 * 
	 * Ȩ�ޣ���Ա ��
	 * 
	 * */
	public List<Store> getAgentAreaStores(int agentAreaId);

	/**
	 * ��ȡ�ɷ������С����С�ꡣ
	 * 
	 * Ȩ�ޣ���Ա��
	 * 
	 * */
	public List<Store> getCanAllotedStores(int agentAreaId);

	/**
	 * ��С�������ŵꡣ
	 * 
	 * Ȩ�ޣ�����Ա����Ա��
	 * 
	 * */
	public void allotStoresToAgentArea(int agentAreaId, List<Long> outIds, List<Long> inIds);

	/**
	 * ��ȡ���д�����
	 * 
	 * */
	public List<BigArea> getAllBigAreas();

	/**
	 * ��ȡ�˴���ID�����о�������
	 * 
	 * */
	public List<ManagerArea> getManagerAreas(int bigAreaId);

	/**
	 * ��ȡ�˾������µ�������������
	 * 
	 * */
	public List<DirectorArea> getDirectorAreas(int managerAreaId);

	/**
	 * ��ȡ���������µ�����CR����
	 * 
	 * */
	public List<AgentArea> getAgentAreas(int directAreaId);

	/**
	 * �����д����趨����Ŀ�꣬�����¼�����ڣ��򴴽���¼�������¼�������޸ļ�¼�����Ұ汾��+1��
	 * 
	 * Ȩ�ޣ�SIS��
	 * 
	 * @param year
	 *            Ŀ�����
	 * @param month
	 *            Ŀ���·�
	 * @param nrs
	 *            Ŀ����
	 * 
	 * */
	public void setBigAreaNrGoal(int year, int month, Map<Integer, Float> nrs);

	/**
	 * �������·ݸ��Լ���Ͻ�µ����о������趨����Ŀ�꣬�����¼�����ڣ��򴴽���¼�������¼�������޸ļ�¼�������汾����Ϊ�븸��¼��ͬ��
	 * 
	 * Ȩ�ޣ���������UM����
	 * 
	 * @param employeeId
	 *            ����Ŀ��Ĵ�������ID�������ߣ�
	 * @param year
	 *            Ŀ�����
	 * @param month
	 *            Ŀ���·�
	 * @param nrs
	 *            �����˲������������о�������nrֵ��map,keyΪ������ID,valueΪNRֵ��
	 * 
	 * */
	public void setManagerAreaNrGoals(int bigAreaId, int year, int month, Map<Integer, Float> nrs);

	/**
	 * �������·ݸ��Լ���Ͻ�µ������������趨����Ŀ�꣬�����¼�����ڣ��򴴽���¼�������¼�������޸ļ�¼�������汾����Ϊ�븸��¼��ͬ��
	 * 
	 * Ȩ�ޣ�����������TDM����
	 * 
	 * @param employeeId
	 *            ����Ŀ��ľ���������ID�������ߣ�
	 * @param year
	 *            Ŀ�����
	 * @param month
	 *            Ŀ���·�
	 * @param nrs
	 *            �����˲���������������������nrֵ��map,keyΪ������ID,valueΪNRֵ��
	 * 
	 * */
	public void setDirectorAreaNrGoals(int managerAreaId, int year, int month, Map<Integer, Float> nrs);

	/**
	 * �������·ݸ��Լ���Ͻ�µ�����CR���趨����Ŀ�꣬�����¼�����ڣ��򴴽���¼�������¼�������޸ļ�¼�������汾����Ϊ�븸��¼��ͬ��
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ����Ŀ�������������ID�������ߣ�
	 * @param year
	 *            Ŀ�����
	 * @param month
	 *            Ŀ���·�
	 * @param nrs
	 *            �����˲�������������CR����nrֵ��map,keyΪCR��ID,valueΪNRֵ��
	 * 
	 * */
	public void setAgentAreaNrGoals(int directAreaId, int year, int month, Map<Integer, Float> nrs);

	/**
	 * SIS��ȡ���������д���Ŀ�ꡣ
	 * 
	 * Ȩ�ޣ����������Σ�SIS����
	 * 
	 * */
	public GlobalGoalResult getGlobalGoal(int year, int month);

	/**
	 * ���������ȡ����������Ŀ�ꡣ
	 * 
	 * Ȩ��:��������UM����
	 * 
	 * @param employeeId
	 *            ��������ID�������ߣ�
	 * 
	 * */
	public BigAreaGoalResult getBigAreaGoal(int bigAreaId, int year, int month);

	/**
	 * �����������ȡ������������Ŀ�ꡣ
	 * 
	 * Ȩ�ޣ�����������TDM����
	 * 
	 * @param employeeId
	 *            ����������ID�������ߣ�
	 * 
	 * */
	public ManagerAreaGoalResult getManagerAreaNrGoal(int managerAreaId, int year, int month);

	/**
	 * �����������ȡ������������Ŀ�ꡣ
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ����������ID�������ߣ�
	 * @param date
	 *            ����
	 * 
	 * */
	public DirectAreaGoalResult getDirectorAreaGoal(int directAreaId, int year, int month);

	/**
	 * ��ȡ����������SDO���б�
	 * 
	 * Ȩ�ޣ�����(TDS)��
	 * 
	 * */
	public List<AgentAreaSdoGoal> getAgentAreaSdoGoals(int directAreaId, int year, int month);

	/**
	 * ����ĳ�µ�SDOĿ�ꡣ
	 * 
	 * Ȩ�ޣ�SIS
	 * 
	 * @param year
	 *            ��
	 * @param month
	 *            ��
	 * @param file
	 *            �ϴ���EXCEL
	 * 
	 * */
	public void setMonthSdo(int year, int month, MultipartFile file);

	/**
	 * ��ȡ���µ�SdoExcel.
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * */
	public SdoExcel getSdoExcel(int year, int month);

	/**
	 * ��ȡĳ���µ�SDOĿ�ꡣ
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * */
	public List<Sdo> getSdos(int year, int month);

	/**
	 * ���θ�CR������SDOĿ�ꡣ
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * 
	 * */
	public void setSdos(int agentAreaId, List<Long> sdoIds, int year, int month);

	/**
	 * ��ȡ�������������CR����
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * 
	 * */
	public List<AgentArea> getDirectorAgentAreas(int directAreaId);

	/**
	 * ��ȡ·�ߡ�
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ���������Ρ�
	 * @param agentAreaId
	 *            CR��ID
	 * @param routeCode
	 *            ��·����
	 * 
	 * */
	public VisitRoute getVisitRoute(int agentAreaId, int routeCode);

	/**
	 * ��ȡ���в��ڴ�·���е��ŵꡣ
	 * 
	 * Ȩ�ޣ�����������(TDS)��
	 * 
	 * @param employeeId
	 *            ����������ID
	 * @param agentAreaId
	 *            Ҫ������CR��ID
	 * @param routeCode
	 *            ��������·����
	 * 
	 * */
	public Page<Store> getNotInThisRouteStores(int agentAreaId, int routeCode, int page, int size);

	/**
	 * ��ȡ���в��ڴ�·���е�ֱӪ�ꡣ
	 * 
	 * Ȩ�ޣ�����������(TDS)��
	 * 
	 * @param employeeId
	 *            ����������ID
	 * @param agentAreaId
	 *            Ҫ������CR��ID
	 * @param routeCode
	 *            ��������·����
	 * 
	 * */
	public Page<Dealer> getNotInThisRouteDealers(int agentAreaId, int routeCode, int page, int size);

	/**
	 * ���θ�ҵ��Ա����·�ߡ�
	 * 
	 * Ȩ�ޣ����������Σ�TDS����
	 * 
	 * @param employeeId
	 *            ����������Ա��ID�������ߣ�
	 * @param agentAreaId
	 *            CR����ID
	 * @param routeCode
	 *            ��·����
	 * @param sets
	 *            ��װ���ŵ����ͺ�ID�Ĳ�����
	 * 
	 * */
	public void setAgentVisitRoute(int agentAreaId, int routeCode, List<NodeSet> sets);

	/**
	 * ��������ɸѡѡ���ȡĳ���µ�ĳ���µ�����ͳ�ơ���������ID��������ΪNULL�����ǲ���ȫΪNULL��
	 * 
	 * Ȩ�ޣ���˾������Ա����
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param startYear
	 *            ��ʼ���
	 * @param startMonth
	 *            ��ʼ�·�
	 * @param endYear
	 *            �������
	 * @param endMonth
	 *            �����·�
	 * @param bigAreaId
	 *            ����ID
	 * @param manageAreaId
	 *            ������ID
	 * @param dirAreaId
	 *            ������ID
	 * @param agentAreaId
	 *            CR��ID
	 * 
	 * */
	public Page<WebSalesVolumeStatsResult> getSalesVolStatisResultsByMonthToMonth(
			int startYear,
			int startMonth,
			int endYear,
			int endMonth,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size);

	/**
	 * ��������ɸѡѡ���ȡ����ĳ�쵽ĳ�������ͳ�ơ���������ID��������ΪNULL�����ǲ���ȫΪNULL��
	 * 
	 * Ȩ�ޣ���˾������Ա����
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param startDay
	 *            ��ʼ��
	 * @param endDay
	 *            ������
	 * @param bigAreaId
	 *            ����ID
	 * @param manageAreaId
	 *            ������ID
	 * @param dirAreaId
	 *            ������ID
	 * @param agentAreaId
	 *            CR��ID
	 * 
	 * */
	public Page<WebSalesVolumeStatsResult> getSalesVolStatisResultsByDayToDay(
			int startDay,
			int endDay,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size);

	/**
	 * ��������ɸѡѡ���ȡĳ���µ�ĳ���µ�������ͳ�ơ���������ID��������ΪNULL�����ǲ���ȫΪNULL��
	 * 
	 * Ȩ�ޣ���˾������Ա����
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param startYear
	 *            ��ʼ���
	 * @param startMonth
	 *            ��ʼ�·�
	 * @param endYear
	 *            �������
	 * @param endMonth
	 *            �����·�
	 * @param bigAreaId
	 *            ����ID
	 * @param manageAreaId
	 *            ������ID
	 * @param dirAreaId
	 *            ������ID
	 * @param agentAreaId
	 *            CR��ID
	 * 
	 * */
	public Page<WebProductivityStatsResult> getSalesProducStatsResultsByMonthToMonth(
			int startYear,
			int startMonth,
			int endYear,
			int endMonth,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size);

	/**
	 * ��������ɸѡѡ���ȡĳ��ĳ�쵽ĳ���������ͳ�ơ���������ID��������ΪNULL�����ǲ���ȫΪNULL��
	 * 
	 * Ȩ�ޣ���˾������Ա����
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param month
	 *            ĳ��
	 * @param startDay
	 *            ��ʼ��
	 * @param endDay
	 *            ������
	 * @param bigAreaId
	 *            ����ID
	 * @param manageAreaId
	 *            ������ID
	 * @param dirAreaId
	 *            ������ID
	 * @param agentAreaId
	 *            CR��ID
	 * 
	 * */
	public Page<WebProductivityStatsResult> getSalesProducStatsResultsByDayToDay(
			int startDay,
			int endDay,
			Integer bigAreaId,
			Integer manageAreaId,
			Integer dirAreaId,
			Integer agentAreaId,
			int page,
			int size);

	/**
	 * ��ȡ�ݷü�¼��
	 * 
	 * @param employeeId
	 *            �˲�ѯ������ID
	 * @param agentId
	 *            ҵ��ԱID
	 * @param startDate
	 *            ��ʼ���ڣ���ʽΪ8λint,����20141015��ʾ����2014.10.15
	 * @param endDate
	 *            �������ڣ���ʽΪ8λint,����20141015��ʾ����2014.10.15
	 * 
	 * */
	public Page<VisitRecordDocument> getVisitRecords(int agentAreaId, int startDate, int endDate, int page, int size);

	/**
	 * ��ȡĳ�������̵��̵��¼
	 * 
	 * @param dealerId
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	Page<InventoriesCheckHistory> getInventoriesHistory(Long dealerId, Date start, Date end, int page, int size);
}
