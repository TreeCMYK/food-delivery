package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class Picked extends AbstractEvent {

    private Long id;

    public Picked(Delivery aggregate) {
        super(aggregate);
    }

    public Picked() {
        super();
    }
}
//>>> DDD / Domain Event
