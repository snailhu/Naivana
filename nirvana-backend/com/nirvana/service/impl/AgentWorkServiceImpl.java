package com.nirvana.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.domain.backend.DisplayType;
import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.Memorandum;
import com.nirvana.domain.backend.PhotoType;
import com.nirvana.domain.backend.PlacedPosition;
import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;
import com.nirvana.domain.backend.VisitNodeType;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.goal.AgentAreaNrGoal;
import com.nirvana.domain.backend.goal.AgentWDNrGoal;
import com.nirvana.domain.backend.goal.sdo.AgentAreaSdoGoal;
import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.domain.common.Product;
import com.nirvana.domain.dealer.CheckHistoryItem;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerOrder;
import com.nirvana.domain.dealer.InventPK;
import com.nirvana.domain.dealer.InventoriesCheckHistory;
import com.nirvana.domain.dealer.Inventory;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreOrder;
import com.nirvana.exception.DataIntegrityException;
import com.nirvana.exception.RecordAcessDeniedException;
import com.nirvana.exception.RecordExistedException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.fdfs.FileOperator;
import com.nirvana.mongo.document.VisitPhotoDocument;
import com.nirvana.mongo.document.VisitRecordDocument;
import com.nirvana.mongo.document.VisitRecordDocument.DeviceInfoDocument;
import com.nirvana.mongo.document.VisitRecordDocument.MainShelfInfoDocument;
import com.nirvana.mongo.document.VisitRecordDocument.SecondShelfInfoDocument;
import com.nirvana.mongo.repository.VisitPhotoDocumentRepository;
import com.nirvana.mongo.repository.VisitRecordDocumentRepository;
import com.nirvana.repository.backend.AgentDealerCategoryRepository;
import com.nirvana.repository.backend.AgentStoreCategoryRepository;
import com.nirvana.repository.backend.EmployeeRepository;
import com.nirvana.repository.backend.MemorandumRepository;
import com.nirvana.repository.backend.PositionRepository;
import com.nirvana.repository.backend.VisitRouteRepository;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.backend.goal.AgentAreaNrGoalRepository;
import com.nirvana.repository.backend.goal.AgentWDNrGoalRepository;
import com.nirvana.repository.backend.goal.sdo.AgentAreaSdoGoalRepository;
import com.nirvana.repository.common.ProductRepository;
import com.nirvana.repository.dealer.DealerOrderRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.DealerSerialNumberRepository;
import com.nirvana.repository.dealer.InventoriesCheckHistoryRepository;
import com.nirvana.repository.dealer.InventoryRepository;
import com.nirvana.repository.store.StoreOrderItemRepository;
import com.nirvana.repository.store.StoreOrderRepository;
import com.nirvana.repository.store.StoreRepository;
import com.nirvana.service.AgentWorkService;
import com.nirvana.service.BackEndCurrentLoginService;
import com.nirvana.service.DealerOrderService;
import com.nirvana.service.InventoryService;
import com.nirvana.service.StoreOrderService;
import com.nirvana.service.StoreRestrockService;
import com.nirvana.service.pojo.app.AppAgentNrGoalResult;
import com.nirvana.service.pojo.app.AppProductivityStatsResult;
import com.nirvana.service.pojo.app.AppSalesVolumeStatsResult;
import com.nirvana.service.pojo.app.InventoryChange;
import com.nirvana.service.pojo.web.DeviceInfoData;
import com.nirvana.service.pojo.web.MainShelfInfoData;
import com.nirvana.service.pojo.web.SecondShelfInfoData;
import com.nirvana.utils.Assert;
import com.nirvana.utils.DateUtils;

@Service
@Transactional
public class AgentWorkServiceImpl implements AgentWorkService {

	@Resource
	private StoreRestrockService storeRestrockService;
	@Resource
	private InventoryService inventoryService;
	@Resource
	private BackEndCurrentLoginService backEndCurrentLoginService;
	@Resource
	private StoreOrderService storeOrderService;
	@Resource
	private DealerOrderService dealerOrderService;
	@Resource
	private EmployeeRepository employeeRepository;
	@Resource
	private VisitRouteRepository visitRouteRepository;
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private MemorandumRepository memorandumRepository;
	@Resource
	private AgentStoreCategoryRepository agentStoreCategoryRepository;
	@Resource
	private AgentDealerCategoryRepository agentDealerCategoryRepository;
	@Resource
	private AgentAreaRepository agentAreaRepository;
	@Resource
	private AgentWDNrGoalRepository agentDaysGoalRepository;
	@Resource
	private AgentWDNrGoalRepository agentWDNrGoalRepository;
	@Resource
	private AgentAreaNrGoalRepository agentAreaNrGoalRepository;
	@Resource
	private ProductRepository productRepository;
	@Resource
	private StoreOrderRepository storeOrderRepository;
	@Resource
	private InventoryRepository inventoryRepository;
	@Resource
	private DealerSerialNumberRepository dealerSerialNumberRepository;
	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private StoreOrderItemRepository storeOrderItemRepository;
	@Resource
	private AgentAreaSdoGoalRepository agentAreaSdoGoalRepository;
	@Resource
	private PositionRepository positionRepository;
	@Resource
	private DealerOrderRepository dealerOrderRepository;
	@Resource
	private InventoriesCheckHistoryRepository inventoriesCheckHistoryRepository;
	@Resource
	private VisitRecordDocumentRepository visitRecordDocumentRepository;
	@Resource
	private VisitPhotoDocumentRepository visitPhotoDocumentRepository;

	private PageRequest getPageRequest(int page, int size) {
		if (page <= 0) {
			page = 1;
		}
		if (size <= 0) {
			size = 20;
		}
		PageRequest request = new PageRequest(page - 1, size);
		return request;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AgentArea> getManageAgentAreas() {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		List<Position> positions = positionRepository.findPositionByEmployeeIdAndPositionType(employee.getId(), PositionType.AGENT);
		List<AgentArea> agentAreas = new ArrayList<AgentArea>();
		for (Position position : positions) {
			AgentArea agentArea = position.getAgentArea();
			agentAreas.add(agentArea);
		}
		return agentAreas;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public VisitRoute getTodayVisitRoute(int agentAreaId) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		int code = VisitRoute.findCode(System.currentTimeMillis());
		VisitRoute visitRoute = visitRouteRepository.findByAgentAreaIdAndRouteCode(agentAreaId, code);
		if (visitRoute == null) {
			throw new DataIntegrityException("��·δ��ʼ����");
		}
		return visitRoute;
	}

	/** �ж���CR������ҵ��Ա�� */
	@Transactional(propagation = Propagation.SUPPORTS)
	private void AgentAreaBelongsEmployee(AgentArea area, Employee employee) {
		if (area == null || employee == null) {
			throw new IllegalArgumentException("��������Ϊ�ա�");
		}
		Position position = area.getAgent();
		if (position == null) {
			throw new IllegalArgumentException("����ʵ���Ƿ������session��");
		}
		Employee needEmployee = position.getEmployee();
		if (!employee.equals(needEmployee)) {
			throw new RecordAcessDeniedException("����Ȩ������");
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Dealer getOneDealerInfo(long dealerId) {
		Dealer dealer = dealerRepository.findOne(dealerId);
		if (dealer == null) {
			throw new RecordNotFoundException("��ֱӪ�겻���ڡ�");
		}
		if (dealer.getIsDirect() == false) {
			throw new IllegalArgumentException("�ⲻ��һ��ֱӪ�ꡣ");
		}
		return dealer;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Store getOneStoreInfo(long storeId) {
		Store store = storeRepository.findOne(storeId);
		if (store == null) {
			throw new RecordNotFoundException("���ŵ겻���ڡ�");
		}
		return store;
	}

	/**
	 * �̰߳�ȫ������Ҫ������
	 * 
	 * */
	private VisitRecordDocument startVisitStore(Store store, Date date) {
		if (store == null || date == null) {
			throw new IllegalArgumentException("��������Ϊ�ա�");
		}

		AgentArea area = store.getAgentArea();
		if (area == null) {
			throw new IllegalArgumentException("���ŵ�δ����С����");
		}
		int areaId = area.getId();
		long storeId = store.getId();

		long id = VisitRecordDocument.generateId(date, areaId, VisitNodeType.DISTRIBUTE_STORE, storeId);

		VisitRecordDocument document = visitRecordDocumentRepository.findOne(id);
		if (document != null) {
			if (document.getIsFinished()) {
				throw new RecordAcessDeniedException("���ŵ걾���Ѿ��ݷ���ɣ��޷��ٴΰݷá�");
			}
			return document;
		}
		document = new VisitRecordDocument();
		document.setId(id);
		document.setStoreId(storeId);
		document.setNodeType(VisitNodeType.DISTRIBUTE_STORE);
		document.setAgentAreaId(areaId);
		document.setStartTime(date);
		return document;
	}

	/**
	 * �̰߳�ȫ������Ҫ������
	 * 
	 * */
	private VisitRecordDocument startVisitDealer(Dealer directStore, Date date) {
		if (directStore == null || date == null) {
			throw new IllegalArgumentException("��������Ϊ�ա�");
		}
		if (directStore.getIsDirect() == false) {
			throw new IllegalArgumentException("�ⲻ��һ��ֱӪ�ꡣ");
		}
		AgentArea agentArea = directStore.getAgentArea();
		if (agentArea == null) {
			throw new IllegalArgumentException("��ֱӪ��δ����С����");
		}
		int areaId = agentArea.getId();
		long storeId = directStore.getId();

		long id = VisitRecordDocument.generateId(date, areaId, VisitNodeType.DIRECT_STORE, storeId);

		VisitRecordDocument document = visitRecordDocumentRepository.findOne(id);
		if (document != null) {
			if (document.getIsFinished()) {
				throw new RecordAcessDeniedException("���ŵ걾���Ѿ��ݷ���ɣ��޷��ٴΰݷá�");
			}
			return document;
		}
		document = new VisitRecordDocument();
		document.setId(id);
		document.setStoreId(storeId);
		document.setNodeType(VisitNodeType.DIRECT_STORE);
		document.setAgentAreaId(areaId);
		document.setStartTime(date);
		return document;
	}

	private StoreOrder placeStoreOrder(Store store, Map<String, Integer> orderItems, boolean isInLine, MultipartFile picture) {
		Assert.notNull(store, "��������Ϊ�ա�");
		Assert.notNull(orderItems, "��������Ϊ�ա�");
		Assert.notNull(picture, "ǩ��ͼƬ����Ϊ�ա�");
		StoreOrder order = storeOrderService.placeOrder(store, orderItems, true, isInLine, picture);
		return order;

	}

	private DealerOrder placeDealerOrder(Dealer directStore, Map<String, Integer> orderItems, boolean isInline, MultipartFile picture) {
		Assert.notNull(directStore, "��������Ϊ�ա�");
		Assert.notNull(orderItems, "��������Ϊ�ա�");
		Assert.notNull(picture, "ǩ��ͼƬ����Ϊ�ա�");
		if (directStore.getIsDirect() == false) {
			throw new IllegalArgumentException("�ⲻ��һ��ֱӪ�ꡣ");
		}
		DealerOrder order = dealerOrderService.placeOrder(directStore, orderItems, true, isInline, picture);
		dealerOrderRepository.saveAndFlush(order);
		return order;
	}

	@Override
	public StoreOrder placeVisitOrder(VisitNodeType type, long storeId, Date date, Map<String, Integer> orderItems, MultipartFile picture) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		Date currentDate = new Date(System.currentTimeMillis());
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(currentDate);
		calendar2.setTime(date);
		if (calendar1.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR) || calendar1.get(Calendar.MONTH) != calendar2.get(Calendar.MONTH)
				|| calendar1.get(Calendar.DAY_OF_MONTH) != calendar2.get(Calendar.DAY_OF_MONTH)) {
			throw new IllegalArgumentException("���ڲ�ƥ�䡣");
		}

		if (type.equals(VisitNodeType.DIRECT_STORE)) {
			Dealer directStore = dealerRepository.findOne(storeId);
			if (directStore == null || directStore.getIsDirect() == false) {
				throw new RecordNotFoundException("��ֱӪ��δ�ҵ���");
			}
			AgentArea agentArea = directStore.getAgentArea();
			if (agentArea == null) {
				throw new RecordAcessDeniedException("����Ȩ���ʴ�ֱӪ�ꡣ");
			}
			AgentAreaBelongsEmployee(agentArea, employee);
			VisitRecordDocument document = startVisitDealer(directStore, currentDate);
			DealerOrder order = placeDealerOrder(directStore, orderItems, true, picture);
			document.setDealerOrderNo(order.getOrderNo());
			document.setOrdered(true);
			visitRecordDocumentRepository.save(document);
			return null;
		} else if (type.equals(VisitNodeType.DISTRIBUTE_STORE)) {
			Store store = storeRepository.findOne(storeId);
			if (store == null) {
				throw new RecordNotFoundException("���ŵ�δ�ҵ���");
			}
			AgentArea agentArea = store.getAgentArea();
			if (agentArea == null) {
				throw new RecordAcessDeniedException("����Ȩ���ʴ��ŵꡣ");
			}
			AgentAreaBelongsEmployee(agentArea, employee);
			VisitRecordDocument document = startVisitStore(store, currentDate);
			StoreOrder order = placeStoreOrder(store, orderItems, true, picture);
			document.setStoreOrderNo(order.getOrderNo());
			document.setOrdered(true);
			visitRecordDocumentRepository.save(document);
			return order;
		}
		return null;
	}

	@Override
	public void synchronizeVisitRecord(
			Date date,
			VisitNodeType nodeType,
			long storeId,
			float longitude,
			float latitude,
			MainShelfInfoData mainShelfInfoData,
			Set<DeviceInfoData> deviceInfoDatas,
			SecondShelfInfoData secondShelfInfoData,
			MultipartFile[] storeHeadPics,
			MultipartFile[] mainShelfPics,
			MultipartFile[] secondShelfPics,
			MultipartFile[] devicePics,
			MultipartFile[] filmPics,
			MultipartFile[] posterPics,
			MultipartFile[] lightPics,
			MultipartFile[] otherPics,
			Boolean isFinished,
			Date finishDate) {

		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		VisitRecordDocument record = null;
		AgentArea agentArea = null;

		if (nodeType.equals(VisitNodeType.DIRECT_STORE)) {
			Dealer directStore = dealerRepository.findOne(storeId);
			if (directStore == null || directStore.getIsDirect() == false) {
				throw new RecordNotFoundException("��ֱӪ��δ�ҵ���");
			}
			agentArea = directStore.getAgentArea();
			if (agentArea == null) {
				throw new RecordAcessDeniedException("����Ȩ���ʴ�ֱӪ�ꡣ");
			}
			AgentAreaBelongsEmployee(agentArea, employee);
			record = startVisitDealer(directStore, date);
		} else if (nodeType.equals(VisitNodeType.DISTRIBUTE_STORE)) {
			Store store = storeRepository.findOne(storeId);
			if (store == null) {
				throw new RecordNotFoundException("���ŵ�δ�ҵ���");
			}
			agentArea = store.getAgentArea();
			if (agentArea == null) {
				throw new RecordAcessDeniedException("����Ȩ���ʴ��ŵꡣ");
			}
			AgentAreaBelongsEmployee(agentArea, employee);
			record = startVisitStore(store, date);
		}

		if (mainShelfInfoData != null) {
			MainShelfInfoDocument mainShelfInfo = record.getMainShelfInfo();
			if (mainShelfInfo == null) {
				mainShelfInfo = new MainShelfInfoDocument();
				record.setMainShelfInfo(mainShelfInfo);
			}
			mainShelfInfo.setDisplCSD(mainShelfInfoData.getDisplCSD());
			mainShelfInfo.setDisplNCB(mainShelfInfoData.getDisplNCB());
			mainShelfInfo.setDistrCSD(mainShelfInfoData.getDistrCSD());
			mainShelfInfo.setDistrNCB(mainShelfInfoData.getDistrNCB());
			mainShelfInfo.setIsFirst(mainShelfInfoData.isFirst());
		}

		if (deviceInfoDatas != null) {
			Set<DeviceInfoDocument> set = record.getDeviceInfos();
			if (set == null) {
				set = new HashSet<DeviceInfoDocument>();
			}
			record.setDeviceInfos(set);
			set.clear();
			for (DeviceInfoData data : deviceInfoDatas) {
				Assert.hasLength(data.getDeviceCode(), "�豸���벻��Ϊ�ա�");
				Assert.hasLength(data.getDvcPosition(), "�豸λ�ò���Ϊ�ա�");
				Assert.hasLength(data.getPurity(), "���Ȳ���Ϊ�ա�");
				DeviceInfoDocument deviceInfo = new DeviceInfoDocument();
				deviceInfo.setCode(data.getDeviceCode());
				deviceInfo.setIsAbnormal(data.isAbnormal());
				deviceInfo.setNote(data.getNote());
				deviceInfo.setPosition(PlacedPosition.parsePosition(data.getDvcPosition()));
				deviceInfo.setPurity(data.getPurity());
				set.add(deviceInfo);
			}
		}

		if (secondShelfInfoData != null) {
			SecondShelfInfoDocument secondShelfInfo = record.getSecondShelfInfo();
			if (secondShelfInfo == null) {
				secondShelfInfo = new SecondShelfInfoDocument();
				record.setSecondShelfInfo(secondShelfInfo);
			}
			Assert.hasLength(secondShelfInfoData.getPosition(), "�ڶ�����λ�ò�������Ϊ�ա�");
			Assert.hasLength(secondShelfInfoData.getType(), "�ڶ����г��з�ʽ��������Ϊ�ա�");
			secondShelfInfo.setArea(secondShelfInfoData.getArea());
			secondShelfInfo.setPosition(PlacedPosition.parsePosition(secondShelfInfoData.getPosition()));
			secondShelfInfo.setType(DisplayType.parseDisplayType(secondShelfInfoData.getType()));
		}

		// ����ͼƬ...

		long indexTag = VisitPhotoDocument.generateIndexTag(agentArea.getId(), nodeType, storeId);

		if (storeHeadPics != null) {
			for (MultipartFile pic : storeHeadPics) {
				VisitPhotoDocument photo = new VisitPhotoDocument();
				photo.setDate(date);
				photo.setLatitude(latitude);
				photo.setLongitude(longitude);
				photo.setType(PhotoType.STOREHEAD);
				String fileId = FileOperator.savePicture(pic);
				photo.setUrl(fileId);
				photo.setRecordId(record.getId());
				photo.setIndexTag(indexTag);
				visitPhotoDocumentRepository.save(photo);
			}
		}
		if (mainShelfPics != null) {
			for (MultipartFile pic : mainShelfPics) {
				VisitPhotoDocument photo = new VisitPhotoDocument();
				photo.setDate(date);
				photo.setLatitude(latitude);
				photo.setLongitude(longitude);
				photo.setType(PhotoType.MAINSHELF);
				String fileId = FileOperator.savePicture(pic);
				photo.setUrl(fileId);
				photo.setRecordId(record.getId());
				visitPhotoDocumentRepository.save(photo);
			}
		}
		if (secondShelfPics != null) {
			for (MultipartFile pic : secondShelfPics) {
				VisitPhotoDocument photo = new VisitPhotoDocument();
				photo.setDate(date);
				photo.setLatitude(latitude);
				photo.setLongitude(longitude);
				photo.setType(PhotoType.SECONDSHELF);
				String fileId = FileOperator.savePicture(pic);
				photo.setUrl(fileId);
				photo.setRecordId(record.getId());
				photo.setIndexTag(indexTag);
				visitPhotoDocumentRepository.save(photo);
			}
		}
		if (devicePics != null) {
			for (MultipartFile pic : devicePics) {
				VisitPhotoDocument photo = new VisitPhotoDocument();
				photo.setDate(date);
				photo.setLatitude(latitude);
				photo.setLongitude(longitude);
				photo.setType(PhotoType.DEVICE);
				String fileId = FileOperator.savePicture(pic);
				photo.setUrl(fileId);
				photo.setRecordId(record.getId());
				photo.setIndexTag(indexTag);
				visitPhotoDocumentRepository.save(photo);
			}
		}
		if (filmPics != null) {
			for (MultipartFile pic : filmPics) {
				VisitPhotoDocument photo = new VisitPhotoDocument();
				photo.setDate(date);
				photo.setLatitude(latitude);
				photo.setLongitude(longitude);
				photo.setType(PhotoType.VIVID);
				String fileId = FileOperator.savePicture(pic);
				photo.setUrl(fileId);
				photo.setRecordId(record.getId());
				photo.setIndexTag(indexTag);
				visitPhotoDocumentRepository.save(photo);
			}
		}
		if (posterPics != null) {
			for (MultipartFile pic : posterPics) {
				VisitPhotoDocument photo = new VisitPhotoDocument();
				photo.setDate(date);
				photo.setLatitude(latitude);
				photo.setLongitude(longitude);
				photo.setType(PhotoType.VIVID);
				String fileId = FileOperator.savePicture(pic);
				photo.setUrl(fileId);
				photo.setRecordId(record.getId());
				photo.setIndexTag(indexTag);
				visitPhotoDocumentRepository.save(photo);
			}
		}
		if (lightPics != null) {
			for (MultipartFile pic : lightPics) {
				VisitPhotoDocument photo = new VisitPhotoDocument();
				photo.setDate(date);
				photo.setLatitude(latitude);
				photo.setLongitude(longitude);
				photo.setType(PhotoType.VIVID);
				String fileId = FileOperator.savePicture(pic);
				photo.setUrl(fileId);
				photo.setRecordId(record.getId());
				photo.setIndexTag(indexTag);
				visitPhotoDocumentRepository.save(photo);
			}
		}
		if (otherPics != null) {
			for (MultipartFile pic : otherPics) {
				VisitPhotoDocument photo = new VisitPhotoDocument();
				photo.setDate(date);
				photo.setLatitude(latitude);
				photo.setLongitude(longitude);
				photo.setType(PhotoType.VIVID);
				String fileId = FileOperator.savePicture(pic);
				photo.setUrl(fileId);
				photo.setRecordId(record.getId());
				photo.setIndexTag(indexTag);
				visitPhotoDocumentRepository.save(photo);
			}
		}

		if (isFinished != null) {
			record.setIsFinished(isFinished);
		}

		if (finishDate != null) {
			record.setFinishTime(finishDate);
		}

		visitRecordDocumentRepository.save(record);

	}

	@Override
	public void setNotes(VisitNodeType type, long storeId, String notes) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		if (type == VisitNodeType.DIRECT_STORE) {
			Dealer dealer = dealerRepository.findOne(storeId);
			if (dealer == null || dealer.getIsDirect() == false) {
				throw new RecordNotFoundException("��ֱӪ��δ�ҵ���");
			}
			AgentArea area = dealer.getAgentArea();
			if (area == null) {
				throw new RecordAcessDeniedException("����Ȩ������ֱӪ�ꡣ");
			}
			AgentAreaBelongsEmployee(area, employee);
			dealer.setNotes(notes);
			dealerRepository.save(dealer);
		} else if (type == VisitNodeType.DISTRIBUTE_STORE) {
			Store store = storeRepository.findOne(storeId);
			if (store == null) {
				throw new RecordNotFoundException("���ŵ�δ�ҵ���");
			}
			AgentArea area = store.getAgentArea();
			if (area == null) {
				throw new RecordAcessDeniedException("����Ȩ�������ŵꡣ");
			}
			AgentAreaBelongsEmployee(area, employee);
			store.setNotes(notes);
			storeRepository.save(store);
		}
	}

	@Override
	public StoreOrder placeOrder(VisitNodeType type, long storeId, Map<String, Integer> orderItems, MultipartFile picture) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		if (type.equals(VisitNodeType.DIRECT_STORE)) {
			Dealer dealer = dealerRepository.findOne(storeId);
			if (dealer == null || dealer.getIsDirect() == false) {
				throw new RecordNotFoundException("��ֱӪ��δ�ҵ���");
			}
			AgentArea area = dealer.getAgentArea();
			if (area == null) {
				throw new RecordAcessDeniedException("����Ȩ������ֱӪ�ꡣ");
			}
			AgentAreaBelongsEmployee(area, employee);
			placeDealerOrder(dealer, orderItems, false, picture);
			return null;
		} else if (type.equals(VisitNodeType.DISTRIBUTE_STORE)) {
			Store store = storeRepository.findOne(storeId);
			if (store == null) {
				throw new RecordNotFoundException("���ŵ�δ�ҵ���");
			}
			AgentArea area = store.getAgentArea();
			if (area == null) {
				throw new RecordAcessDeniedException("����Ȩ�������ŵꡣ");
			}
			AgentAreaBelongsEmployee(area, employee);
			return placeStoreOrder(store, orderItems, false, picture);
		}
		return null;
	}

	@Override
	public StoreOrder submitOrder(long orderNo) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		StoreOrder order = storeOrderRepository.findByOrderNoLocked(orderNo);
		if (order == null) {
			throw new RecordNotFoundException("�˶��������ڡ�");
		}
		AgentArea area = order.getStore().getAgentArea();
		if (area == null) {
			throw new RecordAcessDeniedException("����Ȩ�������ŵꡣ");
		}
		AgentAreaBelongsEmployee(area, employee);
		return storeOrderService.submitOrder(order);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getAgentPagedStoreOrders(int agentAreaId, int page, int pagesize) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea area = agentAreaRepository.findOne(agentAreaId);
		if (area == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(area, employee);
		PageRequest request = getPageRequest(page, pagesize);
		return storeOrderRepository.findByAgentAreaIdAndisAgentHelped(agentAreaId, true, request);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<DealerOrder> getAgentPagedDealerOrders(int agentAreaId, int page, int pagesize) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea area = agentAreaRepository.findOne(agentAreaId);
		if (area == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(area, employee);
		PageRequest request = getPageRequest(page, pagesize);
		Page<DealerOrder> orders = dealerOrderRepository.findByAgentAreaIdAndIsAgentHelped(agentAreaId, true, request);
		return orders;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public AppAgentNrGoalResult getNrGoal(int agentAreaId) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea area = agentAreaRepository.findOne(agentAreaId);
		if (area == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(area, employee);
		AgentWDNrGoal goal = area.getWdNrGoal();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = (year * 100 + month + 1);
		AgentAreaNrGoal agentAreaNrGoal = agentAreaNrGoalRepository.findByAreaIdAndDate(area.getId(), date);
		AppAgentNrGoalResult result = new AppAgentNrGoalResult();
		result.setThisDay(goal.getDayNr());
		result.setThisWeek(goal.getWeekNr());
		result.setThisMonth(agentAreaNrGoal == null ? 0f : agentAreaNrGoal.getNr());
		return result;
	}

	@Override
	public void setThisWeekGoals(int agentAreaId, float nr) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentWDNrGoal goal = agentArea.getWdNrGoal();
		goal.setWeekNr(nr);
		agentWDNrGoalRepository.save(goal);
	}

	@Override
	public void setThisDayGoals(int agentAreaId, float nr) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentWDNrGoal goal = agentArea.getWdNrGoal();
		goal.setDayNr(nr);
		agentWDNrGoalRepository.save(goal);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Sdo> getThisMonthSdos(int agentAreaId) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		Date date = new Date();
		int dateInt = DateUtils.getIntAsyyyyMM(date);
		AgentAreaSdoGoal goal = agentAreaSdoGoalRepository.findByAgentAreaIdAndDate(agentArea.getId(), dateInt);
		List<Sdo> sdos = new ArrayList<Sdo>();
		if (goal != null) {
			sdos.addAll(goal.getSdos());
		}
		return sdos;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public AppProductivityStatsResult getProductivityStatsResult(int agentAreaId) {
		// TODO ��ȡ������ͳ������...
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public AppSalesVolumeStatsResult getSalesVolumeStatsResult(int agentAreaId) {
		// TODO ��ȡ����ͳ������...
		return null;
	}

	@Override
	public List<AgentStoreCategory> getStoreCategories(int agentAreaId) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		List<AgentStoreCategory> categories = new ArrayList<AgentStoreCategory>();
		categories.addAll(agentArea.getStoreCategories());
		return categories;
	}

	@Override
	public Map<String, List<Store>> getStoresGroupByCategories(int agentAreaId) {
		Map<String, List<Store>> map = new HashMap<String, List<Store>>();
		List<AgentStoreCategory> categories = getStoreCategories(agentAreaId);
		for (AgentStoreCategory category : categories) {
			List<Store> stores = new ArrayList<Store>();
			stores.addAll(category.getStores());
			map.put(category.getCategoryName(), stores);
		}
		return map;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Store> getStoresByCategory(int agentAreaId, String category) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentStoreCategory agentStoreCategory = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		List<Store> stores = new ArrayList<Store>();
		if (agentStoreCategory != null) {
			stores.addAll(agentStoreCategory.getStores());
		}
		return stores;
	}

	@Override
	public void addNewStoreCategory(int agentAreaId, String category) {
		Assert.hasLength(category, "�����������Ϊ�ա�");
		if (category.equals(AgentStoreCategory.UNDEFINED)) {
			throw new IllegalArgumentException("�˷����������ã���ʹ��������������");
		}
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentStoreCategory agentStoreCategory = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (agentStoreCategory != null) {
			throw new RecordExistedException("�˷��������Ѵ��ڡ�");
		}

		AgentStoreCategory newCategory = new AgentStoreCategory();
		newCategory.setArea(agentArea);
		newCategory.setCategoryName(category);
		agentStoreCategoryRepository.save(newCategory);
	}

	@Override
	public void deleteStoreCategory(int agentAreaId, String category) {
		Assert.hasLength(category, "�����������Ϊ�ա�");
		if (category.equals(AgentStoreCategory.UNDEFINED)) {
			throw new IllegalArgumentException("Ĭ�Ϸ����޷�ɾ����");
		}
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentStoreCategory nowCategory = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (nowCategory == null) {
			throw new RecordNotFoundException("�˷�������δ�ҵ���");
		}
		AgentStoreCategory undefined = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), AgentStoreCategory.UNDEFINED);
		if (undefined == null) {
			throw new DataIntegrityException("��С��Ĭ�Ϸ���δ��ʼ����");
		}
		storeRepository.updateAgentCategoryByAgentCategory(nowCategory.getId(), undefined.getId());
		agentStoreCategoryRepository.delete(nowCategory);
	}

	@Override
	public void renameStoreCategory(int agentAreaId, String category, String newName) {
		Assert.hasLength(category, "�����������Ϊ�ա�");
		Assert.hasLength(newName, "�����������Ϊ�ա�");
		if (category.equals(AgentStoreCategory.UNDEFINED)) {
			throw new IllegalArgumentException("Ĭ�Ϸ����޷���������");
		}
		if (newName.equals(AgentStoreCategory.UNDEFINED)) {
			throw new RecordAcessDeniedException("�˷����������ã���ѡ��������������");
		}
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentStoreCategory category1 = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (category1 == null) {
			throw new RecordNotFoundException("�˷�������δ�ҵ���");
		}
		AgentStoreCategory category2 = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), newName);
		if (category2 != null) {
			throw new RecordExistedException("�˷��������Ѵ��ڡ�");
		}
		category1.setCategoryName(newName);
		agentStoreCategoryRepository.save(category1);
	}

	@Override
	public void moveStoreToCategory(int agentAreaId, long storeId, String category) {
		Assert.hasLength(category, "��������Ϊ�ա�");
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		Store store = storeRepository.findOne(storeId);
		if (store == null) {
			throw new RecordNotFoundException("���ŵ겻���ڡ�");
		}
		AgentArea actArea = store.getAgentArea();
		if (!agentArea.equals(actArea)) {
			throw new RecordAcessDeniedException("���ŵ겻���ڴ�CR����");
		}
		AgentStoreCategory storeCategory = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (storeCategory == null) {
			throw new RecordNotFoundException("�˷����������ڡ�");
		}
		store.setAgentCategory(storeCategory);
		storeRepository.save(store);
	}

	@Override
	public void moveStoresToCategory(int agentAreaId, List<Long> storeIds, String category) {
		Assert.hasLength(category, "��������Ϊ�ա�");
		Assert.notNull(storeIds, "��������Ϊ�ա�");
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentStoreCategory storeCategory = agentStoreCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (storeCategory == null) {
			throw new RecordNotFoundException("�˷����������ڡ�");
		}
		for (Long storeId : storeIds) {
			Store store = storeRepository.findOne(storeId);
			if (store == null) {
				throw new RecordNotFoundException("�˲�����ĳ���ŵ겻���ڡ�");
			}
			AgentArea actArea = store.getAgentArea();
			if (!agentArea.equals(actArea)) {
				throw new RecordAcessDeniedException("���ŵ겻���ڴ�CR����");
			}
			store.setAgentCategory(storeCategory);
			storeRepository.saveAndFlush(store);
		}
	}

	@Override
	public List<AgentDealerCategory> getDealerCategories(int agentAreaId) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		List<AgentDealerCategory> categories = new ArrayList<AgentDealerCategory>();
		categories.addAll(agentArea.getDealerCategories());
		return categories;
	}

	@Override
	public Map<String, List<Dealer>> getDealersGroupByCategories(int agentAreaId) {
		Map<String, List<Dealer>> map = new HashMap<String, List<Dealer>>();
		List<AgentDealerCategory> categories = getDealerCategories(agentAreaId);
		for (AgentDealerCategory category : categories) {
			List<Dealer> dealers = new ArrayList<Dealer>();
			dealers.addAll(category.getDealers());
			map.put(category.getCategoryName(), dealers);
		}
		return map;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Dealer> getDealersByCategory(int agentAreaId, String category) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentDealerCategory dealerCategory = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (dealerCategory == null) {
			throw new RecordNotFoundException("�˷�����δ�ҵ���");
		}
		List<Dealer> dealers = new ArrayList<Dealer>();
		dealers.addAll(dealerCategory.getDealers());
		return dealers;
	}

	@Override
	public void addNewDealerCategory(int agentAreaId, String category) {
		if (category.equals(AgentDealerCategory.UNDEFINED)) {
			throw new IllegalArgumentException("�˷����������ã���ʹ��������������");
		}
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentDealerCategory dealerCategory = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (dealerCategory != null) {
			throw new RecordExistedException("�˷��������Ѵ��ڡ�");
		}
		AgentDealerCategory newCategory = new AgentDealerCategory();
		newCategory.setArea(agentArea);
		newCategory.setCategoryName(category);
		agentDealerCategoryRepository.save(newCategory);
	}

	@Override
	public void deleteDealerCategory(int agentAreaId, String category) {
		if (category.equals(AgentDealerCategory.UNDEFINED)) {
			throw new IllegalArgumentException("Ĭ�Ϸ����޷�ɾ����");
		}
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentDealerCategory nowCategory = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (nowCategory == null) {
			throw new RecordNotFoundException("�˷�������δ�ҵ���");
		}
		AgentDealerCategory undefined = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), AgentDealerCategory.UNDEFINED);
		if (undefined == null) {
			throw new DataIntegrityException("��С����Ĭ�Ϸ���δ��ʼ����");
		}
		dealerRepository.updateAgentCategoryByAgentCategory(nowCategory.getId(), undefined.getId());
		agentDealerCategoryRepository.delete(nowCategory);
	}

	@Override
	public void renameDealerCategory(int agentAreaId, String category, String newName) {
		if (category.equals(AgentDealerCategory.UNDEFINED)) {
			throw new IllegalArgumentException("Ĭ�Ϸ����޷���������");
		}
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentDealerCategory category1 = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (category1 == null) {
			throw new RecordNotFoundException("�˷�������δ�ҵ���");
		}
		AgentDealerCategory category2 = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), newName);
		if (category2 != null) {
			throw new RecordExistedException("�˷��������Ѵ��ڡ�");
		}
		category1.setCategoryName(newName);
		agentDealerCategoryRepository.save(category1);
	}

	@Override
	public void moveDealerToCategory(int agentAreaId, long dealerId, String category) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		Dealer dealer = dealerRepository.findOne(dealerId);
		if (dealer == null || dealer.getIsDirect() == false) {
			throw new RecordNotFoundException("��ֱӪ�겻���ڡ�");
		}
		AgentArea actArea = dealer.getAgentArea();
		if (!agentArea.equals(actArea)) {
			throw new RecordAcessDeniedException("����Ȩ������ֱӪ�ꡣ");
		}
		AgentDealerCategory dealerCategory = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (dealerCategory == null) {
			throw new RecordNotFoundException("�˷����������ڡ�");
		}
		dealer.setCategory(dealerCategory);
		dealerRepository.save(dealer);
	}

	@Override
	public void moveDealersToCategory(int agentAreaId, List<Long> dealerIds, String category) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		AgentDealerCategory dealerCategory = agentDealerCategoryRepository.findByAgentAreaIdAndCategoryName(agentArea.getId(), category);
		if (dealerCategory == null) {
			throw new RecordNotFoundException("�˷����������ڡ�");
		}
		for (Long dealerId : dealerIds) {
			Dealer dealer = dealerRepository.findOne(dealerId);
			if (dealer == null) {
				throw new RecordNotFoundException("��ֱӪ�겻���ڡ�");
			}
			AgentArea actArea = dealer.getAgentArea();
			if (!agentArea.equals(actArea)) {
				throw new RecordAcessDeniedException("����Ȩ������ֱӪ�ꡣ");
			}
			dealer.setCategory(dealerCategory);
			dealerRepository.save(dealer);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Memorandum> getMemorandums(int agentAreaId) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		return memorandumRepository.findByAreaOrderByEditDateDesc(agentArea);
	}

	@Override
	public void addMemorandum(int agentAreaId, String content) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		Memorandum memorandum = new Memorandum();
		memorandum.setContent(content);
		memorandum.setArea(agentArea);
		memorandumRepository.save(memorandum);
	}

	@Override
	public void editMemorandum(int agentAreaId, long memrdId, String content) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		Memorandum memorandum = memorandumRepository.findOne(memrdId);
		if (!agentArea.equals(memorandum.getArea())) {
			throw new RecordAcessDeniedException("����Ȩ�����˱���¼��");
		}
		memorandum.setContent(content);
		memorandum.setEditDate(new Date());
		memorandumRepository.save(memorandum);
	}

	@Override
	public void deleteMemorandum(int agentAreaId, long memrdId) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(agentArea, employee);
		Memorandum memorandum = memorandumRepository.findOne(memrdId);
		if (!agentArea.equals(memorandum.getArea())) {
			throw new RecordAcessDeniedException("����Ȩ�����˱���¼��");
		}
		memorandumRepository.delete(memorandum);
	}

	@Override
	public void checkInventores(long dealerId, List<InventoryChange> changes, MultipartFile sign) {
		InventoriesCheckHistory history = new InventoriesCheckHistory();
		Set<CheckHistoryItem> items = new HashSet<CheckHistoryItem>();
		Dealer dealer = dealerRepository.findOne(dealerId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (dealer == null) {
			throw new RecordNotFoundException("������δ�ҵ���");
		}
		for (InventoryChange change : changes) {
			Assert.notNull(change.getProductCode(), "��Ʒ��Ų���Ϊ�ա�");
			Product product = productRepository.findOne(change.getProductCode());
			if (product == null) {
				throw new RecordNotFoundException("��Ʒδ�ҵ���");
			}
			CheckHistoryItem item = new CheckHistoryItem();
			InventPK id = new InventPK();
			id.setDealer(dealer);
			id.setProduct(product);
			Inventory inventory = inventoryRepository.findOne(id);
			if (inventory == null) {
				inventory = new Inventory();
				inventory.setPk(id);
				inventory.setSalesVol(0);
			}
			if (change.getAmount() < 0 || change.getPrice() < 0) {
				throw new IllegalArgumentException("�����۸���Ϊ��");
			}

			item.setBeforeAmount(inventory.getAmounts() == null ? 0 : inventory.getAmounts());
			item.setBeforePrice(inventory.getPrice() == null ? 0d : inventory.getPrice());
			item.setAfterAmount(change.getAmount());
			item.setAfterPrice(change.getPrice());
			item.setProduct(product);
			inventory.setAmounts(change.getAmount());
			inventory.setPrice(change.getPrice());
			inventoryRepository.save(inventory);
			items.add(item);
		}
		history.setDealer(dealer);
		history.setItems(items);
		String url = FileOperator.savePicture(sign);
		history.setUrl(url);
		inventoriesCheckHistoryRepository.save(history);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<InventoriesCheckHistory> getCheckHistories(long dealerId, Date start, Date end, int page, int size) {
		return inventoriesCheckHistoryRepository.findByDealerIdAndDateBetweenOrderByDateDesc(dealerId, start, end, getPageRequest(page, size));
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Dealer> getRelationDealers(int agentAreaId) {
		AgentArea agentArea = agentAreaRepository.findOne(agentAreaId);
		if (agentArea == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		Set<Dealer> dealers = agentArea.getDealers();
		List<Dealer> list = new ArrayList<Dealer>();
		list.addAll(dealers);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getAgentPagedStoreOrdersNotAgentHelped(int agentAreaId, int page, int pagesize) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea area = agentAreaRepository.findOne(agentAreaId);
		if (area == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(area, employee);
		PageRequest request = getPageRequest(page, pagesize);
		return storeOrderRepository.findByAgentAreaIdAndisAgentHelped(agentAreaId, false, request);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<DealerOrder> getAgentPagedDealerOrdersNotAgentHelped(int agentAreaId, int page, int pagesize) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea area = agentAreaRepository.findOne(agentAreaId);
		if (area == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(area, employee);
		PageRequest request = getPageRequest(page, pagesize);
		Page<DealerOrder> orders = dealerOrderRepository.findByAgentAreaIdAndIsAgentHelped(agentAreaId, false, request);
		return orders;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<StoreOrder> getAgentPagedStoreOrdersNotAgentHelpedByDate(int agentAreaId, Date start, Date end, int page, int pagesize) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea area = agentAreaRepository.findOne(agentAreaId);
		if (area == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(area, employee);
		PageRequest request = getPageRequest(page, pagesize);
		return storeOrderRepository.findByStoreAgentAreaIdAndIsAgentHelpedAndCreateDateBetweenOrderByIdDesc(agentAreaId, false, start, end, request);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<DealerOrder> getAgentPagedDealerOrdersNotAgentHelpedByDate(int agentAreaId, Date start, Date end, int page, int pagesize) {
		Employee employee = backEndCurrentLoginService.getCurrentLoginEmployee();
		AgentArea area = agentAreaRepository.findOne(agentAreaId);
		if (area == null) {
			throw new RecordNotFoundException("��CR��δ�ҵ���");
		}
		AgentAreaBelongsEmployee(area, employee);
		PageRequest request = getPageRequest(page, pagesize);
		Page<DealerOrder> orders = dealerOrderRepository.findByDealerAgentAreaIdAndIsAgentHelpedAndEnterDateBetweenOrderByIdDesc(agentAreaId, false, start, end, request);
		return orders;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Inventory> getStoresDealersProducts(long storeId) {
		Store store = storeRepository.findOne(storeId);
		Assert.notNull(storeId, "���ŵ겻���ڡ�");
		Dealer dealer = store.getDealer();
		if (dealer == null) {
			return null;
		}
		return inventoryService.getInventories(dealer, 1, 200).getContent();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getInventories(long dealerId, int page, int size) {
		Dealer dealer = dealerRepository.findOne(dealerId);
		Assert.notNull(dealer, "�˾�����δ�ҵ���");
		return inventoryService.getInventories(dealer, page, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getInventoriesByBrand(long dealerId, String brandName, int page, int size) {
		Dealer dealer = dealerRepository.findOne(dealerId);
		Assert.notNull(dealer, "�˾�����δ�ҵ���");
		return inventoryService.getInventoriesByBrand(dealer, brandName, page, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getInventories4Update(long dealerId, int page, int size) {
		Dealer dealer = dealerRepository.findOne(dealerId);
		Assert.notNull(dealer, "�˾�����δ�ҵ���");
		return inventoryService.getInventoriesForUpdate(dealer, page, size);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<Inventory> getInventoriesByBrand4Update(long dealerId, String brandName, int page, int size) {
		Dealer dealer = dealerRepository.findOne(dealerId);
		Assert.notNull(dealer, "�˾�����δ�ҵ���");
		return inventoryService.getInventoriesByBrandForUpdate(dealer, brandName, page, size);
	}

}
