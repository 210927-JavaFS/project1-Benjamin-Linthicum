package com.revature.controllers;

import java.util.List;

import com.revature.services.UserService;
import com.revature.models.*;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller{

    private UserService userService = new UserService();
    
    public Handler getAllUsers = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            List<User> list = userService.getAllUsers();

            ctx.json(list);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };

    public Handler getUser = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            String username = ctx.pathParam("user");
            User user = userService.getUser(username);
            ctx.json(user);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };

    public Handler addUser = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            User user = ctx.bodyAsClass(User.class);
            if (userService.addUser(user)) {
                ctx.status(201);
            }
            else {
                ctx.status(400);
            }
        }
        else {
            ctx.status(401);
        }
    };

    public Handler updateUser = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            User user = ctx.bodyAsClass(User.class);
            if (userService.updateUser(user)) {
                ctx.status(200);
            }
            else {
                ctx.status(400);
            }
        }
        else {
            ctx.status(401);
        }
    };

    public Handler deleteUser = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            String username = ctx.pathParam("user");
            if (userService.deleteUser(username)) {
                ctx.status(200);
            }
            else {
                ctx.status(400);
            }
        }
        else {
            ctx.status(401);
        }
    };

    public Handler login = (ctx) -> {
        UserDTO userDto = ctx.bodyAsClass(UserDTO.class);

        if(userService.login(userDto)) {
            ctx.req.getSession();
            ctx.status(200);
        }
        else{
            ctx.req.getSession().invalidate();
            ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app){
        app.get("/users", this.getAllUsers);
        app.get("/users/:user", this.getUser);
        app.post("/users", this.addUser);
        app.put("/users", this.updateUser);
        app.delete("/users:user", this.deleteUser);
        app.post("/login", this.login);
    }

}
