package in.cw.csense.app.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import in.cw.sense.api.bo.bill.dto.BillDto;

public class BillJSONReader {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		File folder = new File("C:\\cantwait\\bills");
		Gson gson = new Gson();
		
		for(File file :  folder.listFiles()) {
			BillDto billDto = gson.fromJson(new FileReader(file), BillDto.class);
			System.out.println(billDto.getId());
		}
	}
}
