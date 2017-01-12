import com.silentgo.json.annotation.JSONField;

import java.util.Date;
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
    @JSONField("yyyy-MM-dd HH:mm:ss")
    Date time;
    int id;
    String name;
    List<String> tags; // <-- ["Music", "Football", "Running"]
    Address address; // <-- { "street": "No.1 Road", "zipcode": "12345" }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
