package org.etfbl.support.webshopsupport.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginBean implements Serializable {

    private String username;
    private String password;

    public String sayHello(){
        return "hello asd";
    }
}
