package com.revature;

import com.revature.controllers.*;
import com.revature.DAOs.*;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
    
    private static Javalin app;

    public static void main(String[] args) {
        app = Javalin.create((config) -> {
            config.addStaticFiles("/static", Location.CLASSPATH);
        });

        configure(new ReimbursementController(), new UserController());

        app.start(8081);
    }

    private static void configure(Controller... controllers){
        for(Controller c: controllers) {
            c.addRoutes(app);
        }
        UserDAO.populateRoles();
        ReimbursementDAO.populateStatuses();
        ReimbursementDAO.populateTypes();
    }

}
