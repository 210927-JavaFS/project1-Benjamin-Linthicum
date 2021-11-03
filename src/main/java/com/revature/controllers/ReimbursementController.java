package com.revature.controllers;

import java.util.List;

import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import com.revature.models.*;

import io.javalin.Javalin;
import io.javalin.http.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ReimbursementController implements Controller{

    private ReimbursementService reimbursementService = new ReimbursementService();
    private UserService userService = new UserService();
    private Logger log = LoggerFactory.getLogger(ReimbursementController.class);
    
    public Handler getAllReimbursements = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            List<Reimbursement> list = reimbursementService.getAllReimbursements();

            ctx.json(list);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };

    public Handler getReimbursement = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            try {
                int id = Integer.parseInt(ctx.pathParam("reimbursement"));
                Reimbursement reimbursement = reimbursementService.getReimbursement(id);
                ctx.json(reimbursement);
                ctx.status(200);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                ctx.status(406);
            }
        }
        else {
            ctx.status(401);
        }
    };

    public Handler addReimbursement = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            Reimbursement reimbursement = ctx.bodyAsClass(ReimbSubmit.class).convertToReimbursement();
            if (reimbursementService.addReimbursement(reimbursement)) {
                ctx.status(201);
                log.info("Reimbursement request submitted, type: " + reimbursement.getType() + ", description: " + reimbursement.getDescription());
            }
            else {
                ctx.status(400);
            }
        }
        else {
            ctx.status(401);
        }
    };

    public Handler updateReimbursement = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
            if(reimbursementService.updateReimbursement(reimbursement)) {
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

    public Handler deleteReimbursement = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            String idString = ctx.pathParam("reimbursement");
            try {
                int id = Integer.parseInt(idString);
                if(reimbursementService.deleteReimbursement(id)){
                    ctx.status(200);
                }
                else {
                    ctx.status(400);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                ctx.status(406);
            }
        }
        else {
            ctx.status(401);
        }
    };

    public Handler getReimbursementsByStatus = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            try {
                List<Reimbursement> list;
                String status = ctx.bodyAsClass(String.class);
                if(status.equals("All")){ // how the fuck do I get this to work idk
                    list = reimbursementService.getAllReimbursements();
                }
                else{
                    list = reimbursementService.getReimbursementsByStatus(status);
                }
                List<SanitizedReimbursement> finalList = new ArrayList<SanitizedReimbursement>();
                for(Reimbursement r: list){
                    finalList.add(new SanitizedReimbursement(r));
                }
                ctx.json(finalList);
                ctx.status(200);
                log.info("Reimbursements retrieved by finance manager, filtered by: " + status);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                ctx.status(406);
            }
        }
        else {
            ctx.status(401);
        }
    };

    public Handler getReimbursementsByUsername = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            String username = ctx.bodyAsClass(String.class);
            List<Reimbursement> rawList = reimbursementService.getReimbursementsByUsername(username);
            List<SanitizedReimbursement> list = new ArrayList<SanitizedReimbursement>();
            for(Reimbursement r: rawList){
                list.add(new SanitizedReimbursement(r));
            }

            ctx.json(list);
            ctx.status(200);
            log.info("Reimbursements retrieved by employee with username " + username + ".");
        }
        else {
            ctx.status(401);
        }
    };

    public Handler resolveReimbursement = (ctx) -> {
        if (ctx.req.getSession(false) != null) {
            try {
                ResolutionDTO resolution = ctx.bodyAsClass(ResolutionDTO.class);
                Reimbursement reimbursement = resolution.reimbursement.convertToReimbursement();
                reimbursement.setAuthor(userService.getUser(resolution.reimbursement.author));
                if(reimbursement.getAuthor() == null){
                    throw new Exception("Unable to retrieve author's User data from database.");
                }
                reimbursement.setStatus(resolution.status);
                reimbursement.setResolver(userService.getUser(resolution.resolverName));
                if(reimbursement.getResolver() == null){
                    throw new Exception("Unable to retrieve resolver's User data from database.");
                }
                reimbursement.setResolved(new Timestamp(System.currentTimeMillis()));
                if(reimbursementService.updateReimbursement(reimbursement)) {
                    ctx.status(200);
                    log.info("Reimbursement with id " + reimbursement.getId() + " " + reimbursement.getStatus() + " by finance manager " + reimbursement.getResolver() + ".");
                }
                else {
                    ctx.status(400);
                }
            } catch (NumberFormatException e){
                e.printStackTrace();
                ctx.status(406);
            } catch (Exception e){
                e.printStackTrace();
                ctx.status(400);
            }
        }
        else {
            ctx.status(401);
        }
    };
    

    @Override
    public void addRoutes(Javalin app){
        app.get("/reimbursements", this.getAllReimbursements);
        app.get("/reimbursements/:reimbursement", this.getReimbursement);
        app.post("/reimbursements", this.addReimbursement);
        app.put("/reimbursements", this.updateReimbursement);
        app.delete("/reimbursements/:reimbursement", this.deleteReimbursement);
        app.post("/reimbursements/status", this.getReimbursementsByStatus);
        app.post("/reimbursements/username", this.getReimbursementsByUsername);
        app.put("/resolve", this.resolveReimbursement);
    }

}
