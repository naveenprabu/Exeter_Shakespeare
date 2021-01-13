import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtils {

	private static final Charset CHARSET = Charset.forName("UTF-8");
	private static final String FIND_REPLACE_XLSX = "D:\\exeter\\french_dictionary.xlsx";
	private static final int FINDCOL = 0;
	private static final int REPLACECOL = 1;

	public void replaceTextFile(String inFile, String outFile) throws IOException {
		List<FindReplaceStr> list = getFindReplaceList();
		Path path = Paths.get(inFile);
		String str = readFile(path);
		
		for(FindReplaceStr item: list) {
			str = str.replace(item.findStr, item.replaceStr);
			
		}
		Path toPath = Paths.get(outFile);
		BufferedWriter writer = Files.newBufferedWriter(toPath, CHARSET);
		writer.write(str);
		writer.close();
	}

	private String readFile(Path path) throws IOException {
		byte[] bytes = Files.readAllBytes(path);
		return new String(bytes, CHARSET);
	}

	private List<FindReplaceStr> getFindReplaceList() throws EncryptedDocumentException, IOException {
		
		Workbook workbook = WorkbookFactory.create(new File(FIND_REPLACE_XLSX));
		Sheet sheet = workbook.getSheetAt(0);
		DataFormatter dataFormatter = new DataFormatter();
		int rows = sheet.getPhysicalNumberOfRows();
		//store List of findstr and replacestr
		List<FindReplaceStr> list = new ArrayList<FindReplaceStr>();
		//ignore header in excel row[0]
		for(int i=1;i<rows;i++) {
			Row row = sheet.getRow(i);
			list.add(new FindReplaceStr(dataFormatter.formatCellValue(row.getCell(FINDCOL)),
					dataFormatter.formatCellValue(row.getCell(REPLACECOL))));
			
		}
		return list;
	}

}
