package com.ofo.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by XL on 8/26/2017.
 */

@Data

public class Payment {
    @Id
    private Long paymentId;
}
