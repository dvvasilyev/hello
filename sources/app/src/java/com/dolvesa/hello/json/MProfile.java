package com.dolvesa.hello.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 07.05.2015 at 17:26.
 *
 * more complicated example
 */
public class MProfile {

  private static Logger log = LoggerFactory.getLogger(MProfile.class);

  private final List<Profile> profiles;

  private static class Profile {

    private String name;
    private String id;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
  }

  public MProfile() {
    profiles = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      Profile profile = new Profile();
      profile.setName("n" + i);
      profile.setId("i" + i);
      profiles.add(profile);
    }
  }

  public List<Profile> getProfiles() {
    return profiles;
  }

  public String getProfilesAsJson() {
    Gson gson = new Gson();
    JsonElement je = gson.toJsonTree(getProfiles());
    JsonObject jo = new JsonObject();
    jo.add("monitoring-profiles", je);
    String profilesAsJson = jo.toString();
    log.debug("profiles is {}", profilesAsJson);
    return profilesAsJson;
  }
}
