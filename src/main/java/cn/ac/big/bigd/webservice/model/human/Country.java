package cn.ac.big.bigd.webservice.model.human;

public class Country {
    private Integer countryId;

    private String numericalCode;

    private String countryName;

    private String isoAlpha3Code;

    private String isoalpha3code;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getNumericalCode() {
        return numericalCode;
    }

    public void setNumericalCode(String numericalCode) {
        this.numericalCode = numericalCode == null ? null : numericalCode.trim();
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    public String getIsoAlpha3Code() {
        return isoAlpha3Code;
    }

    public void setIsoAlpha3Code(String isoAlpha3Code) {
        this.isoAlpha3Code = isoAlpha3Code == null ? null : isoAlpha3Code.trim();
    }

    public String getIsoalpha3code() {
        return isoalpha3code;
    }

    public void setIsoalpha3code(String isoalpha3code) {
        this.isoalpha3code = isoalpha3code == null ? null : isoalpha3code.trim();
    }
}