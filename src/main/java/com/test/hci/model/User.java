package com.test.hci.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.test.hci.deserializer.SimpleDateDeserializer;
import com.test.hci.deserializer.ToUpperCharDeserializer;
import com.test.hci.deserializer.TrimStringDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", length = 20)
    private String id;
}


