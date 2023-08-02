package bUtilityTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * this has the classes for operations that are used as utilities
 */

public class ParamUtil {

    private static String userID;
    public static final String PAYLOAD_FILE_PATH_GET_DELETE = "src/test/resourcesTest/PostedData.JSON";
    public static final String PAYLOAD_FILE_PATH_POST = "src/test/resourcesTest/PostData.JSON";
    public static final String PAYLOAD_FILE_PATH_PUT = "src/test/resourcesTest/PutData.JSON";
    public static final ObjectMapper mapper = new ObjectMapper();


    public static String getParamValueOne() throws IOException {
        // it takes the id from the file and passed for the Get post to query by ID
        // it takes the id from the file and passed for deletion use
        File fileObj = new File(PAYLOAD_FILE_PATH_GET_DELETE);
        Map<String, Object> userData = mapper.readValue(fileObj, new TypeReference<Map<String, Object>>() {});
        String parameterValueOne = userData.get("id").toString();
        return parameterValueOne;
        }

    public static String getParamValueDelete() throws IOException {

        File fileObj = new File(PAYLOAD_FILE_PATH_GET_DELETE);
        Map<String, Object> userData = mapper.readValue(fileObj, new TypeReference<Map<String, Object>>() {});
        String getParamValueDelete = userData.get("id").toString();
        return getParamValueDelete;
    }

    public static String exportUserID(JsonNode JNode){
        userID = JNode.get("id").asText();
        return userID;
    }

    public static String importUserID(){
        return userID;
    }


    public static void loadFiles() {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File csvFile = new File("src/test/resourcesTest/Post.csv");
            MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class)
                    .with(csvSchema)
                    .readValues(csvFile);
            List<Map<?, ?>> list = mappingIterator.readAll();
            String jsonString = objectMapper.writeValueAsString(list);
            System.out.println(jsonString);

            String importFilePath = "src/test/resourcesTest/PostData.JSON";
            FileWriter fileWriter = new FileWriter(importFilePath);
            JsonNode jsonNode = objectMapper.convertValue(list, JsonNode.class);
            JsonNode jsonArray = jsonNode.get(0);
            jsonString = objectMapper.writeValueAsString(jsonArray);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loadUpdateFiles(){
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File csvFile = new File("src/test/resourcesTest/Put.csv");
            MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class)
                    .with(csvSchema)
                    .readValues(csvFile);
            List<Map<?, ?>> list = mappingIterator.readAll();
            String jsonString = objectMapper.writeValueAsString(list);
            System.out.println(jsonString);

            String importFilePath = "src/test/resourcesTest/PutData.JSON";
            FileWriter fileWriter = new FileWriter(importFilePath);
            JsonNode jsonNode = objectMapper.convertValue(list, JsonNode.class);
            JsonNode jsonArray = jsonNode.get(0);
            jsonString = objectMapper.writeValueAsString(jsonArray);
            fileWriter.write(jsonString);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }






}

