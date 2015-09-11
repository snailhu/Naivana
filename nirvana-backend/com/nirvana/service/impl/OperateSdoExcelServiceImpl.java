package com.nirvana.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nirvana.domain.backend.goal.sdo.MonthSdo;
import com.nirvana.domain.backend.goal.sdo.SdoExcel;
import com.nirvana.domain.backend.goal.sdo.Sdo;
import com.nirvana.domain.backend.goal.sdo.SdoExcelContent;
import com.nirvana.domain.backend.goal.sdo.SdoExcelContentPart;
import com.nirvana.domain.backend.goal.sdo.SdoExcelContentPartElement;
import com.nirvana.domain.backend.goal.sdo.SdoExcelTitle;
import com.nirvana.repository.backend.goal.sdo.MonthSdoRepository;
import com.nirvana.repository.backend.goal.sdo.SdoExcelContentPartElementRepository;
import com.nirvana.repository.backend.goal.sdo.SdoExcelContentPartRepository;
import com.nirvana.repository.backend.goal.sdo.SdoExcelContentRepository;
import com.nirvana.repository.backend.goal.sdo.SdoExcelRepository;
import com.nirvana.repository.backend.goal.sdo.SdoExcelTitleRepository;
import com.nirvana.repository.backend.goal.sdo.SdoRepository;
import com.nirvana.service.OperateSdoExcelService;

@Service
@Transactional
public class OperateSdoExcelServiceImpl implements OperateSdoExcelService {

	@Resource
	private SdoExcelContentPartElementRepository sdoExcelContentPartElementRepository;
	@Resource
	private SdoExcelContentPartRepository sdoExcelContentPartRepository;
	@Resource
	private SdoExcelContentRepository sdoExcelContentRepository;
	@Resource
	private SdoExcelTitleRepository sdoExcelTitleRepository;
	@Resource
	private SdoExcelRepository sdoExcelRepository;
	@Resource
	private SdoRepository sdoRepository;
	@Resource
	private MonthSdoRepository monthSdoRepository;

	private String getCellValue(Cell cell) {
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

	@Override
	public void uploadExcelSdoFile(InputStream is, Integer date) {
		Workbook book = null;
		try {
			book = WorkbookFactory.create(is);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Sheet sheet = book.getSheetAt(0);
			Row row1 = sheet.getRow(1);
			List<Integer> num = new ArrayList<Integer>();
			List<Integer> cycleNum = new ArrayList<Integer>();
			List<SdoExcelContentPartElement> sdoElementList = null;
			List<Sdo> sdoList = null;
			List<SdoExcelContentPart> sdoContentPartList = new ArrayList<SdoExcelContentPart>();
			for (int j = row1.getFirstCellNum() + 1; j < row1.getLastCellNum() - 2; j++) {
				String Row1Value = getCellValue(row1.getCell(j));
				if (Row1Value != null && Row1Value.length() != 0)
					num.add(j);
			}
			num.add(row1.getLastCellNum() - 2);
			for (int i = 0; i < num.size() - 1; i++) {
				cycleNum.add(num.get(i + 1) - num.get(i));
			}
			// 获取title

			SdoExcelTitle sdoExcelTitle = new SdoExcelTitle();
			if (sheet.getRow(0).getCell(0) != null) {
				String ColumnValue1 = getCellValue(sheet.getRow(0).getCell(0));
				sdoExcelTitle.setTitle1(ColumnValue1);
			} else {
				sdoExcelTitle.setTitle1("");
			}
			if (sheet.getRow(2).getCell(0) != null) {
				String ColumnValue3 = getCellValue(sheet.getRow(2).getCell(0));
				sdoExcelTitle.setTitle3(ColumnValue3);
			} else {
				sdoExcelTitle.setTitle3("");
			}
			if (sheet.getRow(3).getCell(0) != null) {
				String ColumnValue4 = getCellValue(sheet.getRow(3).getCell(0));
				sdoExcelTitle.setTitle4(ColumnValue4);
			} else {
				sdoExcelTitle.setTitle4("");
			}
			if (sheet.getRow(4).getCell(0) != null) {
				String ColumnValue5 = getCellValue(sheet.getRow(4).getCell(0));
				sdoExcelTitle.setTitle5(ColumnValue5);
			} else {
				sdoExcelTitle.setTitle5("");
			}
			if (sheet.getRow(5).getCell(0) != null) {
				String ColumnValue6 = getCellValue(sheet.getRow(5).getCell(0));
				sdoExcelTitle.setTitle6(ColumnValue6);
			} else {
				sdoExcelTitle.setTitle6("");
			}
			if (sheet.getRow(6).getCell(0) != null) {
				String ColumnValue7 = getCellValue(sheet.getRow(6).getCell(0));
				sdoExcelTitle.setTitle7(ColumnValue7);
			} else {
				sdoExcelTitle.setTitle7("");
			}
			if (sheet.getRow(7).getCell(0) != null) {
				String ColumnValue8 = getCellValue(sheet.getRow(7).getCell(0));
				sdoExcelTitle.setTitle8(ColumnValue8);
			} else {
				sdoExcelTitle.setTitle8("");
			}
			if (sheet.getRow(8).getCell(0) != null) {
				String ColumnValue9 = getCellValue(sheet.getRow(8).getCell(0));
				sdoExcelTitle.setTitle9(ColumnValue9);
			} else {
				sdoExcelTitle.setTitle9("");
			}
			if (sheet.getRow(9).getCell(0) != null) {
				String ColumnValue10 = getCellValue(sheet.getRow(9).getCell(0));
				sdoExcelTitle.setTitle10(ColumnValue10);
			} else {
				sdoExcelTitle.setTitle10("");
			}
			if (sheet.getRow(10).getCell(0) != null) {
				String ColumnValue11 = getCellValue(sheet.getRow(10).getCell(0));
				sdoExcelTitle.setTitle11(ColumnValue11);
			} else {
				sdoExcelTitle.setTitle11("");
			}
			if (sheet.getRow(11).getCell(0) != null) {
				String ColumnValue12 = getCellValue(sheet.getRow(11).getCell(0));
				sdoExcelTitle.setTitle12(ColumnValue12);
			} else {
				sdoExcelTitle.setTitle12("");
			}
			if (sheet.getRow(12).getCell(0) != null) {
				String ColumnValue13 = getCellValue(sheet.getRow(12).getCell(0));
				sdoExcelTitle.setTitle13(ColumnValue13);
			} else {
				sdoExcelTitle.setTitle13("");
			}
			if (sheet.getRow(13).getCell(0) != null) {
				String ColumnValue14 = getCellValue(sheet.getRow(13).getCell(0));
				sdoExcelTitle.setTitle14(ColumnValue14);
			} else {
				sdoExcelTitle.setTitle14("");
			}
			sdoExcelTitleRepository.saveAndFlush(sdoExcelTitle);
			// 获取content
			int m = 0;
			sdoList = new ArrayList<Sdo>();
			for (int j = 0; j < num.size() - 1; j++) {
				String Row1Title = getCellValue(row1.getCell(num.get(j)));
				sdoElementList = new ArrayList<SdoExcelContentPartElement>();
				for (int i = 1; i <= cycleNum.get(j); i++) {
					m++;
					SdoExcelContentPartElement sdoElement = new SdoExcelContentPartElement();
					Sdo sdo = new Sdo();
					Row row2 = sheet.getRow(2);
					if (row2.getCell(m) != null) {
						String ColumnValue3 = getCellValue(sheet.getRow(2).getCell(m));
						sdoElement.setP3(ColumnValue3);
						sdo.setName(ColumnValue3);
					} else {
						sdoElement.setP3("");
						sdo.setName("");
					}
					if (sheet.getRow(3).getCell(m) != null) {
						String ColumnValue4 = getCellValue(sheet.getRow(3).getCell(m));
						sdoElement.setP4(ColumnValue4);
						sdo.setCriterion(ColumnValue4);
					} else {
						sdoElement.setP4("");
						sdo.setCriterion("");
					}
					if (sheet.getRow(4).getCell(m) != null) {
						String ColumnValue5 = getCellValue(sheet.getRow(4).getCell(m));
						sdoElement.setP5(ColumnValue5);
						sdo.setMethod(ColumnValue5);

					} else {
						sdoElement.setP5("");
						sdo.setMethod("");
					}
					if (sheet.getRow(5).getCell(m) != null) {
						String ColumnValue6 = getCellValue(sheet.getRow(5).getCell(m));
						sdoElement.setP6(ColumnValue6);
						sdo.setValue(ColumnValue6);
					} else {
						sdoElement.setP6("");
						sdo.setValue("");
					}
					if (sheet.getRow(6).getCell(m) != null) {
						String ColumnValue7 = getCellValue(sheet.getRow(6).getCell(m));
						sdoElement.setP7(ColumnValue7);
					} else {
						sdoElement.setP7("");
					}
					if (sheet.getRow(7).getCell(m) != null) {
						String ColumnValue8 = getCellValue(sheet.getRow(7).getCell(m));
						sdoElement.setP8(ColumnValue8);
					} else {
						sdoElement.setP8("");
					}
					if (sheet.getRow(8).getCell(m) != null) {
						String ColumnValue9 = getCellValue(sheet.getRow(8).getCell(m));
						sdoElement.setP9(ColumnValue9);
					} else {
						sdoElement.setP9("");
					}
					if (sheet.getRow(9).getCell(m) != null) {
						String ColumnValue10 = getCellValue(sheet.getRow(9).getCell(m));
						sdoElement.setP10(ColumnValue10);
					} else {
						sdoElement.setP10("");
					}
					if (sheet.getRow(10).getCell(m) != null) {
						String ColumnValue11 = getCellValue(sheet.getRow(10).getCell(m));
						sdoElement.setP11(ColumnValue11);
					} else {
						sdoElement.setP11("");
					}
					if (sheet.getRow(11).getCell(m) != null) {
						String ColumnValue12 = getCellValue(sheet.getRow(11).getCell(m));
						sdoElement.setP12(ColumnValue12);
					} else {
						sdoElement.setP12("");
					}
					if (sheet.getRow(12).getCell(m) != null) {
						String ColumnValue13 = getCellValue(sheet.getRow(12).getCell(m));
						sdoElement.setP13(ColumnValue13);
					} else {
						sdoElement.setP13("");
					}
					if (sheet.getRow(13).getCell(m) != null) {
						String ColumnValue14 = getCellValue(sheet.getRow(13).getCell(m));
						sdoElement.setP14(ColumnValue14);
					} else {
						sdoElement.setP14("");
					}
					sdoElementList.add(sdoElement);
					sdoList.add(sdo);
					sdoExcelContentPartElementRepository.saveAndFlush(sdoElement);
				}
				SdoExcelContentPart sdoPart = new SdoExcelContentPart();
				sdoPart.setTitle(Row1Title);
				sdoPart.setElements(sdoElementList);
				sdoExcelContentPartRepository.saveAndFlush(sdoPart);
				sdoContentPartList.add(sdoPart);
			}
			SdoExcelContent sdoContent = new SdoExcelContent();
			sdoContent.setTitle(getCellValue(sheet.getRow(0).getCell(1)));
			sdoContent.setParts(sdoContentPartList);
			sdoExcelContentRepository.saveAndFlush(sdoContent);

			// 组装title和content成Excel
			MonthSdo monthSdo = new MonthSdo();
			monthSdo.setDate(date);
			monthSdo.setSdos(sdoList);
			monthSdoRepository.saveAndFlush(monthSdo);
			SdoExcel sdoExcel = new SdoExcel();
			sdoExcel.setDate(date);
			sdoExcel.setContent(sdoContent);
			sdoExcel.setTitle(sdoExcelTitle);
			sdoExcelRepository.saveAndFlush(sdoExcel);
		} catch (Exception e) {
			throw new IllegalArgumentException("传入文件格式错误");
		}

	}

}
