package com.dolvesa.hello.json;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 07.05.2015 at 11:41.
 *
 * simple JSON-to-JSON converter
 */
public class JsonJson {

  private static Logger log = LoggerFactory.getLogger(JsonJson.class);

  private class Source {

    private String name;
    private int amount;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAmount() {
      return amount;
    }

    public void setAmount(int amount) {
      this.amount = amount;
    }
  }

  private static class Target {

    private String title;
    private int quantity;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }
  }

  public String sourceAsJson() {
    Source source = new Source();
    source.setName("_name_");
    source.setAmount(13);
    return new Gson().toJson(source);
  }

  public String targetAsJson() {
    Gson gson = new Gson();
    Source source = gson.fromJson(sourceAsJson(), Source.class);
    Target target = new Target();
    target.setTitle(source.getName());
    target.setQuantity(source.getAmount());
    log.debug("title is {}, quantity is {}", target.getTitle(), target.getQuantity());
    return gson.toJson(target);
  }
}
