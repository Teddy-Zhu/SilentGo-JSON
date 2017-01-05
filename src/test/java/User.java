import java.util.List;

/**
 * Project : jsonstream
 * Package : com.itranswarp.jsonstream.test
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class User {
    int id;
    String name;
    List<String> tags; // <-- ["Music", "Football", "Running"]
    Address address; // <-- { "street": "No.1 Road", "zipcode": "12345" }
}
