package com.doNotWorry;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvService {
//    public void readCsvFile() throws IOException {
//        // Load the CSV file as a resource
//        ClassPathResource resource = new ClassPathResource("C:\\work\\food_data.csv");
//
//        // Read the CSV file using OpenCSV
//        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
//            String[] nextLine;
//            while ((nextLine = reader.readNext()) != null) {
//                // Process each row of the CSV file
//                for (String column : nextLine) {
//                    System.out.print(column + " ");
//                }
//                System.out.println();
//            }
//        } catch (CsvValidationException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public List<List<String>> csvData(String dir) throws IOException {
        List<List<String>> ret = new ArrayList<>();
        // 입력 스트림 오브젝트 생성
        BufferedReader br = null;

        //dir 은 csv 파일이 있는 경로
        ClassPathResource resource = new ClassPathResource(dir);


        try {
            InputStream inputStream = resource.getInputStream();
            InputStream fileInputStream =getClass().getResourceAsStream(dir);
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            br = new BufferedReader(inputStreamReader);

            // 대상 CSV 파일의 경로 설정

//            br = Files.newBufferedReader(Paths.get("C:\\work\\food_data.csv"), StandardCharsets.UTF_8);
            // CSV파일에서 읽어들인 1행분의 데이터
            String line ;

            while ((line = br.readLine()) != null) {
                // CSV 파일의 1행을 저장하는 리스트 변수
                List<String> tmpList = new ArrayList<>();
                String array[] = line.split(",");
                // 배열에서 리스트로 변환
                tmpList = Arrays.asList(array);
                // 리스트 내용 출력
//                System.out.println(tmpList);
                // 반환용 리스트에 1행의 데이터를 저장
                ret.add(tmpList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return ret;

    }


}
