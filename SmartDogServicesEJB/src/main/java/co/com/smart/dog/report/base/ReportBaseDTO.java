/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.smart.dog.report.base;

/**
 * @author SmartJungle S.A.S
 *
 */
public class ReportBaseDTO {
    private String logo;
    private SmartTypeExporterReport formato;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public SmartTypeExporterReport getFormato() {
        return formato;
    }

    public void setFormato(SmartTypeExporterReport formato) {
        this.formato = formato;
    }

}
