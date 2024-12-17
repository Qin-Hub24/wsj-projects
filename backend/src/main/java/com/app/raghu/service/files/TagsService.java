package com.app.raghu.service.files;

import com.app.raghu.entity.files.Tags;

import java.util.List;

public interface TagsService {

    List<Tags> getTagsDynamics(String name);

    boolean setTagsAdd(Tags tags);

    boolean setTagsDelete(Tags tags);
}
