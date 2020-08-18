package br.com.herms.blogfolio.model;

import br.com.herms.blogfolio.utils.ConvertUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class ProfileDTO {

    private Long id;
    @NotBlank
    private String givenName;
    @NotBlank
    private String familyName;
    private String birthDate;
    private String jobTitle;
    @Lob
    private String description;

    public ProfileDTO(){
    }

    public ProfileDTO(Profile profile){
        this.id = profile.getId();
        this.givenName = profile.getGivenName();
        this.familyName = profile.getFamilyName();
        this.birthDate = ConvertUtils.localDateToString(profile.getBirthDate());
        this.jobTitle = profile.getJobTitle();
        this.description = profile.getDescription();
    }

    public Profile toProfile(){
        Profile profile = new Profile();
        profile.setId(this.id);
        profile.setGivenName(this.givenName);
        profile.setFamilyName(this.familyName);
        profile.setBirthDate(ConvertUtils.stringToLocalDate(this.birthDate));
        profile.setDescription(this.description);
        profile.setJobTitle(this.jobTitle);
        return profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
