package com.example.wishlistproject.controller;

import com.example.wishlistproject.dto.WishDTO;
import com.example.wishlistproject.dto.wishlistDTO;
import com.example.wishlistproject.model.User;
import com.example.wishlistproject.model.Wishlist;
import com.example.wishlistproject.service.wishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session and return to login page
        session.invalidate();
        return "index";
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/") // index is loginPage
    public String index(@RequestParam("userName") String userName,
                        @RequestParam("userPassword") String userPassword,
                        HttpSession session,
                        Model model)
    {
        // find user in repo - return loggedIn if succes
        User user = wishlistService.getUser(userName);
        if (user != null)
            if (user.getUserPassword().equals(userPassword)) {
                // create session for user and set session timeout to 30 sec (container default: 15 min)
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(300);
                return "frontpage";
            }
        // wrong login info
        model.addAttribute("wrongLoginInfo", true);
        return "index";
    }

    @GetMapping("/frontpage")
    public String frontPage(HttpSession session) {
        return isLoggedIn(session) ? "frontpage" : "index";
    }

    @GetMapping("/createwish")
    public String createWish(Model model, HttpSession session) {
        WishDTO wish = new WishDTO();
        User user = (User) session.getAttribute("user");

        model.addAttribute("wish", wish);
        model.addAttribute("wishlists", wishlistService.getWishlists(user.getUserID()));
        return isLoggedIn(session) ? "createWish" : "index";
    }

    @PostMapping("/createwish")
    public String createdWish(@ModelAttribute("wish") WishDTO wish, HttpSession session) {
        wishlistService.createWish(wish);
        return "redirect:/wishlist/seewishlists";
    }

    @GetMapping("/seewishes/{listid}")
    public String seeWishes(Model model, HttpSession session, @PathVariable int listid) {
        model.addAttribute("wishes", wishlistService.getWishes(listid));
        return isLoggedIn(session) ? "wishes" : "index";
    }

    @PostMapping("/deletewish")
    public String deleteWish(@RequestParam("id") int id) {
        wishlistService.deleteWish(id);
        return "redirect:/wishlist/seewishes";

    }

    @GetMapping("/edit/wish/{id}")
    public String editWish(@PathVariable int wishid, Model model, HttpSession session) {
        WishDTO wish = wishlistService.findWishById(wishid);
        User user = (User) session.getAttribute("user");
        int id = user.getUserID();
        model.addAttribute("wish", wish);
        model.addAttribute("wishlists", wishlistService.getWishlists(id));
        return "editWish";
    }

    @PostMapping("/edit/wish/{id}")
    public String editedWish(@PathVariable int id, @ModelAttribute WishDTO editedWish) {
        wishlistService.editWish(id, editedWish);
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
        return "createUserSuccess";
    }

    @GetMapping("/seewishlists")
    public String seeWishlists(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("wishlists", wishlistService.getWishlists(user.getUserID()));
            return "wishlists";
        } else {
            return "redirect:/wishlist/frontpage";
        }
    }

    @GetMapping("/createwishlist")
    public String createWishlist(Model model, HttpSession session) {
        wishlistDTO wishlist = new wishlistDTO();
        model.addAttribute("wishlist", wishlist);

        return isLoggedIn(session) ? "createWishlist" : "index";
    }

    @PostMapping("/createwishlist")
    public String createdWishlist(@ModelAttribute("wishlist") wishlistDTO wishlist,
                                  HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            wishlistService.createWishlist(user.getUserID(), wishlist);
            return "redirect:/wishlist/seewishlists";
        } else {
            return "redirect:/wishlist/createWishlist";
        }
    }

    @PostMapping("/deletewishlist")
    public String deleteWishlist(@RequestParam("id") int id) {
        wishlistService.deleteWishlist(id);
        return "redirect:/wishlist/seewishlists";
    }

    @GetMapping("/SuccessSeeLists")
    public String seeWishlists(HttpSession session) {
        return isLoggedIn(session) ? "SuccessSeeLists" : "index";
    }



}
