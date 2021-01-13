import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
public class Replace {
	static long before = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	static long start = System.currentTimeMillis();
	private static final String IN_FILE = "D:\\exeter\\t8.shakespeare.txt";
	private static final String OUT_FILE = "D:\\exeter\\output.txt";

	public static void main(String[] args) throws IOException{
		FileUtils futils = new FileUtils();
		futils.replaceTextFile(IN_FILE,OUT_FILE);
		Scanner txtFile = new Scanner(new File("D:\\exeter\\t8.shakespeare.txt"));
		TreeMap<String,Integer> map = new TreeMap<String,Integer>();
		while(txtFile.hasNext()) {
			String word = txtFile.next();
			int count=1;
			if(map.containsKey(word)) {
				count = map.get(word)+1;
			}
			map.put(word, count);
		}
		txtFile.close();
		System.out.print("Frequency");
		for(Map.Entry<String, Integer> entry: map.entrySet()) {
			System.out.println(entry);
		}
		long after = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		long actual = after-before;
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println("Memory usage: "+(double)actual*1024*1024+" mb");
		System.out.println("Running Time: "+formatter.format((end-start)/1000d)+" seconds");
		
	}
	
}
