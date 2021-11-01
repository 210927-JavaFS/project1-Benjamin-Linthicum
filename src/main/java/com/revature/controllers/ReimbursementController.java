package com.revature.controllers;

import java.util.List;

import com.revature.services.ReimbursementService;
import com.revature.models.*;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller{

    private ReimbursementService reimbursementService = new ReimbursementService();
    
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
            Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
            if (reimbursementService.addReimbursement(reimbursement)) {
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
                int statusId = Integer.parseInt(ctx.pathParam("status"));
                List<Reimbursement> list = reimbursementService.getReimbursementsByStatus(statusId);

                ctx.json(list);
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
    

    @Override
    public void addRoutes(Javalin app){
        app.get("/reimbursements", this.getAllReimbursements);
        app.get("/reimbursements/:reimbursement", this.getReimbursement);
        app.post("/reimbursements", this.addReimbursement);
        app.put("/reimbursements", this.updateReimbursement);
        app.delete("/reimbursements/:reimbursement", this.deleteReimbursement);
        app.get("/reimbursements_by_status", this.getReimbursementsByStatus);
    }

}
