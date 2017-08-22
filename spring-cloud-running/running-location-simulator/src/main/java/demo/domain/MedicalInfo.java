package demo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by XL on 8/21/2017.
 */
@Data
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class MedicalInfo {

    private String bandMake;
    private String medCode;
    private String medicalInfoId;
    private String medicalInfoClassification;
    private String description;
    private String aidInstructions;
    private String fmi;
    private String bfr;

}
