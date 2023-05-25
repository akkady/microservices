package me.akkad.customer.doa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_id_sequence")
    @SequenceGenerator(name = "customer_id_sequence",sequenceName = "customer_id_sequence")
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
}
