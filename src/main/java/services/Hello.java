package services;

import main.com.database.DBWorker;
import main.com.database.testQRwithDB;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Path("/hello")
public class Hello {

    public static final String JSON_NAME = "name";
    public static final String JSON_QR_ID = "QR_ID";
    public static final String JSON_QUERY = "Query";
    @POST
    @Path("/json")
    public JSONObject sayPlainTextHello(JSONObject inputJsonObj) throws Exception {

        String input = (String) inputJsonObj.get("input");
        String output = "The input you sent is :" + input;

        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("output", output);

        return outputJsonObj;
    }

    @GET
    @Path("/get_string")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public String sayPlainTextHelloStrGet(@QueryParam("input_1") String inputJsonObj1,@QueryParam("input_2") String inputJsonObj2,@QueryParam("input_3") String inputJsonObj3) throws Exception {
//        String output = "The input you sent is :" + inputJsonObj1 ;


        DBWorker worker = new DBWorker();

        //addLinkToQRCodesDb(worker, "link2");

        testQRwithDB res = selecteIdFromQRCodesDb(worker, inputJsonObj2);

//        System.out.println("id is: " + res.getId());


        JSONObject outputJsonObj = new JSONObject();

        outputJsonObj.put(JSON_NAME, inputJsonObj1 + "MUFACA");
        outputJsonObj.put(JSON_QR_ID, res.getId());
        outputJsonObj.put(JSON_QUERY, inputJsonObj3 + "Это");

        return outputJsonObj.toString();
    }

    @GET
    @Path("/get_html")
    @Produces({MediaType.TEXT_HTML})
    public InputStream ConnectPageHtml(@QueryParam("input_1") String inputJsonObj1,@QueryParam("input_2") String inputJsonObj2,@QueryParam("input_3") String inputJsonObj3) throws IOException {


        DBWorker worker = new DBWorker();

        //addLinkToQRCodesDb(worker, "link2");

        testQRwithDB res = selecteIdFromQRCodesDb(worker, inputJsonObj2);

//        System.out.println("id is: " + res.getId());

        JSONObject json = createJSON(inputJsonObj1, res.getId(), inputJsonObj3);


//        String string = "<!DOCTYPE html>\n" +
//                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\"/>\n" +
//                "    <title>QR-code</title>\n" +
//                "</head>\n" +
//                "<body>\n"  +
//                "</form>\n" +
//                "<p><font size=\"10\" face=\"Arial\">"+ json.toString() +"</p>\n" +
//                "\n" +
//                "</body>\n" +
//                "</html>\n" +
//                "\n";
//
//        String  html_image = "<html>\n" +
//                " <head>\n" +
//                "  <meta charset=\"utf-8\">\n" +
//                "  <title>Мои рисунки</title>\n" +
//                " </head>\n" +
//                " <body>\n" +
//                "  <p><img src=\"images/dzen.png\" alt=\"Письма мастера дзен\"></p>\n" +
//                " </body>\n" +
//                "</html>";


//
//        Reader stringReader = new StringReader(string);
//        HTMLEditorKit htmlKit = new HTMLEditorKit();
//        HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
//        HTMLEditorKit.Parser parser = new ParserDelegator();
//        try {
//            parser.parse(stringReader, htmlDoc.getReader(0), true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        File newHtmlFile = new File("path/new.html");
        File newHtmlFile1 = new File("templates/withSeriaOfImage.html");
//        File newHtmlFile1 = new File("templates/withVideo.html");

        String htmlString = FileUtils.readFileToString(newHtmlFile);
//        FileUtils.writeStringToFile(newHtmlFile, string);

        return new FileInputStream(newHtmlFile1);
    }

    private static JSONObject createJSON(String param_1, int param_2, String param_3)
    {
        JSONObject outputJsonObj = new JSONObject();

        outputJsonObj.put(JSON_NAME, param_1);
        outputJsonObj.put(JSON_QR_ID,"This ID="+ param_2 +" was selected from DB" );
        outputJsonObj.put(JSON_QUERY, param_3);
        return outputJsonObj;
    }

    private static Map<String, Object> createPageVariablesMap(JSONObject request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", request.toString());
        return pageVariables;
    }



    public static testQRwithDB selecteIdFromQRCodesDb(DBWorker worker, String link)
    {

        String query = "select * from testQR where link = '" + link + "'";
        testQRwithDB tqwd = new testQRwithDB();

        try {
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){


                tqwd.setId(resultSet.getInt("id"));
                tqwd.setLink(resultSet.getString("link"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tqwd;
    }


    @POST
@Path("/string")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public String sayPlainTextHelloStr(String inputJsonObj) throws Exception {

//        String output = "The input you sent is :" + inputJsonObj;
        JSONObject outputJsonObj = new JSONObject();
//        outputJsonObj.put("output", output);
        JSONArray array = new JSONArray(inputJsonObj);
//        int a =  array.length();//getJSONObject(0).toString();
        org.json.JSONObject jsonObj  = array.getJSONObject(0);
//        System.out.println(jsonObj.get("name"));
        outputJsonObj.put(JSON_NAME, jsonObj.get(JSON_NAME)+" Пупкин");
        outputJsonObj.put(JSON_QR_ID, jsonObj.get(JSON_QR_ID)+"1");
        outputJsonObj.put(JSON_QUERY, jsonObj.get(JSON_QUERY)+" where a > b");


        return outputJsonObj.toString();
    }




}