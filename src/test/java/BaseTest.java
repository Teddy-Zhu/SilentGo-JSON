import com.silentgo.json.JSON;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.serializer.SerializerKit;

import java.io.IOException;
import java.util.HashMap;

public class BaseTest {

    public static void main(String[] args) throws IOException, NoSuchMethodException {

        String asdasd = "{\"name\":\":+1:\n" +
                "# title test\n" +
                "\n" +
                "ss\n" +
                "----\n" +
                "\n" +
                "\uD83D\uDC4D\"}";
        JSONEntity jsonEntity  =JSON.parse(asdasd);
        System.out.println(jsonEntity);


//        Object a = null;
//        SerializerKit.createSerializer(a.getClass());
//        JSON.toJSONString(new HashMap<>());
//
//        System.out.println(BaseTest.class.getMethod("aaa"));
//
//        String jsonStr = "{\n" +
//                "    \"id\": 123,\n" +
//                "    \"name\": \"Mic[hael\",\n" +
//                "    \"tags\": [\"Mus{ic\", \"Football\", \"Running\"],\n" +
//                "    \"address\": {\n" +
//                "        \"street\": \"No.1 Road\",\n" +
//                "        \"zipcode\": \"1234]5\"\n" +
//                "    }\n" +
//                "}";
//
//        int i = 100000;
//
//        User user = JSON.parse(jsonStr, User.class);
//        Long start = System.currentTimeMillis();
//
//
//        for (int i1 = 0; i1 < i; i1++) {
//            JSON.toJSONString(user);
//        }
//        System.out.println(System.currentTimeMillis() - start);

    }


}
