package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by XL on 8/11/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Embeddable
public class MedicalInfo {
    private long bfr;
    private long fmi;

    public MedicalInfo() {
    }

    public MedicalInfo(long bfr, long fmi) {
        this.bfr = bfr;
        this.fmi = fmi;
    }
}
