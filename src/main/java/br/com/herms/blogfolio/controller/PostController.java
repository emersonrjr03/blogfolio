package br.com.herms.blogfolio.controller;

import br.com.herms.blogfolio.model.Post;
import br.com.herms.blogfolio.model.PostDTO;
import br.com.herms.blogfolio.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PostController {
    private static final String ENDPOINT_POST = "/posts";
    @Autowired
    private PostServiceImpl postService;

    @RequestMapping(value = ENDPOINT_POST, method = RequestMethod.GET)
    public ModelAndView getPosts(){
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = postService.findAll();
        mv.addObject("posts", posts);
        return mv;
    }

    @RequestMapping(value = ENDPOINT_POST + "/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable(value = "id")Long id) {
        ModelAndView mv = new ModelAndView("postDetails");
        Post post = postService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @RequestMapping(value = "newpost", method = RequestMethod.GET)
    public ModelAndView getPostForm(){
        ModelAndView mv = new ModelAndView("postForm");
        //we render the screen with a brand new post, because we use the same page for create and edit posts
        mv.addObject("post", new Post());
        return mv;
    }

    @RequestMapping(value = "savepost", method = RequestMethod.POST)
    public String savePost(@Valid PostDTO postDto, BindingResult result, RedirectAttributes attributes){
        //redirect to the same page
        if(result.hasErrors()){
            attributes.addFlashAttribute("message", "Check if the mandatory fields were filled");
            if(postDto.getId() == null){
                return "redirect:/newpost";
            } else {
                return "redirect:/editpost/" + postDto.getId();
            }

        }
        Post post = postDto.toPost();

        String redirectTo = ENDPOINT_POST;
        if(post.getId() == null){
            post.setDate(LocalDate.now());
        } else {
            Post oldPost = postService.findById(post.getId());
            post.setDate(oldPost.getDate());

            redirectTo = ENDPOINT_POST + "/" + post.getId();
        }

        postService.save(post);
        return "redirect:" + redirectTo;
    }

    @RequestMapping(value = "editpost/{id}", method = RequestMethod.GET)
    public ModelAndView editPost(@PathVariable(value = "id")Long id){
        ModelAndView mv = new ModelAndView("postForm");
        Post post = postService.findById(id);
        mv.addObject("post", post);

        return mv;
    }
}
