package br.com.herms.blogfolio.controller;

import br.com.herms.blogfolio.model.*;
import br.com.herms.blogfolio.service.PostServiceImpl;
import br.com.herms.blogfolio.service.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ProfileController {
    private static final String AJAX_HEADER_NAME = "X-Requested-With";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    private static final String ENDPOINT_PROFILE = "/profile";

    @Autowired
    private ProfileServiceImpl profileService;

    @RequestMapping(value = "/aboutme", method = RequestMethod.GET)
    public ModelAndView getProfile(){
        ModelAndView mv = new ModelAndView("aboutMe");
        mv.addObject("profile", new ProfileDTO(profileService.findOrCreateProfile()));
        return mv;
    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
    public ModelAndView getPostForm(){
        ModelAndView mv = new ModelAndView("profileForm");
        mv.addObject("profile", new ProfileDTO(profileService.findOrCreateProfile()));
        return mv;
    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.POST)
    public String savePost(@Valid ProfileDTO profileDto, BindingResult result, RedirectAttributes attributes){
        //redirect to the same page
        if(result.hasErrors()){
            attributes.addFlashAttribute("message", "Check if the mandatory fields were filled");
            return "redirect:/editprofile";
        }

        profileService.save(profileDto.toProfile());
        return "redirect:/aboutme";
    }

    @RequestMapping(value = "/addExperience", method = RequestMethod.POST)
    public ModelAndView addOrder(ProfileDTO profile, HttpServletRequest request) {
        profile.getExperiencesList().add(new ExperienceDTO());

        ModelAndView mv = new ModelAndView("experiencesList");
        mv.addObject("profile", profile);
        return mv;
    }

    // "removeItem" parameter contains index of a item that will be removed.
    @RequestMapping(value = "/removeExperience/{id}", method = RequestMethod.POST)
    public ModelAndView removeOrder(ProfileDTO profile, @PathVariable("id") int experienceIndex, HttpServletRequest request) {
        profile.getExperiencesList().remove(experienceIndex);

        ModelAndView mv = new ModelAndView("experiencesList");
        mv.addObject("profile", profile);
        return mv;
    }
}
