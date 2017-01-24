import com.silentgo.json.JSON;
import com.silentgo.json.serializer.SerializerKit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project : jsonstream
 * Package : com.itranswarp.jsonstream.test
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class BaseTest {

    public List<String> aaa() {
        return null;
    }

    public static void main(String[] args) throws IOException, NoSuchMethodException {

        JSON.toJSONString(new HashMap<>());

        System.out.println(BaseTest.class.getMethod("aaa"));
        ;
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

        User user = JSON.parse(jsonStr, User.class);
        Long start = System.currentTimeMillis();
        //dDeserializerKit.createDeserializer(User.class);
        //User user = stringParser.parse(jsonStr, User.class, JSON.config);
//        ParserConfig config = new ParserConfig();
//        config.setAsmEnable(false);
        //users = com.alibaba.fastjson.JSON.parseObject(jsonStr, users.getClass());
//        ReflectKit.getSGClass(User.class);
//        ReflectKit.getSGClass(User.class);
//
//        JSON.cc = stringParser.parse(jsonStr.getBytes(), JSON.config);


        for (int i1 = 0; i1 < i; i1++) {
            JSON.toJSONString(user);
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
