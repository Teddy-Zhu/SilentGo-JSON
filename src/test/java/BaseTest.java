import com.silentgo.json.JSON;
import com.silentgo.json.StringParser;
import com.silentgo.json.StringParserImpl;
import com.silentgo.json.model.JSONEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project : jsonstream
 * Package : com.itranswarp.jsonstream.test
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class BaseTest {

    public static void main(String[] args) throws IOException {
        //JsonBuilder jsonBuilder = new JsonBuilder().useJsonObjectFactory(TreeMap::new).useJsonArrayFactory(LinkedList::new);
        String jsonStr = "{\n" +
                "    \"id\": 123,\n" +
                "    \"name\": \"Michael\",\n" +
                "    \"tags\": [\"Music\", \"Football\", \"Running\"],\n" +
                "    \"address\": {\n" +
                "        \"street\": \"No.1 Road\",\n" +
                "        \"zipcode\": \"12345\"\n" +
                "    }\n" +
                "}";
        //JsonReader jsonReader = jsonBuilder.createReader(jsonStr);
        //User user = jsonReader.parse(User.class);
//
//        String json2 = "[12,{\"axx\":[1.2,22,\"xxx\"]}]";
////        JSONEntity entity = new StringParserImpl().parse(jsonStr);
////        System.out.println(entity);
//
//        JSON.config.setLazy(true);
//        StringParser parser = new StringParserImpl();
//
//        int i = 100000;
//
//
//        long start = System.currentTimeMillis();
//        List<JSONEntity> list = new ArrayList<>();
//        for (int i1 = 0; i1 < i; i1++) {
//            list.add(parser.parse(json2));
//        }
//        System.out.println(System.currentTimeMillis() - start);


    }


}
