package com.rupesh.app.email;

import com.rupesh.app.user.enumeration.TemplateName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailRequest {

    private String to;
    private String name;
    private String username;
    private TemplateName template;
    private String confirmationUrl;
    private String token;
    private String subject;

}
