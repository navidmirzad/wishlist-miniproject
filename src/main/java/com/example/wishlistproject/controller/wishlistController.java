package com.example.wishlistproject.controller;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.dto.wishlistDTO;
import com.example.wishlistproject.model.User;
import com.example.wishlistproject.service.wishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class wishlistController {

    private wishlistService wishlistService;

    public wishlistController(wishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/createwish")
    public String createWish(Model model) {
        WishDTO wish = new WishDTO();
        model.addAttribute("wish", wish);

        model.addAttribute("wishlists", wishlistService.getWishLists());
        return "createWish";
    }

    @PostMapping("/createwish")
    public String createdWish(@ModelAttribute("wish") WishDTO wish, HttpSession session) {
        wishlistService.createWish(wish);
        return "redirect:/wishlist/seewishes";
    }

    @GetMapping("/seewishes")
    public String seeWishes(Model model) {
        model.addAttribute("wishes", wishlistService.getWishes());
        return "wishes";
    }

    @PostMapping("/deletewish")
    public String deleteWish(@RequestParam("id") int id) {
        wishlistService.deleteWish(id);
        return "redirect:/wishlist/seewishes";

    }

    @GetMapping("/createuser")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping("/createuser")
    public String createdUser(@ModelAttribute("user") User user) {
        wishlistService.createUser(user);
        return "createUserSucces";
    }

    @GetMapping("/createwishlist")
    public String createWishlist(Model model) {
        wishlistDTO wishlist = new wishlistDTO();
        model.addAttribute("wishlist", wishlist);

        return"createWishlist";
    }

    @PostMapping("/createwishlist")
    public String createdWishlist(@ModelAttribute("wishlist") wishlistDTO wishlist,
                                  Model model) {
        wishlistService.createWishlist(wishlist);
        model.addAttribute("wishlists", wishlistService.getWishLists());

        return "SuccessSeeLists";
    }

    @GetMapping("/SuccessSeeLists")
    public String seeWishlists(HttpSession session) {
        return isLoggedIn(session) ? "SuccessSeeLists" : "index";
    }

    @GetMapping("/index")
    public String showLogin() {
        // return login form
        return "index";
    }

    @PostMapping("/index") // index is loginPage
    public String index(@RequestParam("uid") String uid, @RequestParam("pwd") String pwd,
                        HttpSession session,
                        Model model)
    {
        // find user in repo - return loggedIn if succes
        User user = wishlistService.getUser(uid);
        if (user != null)
            if (user.getUserPassword().equals(pwd)) {
                // create session for user and set session timeout to 30 sec (container default: 15 min)
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30);
                return "/wishlist/SuccessSeeLists";
            }
        // wrong login info
        model.addAttribute("wrongLoginInfo", true);
        return "index";
      //  boolean login = wishlistService.checkLogin(uid, pwd);

      /*  System.out.println(login);
        // ? means if true - : means if else
        return (login) ? "redirect:/wishlist/SuccessSeeLists" : "redirect:/wishlist/index/";*/

    }





}
