import com.silentgo.json.JSON;

import java.util.List;

/**
 * Project : SilentGo
 * Package : PACKAGE_NAME
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class test {


    public void aaaa(List<Long> aaa, String[] aaaa) {

    }

    public static void main(String[] args) throws NoSuchMethodException {

        String a = "{\"zipcode\":\"adadsa\",\"street\":\"在奇偶给你\",\"aaa\":false}";

        Address ac = JSON.parse(a, Address.class);

        System.out.println(JSON.toJSONString(ac));
    }
}
