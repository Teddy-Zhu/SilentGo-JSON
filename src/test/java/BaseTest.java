import com.alibaba.fastjson.parser.ParserConfig;
import com.silentgo.json.JSON;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.deserializer.DeserializerKit;
import com.silentgo.json.mapping.valreader.ReaderKit;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.parser.StringParser;
import com.silentgo.json.parser.StringParserImpl;
import com.silentgo.utils.ReflectKit;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Project : jsonstream
 * Package : com.itranswarp.jsonstream.test
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class BaseTest {
    public static Map<String, Object> aaa = new HashMap<>();

    public static void main(String[] args) throws IOException {
        //JsonBuilder jsonBuilder = new JsonBuilder().useJsonObjectFactory(TreeMap::new).useJsonArrayFactory(LinkedList::new);
        String jsonStr = "{\n" +
                "    \"id\": 123,\n" +
                "    \"name\": \"Mic[hael\",\n" +
                "    \"tags\": [\"Mus{ic\", \"Football\", \"Running\"],\n" +
                "    \"address\": {\n" +
                "        \"street\": \"No.1 Road\",\n" +
                "        \"zipcode\": \"1234]5\"\n" +
                "    }\n" +
                "}";
        //JsonReader jsonReader = jsonBuilder.createReader(jsonStr);
        //User user = jsonReader.parse(User.class);
        //JSON.config.setLazy(true);
        // String json2 = "[12,{\"axx\":[1.2,22,\"xxx\"]}]";

        int i = 100000;


        Long start = System.currentTimeMillis();
        //dDeserializerKit.createDeserializer(User.class);
        List<Map> users = new ArrayList<>();
        //User user = stringParser.parse(jsonStr, User.class, JSON.config);
//        ParserConfig config = new ParserConfig();
//        config.setAsmEnable(false);
        //users = com.alibaba.fastjson.JSON.parseObject(jsonStr, users.getClass());
//        ReflectKit.getSGClass(User.class);
//        ReflectKit.getSGClass(User.class);
//
//        JSON.cc = stringParser.parse(jsonStr.getBytes(), JSON.config);


        for (int i1 = 0; i1 < i; i1++) {
            users.add(JSON.parse(jsonStr, Map.class));
        }
        System.out.println(System.currentTimeMillis() - start);

        //System.out.println(entity);
        // List<User> a = new ArrayList<User>();
        //String acc = "[\"sd\",\"asd\",\" xx\"]";
        // String acaa = "[" + jsonStr + "," + jsonStr + "]";
        //List<User> aaa = com.alibaba.fastjson.JSON.parseArray(acaa, User.class);
        // Class<?> ac = a.getClass();
        // System.out.println(a);
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
