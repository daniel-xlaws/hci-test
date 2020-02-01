package com.test.hci.tuple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModule implements Serializable {
    private String moduleName;
    private int moduleOrder;
}
