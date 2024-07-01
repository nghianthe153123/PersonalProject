package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GooglePoJo {
    private String id;
    private String email;
    private boolean verify_email;
    private String name;
    private String given_name;
    private String family_name;
    private String pictrue;
}
