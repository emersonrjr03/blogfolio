package br.com.herms.blogfolio.service;

import br.com.herms.blogfolio.model.Post;
import br.com.herms.blogfolio.model.Profile;
import br.com.herms.blogfolio.repository.PostRepository;
import br.com.herms.blogfolio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProfileServiceImpl implements BlogService<Profile> {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile findOrCreateProfile(){
        List<Profile> profileList = findAll();

        if(profileList.size() > 0) {
            return profileList.get(0);
        } else {
            Profile profile = new Profile();
            profile.setGivenName("Given Name");
            profile.setFamilyName("Family Name");
            profile.setBirthDate(LocalDate.now());
            return save(profile);
        }
    }
    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile findById(Long id) {
        return profileRepository.findById(id).get();
    }

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }
}
