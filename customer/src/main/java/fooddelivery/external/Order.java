package fooddelivery.external;

import java.util.Date;
import lombok.Data;

@Data
public class Order {

    private Long id;
    private String foodId;
    private Object option;
    private String address;
    private String customerId;
}
