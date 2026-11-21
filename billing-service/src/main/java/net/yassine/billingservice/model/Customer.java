package net.yassine.billingservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    private String Id;
    private String name;
    private String email;

    public void setId(Long id) {
    }
}
