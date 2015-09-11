package com.nirvana.test;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nirvana.domain.common.NnClient;
import com.nirvana.repository.NnClientRepository;

@ContextConfiguration(value = { "classpath:applicationContext.xml", "classpath:applicationContext-jpa.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SaveNnClient {
	
	@Resource
	private NnClientRepository nnClientRepository;
	
	private  String getCellValue(Cell cell) {
		String o = null;
		if (cell.getHyperlink() != null) {
			o = String.valueOf(cell.getHyperlink());
		} else {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				o = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				o = String.valueOf(cell.getBooleanCellValue());
				break;
			// 数值
			case Cell.CELL_TYPE_NUMERIC:
				o = String.valueOf(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				o = String.valueOf(cell.getRichStringCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				o = String.valueOf(cell.getCellFormula());
				break;
			default:
				o = null;
				break;
			}
		}
		return o;
	}
	@Test
	public void saveNnClient(){
		
		
		Workbook book = null;
		try {
			book = WorkbookFactory.create(new File("F:/myeclipse2015/project/Angel/src/test/java/1.xls"));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		NnClient nnClient=new NnClient();
		Sheet sheet = book.getSheetAt(0);
		int n = sheet.getLastRowNum();
		try{
			for(int i=1 ;i<=n;i++){
				Row row = sheet.getRow(i);
				if (row.getCell(0) != null) {
					String UM = getCellValue(row.getCell(0));
					nnClient.setUM(UM);
				} else {
					nnClient.setUM("");
				}
				if (row.getCell(1) != null) {
					String tDM = getCellValue(row.getCell(1));
					nnClient.setTDM(tDM);
				} else {
					nnClient.setTDM("");
				}
				if (row.getCell(2) != null) {
					String tDS = getCellValue(row.getCell(2));
					nnClient.setTDS(tDS);
				} else {
					nnClient.setTDS("");
				}
				if (row.getCell(3) != null) {
					String propertyCode = getCellValue(row.getCell(3));
					nnClient.setPropertyCode(propertyCode);
				} else {
					nnClient.setPropertyCode("");
				}
				if (row.getCell(4) != null) {
					String cRName = getCellValue(row.getCell(4));
					nnClient.setCRName(cRName);
				} else {
					nnClient.setCRName("");
				}
				if (row.getCell(5) != null) {
					String clientCode = getCellValue(row.getCell(5));
					nnClient.setClientCode(clientCode);
				} else {
					nnClient.setClientCode("");
				}
				if (row.getCell(6) != null) {
					String clientName = getCellValue(row.getCell(6));
					nnClient.setClientName(clientName);
				} else {
					nnClient.setClientName("");
				}
				if (row.getCell(7) != null) {
					String clitenAddress = getCellValue(row.getCell(7));
					nnClient.setClitenAddress(clitenAddress);
				} else {
					nnClient.setClitenAddress("");
				}
				if (row.getCell(8) != null) {
					String phone = getCellValue(row.getCell(8));
					nnClient.setPhone(phone);
				} else {
					nnClient.setPhone("");
				}
				if (row.getCell(9) != null) {
					String channelCode = getCellValue(row.getCell(9));
					nnClient.setChannelCode(channelCode);
				} else {
					nnClient.setChannelCode("");
				}
				if (row.getCell(10) != null) {
					String channelName = getCellValue(row.getCell(10));
					nnClient.setChannelName(channelName);
				} else {
					nnClient.setChannelName("");
				}
				if (row.getCell(11) != null) {
					String lineNum = getCellValue(row.getCell(11));
					nnClient.setLineNum(lineNum);
				} else {
					nnClient.setLineNum("");
				}
				if (row.getCell(12) != null) {
					String isClosed = getCellValue(row.getCell(12));
					nnClient.setIsClosed(isClosed);
				} else {
					nnClient.setIsClosed("");
				}if (row.getCell(13) != null) {
					String closeDay = getCellValue(row.getCell(13));
					nnClient.setCloseDay(closeDay);
				} else {
					nnClient.setCloseDay("");
				}if (row.getCell(14) != null) {
					String openDay = getCellValue(row.getCell(14));
					nnClient.setOpenDay(openDay);
				} else {
					nnClient.setOpenDay("");
				}if (row.getCell(15) != null) {
					String dealerCode = getCellValue(row.getCell(15));
					nnClient.setDealerCode(dealerCode);
				} else {
					nnClient.setDealerCode("");
				}if (row.getCell(16) != null) {
					String dealerName = getCellValue(row.getCell(16));
					nnClient.setDealerName(dealerName);
				} else {
					nnClient.setDealerName("");
				}if (row.getCell(17) != null) {
					String eRPclient = getCellValue(row.getCell(17));
					nnClient.setERPclient(eRPclient);
				} else {
					nnClient.setERPclient("");
				}if (row.getCell(18) != null) {
					String clientType = getCellValue(row.getCell(18));
					nnClient.setClientType(clientType);
				} else {
					nnClient.setClientType("");
				}if (row.getCell(19) != null) {
					String deviceCode = getCellValue(row.getCell(19));
					nnClient.setDeviceCode(deviceCode);
				} else {
					nnClient.setDeviceCode("");
				}
				if (row.getCell(20) != null) {
					String volue = getCellValue(row.getCell(20));
					nnClient.setVolue(volue);
				} else {
					nnClient.setVolue("");
				}				
			}
			nnClientRepository.save(nnClient);		
		} catch (Exception e) {
			throw new IllegalArgumentException("传入文件格式错误");
		}		
	}
}
