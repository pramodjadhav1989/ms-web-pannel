package com.web.pannel.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

import com.opencsv.CSVWriter;

public class Util {
	public static String decodeToken(String input) {
		try {
			String jwtToken = input;
			System.out.println("------------ Decode JWT ------------");
			String[] split_string = jwtToken.split("\\.");
			String base64EncodedHeader = split_string[0];
			String base64EncodedBody = split_string[1];
			String base64EncodedSignature = split_string[2];

			Base64 base64Url = new Base64(true);
			String header = new String(base64Url.decode(base64EncodedHeader));
			System.out.println("JWT Header : " + header);

			System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
			String body = new String(base64Url.decode(base64EncodedBody));
			System.out.println("JWT Body : " + body);
			return body;
		} catch (Exception ex) {

		}
		return "";
	}

	public static String writeDataForCustomSeparatorCSV(String fileName, List<Map<String, Object>> mapdata) {

		String base64 = "";
		File file = new File(fileName);

		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter with '|' as separator
			CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			// create a List which contains String array
			List<String[]> data = new ArrayList<String[]>();

			List<String> header = new ArrayList<String>();
			
			for(String h :  mapdata.get(0).keySet()) {
				header.add(h);
			}

			//header = (String[]) mapdata.get(0).keySet().toArray();

			data.add(header.toArray(new String[0]));

			for (Map<String, Object> d : mapdata) {

				List<String> rows = new ArrayList<String>();
				for (String s : header) {
					rows.add(String.valueOf(d.get(s)));
				}

				data.add(rows.toArray(new String[0]));

			}

			writer.writeAll(data);

			// closing writer connection
			writer.close();

			try {
				if (file.exists()) {
					base64 = encodeFileToBase64Binary(file);
					file.delete();
					return base64;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return base64;
	}

	public static String encodeFileToBase64Binary(File file) {
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			return new String(java.util.Base64.getEncoder().encode(bytes));
		} catch (Exception e) {
			return "";
		}

	}

	public static File getImageFromBase64(String base64String, String fileName) {
		/*
		 * String[] strings = base64String.split(","); String extension; switch
		 * (strings[0]) { // check image's extension case "data:image/jpeg;base64":
		 * extension = "jpeg"; break; case "data:image/png;base64": extension = "png";
		 * break; default: // should write cases for more images types extension =
		 * "jpg"; break; }
		 */
		// convert base64 string to binary data
		byte[] data = DatatypeConverter.parseBase64Binary(base64String);
		File file = new File(fileName);
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
			outputStream.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}
