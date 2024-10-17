package com.example.demo;

import com.example.demo.Service.Tools_uszn;
import com.example.demo.entity.AreaEducation;
import com.example.demo.entity.Organizations;
import com.example.demo.entity.Vacations;
import com.example.demo.repo.AreaEnducationRepo;
import com.example.demo.repo.OrgRepo;
import com.example.demo.repo.VacantionRepo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private OrgRepo orgRepo;

	@Autowired
	private AreaEnducationRepo areaEducationRepo;

	@Autowired
	private VacantionRepo vacationRepo;

	@Autowired
	private Tools_uszn toolsUszn;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("start");

		try (FileInputStream fis = new FileInputStream("file.xlsx");
			 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheetAt(0);
			int startRow = 3;

			for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				System.out.println("Curent row:"+ i);
				if (isRowEmpty(row)) {
					continue;
				}

				Vacations vacations = new Vacations();
				ArrayList<String> desc_list = new ArrayList<>();
				ArrayList<String> prim_list = toolsUszn.getPrimList();

				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
						case 0:
							vacations.setWrite_date(new Date());
							vacations.setLook_date(cell.getDateCellValue());
							break;
						case 1:
							vacations.setPosada(capitalizeFirstLetter(cell.getStringCellValue()));
							break;
						case 2:
							Optional<AreaEducation> areaEducationOpt = areaEducationRepo.findByName(cell.getStringCellValue());
							if (areaEducationOpt.isPresent()) {
								vacations.setAreaEducation(areaEducationOpt.get());
							} else {
								System.out.println("Area education not found: " + cell.getStringCellValue());
							}
							break;
						case 3:
							Optional<Organizations> orgOpt = orgRepo.findByName(cell.getStringCellValue());
							if (orgOpt.isPresent()) {
								vacations.setOrganizations(orgOpt.get());
							} else {
								System.out.println("Organization not found: " + cell.getStringCellValue());
							}
							break;
						case 4: case 5: case 6: case 7:
							desc_list.add(cell.getStringCellValue());
							break;
					}
				}

				StringBuilder prim = new StringBuilder();
				for (int j = 0; j < desc_list.size(); j++) {
					if (!desc_list.get(j).isEmpty()) {
						prim.append(prim_list.get(j).replace("{" + j + "}", desc_list.get(j)));
					}
				}
				vacations.setComentar(prim.toString());

				vacationRepo.save(vacations);
			}
		}
	}

	private static boolean isRowEmpty(Row row) {
		for (int i = 0; i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			if (cell != null && cell.getCellType() != CellType.BLANK) {
				return false;
			}
		}
		return true;
	}

	public static String capitalizeFirstLetter(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}
}
