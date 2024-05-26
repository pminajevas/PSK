package com.example.pirmaslab.usecases;

import com.example.pirmaslab.entities.Referee;
import com.example.pirmaslab.interceptors.Duration;
import com.example.pirmaslab.persistence.RefereeDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;

@Model
@ViewScoped
@Duration
public class UpdateReferee implements Serializable {
    @Inject
    private RefereeDAO refereeDAO;

    @Getter
    @Setter
    private Referee refereeToUpdate;

    @PostConstruct
    public void init(){
        loadReferee(Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")));
    }

    private void loadReferee(Long id){
        this.refereeToUpdate = refereeDAO.findOne(id);
    }

    @Transactional
    public String update() {
        try {
            this.refereeDAO.update(refereeToUpdate);
        } catch (OptimisticLockException ole) {
            return "/referees?error=optimistic-lock-exception&faces-redirect=true";
        }

        return "/referees?faces-redirect=true";
    }
}
