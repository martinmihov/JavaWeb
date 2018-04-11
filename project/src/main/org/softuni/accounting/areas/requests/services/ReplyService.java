package org.softuni.accounting.areas.requests.services;

import org.softuni.accounting.areas.requests.domain.models.binding.ReplyBindingModel;
import org.softuni.accounting.areas.requests.domain.models.view.ReplyViewModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public interface ReplyService {

    void saveReply(@Valid ReplyBindingModel model);

    LinkedList<ReplyViewModel> getRepliesByRequest(RequestViewModel model);

    Map<RequestViewModel,LinkedList<ReplyViewModel>> getAllRequestsReplies(Set<RequestViewModel> requests);
}
