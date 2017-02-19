package com.vjentrps.oms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vjentrps.oms.exception.OmsServiceException;
import com.vjentrps.oms.model.Category;
import com.vjentrps.oms.model.Product;
import com.vjentrps.oms.service.CategoryService;
import com.vjentrps.oms.service.ProductService;

@RestController
@RequestMapping(value="/service/product/loaddata")
public class LoadProducDataController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String loadProductData(@RequestParam("filePath") String filePath) throws IOException  {

        FileInputStream inputStream = new FileInputStream(new File(filePath));
        List<Product> products = new ArrayList<Product>();
        
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        
         
		while (iterator.hasNext()) {
			Product product = new Product();
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int i = 0;
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (i == 0) {
					product.setProductName(cell.getStringCellValue());
				} else if (i == 1) {
					Category category = new Category();
					category.setCategoryName(cell.getStringCellValue());
					product.setCategory(category);
				} else if (i == 2) {
					product.setUnitOfMeasure(cell.getStringCellValue());
				} else if (i == 3) {
					product.setUnitBasicRate((double) cell.getNumericCellValue());
				} else if (i == 4) {
					product.setGoodBalance((long) cell.getNumericCellValue());
				} else if (i == 5) {
					product.setDefBalance((long) cell.getNumericCellValue());
				}
				i=i+1;
				
			}
			products.add(product);

		}
		
		if(CollectionUtils.isNotEmpty(products)) {
			for(Product product : products) {
				try {
					Category category = categoryService.getCategoryByName(product.getCategory().getCategoryName());
					if(null!=category) {
						product.getCategory().setCategoryId(category.getCategoryId());
						productService.addProduct(product);
					} else {
						System.out.println(product.getProductName());
					}
				
				} catch (OmsServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
   

		return "";
	}

}
