package org.etfbl.support.webshopsupport.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -4021922494651503066L;

    private String username;
    private String password;

}
