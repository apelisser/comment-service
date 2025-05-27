package com.apelisser.algacomments.commentservice.domain.service;

import com.apelisser.algacomments.commentservice.domain.transfer.ModeratableComment;
import com.apelisser.algacomments.commentservice.domain.transfer.ModerationResult;

public interface ModerationService {

    ModerationResult applyModeration(ModeratableComment comment);

}
