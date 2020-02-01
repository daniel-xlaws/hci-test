package com.test.hci.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_modules")
public class UserModule implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_name", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (module_name) REFERENCES modules (name) ON UPDATE CASCADE ON DELETE CASCADE"))
    private Module module;

    @Column(name = "module_order", nullable = false)
    private int moduleOrder;

    public UserModule(User user, Module module, int moduleOrder) {
        this.user = user;
        this.module = module;
        this.moduleOrder = moduleOrder;
    }
}
