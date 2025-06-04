package fooddelivery.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import fooddelivery.FrontApplication;
import fooddelivery.domain.OrderCanceled;
import fooddelivery.domain.OrderPlaced;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String foodId;

    @Enumerated(EnumType.STRING)
    private List<String> option = new java.util.ArrayList<>();

    private String address;

    private String customerId;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = FrontApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
//>>> DDD / Aggregate Root
