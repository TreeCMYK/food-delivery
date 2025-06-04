package fooddelivery.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import fooddelivery.FrontApplication;
import fooddelivery.domain.Paid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
//<<< DDD / Aggregate Root
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private String status;

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = FrontApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    //<<< Clean Arch / Port Method
    public static void pay(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        Paid paid = new Paid(payment);
        paid.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(orderPlaced.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);

            Paid paid = new Paid(payment);
            paid.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
