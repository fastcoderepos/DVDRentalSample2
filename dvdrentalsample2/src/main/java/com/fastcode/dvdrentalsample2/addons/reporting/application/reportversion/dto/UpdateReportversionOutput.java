package com.fastcode.dvdrentalsample2.addons.reporting.application.reportversion.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
public class UpdateReportversionOutput {

    private String ctype;
    private String description;
    private JSONObject query;
    private String reportType;
    private String title;
    private String versiono;
    private String reportWidth;
    private Boolean isRefreshed;
    private Long userId;
    private String userDescriptiveField;
    private Long reportId;
}