package model.newentity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewOrUpdateClients {

    private int tenantId;

    private String name;

    private String phone;

    private String address;

    private String email;

    private LocalDateTime created;

    private int delFlag;

    private int gender;

    private LocalDate birthDay;

    public NewOrUpdateClients(Integer tenantId, String name, String phone, String address, String email,int gender, LocalDate birthDay, LocalDateTime created, int delFlag) {
        this.tenantId = tenantId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.created = created;
        this.delFlag = delFlag;
        this.gender = gender;
        this.birthDay = birthDay;
    }

    public NewOrUpdateClients() {
    }

}
