package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurant")
public class Restaurant {

    @Id
    private String name;

    private String address;

    @Pattern(regexp = "^\\d+$", message = "Invalid phone number")
    private String phone;

    private LocalTime openHour;

    private LocalTime closeHour;
}
