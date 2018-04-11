package org.softuni.accounting.areas.users.domain.models.view;

import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.areas.requests.domain.models.view.ReplyViewModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;

import java.util.Set;

public class ProfileViewModel {

    private String username;

    private String email;

//    private Set<Request> requests;
    private Set<RequestViewModel> requests;

    public ProfileViewModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RequestViewModel> getRequests() {
        return this.requests;
    }

    public void setRequests(Set<RequestViewModel> requests) {
        this.requests = requests;
    }

    //    public Set<Request> getRequests() {
//        return this.requests;
//    }
//
//    public void setRequests(Set<Request> requests) {
//        this.requests = requests;
//    }

}
