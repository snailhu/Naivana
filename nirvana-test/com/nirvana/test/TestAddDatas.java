package com.nirvana.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.domain.backend.AgentDealerCategory;
import com.nirvana.domain.backend.AgentStoreCategory;
import com.nirvana.domain.backend.Employee;
import com.nirvana.domain.backend.Position;
import com.nirvana.domain.backend.PositionType;
import com.nirvana.domain.backend.VisitRoute;
import com.nirvana.domain.backend.area.AgentArea;
import com.nirvana.domain.backend.area.BigArea;
import com.nirvana.domain.backend.area.DirectorArea;
import com.nirvana.domain.backend.area.ManagerArea;
import com.nirvana.domain.backend.goal.AgentWDNrGoal;
import com.nirvana.domain.backend.usersys.BackEndUser;
import com.nirvana.domain.dealer.Dealer;
import com.nirvana.domain.dealer.DealerStoreCategory;
import com.nirvana.domain.dealer.DealerStorefrontInfo;
import com.nirvana.domain.dealer.usersys.DealerUser;
import com.nirvana.domain.store.Store;
import com.nirvana.domain.store.StoreStorefrontInfo;
import com.nirvana.domain.store.usersys.StoreUser;
import com.nirvana.erp.domain.PepsiCustomer;
import com.nirvana.erp.repository.PepsiCustomerRepository;
import com.nirvana.erp.service.ErpService;
import com.nirvana.repository.backend.AgentDealerCategoryRepository;
import com.nirvana.repository.backend.AgentStoreCategoryRepository;
import com.nirvana.repository.backend.EmployeeRepository;
import com.nirvana.repository.backend.PositionRepository;
import com.nirvana.repository.backend.VisitRouteRepository;
import com.nirvana.repository.backend.area.AgentAreaRepository;
import com.nirvana.repository.backend.area.BigAreaRepository;
import com.nirvana.repository.backend.area.DirectorAreaRepository;
import com.nirvana.repository.backend.area.ManagerAreaRepository;
import com.nirvana.repository.backend.usersys.BackEndUserRepository;
import com.nirvana.repository.dealer.DealerRepository;
import com.nirvana.repository.dealer.DealerSerialNumberRepository;
import com.nirvana.repository.dealer.DealerStockSerialNumberRepository;
import com.nirvana.repository.dealer.DealerStoreCategoryRepository;
import com.nirvana.repository.dealer.usersys.DealerUserRepository;
import com.nirvana.repository.store.StoreRepository;
import com.nirvana.repository.store.usersys.StoreUserRepository;
import com.nirvana.service.DealerService;
import com.nirvana.service.SynchronizeService;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml", "classpath:applicationContext-mongo.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAddDatas {

	@Resource
	private ErpService erpService;
	@Resource
	private SynchronizeService synchronizeService;
	@Resource
	private DealerService dealerService;
	@Resource
	private BigAreaRepository bigAreaRepository;
	@Resource
	private EmployeeRepository employeeRepository;
	@Resource
	private BackEndUserRepository backEndUserRepository;
	@Resource
	private ManagerAreaRepository managerAreaRepository;
	@Resource
	private DirectorAreaRepository directorAreaRepository;
	@Resource
	private AgentAreaRepository agentAreaRepository;
	@Resource
	private StoreRepository storeRepository;
	@Resource
	private StoreUserRepository storeUserRepository;
	@Resource
	private DealerUserRepository dealerUserRepository;
	@Resource
	private PositionRepository positionRepository;
	@Resource
	private VisitRouteRepository visitRouteRepository;
	@Resource
	private DealerRepository dealerRepository;
	@Resource
	private AgentStoreCategoryRepository agentStoreCategoryRepository;
	@Resource
	private AgentDealerCategoryRepository agentDealerCategoryRepository;
	@Resource
	private DealerSerialNumberRepository dealerSerialNumberRepository;
	@Resource
	private DealerStockSerialNumberRepository dealerStockSerialNumberRepository;
	@Resource
	private PepsiCustomerRepository pepsiCustomerRepository;
	@Resource
	private DealerStoreCategoryRepository dealerStoreCategoryRepository;

	@Test
	public void addData() {
		synchronizeService.syncChannels();
		synchronizeService.syncProducts();
		synchronizeService.syncRegions();
		addEmployee();
		addStore();
		addDealer();
		addDirectDealer();
		erpService.syncLocalCustomers(false);
		initDealer();
		addPosition();
		// setPemmision();
		// setRelation();
	}

	@Test
	public void testCreateTable() {
		/** Do nothing. */
	}

	@Test
	public void addPosition() {
		Position position1 = new Position();
		Position position2 = new Position();
		Position position3 = new Position();
		Position position4 = new Position();
		Position position5 = new Position();
		Position position6 = new Position();

		position1.setType(PositionType.INFO_MINISTRY);
		position2.setType(PositionType.SIS);
		position3.setType(PositionType.CDM);
		position4.setType(PositionType.SISM);
		position5.setType(PositionType.MDM);
		position6.setType(PositionType.GM);
		Employee employee = employeeRepository.findOne(1L);
		position1.setEmployee(employee);
		positionRepository.save(position1);
		positionRepository.save(position2);
		positionRepository.save(position3);
		positionRepository.save(position4);
		positionRepository.save(position5);
		positionRepository.save(position6);
	}

	@Test
	public void setPemmision() {
		Employee employee1 = employeeRepository.findOne(1L);
		Employee employee2 = employeeRepository.findOne(2L);

		Position position2 = positionRepository.findOne(2);
		Position position3 = positionRepository.findOne(3);
		Position position4 = positionRepository.findOne(4);
		Position position5 = positionRepository.findOne(5);

		Position agent6 = positionRepository.findOne(6);
		Position agent7 = positionRepository.findOne(7);
		Position agent8 = positionRepository.findOne(8);

		position2.setEmployee(employee2);
		position3.setEmployee(employee2);
		position4.setEmployee(employee2);
		position5.setEmployee(employee2);

		agent6.setEmployee(employee1);
		agent7.setEmployee(employee1);
		agent8.setEmployee(employee1);

		positionRepository.save(position2);
		positionRepository.save(position3);
		positionRepository.save(position4);
		positionRepository.save(position5);

		positionRepository.save(agent6);
		positionRepository.save(agent7);
		positionRepository.save(agent8);

		Employee employee3 = employeeRepository.findOne(3L);
		Position managePosition = new Position();
		managePosition.setType(PositionType.INFO_MINISTRY);
		managePosition.setEmployee(employee3);
		positionRepository.save(managePosition);

	}

	@Test
	public void addStore() {

		for (int i = 0; i < 100; i++) {
			StoreUser user = new StoreUser();
			Store store = new Store();
			store.setIsClose(false);
			StoreStorefrontInfo storefrontInfo = new StoreStorefrontInfo();
			storefrontInfo.setDeliveryAddr("浦庄大道" + i + "号");
			storefrontInfo.setEmail("pepsi" + i + "@sfi.com");
			storefrontInfo.setFaxNum("12315131" + i);
			storefrontInfo.setName("浦庄店" + i);
			storefrontInfo.setPhoneNum("13800000000");
			storefrontInfo.setManager("顾老板");
			storefrontInfo.setPostalCode("223800");
			store.setStorefrontInfo(storefrontInfo);
			user.setStore(store);
			user.setPhone((13800000000L + i) + "");
			user.setPassword("123");
			user.setUsername("store" + i);
			storeUserRepository.save(user);
		}

	}

	@Test
	public void addDealer() {

		DirectorArea directorArea = directorAreaRepository.findOne(1);
		if (directorArea == null) {
			throw new IllegalArgumentException();
		}
		Page<PepsiCustomer> page = pepsiCustomerRepository.findAll(new PageRequest(0, 100));
		List<PepsiCustomer> customers = page.getContent();

		for (int i = 0; i < 5; i++) {

			DealerUser user = new DealerUser();
			user.setPhone((13800000000L + i) + "");
			user.setUsername("dealer" + i);
			user.setPassword("123");
			Dealer dealer = new Dealer();
			dealer.setCodeInERP(customers.get(i).getCustomerId());
			dealer.setDirectorArea(directorArea);
			dealer.setIsClose(false);
			dealer.setIsDirect(false);
			DealerStorefrontInfo storefrontInfo = new DealerStorefrontInfo();
			storefrontInfo.setManager("小明");
			storefrontInfo.setName("浦庄大道" + i + "号经销商");
			storefrontInfo.setBusinessAddr("浦庄大道" + i + "号");
			storefrontInfo.setRegisterAddr("浦庄大道" + i + "号");
			storefrontInfo.setPostCode("123145");
			storefrontInfo.setTown("ASFAS");
			storefrontInfo.setCity("苏州");
			storefrontInfo.setContactType("手机");
			storefrontInfo.setContactValue("13800003800");
			storefrontInfo.setCountry("吴中区");
			storefrontInfo.setCustGrp("06654506");
			storefrontInfo.setInvoiceType("我擦");
			storefrontInfo.setWarehouse("030504");
			dealer.setStorefrontInfo(storefrontInfo);
			user.setDealer(dealer);
			dealerUserRepository.save(user);
		}

	}

	@Test
	public void initDealer() {
		List<Dealer> dealers = dealerRepository.findAll();
		for (Dealer dealer : dealers) {
			dealerService.initDealer(dealer.getId());
		}
	}

	@Test
	public void addDirectDealer() {

		DirectorArea directorArea = directorAreaRepository.findOne(1);
		if (directorArea == null) {
			throw new IllegalArgumentException();
		}

		Page<PepsiCustomer> page = pepsiCustomerRepository.findAll(new PageRequest(0, 100));
		List<PepsiCustomer> customers = page.getContent();

		for (int i = 0; i < 50; i++) {

			DealerUser user = new DealerUser();
			user.setPhone((13900000000L + i) + "");
			user.setUsername("dstore" + i);
			user.setPassword("123");
			Dealer dealer = new Dealer();
			dealer.setCodeInERP(customers.get(i + 6).getCustomerId());
			dealer.setDirectorArea(directorArea);
			dealer.setIsClose(false);
			dealer.setIsDirect(true);
			DealerStorefrontInfo storefrontInfo = new DealerStorefrontInfo();
			storefrontInfo.setManager("小明");
			storefrontInfo.setName("浦庄大道" + i + "号直营店");
			storefrontInfo.setRegisterAddr("浦庄大道" + i + "号");
			storefrontInfo.setPostCode("123145");
			storefrontInfo.setTown("ASFAS");
			storefrontInfo.setCity("苏州");
			storefrontInfo.setContactType("手机");
			storefrontInfo.setContactValue("13800003800");
			storefrontInfo.setCountry("吴中区");
			storefrontInfo.setCustGrp("06654506");
			storefrontInfo.setInvoiceType("我擦");
			storefrontInfo.setWarehouse("030504");
			dealer.setStorefrontInfo(storefrontInfo);
			user.setDealer(dealer);
			dealerUserRepository.save(user);
		}
	}

	@Test
	public void addBigAreaAndPosition() {

		BigArea bigArea1 = new BigArea();
		BigArea bigArea2 = new BigArea();
		BigArea bigArea3 = new BigArea();

		Position fsis1 = new Position();
		Position fsis2 = new Position();
		Position fsis3 = new Position();
		fsis1.setType(PositionType.FSIS);
		fsis2.setType(PositionType.FSIS);
		fsis3.setType(PositionType.FSIS);
		bigArea1.setFsis(fsis1);
		bigArea2.setFsis(fsis2);
		bigArea3.setFsis(fsis3);

		Position bigManager1 = new Position();
		Position bigManager2 = new Position();
		Position bigManager3 = new Position();
		bigManager1.setType(PositionType.UM);
		bigManager2.setType(PositionType.UM);
		bigManager3.setType(PositionType.UM);
		bigArea1.setManager(bigManager1);
		bigArea2.setManager(bigManager2);
		bigArea3.setManager(bigManager3);

		bigArea1.setName("大区1");
		bigArea2.setName("大区2");
		bigArea3.setName("大区3");

		bigArea1.setAreaCode("B1");
		bigArea2.setAreaCode("B2");
		bigArea3.setAreaCode("B3");

		bigAreaRepository.save(bigArea1);
		bigAreaRepository.save(bigArea2);
		bigAreaRepository.save(bigArea3);

	}

	@Test
	public void addManagerAreaAndPosition() {

		List<BigArea> bigAreas = bigAreaRepository.findAll();
		int i = 0;
		for (BigArea bigArea : bigAreas) {
			ManagerArea managerArea1 = new ManagerArea();
			ManagerArea managerArea2 = new ManagerArea();
			ManagerArea managerArea3 = new ManagerArea();

			managerArea1.setBigarea(bigArea);
			managerArea2.setBigarea(bigArea);
			managerArea3.setBigarea(bigArea);

			Position clerk1 = new Position();
			Position clerk2 = new Position();
			Position clerk3 = new Position();
			clerk1.setType(PositionType.CLERK);
			clerk2.setType(PositionType.CLERK);
			clerk3.setType(PositionType.CLERK);
			managerArea1.setClerk(clerk1);
			managerArea2.setClerk(clerk2);
			managerArea3.setClerk(clerk3);

			Position manager1 = new Position();
			Position manager2 = new Position();
			Position manager3 = new Position();
			manager1.setType(PositionType.TDM);
			manager2.setType(PositionType.TDM);
			manager3.setType(PositionType.TDM);
			managerArea1.setManager(manager1);
			managerArea2.setManager(manager2);
			managerArea3.setManager(manager3);

			managerArea1.setAreaCode("M" + (i * 3 + 1));
			managerArea2.setAreaCode("M" + (i * 3 + 2));
			managerArea3.setAreaCode("M" + (i * 3 + 3));

			managerArea1.setName(bigArea.getName() + "经理区" + 1);
			managerArea2.setName(bigArea.getName() + "经理区" + 2);
			managerArea3.setName(bigArea.getName() + "经理区" + 3);

			managerAreaRepository.save(managerArea1);
			managerAreaRepository.save(managerArea2);
			managerAreaRepository.save(managerArea3);

			i++;

		}

	}

	@Test
	public void addDirectAreaAndPosition() {
		List<ManagerArea> managerAreas = managerAreaRepository.findAll();

		int i = 0;
		for (ManagerArea managerArea : managerAreas) {
			DirectorArea directorArea1 = new DirectorArea();
			DirectorArea directorArea2 = new DirectorArea();
			DirectorArea directorArea3 = new DirectorArea();

			Position director1 = new Position();
			Position director2 = new Position();
			Position director3 = new Position();
			director1.setType(PositionType.TDS);
			director2.setType(PositionType.TDS);
			director3.setType(PositionType.TDS);
			directorArea1.setDirector(director1);
			directorArea2.setDirector(director2);
			directorArea3.setDirector(director3);

			directorArea1.setManagerArea(managerArea);
			directorArea2.setManagerArea(managerArea);
			directorArea3.setManagerArea(managerArea);

			directorArea1.setAreaCode("D" + (i * 3 + 1));
			directorArea2.setAreaCode("D" + (i * 3 + 2));
			directorArea3.setAreaCode("D" + (i * 3 + 3));

			directorArea1.setName(managerArea.getName() + "主任区" + 1);
			directorArea2.setName(managerArea.getName() + "主任区" + 2);
			directorArea3.setName(managerArea.getName() + "主任区" + 3);

			directorAreaRepository.save(directorArea1);
			directorAreaRepository.save(directorArea2);
			directorAreaRepository.save(directorArea3);

			i++;
		}

	}

	@Test
	public void addAgentAreaAndPosition() {

		List<DirectorArea> directorAreas = directorAreaRepository.findAll();
		int i = 0;
		for (DirectorArea directorArea : directorAreas) {
			AgentArea agentArea1 = new AgentArea();
			AgentArea agentArea2 = new AgentArea();
			AgentArea agentArea3 = new AgentArea();

			Position agent1 = new Position();
			Position agent2 = new Position();
			Position agent3 = new Position();
			agent1.setType(PositionType.AGENT);
			agent2.setType(PositionType.AGENT);
			agent3.setType(PositionType.AGENT);
			agentArea1.setAgent(agent1);
			agentArea2.setAgent(agent2);
			agentArea3.setAgent(agent3);

			agentArea1.setAreaCode("A" + (i * 3 + 1));
			agentArea2.setAreaCode("A" + (i * 3 + 2));
			agentArea3.setAreaCode("A" + (i * 3 + 3));

			agentArea1.setDirectorArea(directorArea);
			agentArea2.setDirectorArea(directorArea);
			agentArea3.setDirectorArea(directorArea);

			agentArea1.setDesc(directorArea.getName() + "CR区" + 1);
			agentArea2.setDesc(directorArea.getName() + "CR区" + 2);
			agentArea3.setDesc(directorArea.getName() + "CR区" + 3);

			AgentWDNrGoal wdNrGoal1 = new AgentWDNrGoal();
			AgentWDNrGoal wdNrGoal2 = new AgentWDNrGoal();
			AgentWDNrGoal wdNrGoal3 = new AgentWDNrGoal();
			wdNrGoal1.setDayNr(0F);
			wdNrGoal2.setDayNr(0F);
			wdNrGoal3.setDayNr(0F);
			wdNrGoal1.setWeekNr(0F);
			wdNrGoal2.setWeekNr(0F);
			wdNrGoal3.setWeekNr(0F);
			agentArea1.setWdNrGoal(wdNrGoal1);
			agentArea2.setWdNrGoal(wdNrGoal2);
			agentArea3.setWdNrGoal(wdNrGoal3);

			AgentStoreCategory storeCategory1 = new AgentStoreCategory();
			AgentStoreCategory storeCategory2 = new AgentStoreCategory();
			AgentStoreCategory storeCategory3 = new AgentStoreCategory();
			storeCategory1.setCategoryName(AgentStoreCategory.UNDEFINED);
			storeCategory2.setCategoryName(AgentStoreCategory.UNDEFINED);
			storeCategory3.setCategoryName(AgentStoreCategory.UNDEFINED);
			Set<AgentStoreCategory> storeCategories1 = new HashSet<AgentStoreCategory>();
			Set<AgentStoreCategory> storeCategories2 = new HashSet<AgentStoreCategory>();
			Set<AgentStoreCategory> storeCategories3 = new HashSet<AgentStoreCategory>();
			storeCategories1.add(storeCategory1);
			storeCategories2.add(storeCategory2);
			storeCategories3.add(storeCategory3);
			agentArea1.setStoreCategories(storeCategories1);
			agentArea2.setStoreCategories(storeCategories2);
			agentArea3.setStoreCategories(storeCategories3);

			AgentDealerCategory dealerCategory1 = new AgentDealerCategory();
			AgentDealerCategory dealerCategory2 = new AgentDealerCategory();
			AgentDealerCategory dealerCategory3 = new AgentDealerCategory();
			dealerCategory1.setCategoryName(AgentDealerCategory.UNDEFINED);
			dealerCategory2.setCategoryName(AgentDealerCategory.UNDEFINED);
			dealerCategory3.setCategoryName(AgentDealerCategory.UNDEFINED);
			Set<AgentDealerCategory> dealerCategories1 = new HashSet<AgentDealerCategory>();
			Set<AgentDealerCategory> dealerCategories2 = new HashSet<AgentDealerCategory>();
			Set<AgentDealerCategory> dealerCategories3 = new HashSet<AgentDealerCategory>();
			dealerCategories1.add(dealerCategory1);
			dealerCategories2.add(dealerCategory2);
			dealerCategories3.add(dealerCategory3);
			agentArea1.setDealerCategories(dealerCategories1);
			agentArea2.setDealerCategories(dealerCategories2);
			agentArea3.setDealerCategories(dealerCategories3);

			agentAreaRepository.save(agentArea1);
			agentAreaRepository.save(agentArea2);
			agentAreaRepository.save(agentArea3);

			i++;

		}

	}

	@Test
	public void addVisitRoute() {
		List<AgentArea> agentAreas = agentAreaRepository.findAll();
		for (AgentArea agentArea : agentAreas) {
			for (int i = 1; i <= 10; i++) {
				VisitRoute route = new VisitRoute();
				route.setArea(agentArea);
				route.setCode(i);
				visitRouteRepository.save(route);
			}
		}

	}

	@Test
	public void addEmployee() {
		for (int i = 0; i < 10; i++) {
			BackEndUser user = new BackEndUser();
			Employee employee = new Employee();
			employee.setECode("E00" + i);
			employee.setName("大屁股");
			user.setEmployee(employee);
			user.setUsername("user" + i);
			user.setPassword("123");
			user.setPhone((18112545949L + i) + "");
			backEndUserRepository.save(user);
		}
	}

	@Test
	public void setRelation() {
		// 設定Dealer和Store的关系。ID为 1-20的Store关联到ID为1的Dealer上。
		Dealer dealer1 = dealerRepository.findOne(1l);
		AgentArea agentArea1 = agentAreaRepository.findOne(1);
		DealerStoreCategory dealerStoreCategory1 = dealerStoreCategoryRepository.findOne(1l);
		AgentStoreCategory storeCategory1 = agentStoreCategoryRepository.findOne(1l);
		AgentDealerCategory agentDealerCategory1 = agentDealerCategoryRepository.findOne(1l);

		for (long i = 1; i <= 20l; i++) {
			Store store = storeRepository.findOne(i);
			store.setDealer(dealer1);
			store.setAgentArea(agentArea1);
			store.setAgentCategory(storeCategory1);
			store.setDealerCategory(dealerStoreCategory1);
			storeRepository.save(store);
		}

		// 设定Dealer和AgentArea之间的关系。将ID为1的Dealer和ID为1的AgentArea关联。

		Set<Dealer> set = new HashSet<Dealer>();
		set.add(dealer1);
		agentArea1.setDealers(set);
		agentAreaRepository.save(agentArea1);

		// 設定直营Dealer和AgentArea之间的关系，将ID为6-55的Dealer与AgentArea1关联。

		for (long i = 6l; i <= 55l; i++) {
			Dealer dealer = dealerRepository.findOne(i);
			dealer.setAgentArea(agentArea1);
			dealer.setCategory(agentDealerCategory1);
			dealerRepository.save(dealer);
		}
	}
}