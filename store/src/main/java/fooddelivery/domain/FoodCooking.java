package fooddelivery.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import fooddelivery.StoreApplication;
import fooddelivery.domain.CookFinished;
import fooddelivery.domain.CookStarted;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "FoodCooking_table")
@Data
//<<< DDD / Aggregate Root
public class FoodCooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    private String foodId;

    private String orderId;

    @Enumerated(EnumType.STRING)
    private List<String> options = new java.util.ArrayList<>();

    private String storeId;

    private String customerId;

    @PostPersist
    public void onPostPersist() {
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();

        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    public static FoodCookingRepository repository() {
        FoodCookingRepository foodCookingRepository = StoreApplication.applicationContext.getBean(
            FoodCookingRepository.class
        );
        return foodCookingRepository;
    }

    //<<< Clean Arch / Port Method
    public void accept(AcceptCommand acceptCommand) {
        //implement business logic here:

        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void 주문정보복제(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        FoodCooking foodCooking = new FoodCooking();
        repository().save(foodCooking);

        */

        /** Example 2:  finding and process
        

        repository().findById(orderPlaced.get???()).ifPresent(foodCooking->{
            
            foodCooking // do something
            repository().save(foodCooking);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatus(Paid paid) {
        //implement business logic here:

        /** Example 1:  new item 
        FoodCooking foodCooking = new FoodCooking();
        repository().save(foodCooking);

        */

        /** Example 2:  finding and process
        

        repository().findById(paid.get???()).ifPresent(foodCooking->{
            
            foodCooking // do something
            repository().save(foodCooking);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
